package kuvaev.mainapp.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private final Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public void Open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    public void Insert(String noteName, String noteDesc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, noteName);
        contentValues.put(DatabaseHelper.DESC, noteDesc);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor Fetch() {
        String[] columns = {
                DatabaseHelper._ID,
                DatabaseHelper.SUBJECT,
                DatabaseHelper.DESC
        };

        Cursor cursor = sqLiteDatabase.query(
                DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void Update(long id, String noteName, String noteDesc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, noteName);
        contentValues.put(DatabaseHelper.DESC, noteDesc);

        sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues,
                DatabaseHelper._ID + " = " + id, null);
    }

    public void Delete(long id) {
        sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = " + id, null);
    }
}
