package fr.esgi.davidghetto.loupgarou.activities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.esgi.davidghetto.loupgarou.activities.logic.User;

public class UserDB {

    private static final int VERSION_BDD = 1;
    private static final String DB_NAME = "loupgarou.db";

    public static final String TABLE_USERS = "table_users";

    public static final String COL_ID = "id";
    public static final int NUM_COL_ID = 0;

    public static final String COL_NAME = "name";
    public static final int NUM_COL_NAME = 1;

    public static final String COL_SCORE = "score";
    public static final int NUM_COL_SCORE = 2;

    private SQLiteDatabase bdd;

    private MyDatabaseSQLite maBaseSQLite;

    public UserDB(Context context) {
        // On crée la BDD et sa table
        maBaseSQLite = new MyDatabaseSQLite(context, DB_NAME, null, VERSION_BDD);
    }

    public void open() {
        // On ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        // On ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertUser(User user) {
        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        // On lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NAME, user.getName());
        values.put(COL_SCORE, user.getScore());
        // On insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_USERS, null, values);
    }

    public int updateUser(int id, User user) {
        // La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        // Il faut simple préciser quelle user on doit mettre à jour grâce à l'id
        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getName());
        values.put(COL_SCORE, user.getScore());
        return bdd.update(TABLE_USERS, values, COL_ID + " = " + id, null);
    }

    public int removeUserWithID(int id) {
        // Suppression d'un user de la BDD grâce à l'id
        return bdd.delete(TABLE_USERS, COL_ID + " = " + id, null);
    }

    public User getUserWithName(String name) {
        // Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son nom)
        Cursor c = bdd.query(TABLE_USERS, new String[]{COL_ID, COL_NAME, COL_SCORE}, COL_NAME + " LIKE \"" + name + "\"", null, null, null, null);
        return cursorToUser(c);
    }

    // Cette méthode permet de convertir un cursor en un user
    private User cursorToUser(Cursor c) {
        // Si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        // Sinon on se place sur le premier élément
        c.moveToFirst();

        // On créé un user
        User user = new User();
        // On lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        user.setId(c.getInt(NUM_COL_ID));
        user.setName(c.getString(NUM_COL_NAME));
        user.setScore(c.getInt(NUM_COL_SCORE));

        // On ferme le cursor
        c.close();

        // On retourne le user
        return user;
    }
}