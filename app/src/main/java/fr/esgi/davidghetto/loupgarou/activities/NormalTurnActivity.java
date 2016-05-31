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

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.activities.generic.PickActivity;
import fr.esgi.davidghetto.loupgarou.activities.generic.VoteActivity;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;
import fr.esgi.davidghetto.loupgarou.utils.Helper;
import fr.esgi.davidghetto.loupgarou.utils.RequestCodes;

public class NormalTurnActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView actualTextToDisplay;
    public ImageView backgroundImageNight;
    public ImageView backgroundImageDay;
    public Button nextButton;
    public Button infosButton;

    enum GameState {
        INIT,
        FORTUNE_TELLER_WAKES_UP, FORTUNE_TELLER_PICKS,
        WEREWOLVES_WAKE_UP, WEREWOLVES_PICK,
        WITCH_WAKES_UP, WITCH_ACTION,
        VILLAGE_WAKES_UP, VILLAGE_VOTES_TEXT, VILLAGE_VOTES, VILLAGE_KILL,
        VILLAGERS_VICTORY, WEREWOLVES_VICTORY, LOVERS_VICTORY
    }

    GameState currentGameState = GameState.INIT;

    public ArrayList<Player> players;
    public Player playerInTrouble;
    public Player playerExecuted;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_turn);

        actualTextToDisplay = (TextView) findViewById(R.id.actual_text_to_display);
        backgroundImageNight = (ImageView) findViewById(R.id.background_image_night_turn);
        backgroundImageDay = (ImageView) findViewById(R.id.background_image_day_turn);

        nextButton = (Button) findViewById(R.id.button_next_action);
        if (nextButton != null) {
            nextButton.setOnClickListener(this);
        }

        infosButton = (Button) findViewById(R.id.normal_turn_infos);
        if (infosButton != null) {
            infosButton.setOnClickListener(this);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(ExtraKeys.PLAYERS_LIST_KEY)) {
            players = getIntent().getExtras().getParcelableArrayList(ExtraKeys.PLAYERS_LIST_KEY);
        } else {
            players = Helper.getPlayers(false);
        }

        actualTextToDisplay.setText(R.string.normal_turn_text_night_falls);
    }

    @Override
    public void onClick(View v) {
        if (v == infosButton) {
            Intent gameInfosIntent = new Intent(this, GameInfoActivity.class);
            gameInfosIntent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, players);
            startActivity(gameInfosIntent);
        } else if (v == nextButton) {
            doNext();
        }
    }

    public void computeNextGameState() {
        // TODO: ajouter les rôles manquants (chasseur, sorcière)
        // TODO: faire la désignation du prochain maire quand l'actuel meurt
        switch (currentGameState) {
            case INIT:
                playerExecuted = null;
                playerInTrouble = null;
                Player fortuneTeller = getFortuneTeller();
                if (fortuneTeller != null && fortuneTeller.isAlive()) {
                    currentGameState = GameState.FORTUNE_TELLER_WAKES_UP;
                } else {
                    currentGameState = GameState.WEREWOLVES_WAKE_UP;
                }
                break;
            case FORTUNE_TELLER_WAKES_UP:
                currentGameState = GameState.FORTUNE_TELLER_PICKS;
                break;
            case FORTUNE_TELLER_PICKS:
                currentGameState = GameState.WEREWOLVES_WAKE_UP;
                break;
            case WEREWOLVES_WAKE_UP:
                currentGameState = GameState.WEREWOLVES_PICK;
                break;
            case WEREWOLVES_PICK:
                Player witch = getWitch();
                if (witch != null && witch.isAlive()) {
                    currentGameState = GameState.WITCH_WAKES_UP;
                } else {
                    currentGameState = GameState.VILLAGE_WAKES_UP;
                }
                break;
            case WITCH_WAKES_UP:
                currentGameState = GameState.WITCH_ACTION;
                break;
            case WITCH_ACTION:
                currentGameState = GameState.VILLAGE_WAKES_UP;
                break;
            case VILLAGE_WAKES_UP:
                currentGameState = GameState.VILLAGE_VOTES_TEXT;
                break;
            case VILLAGE_VOTES_TEXT:
                currentGameState = GameState.VILLAGE_VOTES;
                break;
            case VILLAGE_VOTES:
                currentGameState = GameState.VILLAGE_KILL;
                break;
            case VILLAGE_KILL:
                // TODO: vérifier les conditions de victoire plus souvent (pas besoin de faire le vote si reste que des loups)
                int numberOfVillagersAlive = 0;
                int numberOfWerewolvesAlive = 0;

                for (Player player : players) {
                    numberOfVillagersAlive += (player.getRole() != Role.WEREWOLF && player.isAlive() ? 1 : 0);
                    numberOfWerewolvesAlive += (player.getRole() == Role.WEREWOLF && player.isAlive() ? 1 : 0);
                }

                ArrayList<Player> lovers = getLovers();

                if (numberOfVillagersAlive + numberOfWerewolvesAlive == 2 && lovers.get(0).isAlive() && lovers.get(1).isAlive()) {
                    currentGameState = GameState.LOVERS_VICTORY;
                } else if (numberOfWerewolvesAlive == 0) {
                    currentGameState = GameState.VILLAGERS_VICTORY;
                } else if (numberOfVillagersAlive == 0) {
                    currentGameState = GameState.WEREWOLVES_VICTORY;
                } else {
                    currentGameState = GameState.INIT;
                }
                break;
            case VILLAGERS_VICTORY:
                // No next state
                break;
            case WEREWOLVES_VICTORY:
                // No next state
                break;
            case LOVERS_VICTORY:
                // No next state
                break;
        }
    }

    public void doNext() {
        computeNextGameState();

        switch (currentGameState) {
            case INIT:
                actualTextToDisplay.setText(R.string.normal_turn_text_night_falls);
                backgroundImageDay.setVisibility(View.INVISIBLE);
                backgroundImageNight.setVisibility(View.VISIBLE);
                break;
            case FORTUNE_TELLER_WAKES_UP:
                actualTextToDisplay.setText(R.string.normal_turn_text_fortune_teller_wakes_up);
                break;
            case FORTUNE_TELLER_PICKS:
                Intent fortuneTellerIntent = new Intent(this, PickActivity.class);
                fortuneTellerIntent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, getPlayersAlive());
                fortuneTellerIntent.putExtra(ExtraKeys.PICK_ACTIVITY_HEADER, getString(R.string.fortune_teller_pick_header));
                startActivityForResult(fortuneTellerIntent, RequestCodes.REQUEST_CODE_PICK);
                break;
            case WEREWOLVES_WAKE_UP:
                actualTextToDisplay.setText(R.string.normal_turn_text_werewolves_wake_up);
                break;
            case WEREWOLVES_PICK:
                Intent werewolvesIntent = new Intent(this, PickActivity.class);
                werewolvesIntent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, getPlayersAlive());
                werewolvesIntent.putExtra(ExtraKeys.PICK_ACTIVITY_HEADER, getString(R.string.werewolves_pick_header));
                startActivityForResult(werewolvesIntent, RequestCodes.REQUEST_CODE_PICK);
                break;
            case WITCH_WAKES_UP:
                actualTextToDisplay.setText(R.string.normal_turn_text_witch_wakes_up);
                break;
            case WITCH_ACTION:
                // TODO : TUER QQUN SI VOULU
                // TODO: SAUVER LE JOUEUR SI VOULU
                break;
            case VILLAGE_WAKES_UP:
                actualTextToDisplay.setText(R.string.normal_turn_text_village_wakes_up);
                backgroundImageDay.setVisibility(View.VISIBLE);
                backgroundImageNight.setVisibility(View.INVISIBLE);

                // textToDisplay += "X est mort !";
                // TODO : REVELER LA CARTE DU JOUEUR MORT
                // TODO : EN FONCTION DE LA CARTE ON FAIS UNE DERNIERE ACTION OU NON
                break;
            case VILLAGE_VOTES_TEXT:
                actualTextToDisplay.setText(R.string.normal_turn_text_village_votes);
                break;
            case VILLAGE_VOTES:
                Intent voteIntent = new Intent(this, VoteActivity.class);
                voteIntent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, getPlayersAlive());
                startActivityForResult(voteIntent, RequestCodes.REQUEST_CODE_VOTE);
                break;
            case VILLAGE_KILL:
                actualTextToDisplay.setText(getResources().getString(R.string.normal_turn_text_village_kill) + " " + playerExecuted.getName());
                break;
            case VILLAGERS_VICTORY:
                actualTextToDisplay.setText(R.string.normal_turn_text_villagers_victory);
                break;
            case WEREWOLVES_VICTORY:
                actualTextToDisplay.setText(R.string.normal_turn_text_werewolves_victory);
                break;
            case LOVERS_VICTORY:
                actualTextToDisplay.setText(R.string.normal_turn_text_lovers_victory);
                break;
        }
    }

    public void playerDied(Player playerWhoDied) {
        if (playerWhoDied.isLover()) {
            // TODO: Eviter récursivité
            ArrayList<Player> lovers = getLovers();
            Player otherLover;
            if (playerWhoDied.getName().equals(lovers.get(0))) {
                otherLover = lovers.get(1);
            } else {
                otherLover = lovers.get(0);
            }
            otherLover.setAlive(false);
            // TODO: afficher un message
            playerDied(otherLover);
        }
        if (playerWhoDied.getRole() == Role.HUNTER) {
            // TODO: Pick de qui tuer
        }
        if (playerWhoDied.isCaptain()) {
            // TODO: pick du prochain
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCodes.REQUEST_CODE_PICK:
                if (resultCode == RESULT_OK) {
                    Player playerPicked = data.getExtras().getParcelable(ExtraKeys.PICK_ACTIVITY_KEY);
                    if (currentGameState == GameState.FORTUNE_TELLER_PICKS) {
                        Intent toDisplayActivityIntent = new Intent(this, DisplayCardActivity.class);
                        toDisplayActivityIntent.putExtra(ExtraKeys.DISPLAY_PLAYER_KEY, playerPicked);
                        startActivity(toDisplayActivityIntent);
                        doNext();
                    } else if (currentGameState == GameState.WEREWOLVES_PICK) {
                        playerInTrouble = getPlayer(playerPicked.getName());
                        playerInTrouble.setAlive(false);
                        doNext();
                    }
                } else {
                    System.out.println("ERREUR, pas de pick !!!");
                }
                break;
            case RequestCodes.REQUEST_CODE_VOTE:
                if (resultCode == RESULT_OK) {
                    Player playerVotedFor = data.getExtras().getParcelable(ExtraKeys.VOTE_ACTIVITY_KEY);
                    if (currentGameState == GameState.VILLAGE_VOTES) {
                        playerExecuted = getPlayer(playerVotedFor.getName());
                        playerExecuted.setAlive(false);
                        doNext();
                    }
                } else {
                    System.out.println("ERREUR, pas de vote !!!");
                }
                break;
        }
    }

    public Player getFortuneTeller() {
        for (Player player : players) {
            if (player.getRole() == Role.FORTUNE_TELLER) {
                return player;
            }
        }
        return null;
    }

    public Player getCaptain() {
        for (Player player : players) {
            if (player.isCaptain()) {
                return player;
            }
        }
        return null;
    }

    public Player getHunter() {
        for (Player player : players) {
            if (player.getRole() == Role.HUNTER) {
                return player;
            }
        }
        return null;
    }

    public Player getWitch() {
        for (Player player : players) {
            if (player.getRole() == Role.WITCH) {
                return player;
            }
        }
        return null;
    }

    public Player getCupid() {
        for (Player player : players) {
            if (player.getRole() == Role.CUPID) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Player> getLovers() {
        ArrayList<Player> lovers = new ArrayList<>();
        for (Player player : players) {
            if (player.isLover()) {
                lovers.add(player);
            }
        }
        if (!lovers.isEmpty()) {
            return lovers;
        } else {
            return null;
        }
    }

//    public ArrayList<Player> getPlayersWithoutPlayer(Player playerToRemove) {
//        ArrayList<Player> newPlayers = new ArrayList<>(players);
//        for (Player player : players) {
//            if (player.getName().equals(playerToRemove.getName())) {
//                newPlayers.remove(playerToRemove);
//            }
//        }
//        return newPlayers;
//    }

    public ArrayList<Player> getPlayersAlive() {
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isAlive()) {
                newPlayers.add(player);
            }
        }
        return newPlayers;
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        // Devrait pas arriver
        return null;
    }
}

