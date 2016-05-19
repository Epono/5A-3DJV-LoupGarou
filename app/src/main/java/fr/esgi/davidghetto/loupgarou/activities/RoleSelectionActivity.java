package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startGameButton;
    private ArrayList<Player> players;
    private ArrayList<Role> roles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_selection);

        startGameButton = (Button) findViewById(R.id.launch_game_button);
        if(startGameButton != null)
            startGameButton.setOnClickListener(this);

        players = getIntent().getExtras().getParcelableArrayList("players");
        roles = new ArrayList<Role>();
    }

    @Override
    public void onClick(View v) {
        if(v == startGameButton){
            Intent toRoleAttributionIntent = new Intent(this, RoleAttributionActivity.class);
            toRoleAttributionIntent.putParcelableArrayListExtra("players", players);
            toRoleAttributionIntent.putParcelableArrayListExtra("roles", roles);
            startActivity(toRoleAttributionIntent);
            finish();
        }
    }
}
