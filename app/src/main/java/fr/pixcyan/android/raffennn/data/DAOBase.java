package fr.pixcyan.android.raffennn.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * TODO
 *
 * @author PixCyan
 */
public abstract class DAOBase {

    // TAG pour le log
    private static final String TAG = "DBAdapter";

    private SQLiteDatabase db;
    private DBHelper databaseHelper;

    // refaire avec getWritableDatabase(); et getReadableDatabase();
    public DAOBase(Context context) {
        databaseHelper = new DBHelper(context, null);
    }

    // On ouvre la base de données en écriture
    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }

    //on ferme l'accès à la base de données
    public void close() {
        db.close();
    }

    //
    public SQLiteDatabase getDB() {
        return db;
    }
}
