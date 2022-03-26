package com.application.cardle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseCardle extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "CardleDB";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Table name and column

    // CARD
    private static final String TABLE_CARD = "Card";
    private static final String COLUMN_ID_CARD ="id_card";
    private static final String COLUMN_QUESTION ="question";
    private static final String COLUMN_RESPONSE ="response";

    // DECK
    private static final String TABLE_DECK = "Deck";
    private static final String COLUMN_ID_DECK ="id_deck";
    private static final String COLUMN_NAME_DECK ="name_deck";

    // COURSE
    private static final String TABLE_COURSE = "Course";
    private static final String COLUMN_ID_COURSE ="id_course";
    private static final String COLUMN_NAME_COURSE ="name_course";

    // DATE
    private static final String TABLE_DATE = "Date";
    private static final String COLUMN_ID_DATE ="id_date";
    private static final String COLUMN_DAY ="day";
    private static final String COLUMN_MONTH ="month";
    private static final String COLUMN_YEAR ="year";

    // TIME
    private static final String TABLE_TIME = "Time";
    private static final String COLUMN_ID_TIME ="id_date";
    private static final String COLUMN_HOUR ="hour";
    private static final String COLUMN_MINUTE ="minute";

    // PRACTISE
    private static final String TABLE_PRACTISE = "Practise";
    private static final String COLUMN_ANSWER ="answer";

    // Constructor
    public DatabaseCardle(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db){
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String deck = "CREATE TABLE " + TABLE_DECK + " ("
                            + COLUMN_ID_DECK + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                            + COLUMN_NAME_DECK + " TEXT)";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(deck);

        // Table card
        String card = "CREATE TABLE " + TABLE_CARD + " ("
                            + COLUMN_ID_CARD + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                            + COLUMN_QUESTION + " TEXT, "
                            + COLUMN_ANSWER + " TEXT, "
                            + " FOREIGN KEY ( " + COLUMN_ID_DECK + " ) REFERENCES " + TABLE_DECK + " ( " + COLUMN_ID_DECK +" ))";
        db.execSQL(card);

    }

    // METHODS
    public void addNewDeck(String deckName){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(COLUMN_NAME_DECK, deckName);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_DECK, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // Add a new card
    public void addNewCard(String question, String response){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(COLUMN_QUESTION, question);
        values.put(COLUMN_RESPONSE, response);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_DECK, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public ArrayList<DeckModel> readDecks() {

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorDecks = db.rawQuery("SELECT * FROM " + TABLE_DECK, null);

        // on below line we are creating a new array list.
        ArrayList<DeckModel> deckModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorDecks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                deckModelArrayList.add(new DeckModel(cursorDecks.getString(1)));
            } while (cursorDecks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDecks.close();
        return deckModelArrayList;
    }

    public boolean checkTableEmpty(String tableName){

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + tableName, null);
        boolean rowExists;

        rowExists = mCursor.moveToFirst();

        mCursor.close();

        return rowExists;
    }

    // Upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECK);
        onCreate(db);
    }

}
