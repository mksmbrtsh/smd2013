package com.maximsblog.blogspot.com.smd2013;

import java.io.File;

import com.maximsblog.blogspot.com.smd2013.ViewerFragment.ViewerActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemsClickDialog extends Activity implements OnItemClickListener {

	private TextView messageTxt;
	private ListView listView;
	private boolean mBodyColumn;
	private boolean mManufacturerColumn;
	private boolean mTsoklevkaColumn;
	private boolean mParametersColumn;

	private Item[] itemss;
	private String mDBpath;
	private String mCode;
	private String mName;
	private String mPartnumber;
	private String mStyle;
	private String mManufacturer;
	private String mParameters;
	private String mBase;
	private String mPackege;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.itemclick_activity);
		Intent intent = getIntent();
		mCode = intent.getStringExtra("code");
		mName = intent.getStringExtra("name");
		mPartnumber = intent.getStringExtra("partnumber");
		mStyle = intent.getStringExtra("style");
		mManufacturer = intent.getStringExtra("manufacturer");
		mParameters = intent.getStringExtra("parameters");
		mBase = intent.getStringExtra("base");
		mPackege = intent.getStringExtra("packege");
		((TextView) findViewById(R.id.title_txt)).setText(mCode + " \\ "
				+ mPartnumber);
		
		messageTxt = (TextView) findViewById(R.id.textView1);
		if(mName!=null)
			messageTxt.setText(mName);
		else
			messageTxt.setVisibility(View.GONE);
		listView = (ListView) findViewById(R.id.listView1);
		View w = findViewById(R.id.w);
		w.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				
			}

		});

		String[] items_text = new String[4];
		//File file = new File(SettingsActivity.getStringValue(this, "pdfpath"),
		//		mItems.Name + ".pdf");
		//if (file.exists()) {
		//	items_text[0] = getString(R.string.viewdatasheet);
		//} else {
			items_text[0] = getString(R.string.searchdatasheet);
		//}
		items_text[1] = getString(R.string.searchininternet);
		items_text[2] = getString(R.string.viewbody);
		items_text[3] = getString(R.string.viewtsoklevka);

		itemss = new Item[4];
		itemss[0] = new Item(items_text[0], R.drawable.ic_launcher,
				R.string.searchdatasheet);
		itemss[1] = new Item(items_text[1], R.drawable.ic_launcher,
				R.string.searchininternet);
			itemss[2] = new Item(items_text[2], R.drawable.ic_launcher,
					R.string.viewbody);
			itemss[3] = new Item(items_text[3], R.drawable.ic_launcher,
					R.string.viewtsoklevka);

		ListAdapter adapter = new ArrayAdapter<Item>(this,
				android.R.layout.select_dialog_item, android.R.id.text1, itemss) {
			@Override
			public View getView(int pos, View cView, ViewGroup par) {
				// User super class to create the View
				View v = super.getView(pos, cView, par);
				TextView tv = (TextView) v.findViewById(android.R.id.text1);

				// Put the image on the TextView
				tv.setCompoundDrawablesWithIntrinsicBounds(itemss[pos].icon, 0,
						0, 0);

				// Add margin between image and text (support various screen
				// densities)
				int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
				tv.setCompoundDrawablePadding(dp5);
				tv.setTextColor(Color.GRAY);
				return v;
			}
		};
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	private static class Item {
		public final String text;
		public final int icon;
		public final int id;

		public Item(String text, Integer icon, Integer ident) {
			this.text = text;
			this.icon = icon;
			this.id = ident;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (itemss[arg2].id == R.string.searchdatasheet) {
			/*File file = new File(SettingsActivity.getStringValue(this,
					"pdfpath"), mItems.Name + ".pdf");
			if (file.exists()) {
				Uri path = Uri.fromFile(file);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(path, "application/pdf");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				try {
					startActivity(intent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(this, R.string.error_notfindpdfopenapp,
							Toast.LENGTH_SHORT).show();
				}
				finish();
			} else {*/
				final Item[] i = new Item[5];
				i[0] = new Item("datasheetcatalog.net", R.drawable.ic_launcher, 0);
				i[1] = new Item("alldatacheet.com", R.drawable.ic_launcher, 0);
				i[2] = new Item("datasheet4u.com", R.drawable.ic_launcher, 0);
				i[3] = new Item("ic-on-line.cn", R.drawable.ic_launcher, 0);
				i[4] = new Item("chipfind.ru", R.drawable.ic_launcher, 0);

				ListAdapter adapter = new ArrayAdapter<Item>(this,
						android.R.layout.select_dialog_item,
						android.R.id.text1, i) {
					@Override
					public View getView(int pos, View cView, ViewGroup par) {
						// User super class to create the View
						View v = super.getView(pos, cView, par);
						TextView tv = (TextView) v
								.findViewById(android.R.id.text1);

						// Put the image on the TextView
						tv.setCompoundDrawablesWithIntrinsicBounds(i[pos].icon,
								0, 0, 0);

						// Add margin between image and text (support various
						// screen
						// densities)
						int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
						tv.setCompoundDrawablePadding(dp5);
						tv.setTextColor(Color.GRAY);
						return v;
					}
				};

				AlertDialog a = new Builder(this)
						.setAdapter(adapter, new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								StringBuilder s = new StringBuilder("http://");
								switch (which) {
								case 0:
									s.append("search.datasheetcatalog.net/key/");
									break;
								case 1:
									s.append("www.alldatasheet.com/view.jsp?Searchword=");
									break;
								case 2:
									s.append("www.datasheet4u.net/share_search.php?sWord=");
									break;
								case 3:
									s.append("www.ic-on-line.cn/search.php?part=");
									break;
								case 4:
									s.append("doc.chipfind.ru/search.htm?t=part&s=");
									break;
								default:
									break;
								}
								s.append(mPartnumber);
								Intent browserIntent = new Intent(
										Intent.ACTION_VIEW, Uri.parse(s
												.toString()));
								startActivity(browserIntent);
								finish();
							}
						}).setOnCancelListener(new OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {
								finish();
							}
						}).setTitle(mCode + " \\ " + mPartnumber)
						.create();
				a.show();

			//}
		} else if (itemss[arg2].id == R.string.viewbody) {
			Intent intent = new Intent(this, ViewerActivity.class);
			intent.putExtra("file", "Packages/" + mBase.toLowerCase() + ".gif");
			intent.putExtra("id", 2);
			startActivity(intent);
			finish();

		} else if (itemss[arg2].id == R.string.viewtsoklevka) {
			Intent intent = new Intent(this, ViewerActivity.class);
			intent.putExtra("file", "Base/" + mPackege.toLowerCase() + ".gif");
			intent.putExtra("id", 1);
			startActivity(intent);
			finish();
			finish();
		} else if (itemss[arg2].id == R.string.searchininternet) {
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);  
			intent.putExtra(SearchManager.QUERY, mPartnumber);  
			startActivity(intent);
		}
	}
}
