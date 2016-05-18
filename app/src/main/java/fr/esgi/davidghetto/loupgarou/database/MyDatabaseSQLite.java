package fr.esgi.davidghetto.loupgarou.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDatabaseSQLite extends SQLiteOpenHelper {

    private static final String CREATE_BDD = "CREATE TABLE " + UserDB.TABLE_USERS + " ("
            + UserDB.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserDB.COL_NAME + " TEXT NOT NULL, "
            + UserDB.COL_SCORE + " INTEGER NOT NULL DEFAULT 0);";

    public MyDatabaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // On créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        // comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + UserDB.TABLE_USERS + ";");
        onCreate(db);
    }
}
