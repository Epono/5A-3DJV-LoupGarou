package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class NormalNightTurnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button infosButton;

    enum VictoryState {
        NOT_FINISHED, VILLAGERS_VICTORY, WEREWOLVES_VICTORY, LOVERS_VICTORY
    }

    public Iterator<Player> iterator;
    public ArrayList<Player> players;
    public Player player_in_trouble;
    public Player player_executed;
    public ArrayList<Role> activeRoles;
    public String text_to_display;
    public Button next_dialog;
    public ImageView background_image_day;
    public ImageView background_image_night;
    public TextView actual_text_to_display;
    public int cpt;
    public ArrayList<Player> playersAlive;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_normal_turn);

        actual_text_to_display = (TextView) findViewById(R.id.actual_text_to_display);
        background_image_day = (ImageView) findViewById(R.id.background_image_day_turn);
        background_image_night = (ImageView) findViewById(R.id.background_image_night_turn);

        next_dialog = (Button) findViewById(R.id.button_next_action);
        if (next_dialog != null) {
            next_dialog.setOnClickListener(this);
        }

        infosButton = (Button) findViewById(R.id.normal_turn_infos);
        if(infosButton != null) {
            infosButton.setOnClickListener(this);
        }

        //  Game stateOfTheGame = getIntent().getExtras().getParcelable("game");
        players = getIntent().getExtras().getParcelableArrayList("players");
        activeRoles = getIntent().getExtras().getParcelableArrayList("roles");
        playersAlive = new ArrayList<Player>(players);
        // iterator = players.iterator();
        // activeRoles = stateOfTheGame.getActiveRoles();*/


        actual_text_to_display.setText("La nuit tombe sur le village les villageois s'endorment...");

    }

    @Override
    public void onClick(View v) {
        if(v == infosButton) {
            Intent gameInfosIntent = new Intent(this, GameInfoActivity.class);
            gameInfosIntent.putParcelableArrayListExtra(AddPlayersActivity.PLAYER_LIST_KEY, players);
            startActivity(gameInfosIntent);
        }
        cpt++;
        if (v == next_dialog && cpt == 1) {
            for (Player p : players) {
                if (p.getRole() == Role.SEER && p.isAlive()) {
                    text_to_display = "La voyante se réveille et désigne un joueur dont elle va pouvoir voir la carte";
                    actual_text_to_display.setText(text_to_display);
                }
            }
        }
        else if (v == next_dialog && cpt == 2) {
            //VOYANTE
            for (Player p : players) {
                if (p.getRole() == Role.SEER && p.isAlive()) {
                    Intent voyanteIntent = new Intent(this, PickActivity.class);
                    voyanteIntent.putParcelableArrayListExtra(AddPlayersActivity.PLAYER_LIST_KEY, players);
                    startActivityForResult(voyanteIntent, PickActivity.REQUEST_CODE_PICK);
                }
            }
        }

        if (v == next_dialog && cpt == 3) {
            //LOUP GAROU
            text_to_display = "Les loup garous se réveillent et désigner une cible a tuer";
            actual_text_to_display.setText(text_to_display);

            //TODO : ACTION ET STOCKAGE DU PLAYER A KILL



            Intent wolfIntent = new Intent(this, PickActivity.class);
            wolfIntent.putParcelableArrayListExtra("players", playersAlive);
            startActivityForResult(wolfIntent, PickActivity.REQUEST_CODE_PICK);



        }

        if (v == next_dialog && cpt == 4) {
            for (Player p : players) {
                if (p.getRole() == Role.WITCH && p.isAlive()) {
                    text_to_display = "La sorcière se réveille, elle peut sauver le vilageois désigner ou tuer une personne de son choix";
                    actual_text_to_display.setText(text_to_display);

                    //TODO : TUER QQUN SI VOULU

                    //TODO: SAUVER LE JOUEUR SI VOULU
                }
            }

            if (v == next_dialog && cpt == 5) {
                background_image_night.setVisibility(View.INVISIBLE);
                background_image_day.setVisibility(View.VISIBLE);

                text_to_display = "Le village se réveille, ";
                text_to_display += "X est mort !";
                actual_text_to_display.setText(text_to_display);

                //TODO : REVELER LA CARTE DU JOUEUR MORT


                //TODO : EN FONCTION DE LA CARTE ON FAIS UNE DERNIERE ACTION OU NON

            }

            if (v == next_dialog && cpt == 6) {
                text_to_display = "Il est temps de déterminer qui va être envoyé au bûcher";
                actual_text_to_display.setText(text_to_display);

                Intent voteIntent = new Intent(this, VoteActivity.class);
                voteIntent.putParcelableArrayListExtra("players" ,playersAlive);
                startActivityForResult(voteIntent,VoteActivity.REQUEST_CODE_VOTE);

            }



            if (partieFinie() == VictoryState.VILLAGERS_VICTORY) {

                text_to_display = "LES VILLAGEOIS WIN";
                actual_text_to_display.setText(text_to_display);
                //Intent DayTurnIntent = new Intent(this, NormalDayTurnActivity.class);
                //startActivity(DayTurnIntent);
                //finish();
            } else if (partieFinie() == VictoryState.WEREWOLVES_VICTORY) {
                text_to_display = "LES LOUP GAROU WIN";
                actual_text_to_display.setText(text_to_display);
            } else if (partieFinie() == VictoryState.LOVERS_VICTORY) {
                text_to_display = "LES AMOUREUX WIN";
                actual_text_to_display.setText(text_to_display);
            }
        }
    }


    public VictoryState partieFinie() {
        int villagersAlive = 0;
        int werewolvesAlive = 0;

        for (Player p : players) {
            villagersAlive += (p.getRole() != Role.WEREWOLF && p.isAlive() ? 1 : 0);
            werewolvesAlive += (p.getRole() == Role.WEREWOLF && p.isAlive() ? 1 : 0);
        }

        if (villagersAlive == 0) {
            return VictoryState.VILLAGERS_VICTORY;
        } else if (werewolvesAlive == 0) {
            return VictoryState.WEREWOLVES_VICTORY;
        } else {
            return VictoryState.NOT_FINISHED;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PickActivity.REQUEST_CODE_PICK:
                if (resultCode == RESULT_OK) {
                    if (cpt == 2) {
                        Intent toDisplayActivityIntent = new Intent(this, DisplayCardActivity.class);
                        toDisplayActivityIntent.putExtra(DisplayCardActivity.DISPLAY_PLAYER_KEY, data.getParcelableExtra(PickActivity.PICK_ACTIVITY_KEY));
                        startActivity(toDisplayActivityIntent);
                        text_to_display = "Les loup garous se réveillent et désigner une cible a tuer";
                        actual_text_to_display.setText(text_to_display);
                    } else if (cpt == 3) {
                        // afficher le lover
                        Player p = data.getExtras().getParcelable("pick");
                        for (Player temp : players) {
                            if (p.getName().equals(temp.getName())) {
                                player_in_trouble = temp;
                                temp.setAlive(false);
                                playersAlive.remove(temp);
                                text_to_display = "La cible a été désigné !";
                                actual_text_to_display.setText(text_to_display);
                            }
                        }
                    } else {
                        System.out.println("ERREUR, pas de lover ! AAAAAAAAAAAAAAAA");
                    }
                }
                break;
            case VoteActivity.REQUEST_CODE_VOTE:
                if (resultCode == RESULT_OK) {
                    // afficher le lover
                    Player p = data.getExtras().getParcelable("pick");
                    for (Player temp : players) {
                        if (p.getName().equals(temp.getName())) {
                            player_executed = temp;
                            temp.setAlive(false);
                            playersAlive.remove(temp);
                        }
                    }
                } else {
                    System.out.println("ERREUR, pas de lover ! AAAAAAAAAAAAAAAA");
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
