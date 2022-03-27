package com.application.cardle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseCardle extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "TestDB";

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
        // Table card
        String card = "CREATE TABLE " + TABLE_CARD + " ("
                            + COLUMN_ID_CARD  + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                            + COLUMN_QUESTION + " TEXT, "
                            + COLUMN_RESPONSE + " TEXT, "
                            + COLUMN_ID_DECK  + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(deck);
        db.execSQL(card);

    }

    // reset all data
    public void reset(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DECK, null, null);
        db.delete(TABLE_CARD, null, null);
        db.close();
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
    public void addNewCard(String question, String response, Integer foreignK){

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
        values.put(COLUMN_ID_DECK, foreignK);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_CARD, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // Get deck ID
    public Integer getIdDeck(String deckName){

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor mCursor = db.rawQuery("SELECT " + COLUMN_ID_DECK +
                                         " FROM "  + TABLE_DECK +
                                         " WHERE " + COLUMN_NAME_DECK +
                                         " == '" + deckName + "'", null);

        // on below line we are creating a new array list.
        ArrayList<Integer> idDeck = new ArrayList<>();

        if (mCursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                idDeck.add(mCursor.getInt(0));
            } while (mCursor.moveToNext());
            // moving our cursor to next.
        }

        System.out.println(idDeck.get(0).intValue());
        mCursor.close();

        return idDeck.get(0);
    }

    // Delete a card
    public void delCard(String deckId){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CARD, COLUMN_ID_CARD + "=" + deckId, null);

        db.close();

    }

    // Replace a name deck
    public void replaceDeckName(String deckId,String deckName){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlC = "REPLACE INTO Deck (id_deck,name_deck) VALUES (" + deckId + ",'" + deckName + "')";
        db.execSQL(sqlC);
        db.close();
    }

    // Return all card list with deck id argument
    public ArrayList<CardModel> readCards(String deckID){

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCards = db.rawQuery("SELECT " + COLUMN_ID_CARD + " , "
                + COLUMN_QUESTION + " , "
                + COLUMN_RESPONSE
                + " FROM " + TABLE_CARD
                + " WHERE " + COLUMN_ID_DECK
                + " == " + deckID, null);

        // on below line we are creating a new array list.
        ArrayList<CardModel> cardModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCards.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cardModelArrayList.add(new CardModel(cursorCards.getInt(0),
                                                     cursorCards.getString(1),
                                                     cursorCards.getString(2)));
            } while (cursorCards.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCards.close();

        return cardModelArrayList;
    }

    // Return all deck list
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

    // Return all deck list
    public Integer sizeTable(String nameTable) {

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorDecks = db.rawQuery("SELECT * FROM " + nameTable, null);

        // on below line we are creating a new array list.
        ArrayList<DeckModel> resArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorDecks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                resArrayList.add(new DeckModel(cursorDecks.getString(1)));
            } while (cursorDecks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDecks.close();

        return resArrayList.size();
    }

    // Return empty or not
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        onCreate(db);
    }
}
