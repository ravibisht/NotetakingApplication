package com.stark.jarvis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DAO extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String TAG = "DAO";
    public DAO(@Nullable Context context) {
        super(context, "NotesDataBase.db",null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Notes(id integer primary key autoincrement ,title text,note_content text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Notes ");
         onCreate(sqLiteDatabase);
    }

    public boolean insertOrUpdateNote(int id,String title,String NoteContent){
        db=getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("note_content",NoteContent);
        if(id==0){
            db.insert("Notes",null,contentValues);
        }
        else{

            db.update("Notes",contentValues,"id=?",new String[]{String.valueOf(id)});
        }
        return true;

    }

    public void deleteNote(int id){
        db=getWritableDatabase();
        db.delete("Notes","id=?",new String[]{String.valueOf(id)});

    }

    public ArrayList<Note> getAllNotes() {
        db = getReadableDatabase();
        ArrayList<Note> allNotes = new ArrayList<>();

        Cursor cursor = db.rawQuery("select id,title,note_content from Notes order by id desc", null);



        if(cursor.moveToFirst()) {
            do {

                Log.i(TAG, "getAllNotes:  id : "+cursor.getInt(cursor.getColumnIndex("id")));
                allNotes.add(
                        new Note(
                                cursor.getInt(cursor.getColumnIndex("id")),
                                cursor.getString(cursor.getColumnIndex("title")),
                                cursor.getString(cursor.getColumnIndex("note_content"))));

            } while (cursor.moveToNext());

        }
        return allNotes;
        }


}
