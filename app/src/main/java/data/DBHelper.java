package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * TODO
 *
 * @author PixCyan
 */
public class DBHelper {
    // VERSION de la bdd, permet les mises à jour des tables et champs au lancement de l'application
    private static final int VERSION = 3;

    // NOM de la base
    private static final String DATABASE_NAME = "database_test_dut_2a";

    // TAG pour le log
    private static final String TAG = "DBHelper";

    // Constructeur
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Créer la table question
        db.execSQL(DAOQuestion.CREATE_TABLE);

        // Insérer les données
        for (String insert : DAOQuestion.getInsertSQL()) {
            db.execSQL(insert);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Log
        Log.w(TAG, "UPGRADING DATABASE FROM VERSION " + oldVersion
                + " TO "
                + newVersion + ", WHICH WILL DESTROY ALL OLD DATA !");

        // DROP
        db.execSQL(DAOQuestion.DROP_TABLE);

        // Relancer la création
        onCreate(db);
    }
}
