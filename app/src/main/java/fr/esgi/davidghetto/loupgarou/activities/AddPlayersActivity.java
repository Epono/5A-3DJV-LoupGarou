package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class AddPlayersActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final int NB_MIN_PLAYER = 7;
    private static final int ROLE_REQUEST_CODE = 1;

    public static final String ROLE_LIST_KEY = "roles";
    public static final String PLAYER_LIST_KEY = "players";

    private ListViewCompat playerListView;
    private EditText playerEditText;
    private CheckBox editModeCheckbox;
    private Button playerAddButton;
    private Button startGameButton;
    private Button chooseRoleButton;
    private PlayersAdapter playersAdapter;

    private ArrayList<Player> players;
    private ArrayList<Role> roles;

    private boolean rolesChosen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerListView = (ListViewCompat) findViewById(R.id.player_list_view);
        playerEditText = (EditText) findViewById(R.id.player_add_text);
        playerAddButton = (Button) findViewById(R.id.button_add_player);
        startGameButton = (Button) findViewById(R.id.button_start_game);
        editModeCheckbox = (CheckBox) findViewById(R.id.edit_player_list_button);
        startGameButton = (Button) findViewById(R.id.button_start_game);
        chooseRoleButton = (Button) findViewById(R.id.button_choose_role);

        players = new ArrayList<>();

        if (playerAddButton != null)
            playerAddButton.setOnClickListener(this);
        if (editModeCheckbox != null)
            editModeCheckbox.setOnCheckedChangeListener(this);
        if(startGameButton != null)
            startGameButton.setOnClickListener(this);
        if(chooseRoleButton != null)
            chooseRoleButton.setOnClickListener(this);

        playersAdapter = new PlayersAdapter(this);
        playerListView.setAdapter(playersAdapter);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = playersAdapter.getItem(position);
                Toast.makeText(AddPlayersActivity.this, "PlayerGame clicked : " + player.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == playerAddButton) {
            String playerName = playerEditText.getText().toString();
            if (!playerName.isEmpty()) {
                playersAdapter.add(new Player(playerName));
                playerEditText.getText().clear();
            }
            if(playersAdapter.getCount() >= NB_MIN_PLAYER)
                startGameButton.setEnabled(true);
        } else if(v == chooseRoleButton){
            Intent toRolePickActivityIntent = new Intent(this, RoleSelectionActivity.class);
            startActivityForResult(toRolePickActivityIntent, ROLE_REQUEST_CODE);
        } else if(v == startGameButton){
            if(rolesChosen) {
                Intent toRoleAttributionActivity = new Intent(this, RoleAttributionActivity.class);
                for (int i = 0; i < playersAdapter.getCount(); i++) {
                    players.add(playersAdapter.getItem(i));
                }
                toRoleAttributionActivity.putParcelableArrayListExtra(PLAYER_LIST_KEY, players);
                toRoleAttributionActivity.putParcelableArrayListExtra(ROLE_LIST_KEY, roles);
                startActivity(toRoleAttributionActivity);
                finish();
            }
            else
                Toast.makeText(this, "Choose Roles before starting the game", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        playersAdapter.setEditMode(isChecked);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ROLE_REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    roles = data.getParcelableArrayListExtra(ROLE_LIST_KEY);
                    if(roles != null)
                        rolesChosen = roles.size() > 0;
                } else {
                    Toast.makeText(this, "Walah t'as fait quoi là ?", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
