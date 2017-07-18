package com.example.colin.gwent_android_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Colin on 7/17/2017.
 */

public class CardDatabaseCreator extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CARD_TABLE_NAME = "cards";
    private static final String COL_UUID = "uuid";
    private static final String COL_NAME = "name";
    private static final String COL_VAR_JSON = "varJson";
    private static final String COL_DETAIL_JSON = "detailJson";
    private static final String COL_IMG_BLOB = "imgBlob";
    private static final String CARD_TABLE_CREATE =
            "CREATE TABLE " + CARD_TABLE_NAME + " (" +
                    COL_UUID + " TEXT," +
                    COL_NAME + " TEXT," +
                    COL_VAR_JSON + " TEXT," +
                    COL_DETAIL_JSON + " TEXT," +
                    COL_IMG_BLOB + " BLOB);";

    CardDatabaseCreator(Context context) {
        super(context, CARD_TABLE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CARD_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Handle database upgrade here
    }

}
