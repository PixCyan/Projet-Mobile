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
public class DAOCompte extends DAOBase {

    // Nom de la table
    public static final String TABLE_COMPTE = "COMPTE";

    // Table: QUESTION
    public static final String COL_ID = "id";
    public static final String LOGIN = "login";
    public static final String MDP = "mot_de_passe";
    public static final String SCORE_MATHS_MULT = "score_multiplication";
    public static final String SCORE_MATHS_ADD = "score_addition";
    public static final String SCORE_CULT_ART = "score_art";
    public static final String SCORE_CULT_CAPITALES = "score_capitales";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table compte
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_COMPTE + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOGIN + " TEXT NOT NULL, " +
                    MDP + " TEXT NOT NULL, " +
                    SCORE_MATHS_MULT + " TEXT NOT NULL, " +
                    SCORE_MATHS_ADD + " TEXT NOT NULL);" +
                    SCORE_CULT_ART + " TEXT NOT NULL, " +
                    SCORE_CULT_CAPITALES + " TEXT NOT NULL, ";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table compte
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_COMPTE + ";";

    private static final String[] DATA = new String[]{
            "'TestMan', 'test', '0', '0', '0', '0'"};

    //Constructeur
    public DAOCompte(Context context) {
        super(context);
    }

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_COMPTE + "("
                + LOGIN + ", "
                + MDP + ", "
                + SCORE_MATHS_MULT + ","
                + SCORE_MATHS_ADD + ","
                + SCORE_CULT_ART + ", "
                + SCORE_CULT_CAPITALES + ") VALUES ";

        String[] liste = new String[DATA.length];
        int i = 0;
        for (String compte : DATA) {
            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + compte + ")";
            i++;
        }
        return liste;
    }

    public long insert(Compte compte) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(LOGIN, compte.getLogin());
        values.put(MDP, compte.getMdp());
        values.put(SCORE_MATHS_MULT, compte.getScore_mult());
        values.put(SCORE_MATHS_ADD, compte.getScore_add());
        values.put(SCORE_CULT_ART, compte.getScore_art());
        values.put(SCORE_CULT_CAPITALES, compte.getScore_capitales());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_COMPTE, null, values);
    }

    public int update(Compte compte) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(LOGIN, compte.getLogin());
        values.put(MDP, compte.getMdp());
        values.put(SCORE_MATHS_MULT, compte.getScore_mult());
        values.put(SCORE_MATHS_ADD, compte.getScore_add());
        values.put(SCORE_CULT_ART, compte.getScore_art());
        values.put(SCORE_CULT_CAPITALES, compte.getScore_capitales());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_COMPTE, values, COL_ID + " = " + compte.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une question de la BD à partir de l'ID
        return getDB().delete(TABLE_COMPTE, COL_ID + " = " + id, null);
    }

    public int remove(Compte compte) {

        return removeByID(compte.getId());
    }

    public List<Compte> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_COMPTE, null);

        return cursorToListCompte(cursor);
    }

    public Compte retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_COMPTE + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstCompte(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de questions
    private List<Compte> cursorToListCompte(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexLogin = cursor.getColumnIndex(LOGIN);
        int indexMDP = cursor.getColumnIndex(MDP);
        int indexScoreMult = cursor.getColumnIndex(SCORE_MATHS_MULT);
        int indexScoreAdd = cursor.getColumnIndex(SCORE_MATHS_ADD);
        int indexScoreArt = cursor.getColumnIndex(SCORE_CULT_ART);
        int indexScoreCap = cursor.getColumnIndex(SCORE_CULT_CAPITALES);


        // Declaration et initialisation d'une liste de compte
        ArrayList<Compte> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'un compte
            Compte compte = new Compte();
            compte.setId(cursor.getInt(indexId));
            compte.setLogin(cursor.getString(indexLogin));
            compte.setMdp(cursor.getString(indexMDP));
            //TODO verifier les suivantes :
            compte.setScore_mult(cursor.getInt(indexScoreMult));
            compte.setScore_add(cursor.getInt(indexScoreAdd));
            compte.setScore_art(cursor.getInt(indexScoreArt));
            compte.setScore_capitales(cursor.getInt(indexScoreCap));

            // Ajout dans la liste
            liste.add(compte);
        }
        // Fermeture du cursor
        cursor.close();
        return liste;
    }

    //Cette méthode permet de convertir un cursor en un compte
    private Compte cursorToFirstCompte(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexLogin = cursor.getColumnIndex(LOGIN);
        int indexMDP = cursor.getColumnIndex(MDP);
        int indexScoreMult = cursor.getColumnIndex(SCORE_MATHS_MULT);
        int indexScoreAdd = cursor.getColumnIndex(SCORE_MATHS_ADD);
        int indexScoreArt = cursor.getColumnIndex(SCORE_CULT_ART);
        int indexScoreCap = cursor.getColumnIndex(SCORE_CULT_CAPITALES);


        // Declaration d'une compte
        Compte compte = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'un compte
            compte = new Compte();
            compte.setId(cursor.getInt(indexId));
            compte.setLogin(cursor.getString(indexLogin));
            compte.setMdp(cursor.getString(indexMDP));
            //TODO verifier les suivantes :
            compte.setScore_mult(cursor.getInt(indexScoreMult));
            compte.setScore_add(cursor.getInt(indexScoreAdd));
            compte.setScore_art(cursor.getInt(indexScoreArt));
            compte.setScore_capitales(cursor.getInt(indexScoreCap));

        }

        // Fermeture du cursor
        cursor.close();
        return compte;
    }



}
