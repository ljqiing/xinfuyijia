
package com.android.mslauncher;

import java.util.ArrayList;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class MstarDatabaseProvider extends ContentProvider {
    private static final String TAG = "MstarDatabaseProvider";

    private MstarSQLiteHelper mOpenHelper;

    // database name
    private static final String DATABASE_NAME = "home.db";

    // table name
    private static final String TABLE_NAME = "mstaritem";

    private static final String CONTENT_LABEL = "content://";

    // authority
    private static final String AUTHORITY = "com.mstar.xy.MstarDatabaseProvider";

    private static final String MARK_SLASH = "/";

    // uri
    private static final String URI = CONTENT_LABEL + AUTHORITY + MARK_SLASH + TABLE_NAME;

    private static final Uri CONTENT_URI = Uri.parse(URI);

    // for a single record
    private static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.databaseprovider";

    // for multiple items
    private static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.databaseprovider";

    // uri matcher for matching data operation
    private static final UriMatcher mUriMatcher;

    // _id
    private static final String DATABASE_ID = "_id";

    // equal operator
    private static final String MARK_EQUAL = "=";

    // default sort order
    private static final String DEFAULT_SORT_ORDER = "_id asc";

    // label for all record
    private static final int TYPE_ALL = 1;

    // label for one single record
    private static final int TYPE_ITEM = 2;

    private static final String MARK_SHARP = "#";
    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, TABLE_NAME, TYPE_ALL);
        mUriMatcher.addURI(AUTHORITY, TABLE_NAME + MARK_SLASH + MARK_SHARP, TYPE_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDatabase = mOpenHelper.getWritableDatabase();
        int result = 0;
        result = sqlDatabase.delete(TABLE_NAME, selection, selectionArgs);
        // Notify registered observers that a row was updated
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case TYPE_ALL:
                return CONTENT_TYPE;
            case TYPE_ITEM:
                return CONTENT_TYPE_ITEM;
            default: {
                Log.w(TAG, "Unknow URI");
                break;
            }
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqlDatabase = mOpenHelper.getWritableDatabase();
        long rowId = sqlDatabase.insert(TABLE_NAME, null, values);
        Log.i(TAG, "***********have insert at:" + rowId);
        if (rowId > 0) {
            // for add id for item
            Uri insertUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            // Notify registered observers that a row was updated
            getContext().getContentResolver().notifyChange(insertUri, null);
            return insertUri;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MstarSQLiteHelper(getContext(), DATABASE_NAME, null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String arg4) {
        SQLiteQueryBuilder sqlQueryBuilder = new SQLiteQueryBuilder();
        sqlQueryBuilder.setTables(TABLE_NAME);
        switch (mUriMatcher.match(uri)) {
            case TYPE_ALL:
                break;
            case TYPE_ITEM:
                sqlQueryBuilder
                        .appendWhere(DATABASE_ID + MARK_EQUAL + uri.getPathSegments().get(1));
                break;
            default: {
                Log.w(TAG, "DatabaseProvider#query exception.");
            }
        }

        SQLiteDatabase sqlDatabase = mOpenHelper.getWritableDatabase();
        Cursor cursor = sqlQueryBuilder.query(sqlDatabase, projection, selection, selectionArgs,
                null, null, DEFAULT_SORT_ORDER);
        // register for specified uri to listening data changing
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDatabase = mOpenHelper.getWritableDatabase();
        int result = 0;
        result = sqlDatabase.update(TABLE_NAME, values, selection, selectionArgs);
        // Notify registered observers that a row was updated
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Log.v(TAG, "applyBatch:");
        db.beginTransaction();
        try {
            ContentProviderResult[] results = super.applyBatch(operations);
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }

}
