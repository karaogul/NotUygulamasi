package com.example.sqlitenotuygulamasi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_ADI = "NotDB";
    private static final int DATABASE_VERSIYON = 1;
    private static final String TABLE_ADI = "Notes";
    private static final String SAYICI = "Sayici";

    private static final String ID_ALAN = "id";
    private static final String BASLIK_ALAN = "baslik";
    private static final String ACIKLAMA_ALAN = "aciklama";
    private static final String SILINEN_ALAN = "silinen";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("GG-aa-yyyy SS:dd:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_ADI, null, DATABASE_VERSIYON);
    }

    public static SQLiteManager databaseOrnegi(Context context) {
        if (sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE  ")
                .append(TABLE_ADI)
                .append("(")
                .append(SAYICI)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_ALAN)
                .append(" INT, ")
                .append(BASLIK_ALAN)
                .append(" TEXT, ")
                .append(ACIKLAMA_ALAN)
                .append(" TEXT, ")
                .append(SILINEN_ALAN)
                .append(" TEXT)");

            sqLiteDatabase.execSQL(sql.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int eskiVersiyon, int yeniVersiyon) {
//        switch (eskiVersiyon)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLO_ADI + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//            case 2:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLO_ADI + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//        }
    }

    public void ekleNotToDatabase(Not not) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_ALAN, not.getId());
        contentValues.put(BASLIK_ALAN, not.getBaslik());
        contentValues.put(ACIKLAMA_ALAN, not.getAciklama());
        contentValues.put(SILINEN_ALAN, getStringFromDate(not.getSilinen()));

        sqLiteDatabase.insert(TABLE_ADI, null, contentValues);
    }

    public void populateNotListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("Select * from " + TABLE_ADI, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(1);
                    String baslik = result.getString(2);
                    String aciklama = result.getString(3);
                    String stringSilinen = result.getString(4);
                    Date silinen = getDateFromString(stringSilinen);
                    Not not = new Not(id, baslik, aciklama, silinen);
                    Not.notArrayList.add(not);

                }
            }
        }
    }

    public void guncelleNotInDB(Not not) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_ALAN, not.getId());
        contentValues.put(BASLIK_ALAN, not.getBaslik());
        contentValues.put(ACIKLAMA_ALAN, not.getAciklama());
        contentValues.put(SILINEN_ALAN, getStringFromDate(not.getSilinen()));

        sqLiteDatabase.update(TABLE_ADI, contentValues, ID_ALAN + "=?", new String[]{String.valueOf(not.getId())});
    }

    private String getStringFromDate(Date date) {
        if (date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
