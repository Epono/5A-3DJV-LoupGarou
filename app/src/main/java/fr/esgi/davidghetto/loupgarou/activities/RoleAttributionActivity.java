package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class RoleAttributionActivity extends AppCompatActivity {

    enum State {
        CARD_DISPLAYED, NAME_DISPLAYED
    }

    private TextView playersNameText;
    private TextView playersRoleNameText;
    private ImageView playersRoleImage;
    private Button nextButton;
    private TextView timerText;

    private DecimalFormat df = new DecimalFormat();
    private State currentState = State.CARD_DISPLAYED;

    public Iterator<Player> iterator;
    public ArrayList<Player> players;
    public ArrayList<Role> roles;

    Player currentPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_attribution);

        playersNameText = (TextView) findViewById(R.id.role_attribution_player_s_name);
        playersRoleNameText = (TextView) findViewById(R.id.role_attribution_player_s_role_text);
        playersRoleImage = (ImageView) findViewById(R.id.role_attribution_player_s_role_image);
        nextButton = (Button) findViewById(R.id.role_attribution_next);
        timerText = (TextView) findViewById(R.id.role_attribution_timer);

        roles = getIntent().getExtras().getParcelableArrayList("roles");
        players = getIntent().getExtras().getParcelableArrayList("players");
        iterator = players.iterator();

        // TODO
        boolean lovers = true;
        roles = new ArrayList<Role>();
        roles.add(Role.CUPIDON);
        roles.add(Role.LITTLE_GIRL);
        roles.add(Role.WITCH);

        Random r = new Random();

        // Si y'a amoureux, les désigner aléatoirement
        if (lovers) {
            int lover1 = r.nextInt(players.size());
            int lover2 = r.nextInt(players.size());
            while (lover2 == lover1) {
                lover2 = r.nextInt(players.size());
            }
            players.get(lover1).setLover(true);
            players.get(lover2).setLover(true);
        }

        // Choisir un nombre de loups en fonction du nombre de joueurs (environ 1/3)
        int numberOfWerewolves = players.size() / 3;
        for (int i = 0; i < numberOfWerewolves; ++i) {
            players.get(r.nextInt(players.size())).setRole(Role.WEREWOLF);
        }

        // Pour chaque role spécial restant, affecter à un villageois aléatoirement
        for (Role role : roles) {
            if (role == Role.CUPIDON || role == Role.HUNTER || role == Role.LITTLE_GIRL || role == Role.SEER || role == Role.WITCH) {
                Player p = players.get(r.nextInt(players.size()));
                while (p.getRole() != Role.VILLAGER) {
                    p = players.get(r.nextInt(players.size()));
                }
                p.setRole(role);
            }
        }

        df.setMaximumFractionDigits(1);

        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (RoleAttributionActivity.this.iterator.hasNext() || currentState == State.NAME_DISPLAYED) {
                        displayNext();
                    } else {
                        Intent firstTurnActivity = new Intent(RoleAttributionActivity.this, FirstTurnActivity.class);
                        startActivity(firstTurnActivity);

                        finish();
                    }
                }
            });
        }

        displayNext();
    }

    public void displayNext() {
        switch (currentState) {
            case NAME_DISPLAYED:
                // Timer puis afficher carte
                timerText.setVisibility(View.VISIBLE);
                playersRoleImage.setVisibility(View.INVISIBLE);
                nextButton.setEnabled(false);

                new CountDownTimer(2 * 1000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerText.setText(df.format((float) millisUntilFinished / 1000) + " s");
                    }

                    @Override
                    public void onFinish() {
                        timerText.setVisibility(View.INVISIBLE);
                        playersRoleImage.setVisibility(View.VISIBLE);
                        nextButton.setEnabled(true);
                        playersRoleNameText.setText(getResources().getString(currentPlayer.getRole().getNameRes()));
                        playersRoleImage.setImageDrawable(getResources().getDrawable(currentPlayer.getRole().getDrawableRes()));
                        currentState = State.CARD_DISPLAYED;
                    }
                }.start();
                break;
            case CARD_DISPLAYED:
                // Cacher et afficher juste nouveau nom
                currentPlayer = iterator.next();

                playersNameText.setText(currentPlayer.getName());
                playersRoleNameText.setText("");
                playersRoleImage.setImageDrawable(null);
                playersRoleImage.setVisibility(View.INVISIBLE);

                currentState = State.NAME_DISPLAYED;
                break;
        }
    }
}
