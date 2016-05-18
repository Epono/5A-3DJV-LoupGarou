package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.esgi.davidghetto.loupgarou.database.UserDB;
import fr.esgi.davidghetto.loupgarou.logic.User;
import fr.esgi.davidghetto.loupgarou.R;

public class MainActivity extends AppCompatActivity {

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.play_button);

        if (playButton != null) {
            playButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent addPlayersActivityIntent = new Intent(MainActivity.this, AddPlayersActivity.class);
                    startActivity(addPlayersActivityIntent);
                }
            });
        }

        //testDB();
    }

    public void testDB() {
        // Création d'une instance de ma classe UserDB
        UserDB userDB = new UserDB(this);

        // On ouvre la base de données pour écrire dedans
        userDB.open();

        User user2 = userDB.getUserWithName("Plus Jean Jacques");
        if (user2 != null) {
            Toast.makeText(this, "IL EST VIVANT !", Toast.LENGTH_LONG).show();
            userDB.removeUserWithID(user2.getId());
        } else {
            // Création d'un livre
            User user = new User("Jean Jacques");

            // On insère le user que l'on vient de créer
            userDB.insertUser(user);

            // Pour vérifier que l'on a bien créé notre user dans la BDD
            // On extrait le user de la BDD grâce au nom du user que l'on a créé précédemment
            User userFromDB = userDB.getUserWithName(user.getName());

            // Si un user est retourné (donc si le user à bien été ajouté à la BDD)
            if (userFromDB != null) {
                // On affiche les infos du user dans un Toast
                Toast.makeText(this, userFromDB.toString(), Toast.LENGTH_LONG).show();

                // On modifie le nom du user
                userFromDB.setName("Plus Jean Jacques");

                // Puis on met à jour la BDD
                userDB.updateUser(userFromDB.getId(), userFromDB);
            }

            // On extrait le user de la BDD grâce au nouveau nom
            userFromDB = userDB.getUserWithName("Plus Jean Jacques");

            // S'il existe un user possédant ce nom dans la BDD
            if (userFromDB != null) {
                // On affiche les nouvelle info du user pour vérifier que le nom du user a bien été mis à jour
                Toast.makeText(this, userFromDB.toString(), Toast.LENGTH_LONG).show();
            }

            // On essaie d'extraire de nouveau le user de la BDD toujours grâce à son nouveau nom
            userFromDB = userDB.getUserWithName("Plus Jean Jacques");

            // Si aucun user n'est retourné
            if (userFromDB == null) {
                //On affiche un message indiquant que le user n'existe pas dans la BDD
                Toast.makeText(this, "Ce user n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
            }
            // Si le user existe encore
            else {
                // On affiche un message indiquant que le user existe dans la BDD
                Toast.makeText(this, "Ce user existe dans la BDD", Toast.LENGTH_LONG).show();
            }
        }

        userDB.close();
    }
}
