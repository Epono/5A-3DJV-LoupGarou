package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    public Button next_dialog;
    public ImageView background_image_day;
    public ImageView background_image_night;
    public TextView actual_text_to_display;
    public int cpt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_normal_turn);

        actual_text_to_display = (TextView) findViewById(R.id.actual_text_to_display);
        background_image_day = (ImageView) findViewById(R.id.background_image_day_turn);
        background_image_night = (ImageView) findViewById(R.id.background_image_night_turn);

        next_dialog = (Button) findViewById(R.id.button_next_action);
        if(next_dialog != null)
        {
            next_dialog.setOnClickListener(this);
        }




        /*  Game stateOfTheGame = getIntent().getExtras().getParcelable("game");
        players = getIntent().getExtras().getParcelable("players");
        iterator = players.iterator();
        activeRoles = stateOfTheGame.getActiveRoles();*/


        actual_text_to_display.setText("La nuit tombe sur le village les villageois s'endorment...");

    }

    @Override
    public void onClick(View v) {
        cpt++;
        if (v == next_dialog && cpt == 1) {
            //VOYANTE
            // if (activeRoles.contains(Role.SEER)) {
            text_to_display = "La voyante se réveille et désigne un joueur dont elle va pouvoir voir la carte";
            actual_text_to_display.setText(text_to_display);

            // }
        }

        if (v == next_dialog && cpt == 2) {
            //LOUP GAROU
            text_to_display = "Les loup garous se réveillent et désigner une cible a tuer";
            actual_text_to_display.setText(text_to_display);
        }

        if (v == next_dialog && cpt == 3){
           // if (activeRoles.contains(Role.WITCH)) {
                text_to_display = "La sorcière se réveille, elle peut sauver le vilageois désigner ou tuer une personne de son choix";
                actual_text_to_display.setText(text_to_display);
            //}
    }

        if(v == next_dialog && cpt == 4)
        {
            background_image_night.setVisibility(View.INVISIBLE);
            background_image_day.setVisibility(View.VISIBLE);
            //Intent DayTurnIntent = new Intent(this, NormalDayTurnActivity.class);
            //startActivity(DayTurnIntent);
        }

            //finish();
        }

}
