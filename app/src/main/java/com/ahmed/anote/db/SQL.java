package com.ahmed.anote.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.ahmed.anote.pinForm.EnteredValues;

public class SQL {

    private SQLiteDatabase db;

    public SQL(@NonNull SQLiteDatabase db) {
        this.db = db;
    }

    public static final String CREATE_PINS_TABLE =
            "CREATE TABLE " + Contract.Pins.TABLE_NAME + " (" +
             Contract.Pins._ID + " INTEGER PRIMARY KEY," +
             Contract.Pins.COLUMN_PIN + " INTEGER," +
             Contract.Pins.COLUMN_KEY + " TEXT UNIQUE, " +
             Contract.Pins.COLUMN_HINT + " TEXT)";

    public static final String DELETE_PINS_TABLE =
            "DROP TABLE IF EXISTS " + Contract.Pins.TABLE_NAME;

    public void INSERT_PIN(EnteredValues enteredValues) {
        if (db.isReadOnly()) {
            throw new SQLException("The input DB is read only!");
        }

        ContentValues values = new ContentValues();
        values.put(Contract.Pins.COLUMN_KEY, enteredValues.getEnteredKey());
        values.put(Contract.Pins.COLUMN_PIN, enteredValues.getEnteredPin());
        values.put(Contract.Pins.COLUMN_HINT, enteredValues.getEnteredHint());

        db.insert(Contract.Pins.TABLE_NAME, null, values);
    }

    public void UPDATE_PIN(EnteredValues enteredValues, String key) {
        if (db.isReadOnly()) {
            throw new SQLException("The input DB is read only!");
        }

        ContentValues values = new ContentValues();
        values.put(Contract.Pins.COLUMN_KEY, enteredValues.getEnteredKey());
        values.put(Contract.Pins.COLUMN_PIN, enteredValues.getEnteredPin());
        values.put(Contract.Pins.COLUMN_HINT, enteredValues.getEnteredHint());

        db.update(Contract.Pins.TABLE_NAME,
                values,
                Contract.Pins.COLUMN_KEY + "=?",
                new String[] {key});
    }

    public boolean KEY_EXISTS(String key) {
        Cursor cursor = db.query(
                Contract.Pins.TABLE_NAME,
                null,
                Contract.Pins.COLUMN_KEY + "=?",
                new String[] {key},
                null, null, null);

        return (cursor.getCount() >= 1) ? true : false;
    }

    public Cursor GET_PINS_TABLE() {
        return db.query(Contract.Pins.TABLE_NAME, null, null, null, null, null, null);
    }

    public String GET_PIN_FROM_KEY(String key) {
        Cursor cursor = db.query(
                Contract.Pins.TABLE_NAME,
                new String[] {Contract.Pins.COLUMN_PIN},
                Contract.Pins.COLUMN_KEY + "=?",
                new String[] {key},
                null, null, null);

        int rowCount = cursor.getCount();
        if (rowCount > 1) {
            throw new SQLException(rowCount + " rows returned. Expected just 1.");
        }

        if (cursor.moveToNext()) {
            String pin = cursor.getString(cursor.getColumnIndex(Contract.Pins.COLUMN_PIN));
            cursor.close();
            return pin;
        }
        else {
            cursor.close();
            throw new SQLException("Pin corresponding to given key not found.");
        }
    }

    public void DELETE_PIN(String key, String pin) {
        db.delete(
                Contract.Pins.TABLE_NAME,
                Contract.Pins.COLUMN_KEY + "=? AND " + Contract.Pins.COLUMN_PIN + "=?",
                new String[] {key, pin});
    }

    public boolean dbIsReadOnly() {
        return db.isReadOnly();
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
