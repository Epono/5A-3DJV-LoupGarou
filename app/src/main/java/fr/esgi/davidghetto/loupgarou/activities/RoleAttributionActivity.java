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

    private TextView playersNameText;
    private TextView playersRoleNameText;
    private ImageView playersRoleImage;
    private Button nextButton;

    public Iterator<Player> iterator;
    public ArrayList<Player> players;
    public ArrayList<Role> roles;
    private TextView timerText;

    DecimalFormat df = new DecimalFormat();

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
            players.get(r.nextInt(players.size())).setLover(true);
        }

        // Choisir un nombre de loups en fonction du nombre de joueurs (environ 1/3)
        int numberOfWerewolves = players.size() / 3;
        for (int i = 0; i < numberOfWerewolves; ++i) {
            players.get(r.nextInt(players.size())).setRole(Role.WEREWOLF);
        }

        // Pour chaque role spécial restant, affecter un joueur aléatoirement
        for (Role role : roles) {
            if (role == Role.CUPIDON || role == Role.HUNTER || role == Role.LITTLE_GIRL || role == Role.SEER || role == Role.WITCH)
                players.get(r.nextInt(players.size())).setRole(role);
        }

        df.setMaximumFractionDigits(1);

        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (RoleAttributionActivity.this.iterator.hasNext()) {
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
        final Player player = iterator.next();

        playersNameText.setText(player.getName());
        playersRoleNameText.setText("");
        playersRoleImage.setImageDrawable(null);

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
                playersRoleNameText.setText(getResources().getString(player.getRole().getNameRes()));
                playersRoleImage.setImageDrawable(getResources().getDrawable(player.getRole().getDrawableRes()));
            }
        }.start();
    }
}
