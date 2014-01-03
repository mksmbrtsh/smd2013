package com.maximsblog.blogspot.com.smd2013;

import com.maximsblog.blogspot.com.smd2013.R;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public final class CatalogFragment extends Fragment implements
		LoaderCallbacks<Cursor>, OnItemClickListener {
	private SmdAdapter adapter;
	private EditText mSearch;
	private ListView mList;
	private Cursor mCursor;
	private LoaderManager loadermanager;
	private ProgressBar mEmptyView;
	private String mFilter;
	private int mPosition;

	public static CatalogFragment newInstance(String content) {
		CatalogFragment fragment = new CatalogFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mFilter = savedInstanceState.getString("filter");
			mPosition = savedInstanceState.getInt("position");
		} else {
			mFilter = "";
			mPosition = 0;
		}
		loadermanager = getLoaderManager();
		loadermanager.initLoader(1, null, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.catalog_fragment, container, false);
		mList = (ListView) layout.findViewById(R.id.catalog_list);

		mList.setOnItemClickListener(this);
		adapter = new SmdAdapter(getActivity(), R.layout.row, null,
				new String[] { "name", "code", "partnumber", "style",
						"manufacturer", "parameters", "base", "package" },
				new int[] { R.id.name, R.id.code, R.id.partnumber, R.id.style,
						R.id.manufacturer, R.id.parameters, R.id.base,
						R.id.packege }, mFilter);
		mList.setAdapter(adapter);
		mEmptyView = (ProgressBar) layout.findViewById(R.id.empty);

		mList.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.VISIBLE);
		return layout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("filter", mFilter);
		outState.putInt("position", mPosition);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String f = mFilter.length() > 0 ?  
				"name" + " LIKE '%" + mFilter.toUpperCase() + "%' OR "
				+ "partnumber" + " LIKE '%" + mFilter.toUpperCase()	+ "%' OR " 
				+ "manufacturer" + " LIKE '%" + mFilter.toUpperCase()	+ "%' OR "
				+ "parameters" + " LIKE '%" + mFilter.toUpperCase()	+ "%' OR "
				+ "code" + " LIKE '%" + mFilter.toUpperCase()	+ "%' OR "
				+ "base" + " LIKE '%" + mFilter.toUpperCase()	+ "%' OR "
				+ "package" + " LIKE '%" + mFilter.toUpperCase()	+ "%'"
				:	null;
		CursorLoader loader = new CursorLoader(this.getActivity(),
				RecordsDbHelper.CONTENT_URI_ITEMS, null, f, null, null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		adapter.swapCursor(cursor);
		adapter.setFilter(mFilter);
		mEmptyView.setVisibility(View.GONE);
		mList.setVisibility(View.VISIBLE);
		if(mList.getCount() > 0 )
			mList.smoothScrollToPosition(mPosition);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}

	public void setFilter(String filterWord) {
		if (!filterWord.equals(mFilter)) {
			mList.setVisibility(View.GONE);
			mEmptyView.setVisibility(View.VISIBLE);
			mFilter = filterWord;
			mPosition = 0;
			loadermanager.restartLoader(1, null, this);
		} else {
			int pos = mPosition;
			pos = pos + 1 < mList.getCount()? pos+1: 0;
			mList.smoothScrollToPosition(mPosition = pos);
		}
	}
}
