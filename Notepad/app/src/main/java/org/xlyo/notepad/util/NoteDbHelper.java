package org.xlyo.notepad.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.xlyo.notepad.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHelper extends SQLiteOpenHelper {
    // 数据库版本号
    private static final int DATABASE_VERSION = 1;
    // 数据库名称
    private static final String DATABASE_NAME = "NoteDatabase.db";

    // 表名称
    private static final String TABLE_NOTE = "note";

    // 表中的列名称
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_TIME = "time";

    // 创建表的SQL语句
    private static final String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_CONTENT + " TEXT,"
            + COLUMN_TIME + " TEXT" + ")";

    // 删除表的SQL语句
    private static final String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOTE;

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建note表
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 当数据库版本更新时，先删除旧表，然后创建新表
        db.execSQL(DROP_NOTE_TABLE);
        onCreate(db);
    }

    // 插入新的note记录
    public boolean addNote(String id, String title, String content, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CONTENT, content);
        contentValues.put(COLUMN_TIME, time);

        // 由于id是主键，如果id已存在，则插入操作会失败
        // 因此，我们首先检查id是否已存在
        Cursor cursor = db.query(TABLE_NOTE, new String[]{COLUMN_ID}, COLUMN_ID + "=?", new String[]{id}, null, null, null);
        if (cursor.moveToFirst()) {
            // 如果id已存在，则返回false
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();

        // 插入记录
        long result = db.insertWithOnConflict(TABLE_NOTE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return result != -1; // 如果result为-1，则插入失败
    }

    public List<NoteBean> getAllNotes() {
        List<NoteBean> noteList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") NoteBean note = new NoteBean(
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TIME))
                );
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    // 更新note记录
    public boolean updateNote(String id, String title, String content, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CONTENT, content);
        contentValues.put(COLUMN_TIME, time);
        int result = db.update(TABLE_NOTE, contentValues, COLUMN_ID + " = ?", new String[]{id});
        db.close();
        return result > 0; // 如果result大于0，则更新成功
    }

    // 删除note记录
    public boolean deleteNote(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NOTE, COLUMN_ID + " = ?", new String[]{id});
        db.close();
        return result > 0; // 如果result大于0，则删除成功
    }

    @SuppressLint("Range")
    public NoteBean getNoteById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_TIME},
                COLUMN_ID + "=?", new String[]{id}, null, null, null, null);

        NoteBean note = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                note = new NoteBean(
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TIME))
                );
            }
            cursor.close();
        }
        db.close();
        return note;
    }

    // 删除数据表中所有数据的方法
    public void deleteAllNotes() {
        SQLiteDatabase db = this.getWritableDatabase();
        // 删除数据表中的所有数据
        db.delete(TABLE_NOTE, null, null);
        // 关闭数据库连接
        db.close();
    }
}
