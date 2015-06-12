package fr.pixcyan.android.raffennn.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author PixCyan
 */
public class DAOCapitale extends DAOBase  {
    // Nom de la table
    public static final String TABLE_CAPITALE = "CAPITALE";

    // Table: QUESTION
    public static final String PAYS = "pays";
    public static final String CAPITALE = "capitale";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_CAPITALE + " (" +
                    PAYS + " Varchar(20) PRIMARY KEY, " +
                    CAPITALE + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_CAPITALE + ";";


    // Données pour la table
    private static final String[] DATA = new String[]{
            "'France', 'Paris'",
            "'Allemagne', 'Berlin'",
            "'Grèce', 'Athenes'",
            "'Suisse', 'Berne'",
            "'Argentine', 'Buenos Aires'",
            "'Chine', 'Pekin'",
            "'Inde', 'New Delhi'",
            "'Chili', 'Santiago'",
            "'Suède', 'Stockholm'",
            "'Japon', 'Tokyo'",
            "'Autriche', 'Vienne'",
            "'Croatie', 'Zagreb'",
            "'Tunisie', 'Tunis'",
            "'Portugal', 'Lisbonne'",
            "'Pérou', 'Lima'",
            "'Espagne', 'Madrid'",
            "'Royaume-Uni', 'Londres'",};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_CAPITALE + "("
                + PAYS + ", "
                + CAPITALE + ") VALUES ";

        String[] liste = new String[DATA.length];
        int i = 0;
        for (String paysCapitale : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + paysCapitale + ")";
            i++;
        }

        //
        return liste;
    }

    public DAOCapitale(Context context) {
        super(context);
    }


    public long insert(Capitale capitale) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(PAYS, capitale.getPays());
        values.put(CAPITALE, capitale.getCapitale());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_CAPITALE, null, values);
    }

    public int update(Capitale capitale) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(PAYS, capitale.getPays());
        values.put(CAPITALE, capitale.getCapitale());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_CAPITALE, values, PAYS+ " = " + capitale.getPays(), null);
    }

    public int removeByPays(String pays) {

        //Suppression d'une question de la BD à partir de l'ID
        return getDB().delete(TABLE_CAPITALE, PAYS + " = " + pays, null);
    }

    public int remove(Capitale capitale) {

        return removeByPays(capitale.getPays());
    }

    public List<Capitale> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_CAPITALE, null);

        return cursorToListCapitale(cursor);
    }

    public Capitale retrieveByPays(String pays) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_CAPITALE + " WHERE " + PAYS + "=?", new String[]{pays});

        return cursorToFirstCapitale(cursor);
    }

    public Capitale getPaysRandom() {

        //Récupère dans un Cursor les valeur correspondant à une question au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_CAPITALE + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstCapitale(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de questions
    private List<Capitale> cursorToListCapitale(Cursor cursor) {

        // Récupére l'index des champs
        int indexPays = cursor.getColumnIndex(PAYS);
        int indexCapitalen = cursor.getColumnIndex(CAPITALE);


        // Declaration et initialisation d'une liste de question
        ArrayList<Capitale> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une question
            Capitale capitale = new Capitale();
            capitale.setPays(cursor.getString(indexPays));
            capitale.setCapitale(cursor.getString(indexCapitalen));

            // Ajout dans la liste
            liste.add(capitale);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une question
    private Capitale cursorToFirstCapitale(Cursor cursor) {

        // Récupére l'index des champs
        int indexPays = cursor.getColumnIndex(PAYS);
        int indexCapitalen = cursor.getColumnIndex(CAPITALE);
        Capitale capitale = null;
        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une question
            capitale = new Capitale();
            capitale.setPays(cursor.getString(indexPays));
            capitale.setCapitale(cursor.getString(indexCapitalen));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return capitale;
    }
}
