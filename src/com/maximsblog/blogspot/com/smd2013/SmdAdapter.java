package com.maximsblog.blogspot.com.smd2013;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class SmdAdapter extends SimpleCursorAdapter {

	private String mFilter;

	private BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
			Color.YELLOW);
	private ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
			Color.BLACK);

	public SmdAdapter(Context context, int layout, Cursor c, String[] from,
			int[] to, String filter) {
		super(context, layout, c, from, to);
		mFilter = filter;
	}

	public void setFilter(String filter) {
		mFilter = filter;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		super.bindView(view, context, cursor);
		for (int i1 = 0, cnt1 = this.mTo.length; i1 < cnt1; i1++) {
			TextView t = (TextView) view.findViewById(mTo[i1]);
			if (t.getText().length() > 0) {
				t.setVisibility(View.VISIBLE);
				Spannable wordtoSpan = new SpannableString(t.getText());
				int i = t.getText().toString().toLowerCase()
						.indexOf(mFilter.toLowerCase());
				if (i != -1) {
					wordtoSpan.setSpan(backgroundColorSpan, i,
							i + mFilter.length(),
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					wordtoSpan.setSpan(foregroundColorSpan, i,
							i + mFilter.length(),
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					t.setText(wordtoSpan);
				}
			} else
				t.setVisibility(View.GONE);
		}
	}
}
