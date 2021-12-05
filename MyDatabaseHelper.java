package com.example.librarymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.database.Cursor;
import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PRICE = "book_price";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PRICE + " INTEGER) ;";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addBook(String title, String author, int price) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues newElement = new ContentValues();

        newElement.put(COLUMN_TITLE, title);
        newElement.put(COLUMN_AUTHOR, author);
        newElement.put(COLUMN_PRICE, price);
        long result = database.insert(TABLE_NAME, null, newElement);
        if (result == -1) {
            Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully Added!", Toast.LENGTH_LONG).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateBook(String row_id, String title, String author, int price) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues newElement = new ContentValues();

        newElement.put(COLUMN_TITLE, title);
        newElement.put(COLUMN_AUTHOR, author);
        newElement.put(COLUMN_PRICE, price);

        long result = database.update(TABLE_NAME, newElement, "id=?", new String[] {row_id});
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_LONG).show();
        }
    }
}
