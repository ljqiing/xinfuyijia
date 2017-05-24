
package com.android.mslauncher;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MstarSQLiteHelper extends SQLiteOpenHelper {
    private Context mContext;

    private final String TAG = "MstarSQLiteHelper";

    // table name
    private static final String TABLE_NAME = "mstaritem";

    // default value for info of apps in xml
    private static final int DEFAULT_VALUE_0 = 0;

    // blend scene name
    private static final String COLUMN_SCENE = "scene";

    // blend agent name
    private static final String COLUMN_AGENT = "agent";

    // blend object name
    private static final String COLUMN_OBJECT = "object";

    // app title name
    private static final String COLUMN_TITLE = "title";

    // app package name
    private static final String COLUMN_PACKAGENAME = "packageName";

    // app class name
    private static final String COLUMN_CLASSNAME = "className";

    // app level
    private static final String COLUMN_LEVEL = "level";

    // app at which page
    private static final String COLUMN_PAGE = "page";

    // app at where
    private static final String COLUMN_INDEX = "iconindex";

    // xml attribute
    private static final String XML_INDEX = "index";

    // xml tag name
    private static final String XML_TAG_BEGIN = "item";

    public MstarSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists mstaritem("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "scene TEXT NOT NULL,"
                + "agent TEXT NOT NULL," + "object TEXT NOT NULL," + "title TEXT NOT NULL,"
                + "packageName TEXT NOT NULL," + "className TEXT NOT NULL,"
                + "level INTEGER DEFAULT 0," + "page INTEGER DEFAULT 0,"
                + "iconindex INTEGER DEFAULT 0" + ")");
        loaddefault(db);
    }

    private void loaddefault(SQLiteDatabase db) {
        try {
            XmlResourceParser parser = mContext.getResources().getXml(R.xml.mstariteminfo);
            int position;
            while ((position = parser.next()) != XmlResourceParser.END_DOCUMENT) {
                if (position != XmlResourceParser.START_TAG) {
                    continue;
                }

                String name = parser.getName();
                if (XML_TAG_BEGIN.equals(name)) {
                    MstarItemInfo iteminfo = new MstarItemInfo();
                    iteminfo.setScene(parser.getAttributeValue(null, COLUMN_SCENE));
                    iteminfo.setAgent(parser.getAttributeValue(null, COLUMN_AGENT));
                    iteminfo.setObject(parser.getAttributeValue(null, COLUMN_OBJECT));
                    iteminfo.setTitle(parser.getAttributeValue(null, COLUMN_TITLE));
                    iteminfo.setPackageName(parser.getAttributeValue(null, COLUMN_PACKAGENAME));
                    iteminfo.setClassName(parser.getAttributeValue(null, COLUMN_CLASSNAME));
                    iteminfo.setLevel(parser.getAttributeIntValue(null, COLUMN_LEVEL,
                            DEFAULT_VALUE_0));
                    iteminfo.setPage(parser
                            .getAttributeIntValue(null, COLUMN_PAGE, DEFAULT_VALUE_0));
                    iteminfo.setIconIndex(parser.getAttributeIntValue(null, XML_INDEX,
                            DEFAULT_VALUE_0));
                    insert(db, iteminfo);
                }
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void insert(SQLiteDatabase db, MstarItemInfo iteminfo) {
        long result = -1;

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCENE, iteminfo.getScene());
        values.put(COLUMN_AGENT, iteminfo.getAgent());
        values.put(COLUMN_OBJECT, iteminfo.getObject());
        values.put(COLUMN_TITLE, iteminfo.getTitle());
        values.put(COLUMN_PACKAGENAME, iteminfo.getPackageName());
        values.put(COLUMN_CLASSNAME, iteminfo.getClassName());
        values.put(COLUMN_LEVEL, iteminfo.getLevel());
        values.put(COLUMN_PAGE, iteminfo.getPage());
        values.put(COLUMN_INDEX, iteminfo.getIconIndex());

        result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Log.i(TAG, "---db insert ok");
        } else {
            Log.i(TAG, "---db insert fail");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
