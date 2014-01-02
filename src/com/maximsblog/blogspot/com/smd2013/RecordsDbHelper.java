package com.maximsblog.blogspot.com.smd2013;

import java.util.Date;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RecordsDbHelper extends ContentProvider {

	public static final String AUTHORITY = "maximsblog.blogspot.com.smd2013.providers.db";
	private static final UriMatcher sUriMatcher;
	public static final int ITEMS = 1;
	public static final int ITEMS_ID = 2;
	
	public final static String MAIN_TABLE = OpenDBHelper.TABLE;
	public final static String ID = OpenDBHelper.ID;
	public final static String CODE = OpenDBHelper.CODE;
	public final static String PARTNUMBER = OpenDBHelper.PARTNUMBER;
	public final static String MANUFACTURER = OpenDBHelper.MANUFACTURER;
	public final static String STYLE = OpenDBHelper.STYLE;
	public final static String NAME = OpenDBHelper.NAME;
	public final static String PARAMETERS = OpenDBHelper.PARAMETERS;
	public final static String BASE = OpenDBHelper.BASE;
	public final static String PACKEGE = OpenDBHelper.PACKEGE;
	
	private static HashMap<String, String> timersProjectionMap;
	

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, MAIN_TABLE, ITEMS);
		sUriMatcher.addURI(AUTHORITY, MAIN_TABLE + "/#", ITEMS_ID);

		timersProjectionMap = new HashMap<String, String>();
		timersProjectionMap.put(ID, ID);
		timersProjectionMap.put(CODE, CODE);
		timersProjectionMap.put(PARTNUMBER, PARTNUMBER);
		timersProjectionMap.put(MANUFACTURER, MANUFACTURER);
		timersProjectionMap.put(STYLE, STYLE);
		timersProjectionMap.put(NAME, NAME);
		timersProjectionMap.put(PARAMETERS, PARAMETERS);
		timersProjectionMap.put(BASE, BASE);
		timersProjectionMap.put(PACKEGE, PACKEGE);
	}

	public static final Uri CONTENT_URI_ITEMS = Uri.parse("content://"
			+ AUTHORITY + "/main");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.jwei512.notes";

	SQLiteDatabase mDB;
	private OpenDBHelper openHelper;

	@Override
	public boolean onCreate() {
		Context context = getContext();
		openHelper = new OpenDBHelper(context);
		mDB = openHelper.getReadableDatabase();
		return (mDB == null) ? false : true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case ITEMS:
			return "vnd.android.cursor.dir/vnd.jwei512.timers";
		case ITEMS_ID:
			return "vnd.android.cursor.dir/vnd.jwei512.timers";
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
			throw new IllegalArgumentException("Unknown URI " + uri);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		Cursor c;

		switch (sUriMatcher.match(uri)) {
		case ITEMS:
			qb.setTables(MAIN_TABLE);
			qb.setProjectionMap(timersProjectionMap);
			break;
		case ITEMS_ID:
			qb.setTables(MAIN_TABLE);
			qb.setProjectionMap(timersProjectionMap);
			selection = selection + ID + "=" + uri.getLastPathSegment();
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		c = qb.query(mDB, projection, selection, selectionArgs, null, null,
				sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
			throw new IllegalArgumentException("Unknown URI " + uri);
	}

	
}
