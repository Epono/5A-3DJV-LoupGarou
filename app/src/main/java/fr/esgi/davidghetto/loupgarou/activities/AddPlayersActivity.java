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

public class AddPlayersActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final int NB_MIN_PLAYER = 7;

    private ListViewCompat playerListView;
    private EditText playerEditText;
    private Button playerAddButton;
    private CheckBox editModeCheckbox;
    private Button startGameButton;
<<<<<<< HEAD

    public PlayersAdapter playersAdapter;
=======
>>>>>>> b83a4bf98103a6a5df88d40f78fb914cb3e72784

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

        if (playerAddButton != null) {
            playerAddButton.setOnClickListener(this);
        }

        if (editModeCheckbox != null) {
            editModeCheckbox.setOnCheckedChangeListener(this);
        }

        if(startGameButton != null){
            startGameButton.setOnClickListener(this);
        }

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
        } else if(v == startGameButton){
            if(playersAdapter.getCount() >= NB_MIN_PLAYER){
                Intent toRolePickActivityIntent = new Intent(this, RoleSelectionActivity.class);
                ArrayList<Player> players = new ArrayList<Player>();
                for(int i = 0; i < playersAdapter.getCount(); i++){
                    players.add(playersAdapter.getItem(i));
                }
                toRolePickActivityIntent.putParcelableArrayListExtra("players", players);
                startActivity(toRolePickActivityIntent);
                finish();
            }
        }

       /* if(playersAdapter.getCount() >= 5)
        {*/
            startGameButton.setEnabled(true);
        /*}
        else
        {
            startGameButton.setEnabled(false);
        }*/

        if(v == startGameButton)
        {
            Intent playIntent = new Intent(this, RoleSelectionActivity.class);
            startActivity(playIntent);
            finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        playersAdapter.setEditMode(isChecked);
    }
}
