package com.example.crud1_content_provider.data;

import static android.provider.BaseColumns._ID;
import static com.example.crud1_content_provider.data.PersonContract.PersonContractEntry.COLUMN_NAME_MAJOR;
import static com.example.crud1_content_provider.data.PersonContract.PersonContractEntry.COLUMN_NAME_NAME;
import static com.example.crud1_content_provider.data.PersonContract.PersonContractEntry.TABLE_NAME;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static String AUTHORITY = "com.idk.test.provider.crud";
    public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    static UriMatcher myUri = new UriMatcher(UriMatcher.NO_MATCH);
    // request code bach tfr9 bin l URIs
    static int CODE = 1;

    static {
        myUri.addURI(AUTHORITY, TABLE_NAME, CODE);
    }
    private SQLiteDatabase writableDb;
    private SQLiteDatabase readableDb;


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = writableDb.insert(TABLE_NAME, null, values);
        if (row > 0) {
            uri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public boolean onCreate() {
        PersonDbHelper dbHelper = new PersonDbHelper(getContext());
        writableDb = dbHelper.getWritableDatabase();
        readableDb = dbHelper.getReadableDatabase();
        if (writableDb == null || readableDb == null) {
            return false;
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(TABLE_NAME);
        Cursor cursor = query.query(readableDb, null, null, null, null, null, _ID);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static class PersonDbHelper extends SQLiteOpenHelper {
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME_NAME + " TEXT," +
                        COLUMN_NAME_MAJOR + " TEXT)";

        public static final String DATABASE_NAME = "Person.db";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static int DATABASE_VERSION = 1;

        public PersonDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            DATABASE_VERSION++;
            onCreate(db);
        }
    }
}