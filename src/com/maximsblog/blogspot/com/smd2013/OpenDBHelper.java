package com.maximsblog.blogspot.com.smd2013;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
 
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OpenDBHelper extends SQLiteAssetHelper {

	public static final String DATABASE_NAME = "catalog";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE = "main";
	public static final String ID = "_id";
	public static final String CODE = "code";
	public static final String PARTNUMBER = "partnumber";
	public static final String MANUFACTURER = "manufacturer";
	public static final String STYLE = "style";
	public static final String NAME = "name";
	public static final String PARAMETERS = "parameters";
	public static final String BASE = "base";
	public static final String PACKEGE = "package";
	
	public OpenDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


}