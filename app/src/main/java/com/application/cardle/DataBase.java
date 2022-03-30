package com.application.cardle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.application.cardle.card.CardModal;
import com.application.cardle.deck.DeckModal;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "DataDB";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    /*
     * Database Table name and column
     */

    // CARD
    private static final String TABLE_CARD = "Card";
    private static final String COLUMN_ID_CARD ="id_card";
    private static final String COLUMN_QUESTION ="question";
    private static final String COLUMN_RESPONSE ="response";

    // DECK
    private static final String TABLE_DECK = "Deck";
    private static final String COLUMN_ID_DECK ="id_deck";
    private static final String COLUMN_NAME_DECK ="name_deck";

    /**
     * Constructor DataBase : the database of Cardle app
     * @param context name of activity context
     */
    public DataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        // Table deck
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

    /**
     * Reset all data.
     */
    public void reset(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DECK, null, null);
        db.delete(TABLE_CARD, null, null);
        db.close();
    }

    /**
     * Adding deck method.
     * @param deckName deck name
     */
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

    /**
     * Adding card method.
     * @param question question
     * @param response response
     * @param foreignK id_deck
     */
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

    /**
     * Get the id deck.
     * @param deckName deck name
     * @return identification deck
     */
    public Integer getIdDeck(String deckName){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {COLUMN_ID_DECK};
        String selection = COLUMN_NAME_DECK + " = ?";
        String [] args = {String.valueOf(deckName)};
        Cursor mCursor = db.query(TABLE_DECK,projection,selection,args,null,null,null);
        mCursor.moveToFirst();
        Integer res = mCursor.getInt(0);
        mCursor.close();
        return res;
    }

    /**
     * Get number of cards in deck
     * @param deckName deck name
     * @return cards number
     */
    public Integer getNbCard(String deckName){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {COLUMN_ID_CARD};
        String selection = COLUMN_ID_DECK + " = ?";
        String [] args = {String.valueOf(getIdDeck(deckName))};
        Cursor mCursor = db.query(TABLE_CARD,projection,selection,args,null,null,null);
        mCursor.moveToFirst();
        Integer res = mCursor.getCount();
        mCursor.close();
        return res;
    }

    /**
     * Delete the card in database.
     * @param cardId id card
     */
    public void delCard(String cardId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARD, COLUMN_ID_CARD + "=" + cardId, null);
        db.close();
    }

    /**
     * Delete the card with id deck foreign key
     * @param deckId id deck
     */
    public void delCardwhichDeck(String deckId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARD, COLUMN_ID_DECK + "=" + deckId, null);
        db.close();
    }

    /**
     * Delete the deck in database.
     * @param deckName id deck
     */
    public boolean delDeck(String deckName){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean res = db.delete(TABLE_DECK, COLUMN_NAME_DECK + "=?",new String[]{deckName}) > 0;
        db.close();
        return res;
    }

    /**
     * Delete the deck and his cards
     * @param deckName deck name
     */
    public void delAllCard(String deckName){
        SQLiteDatabase db = this.getWritableDatabase();
        delCardwhichDeck(getIdDeck(deckName).toString());
        delDeck(deckName);
        db.close();
    }

    /**
     * Replace from actual deck name to new.
     * @param deckId id deck
     * @param deckName name deck
     */
    public void replaceDeckName(String deckId,String deckName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID_DECK,deckId);
        contentValues.put(COLUMN_NAME_DECK,deckName);
        db.replace(TABLE_DECK,null,contentValues);
        db.close();
    }

    /**
     * Get all card in a list.
     * @param deckID id deck
     * @return list of cards
     */
    public ArrayList<CardModal> readCards(String deckID){
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
        ArrayList<CardModal> cardModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCards.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cardModalArrayList.add(new CardModal(cursorCards.getInt(0),
                                                     cursorCards.getString(1),
                                                     cursorCards.getString(2)));
            } while (cursorCards.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCards.close();
        return cardModalArrayList;
    }

    /**
     * Get all deck in a list.
     * @return list of deck
     */
    public ArrayList<DeckModal> readDecks() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorDecks = db.rawQuery("SELECT * FROM " + TABLE_DECK, null);

        // on below line we are creating a new array list.
        ArrayList<DeckModal> deckModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorDecks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                deckModalArrayList.add(new DeckModal(cursorDecks.getString(1)));
            } while (cursorDecks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDecks.close();
        return deckModalArrayList;
    }

    /**
     * Check if the table is empty.
     * @param tableName name table
     * @return boolean true or false
     */
    public boolean checkTableEmpty(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + tableName, null);
        boolean rowExists;
        rowExists = mCursor.moveToFirst();
        mCursor.close();
        return rowExists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        onCreate(db);
    }
}