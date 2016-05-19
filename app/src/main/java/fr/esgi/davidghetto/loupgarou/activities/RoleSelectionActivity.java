package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.esgi.davidghetto.loupgarou.R;

/**
 * Created by Lucas on 18/05/2016.
 */
public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener{


    private Button startGameButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_selection);

        startGameButton = (Button) findViewById(R.id.launch_game_button);
        if(startGameButton != null)
            startGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == startGameButton){
            Intent toRoleAttributionIntent = new Intent(this, RoleAttributionActivity.class);
            startActivity(toRoleAttributionIntent);
            finish();
        }
    }
}
