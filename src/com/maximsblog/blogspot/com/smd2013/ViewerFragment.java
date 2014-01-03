package com.maximsblog.blogspot.com.smd2013;

import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewerFragment extends Fragment {
	private ImageView img;

	@Override
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		setRetainInstance(true);

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.viewer_fragment, container, false);

		return layout;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		View v = this.getView();
		img = (ImageView) v.findViewById(R.id.imageView1);
		String path = getActivity().getIntent().getStringExtra("file");
		int id = getActivity().getIntent().getIntExtra("id", 1);
		if (path != null)
			loadImg(path);
		TextView name = (TextView) v.findViewById(R.id.name_txt);
		if (this.getId() == 1) {
			name.setText(R.string.tsoklevka);
		} else if (this.getId() == 2) {
			name.setText(R.string.body);
		} else {

			if (id == 1) {
				name.setText(R.string.tsoklevka);
			} else if (id == 2) {
				name.setText(R.string.body);
			}
		}
	}

	public void loadImg(String file) {
		try {

			AssetManager assetManager = getActivity().getAssets();
			InputStream is = assetManager.open(file);
			img.setImageBitmap(BitmapFactory.decodeStream(is));
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static class ViewerActivity extends FragmentActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			if (savedInstanceState == null) {
				// During initial setup, plug in the details fragment.
				ViewerFragment details = new ViewerFragment();
				details.setArguments(getIntent().getExtras());
				// Items items = (Items)getIntent().getParcelableExtra("items");
				// setTitle(items.Code +" \\ " + items.Name);
				getSupportFragmentManager().beginTransaction()
						.add(android.R.id.content, details).commit();
			}
		}
	}

}
