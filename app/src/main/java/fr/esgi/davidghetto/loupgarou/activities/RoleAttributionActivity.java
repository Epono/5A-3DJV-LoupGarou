package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;

public class RoleAttributionActivity extends AppCompatActivity {

    private TextView playersNameText;
    private TextView playersRoleNameText;
    private ImageView playersRoleImage;
    private Button nextButton;
    public Iterator<Player> iterator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_attribution);

        playersNameText = (TextView) findViewById(R.id.role_attribution_player_s_name);
        playersRoleNameText = (TextView) findViewById(R.id.role_attribution_player_s_role_text);
        playersRoleImage = (ImageView) findViewById(R.id.role_attribution_player_s_role_image);
        nextButton = (Button) findViewById(R.id.role_attribution_next);

        List<Player> players /*= getIntent().getExtras()*/;

        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (RoleAttributionActivity.this.iterator.hasNext()) {
                        Player player = iterator.next();

                        playersNameText.setText(player.getName());
                        playersRoleNameText.setText(getResources().getString(player.getRole().getName()));
                        playersRoleImage.setImageDrawable(player.getRole().getDrawableRes());


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
