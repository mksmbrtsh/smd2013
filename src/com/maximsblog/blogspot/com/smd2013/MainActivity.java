package com.maximsblog.blogspot.com.smd2013;

import java.io.File;
import java.util.Locale;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;

import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends SherlockFragmentActivity {

	private EditText mEditFilter;
	private String mFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		OpenDBHelper mDBh = new OpenDBHelper(this);
		if (savedInstanceState != null)
			mFilter = savedInstanceState.getString("filter");
		else
			mFilter = "";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		menu.getItem(0).setActionView(R.layout.search_title);
		mEditFilter = (EditText) menu.getItem(0).getActionView()
				.findViewById(R.id.search_editText);
		mEditFilter.setText(mFilter);
		mEditFilter.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		mEditFilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
		        	CatalogFragment cf = (CatalogFragment) getSupportFragmentManager()
							.findFragmentById(R.id.fragment1);
					cf.setFilter(mEditFilter.getText().toString());
		            return true;
		        }
		        return false;
		    }
		});
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.about_item) {
			AboutFragment about = new AboutFragment();
			about.show(getSupportFragmentManager(), "about");
		} else if (item.getItemId() == R.id.menu_next_search) {
			CatalogFragment cf = (CatalogFragment) getSupportFragmentManager()
					.findFragmentById(R.id.fragment1);
			cf.setFilter(mEditFilter.getText().toString());
		} else if (item.getItemId() == R.id.pdf_path) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(this);
			String pdfpath = preferences.getString("pdfpath", getCacheDir()
					.getAbsolutePath());
			Intent intent = new Intent(this, FileDialog.class);
			intent.putExtra(FileDialog.START_PATH, pdfpath);
			intent.putExtra(FileDialog.CAN_SELECT_DIR, true);
			intent.putExtra(FileDialog.SELECTION_MODE, SelectionMode.MODE_OPEN);
			startActivityForResult(intent, 777);

		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null && requestCode == 777 && resultCode == RESULT_OK) {
			File f = new File(data.getStringExtra(FileDialog.RESULT_PATH));
			String pdfpath = f.getAbsolutePath();
			Editor editor = PreferenceManager.getDefaultSharedPreferences(this)
					.edit();
			editor.putString("pdfpath", pdfpath);
			editor.commit();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if (mEditFilter != null)
			outState.putString("filter", mEditFilter.getText().toString());
		super.onSaveInstanceState(outState);
	}

}
