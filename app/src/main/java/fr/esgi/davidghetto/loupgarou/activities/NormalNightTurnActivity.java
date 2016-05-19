package fr.esgi.davidghetto.loupgarou.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Iterator;
import java.util.List;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Game;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;
/**
 * Created by Lucas on 19/05/2016.
 */
public class NormalNightTurnActivity extends AppCompatActivity implements View.OnClickListener {

    public Iterator<Player> iterator;
    public List<Player> players;
    public List<Role> activeRoles;
    public String text_to_display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_normal_turn);


        Game stateOfTheGame = getIntent().getExtras().getParcelable("game");
        players = getIntent().getExtras().getParcelable("players");
        iterator = players.iterator();
        activeRoles = stateOfTheGame.getActiveRoles();
    }

    @Override
    public void onClick(View v) {

        //VOYANTE
        if(activeRoles.contains(Role.SEER)) {

            text_to_display = "La voyante se réveille et désigne un joueur dont elle va pouvoir voir la carte";
        }


        //LOUP GAROU
        text_to_display = "Les loup garous se réveillent et désigner une cible a tuer";

        if(activeRoles.contains(Role.WITCH)) {
            text_to_display = "La sorcière se réveille, elle peut sauver le vilageois désigner ou tuer une personne de son choix";
        }



            finish();
        }

}
