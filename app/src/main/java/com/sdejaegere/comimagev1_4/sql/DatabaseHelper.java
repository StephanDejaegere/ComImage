package com.sdejaegere.comimagev1_4.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.model.Question;
import com.sdejaegere.comimagev1_4.model.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Créé par sdejaegere le 19/05/2019.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Version de la base de données
    private static final int DATABASE_VERSION = 1;

    // Nom de la base de données
    private static final String DATABASE_NAME = "ComImage.db";

    // Table utilisateur
    private static final String TABLE_USER = "user";

    //Table question
    private static final String TABLE_QUESTION = "question";

    // Les noms des colonnes de la table Utilisateur
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //Les noms des colonnes de la table Question
    private static final String COLUMN_QUESTION_ID = "question_id";
    private static final String COLUMN_QUESTION_TEXT = "text_question";
    private static final String COLUMN_QUESTION_IDUSER = "question_user_id";
    private static final String COLUMN_QUESTION_PICTO_UN = "question_picto_un";
    private static final String COLUMN_QUESTION_PICTO_DEUX = "question_picto_deux";
    private static final String COLUMN_QUESTION_PICTO_TROIS = "question_picto_trois";
    private static final String COLUMN_QUESTION_PICTO_QUATRE = "question_picto_quatre";
    private static final String COLUMN_QUESTION_PICTO_CINQ = "question_picto_cinq";
    private static final String COLUMN_QUESTION_NOM_PICTO_UN = "question_nom_picto_un";
    private static final String COLUMN_QUESTION_NOM_PICTO_DEUX = "question_nom_picto_deux";
    private static final String COLUMN_QUESTION_NOM_PICTO_TROIS = "question_nom_picto_trois";
    private static final String COLUMN_QUESTION_NOM_PICTO_QUATRE = "question_nom_picto_quatre";
    private static final String COLUMN_QUESTION_NOM_PICTO_CINQ = "question_nom_picto_cinq";

        // requete SQL pour créer la table utilisateur
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    //requeste pour créer la table question
    private String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
            + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUESTION_TEXT + " TEXT,"
            + COLUMN_QUESTION_IDUSER + " TEXT," + COLUMN_QUESTION_PICTO_UN + " INTEGER," +
    COLUMN_QUESTION_PICTO_DEUX + " INTEGER," + COLUMN_QUESTION_PICTO_TROIS + " INTEGER," +
    COLUMN_QUESTION_PICTO_QUATRE + " INTEGER," + COLUMN_QUESTION_PICTO_CINQ + " INTEGER," +
    COLUMN_QUESTION_NOM_PICTO_UN + " TEXT," + COLUMN_QUESTION_NOM_PICTO_DEUX + " TEXT," +
    COLUMN_QUESTION_NOM_PICTO_TROIS + " TEXT," + COLUMN_QUESTION_NOM_PICTO_QUATRE + " TEXT," +
    COLUMN_QUESTION_NOM_PICTO_CINQ + " TEXT" +")";


    // requete droptable sql user
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //request droptable sql question
    private String DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTION;


    /**
     * Constructeur
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User && Question Tables if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_QUESTION_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * Méthode pour créer un nouvel utilisateur
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String md5Password = MD5(user.getPassword());

        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, md5Password);
        db.insert(TABLE_USER, null, values);

        db.close();

    }

    public void basicQuestionsSet(int i){

        addBdd("As-tu vu les dix pantalons blancs?", i, R.drawable.chiffre_10, R.drawable.habit_pantalon, R.drawable.couleur_blanc, R.drawable.add, R.drawable.add, "Chiffre 10", "Pantalon", "Blanc", "", "");
        addBdd("Veux-tu jouer aux dominos à la maison?", i, R.drawable.jouet_dominos, R.drawable.lieu_maison, R.drawable.add , R.drawable.add, R.drawable.add, "Dominos","Maison","","","");
        addBdd("On va à la piscine ?", i, R.drawable.lieu_piscine, R.drawable.add, R.drawable.add , R.drawable.add, R.drawable.add, "Piscine", "", "", "", "");
        addBdd("as-tu mal aux pieds ?", i, R.drawable.mal_pieds, R.drawable.add, R.drawable.add , R.drawable.add, R.drawable.add, "Mal aux pieds", "", "", "", "");
    }

    /**
     * Méthode pour créer une nouvelle question
     *
     * @param question
     */
    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_TEXT, question.getQuestion());
        values.put(COLUMN_QUESTION_IDUSER, question.getUserId());
        values.put(COLUMN_QUESTION_PICTO_UN, question.getPictoUn());
        values.put(COLUMN_QUESTION_PICTO_DEUX, question.getPictoDeux());
        values.put(COLUMN_QUESTION_PICTO_TROIS, question.getPictoTrois());
        values.put(COLUMN_QUESTION_PICTO_QUATRE, question.getPictoQuatre());
        values.put(COLUMN_QUESTION_PICTO_CINQ, question.getPictoCinq());
        values.put(COLUMN_QUESTION_NOM_PICTO_UN, question.getNomPictoUn());
        values.put(COLUMN_QUESTION_NOM_PICTO_DEUX, question.getNomPictoDeux());
        values.put(COLUMN_QUESTION_NOM_PICTO_TROIS, question.getNomPictoTrois());
        values.put(COLUMN_QUESTION_NOM_PICTO_QUATRE, question.getNomPictoQuatre());
        values.put(COLUMN_QUESTION_NOM_PICTO_CINQ, question.getNomPictoCinq());

        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    /**
     * Méthode pour récupérer la liste de questions d'un utilisateur spécifique
     *
     * @return list
     */
    public List<Question> getSpecificUserQuestion(String id) {

        String[] columns = {
                COLUMN_QUESTION_ID,
                COLUMN_QUESTION_TEXT,
                COLUMN_QUESTION_IDUSER,
                COLUMN_QUESTION_PICTO_UN,
                COLUMN_QUESTION_PICTO_DEUX,
                COLUMN_QUESTION_PICTO_TROIS,
                COLUMN_QUESTION_PICTO_QUATRE,
                COLUMN_QUESTION_PICTO_CINQ,
                COLUMN_QUESTION_NOM_PICTO_UN,
                COLUMN_QUESTION_NOM_PICTO_DEUX,
                COLUMN_QUESTION_NOM_PICTO_TROIS,
                COLUMN_QUESTION_NOM_PICTO_QUATRE,
                COLUMN_QUESTION_NOM_PICTO_CINQ

        };
        // trier
        String sortOrder = COLUMN_QUESTION_ID + " ASC";

        String selection = COLUMN_QUESTION_IDUSER + " = ?";

        String[] selectionArgs = {id};


        List<Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db = this.getReadableDatabase();

        // requete pour récupérer les utilisateurs
        /**
         * Fonction de requete qui ressemblerait à
         * "SELECT question_id,question_text FROM question where userId = XXX ORDER BY user_name;"
         * en sql classique
         */
        Cursor cursor = db.query(TABLE_QUESTION, //la table à utiliser
                columns,    //les colonnes à récupérer
                selection,        //la clause WHERE si besoin
                selectionArgs,        //Les valeurs du WHERE
                null,       //regrouper les lignes
                null,       //trier les lignes
                sortOrder); //trier


        // On va de ligne en ligne et on ajoute le tout à questionList
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ID))));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_TEXT)));
                question.setUserId(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_IDUSER)));

                if(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_UN)) != null){
                    question.setPictoUn(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_UN))));
                    question.setNomPictoUn(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_NOM_PICTO_UN)));
                }

                if(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_DEUX)) != null){
                    question.setPictoDeux(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_DEUX))));
                    question.setNomPictoDeux(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_NOM_PICTO_DEUX)));
                }

                if(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_TROIS)) != null){
                    question.setPictoTrois(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_TROIS))));
                    question.setNomPictoTrois(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_NOM_PICTO_TROIS)));
                }

                if(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_QUATRE)) != null){
                    question.setPictoQuatre(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_QUATRE))));
                    question.setNomPictoQuatre(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_NOM_PICTO_QUATRE)));
                }

                if(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_CINQ)) != null){
                    question.setPictoCinq(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_PICTO_CINQ))));
                    question.setNomPictoCinq(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_NOM_PICTO_CINQ)));
                }

                // Ajout
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // on renvoie l'ensemble des infos récupérés
        return questionList;
    }


    /**
     * Méthode de vérification si un utilisateur existe
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {


        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // on précise le where
        String selection = COLUMN_USER_EMAIL + " = ?";

        // argument pour le where
        String[] selectionArgs = {email};

        // la requete conditionnelle
        /**
         * Fonctionne comme une requete sql équivalente à
         * SELECT user_id FROM user WHERE user_email = 'bob@dylan.org';
         */
        Cursor cursor = db.query(TABLE_USER, //la table à utiliser
                columns,                    //les colonnes à retourner
                selection,                  //le where
                selectionArgs,              //la valeur du where
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * Méthode pour vérifier si la question existe déjà dans la bdd
     * @param question
     * @return
     */
    public boolean checkQuestion(String question, int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_QUESTION + " where " + COLUMN_QUESTION_IDUSER + "='" + id + "' AND " + COLUMN_QUESTION_TEXT + "='" + question + "'" , null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * Méthode pour vérifier si un utilisateur existe
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // on récupére des colonnes
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // critère de sélection
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // valeur de sélection
        String[] selectionArgs = {email, password};

        // requete
        /**
         * Requete équivalente à la requete SQL
         * SELECT user_id FROM user WHERE user_email = 'bob@dylan.org' AND user_password = 'prout';
         */
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public void addBdd(String question, int iduser, int pictoUn, int pictoDeux, int pictoTrois, int pictoQuatre, int pictoCinq, String nomPictoUn, String nomPictoDeux, String nomPictoTrois, String nomPictoQuatre, String nomPictoCinq){

        if (!checkQuestion(question, iduser)){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_TEXT, question);
        values.put(COLUMN_QUESTION_IDUSER, iduser);
        if(pictoUn != R.drawable.add){
            values.put(COLUMN_QUESTION_PICTO_UN, pictoUn);
            values.put(COLUMN_QUESTION_NOM_PICTO_UN, nomPictoUn);}
        if(pictoDeux != R.drawable.add){
            values.put(COLUMN_QUESTION_PICTO_DEUX, pictoDeux);
            values.put(COLUMN_QUESTION_NOM_PICTO_DEUX, nomPictoDeux);}
        if(pictoTrois != R.drawable.add){
            values.put(COLUMN_QUESTION_PICTO_TROIS, pictoTrois);
            values.put(COLUMN_QUESTION_NOM_PICTO_TROIS, nomPictoTrois);}
        if(pictoQuatre != R.drawable.add){
            values.put(COLUMN_QUESTION_PICTO_QUATRE, pictoQuatre);
            values.put(COLUMN_QUESTION_NOM_PICTO_QUATRE, nomPictoQuatre);}
        if(pictoCinq != R.drawable.add){
            values.put(COLUMN_QUESTION_PICTO_CINQ, pictoCinq);
            values.put(COLUMN_QUESTION_NOM_PICTO_CINQ, nomPictoCinq);}
        db.insert(TABLE_QUESTION, null, values);
        db.close();
        }

    }

    public String getUserId(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // on précise le where
        String selection = COLUMN_USER_EMAIL + " = ?";

        // argument pour le where
        String[] selectionArgs = {email};

        String id = "";

        // la requete conditionnelle
        /**
         * Fonctionne comme une requete sql équivalente à
         * SELECT user_id FROM user WHERE user_email = 'bob@dylan.org';
         */
        Cursor cursor = db.query(TABLE_USER, //la table à utiliser
                columns,                    //les colonnes à retourner
                selection,                  //le where
                selectionArgs,              //la valeur du where
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
        }
        return id;
    }

    public String getUserName(String email){
        String[] columns = {
                COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_USER_EMAIL + "='" + email + "'" , null);

        String name = "";
        if (cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
        }
        cursor.close();
        return name;
    }

    public void updateQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_ID, question.getId());
        values.put(COLUMN_QUESTION_TEXT, question.getQuestion());
        values.put(COLUMN_QUESTION_IDUSER, question.getUserId());
        values.put(COLUMN_QUESTION_PICTO_UN, question.getPictoUn());
        values.put(COLUMN_QUESTION_PICTO_DEUX, question.getPictoDeux());
        values.put(COLUMN_QUESTION_PICTO_TROIS, question.getPictoTrois());
        values.put(COLUMN_QUESTION_PICTO_QUATRE, question.getPictoQuatre());
        values.put(COLUMN_QUESTION_PICTO_CINQ, question.getPictoCinq());
        values.put(COLUMN_QUESTION_NOM_PICTO_UN, question.getNomPictoUn());
        values.put(COLUMN_QUESTION_NOM_PICTO_DEUX, question.getNomPictoDeux());
        values.put(COLUMN_QUESTION_NOM_PICTO_TROIS, question.getNomPictoTrois());
        values.put(COLUMN_QUESTION_NOM_PICTO_QUATRE, question.getNomPictoQuatre());
        values.put(COLUMN_QUESTION_NOM_PICTO_CINQ, question.getNomPictoCinq());

        // MAJ
        db.update(TABLE_QUESTION, values, COLUMN_QUESTION_ID + " = "+question.getId(),
                null);
        db.close();
    }

    /**
     * Méthode pour mettre à jour les utilisateurs
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        String md5Password = MD5(user.getPassword());

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, md5Password);

        // MAJ
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_QUESTION, COLUMN_QUESTION_ID + " = ?",
                new String[]{String.valueOf(question.getId())});
        db.close();
    }

    public String getUserEmailFromId(String id){
        String[] columns = {
                COLUMN_USER_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_USER_ID + "='" + id + "'" , null);

        String email = "";

        if (cursor.moveToFirst()){
            email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL));
        }
        cursor.close();

        return email;


    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}