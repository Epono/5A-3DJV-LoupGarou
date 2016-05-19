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

import java.util.ArrayList;
import java.util.Iterator;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;

public class RoleAttributionActivity extends AppCompatActivity {

    private TextView playersNameText;
    private TextView playersRoleNameText;
    private ImageView playersRoleImage;
    private Button nextButton;

    public Iterator<Player> iterator;
    public ArrayList<Player> players;
    private TextView timerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_attribution);

        playersNameText = (TextView) findViewById(R.id.role_attribution_player_s_name);
        playersRoleNameText = (TextView) findViewById(R.id.role_attribution_player_s_role_text);
        playersRoleImage = (ImageView) findViewById(R.id.role_attribution_player_s_role_image);
        nextButton = (Button) findViewById(R.id.role_attribution_next);
        timerText = (TextView) findViewById(R.id.role_attribution_timer);

        players = getIntent().getExtras().getParcelableArrayList("players");
        iterator = players.iterator();


        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (RoleAttributionActivity.this.iterator.hasNext()) {

                        final Player player = iterator.next();

                        playersNameText.setText(player.getName());
                        playersRoleNameText.setText("");
                        playersRoleImage.setImageDrawable(null);

                        timerText.setVisibility(View.VISIBLE);
                        playersRoleImage.setVisibility(View.INVISIBLE);

                        new CountDownTimer(5 * 60000, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timerText.setText(millisUntilFinished / 1000 + " s");
                            }

                            @Override
                            public void onFinish() {
                                timerText.setVisibility(View.INVISIBLE);
                                playersRoleImage.setVisibility(View.VISIBLE);
                                playersRoleNameText.setText(getResources().getString(player.getRole().getName()));
                                playersRoleImage.setImageDrawable(getResources().getDrawable(player.getRole().getDrawableRes()));
                            }
                        };
                    } else {
                        Intent firstTurnActivity = new Intent(RoleAttributionActivity.this, FirstTurnActivity.class);
                        startActivity(firstTurnActivity);

                        finish();
                    }
                }
            });
        }
    }
}
