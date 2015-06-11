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
public class DAOQuestion extends DAOBase {

    // Nom de la table
    public static final String TABLE_QUESTION_REPONSE = "QUESTION_REPONSE";

    // Table: QUESTION
    public static final String COL_ID = "id";
    public static final String COL_QUESTION = "question";
    public static final String COL_BONNE_REPONSE = "bonne_reponse";
    public static final String COL_MAUVAISE_REPONSE_1 = "mauvaise_reponse_1";
    public static final String COL_MAUVAISE_REPONSE_2 = "mauvaise_reponse_2";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_QUESTION_REPONSE + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_QUESTION + " TEXT NOT NULL, " +
                    COL_BONNE_REPONSE + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_1 + " TEXT NOT NULL, " +
                    COL_MAUVAISE_REPONSE_2 + " TEXT NOT NULL);";

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTION_REPONSE + ";";


    // Données pour la table
    private static final String[] DATA = new String[]{
            "'Qu'elle est la couleur du cheval bleu d'Henri IV ?', 'Bleu', 'Blanc', 'Noir'",
            "'Qui a peint la Joconde ?', 'Léonard de Vinci', 'Picasso', 'Van Gogh'",
            "'Quel est le vrai nom de Molière ?', 'Jean-Baptiste Poquelin','Jean-Claude Van Damme', 'Jean Dujardin'",
            "'Qui est Botticelli ?', 'Un peintre italien','Un célèbre inventeur italien', 'Un sculpteur italien'"};

    // retourne une liste de chaînes de caractères représentant les instructions SQL d'insertion de données dans la table
    public static String[] getInsertSQL() {
        String insertSQL = "INSERT INTO " + TABLE_QUESTION_REPONSE + "("
                + COL_QUESTION + ", "
                + COL_BONNE_REPONSE + ", "
                + COL_MAUVAISE_REPONSE_1 + ", "
                + COL_MAUVAISE_REPONSE_2 + ") VALUES ";

        //
        String[] liste = new String[DATA.length];
        int i = 0;
        for (String questionReponse : DATA) {

            // Instruction SQL INSERT
            liste[i] = insertSQL + "(" + questionReponse + ")";
            i++;
        }

        //
        return liste;
    }

    public DAOQuestion(Context context) {
        super(context);
    }

    public long insert(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getBonneReponse());
        values.put(COL_MAUVAISE_REPONSE_1, question.getMauvaiseReponse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getMauvaiseReponse2());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_QUESTION_REPONSE, null, values);
    }

    public int update(Question question) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_BONNE_REPONSE, question.getBonneReponse());
        values.put(COL_MAUVAISE_REPONSE_1, question.getMauvaiseReponse1());
        values.put(COL_MAUVAISE_REPONSE_2, question.getMauvaiseReponse2());

        // Insertion de l'objet dans la BD via le ContentValues et l'identifiant
        return getDB().update(TABLE_QUESTION_REPONSE, values, COL_ID + " = " + question.getId(), null);
    }

    public int removeByID(int id) {

        //Suppression d'une question de la BD à partir de l'ID
        return getDB().delete(TABLE_QUESTION_REPONSE, COL_ID + " = " + id, null);
    }

    public int remove(Question question) {

        return removeByID(question.getId());
    }

    public List<Question> selectAll() {

        //Récupère dans un Cursor les valeur correspondant à des enregistrements de question contenu dans la BD
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE, null);

        return cursorToListQuestion(cursor);
    }

    public Question retrieveByID(int id) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " WHERE " + COL_ID + "=?", new String[]{Integer.toString(id)});

        return cursorToFirstQuestion(cursor);
    }

    public Question getQuestionRandom() {

        //Récupère dans un Cursor les valeur correspondant à une question au hasard
        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_QUESTION_REPONSE + " ORDER BY RANDOM() LIMIT 1", null);

        return cursorToFirstQuestion(cursor);
    }

    //Cette méthode permet de convertir un cursor en une liste de questions
    private List<Question> cursorToListQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration et initialisation d'une liste de question
        ArrayList<Question> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une question
            Question question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setBonneReponse(cursor.getString(indexBonneReponse));
            question.setMauvaiseReponse1(cursor.getString(indexMauvaiseReponse1));
            question.setMauvaiseReponse2(cursor.getString(indexMauvaiseReponse2));

            // Ajout dans la liste
            liste.add(question);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }

    //Cette méthode permet de convertir un cursor en une question
    private Question cursorToFirstQuestion(Cursor cursor) {

        // Récupére l'index des champs
        int indexId = cursor.getColumnIndex(COL_ID);
        int indexQuestion = cursor.getColumnIndex(COL_QUESTION);
        int indexBonneReponse = cursor.getColumnIndex(COL_BONNE_REPONSE);
        int indexMauvaiseReponse1 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_1);
        int indexMauvaiseReponse2 = cursor.getColumnIndex(COL_MAUVAISE_REPONSE_2);


        // Declaration d'une question
        Question question = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une question
            question = new Question();
            question.setId(cursor.getInt(indexId));
            question.setQuestion(cursor.getString(indexQuestion));
            question.setBonneReponse(cursor.getString(indexBonneReponse));
            question.setMauvaiseReponse1(cursor.getString(indexMauvaiseReponse1));
            question.setMauvaiseReponse2(cursor.getString(indexMauvaiseReponse2));

        }

        // Fermeture du cursor
        cursor.close();

        //
        return question;
    }
}
