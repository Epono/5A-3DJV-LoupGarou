package fr.esgi.davidghetto.loupgarou.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersGameInfoAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;
import fr.esgi.davidghetto.loupgarou.utils.Helper;

public class GameInfoActivity extends AppCompatActivity {

    private PlayersGameInfoAdapter playersGameInfoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        ArrayList<Player> players;

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(ExtraKeys.PLAYERS_LIST_KEY)) {
            players = getIntent().getExtras().getParcelableArrayList(ExtraKeys.PLAYERS_LIST_KEY);
        } else {
            players = Helper.getPlayers(true);
        }

        playersGameInfoAdapter = new PlayersGameInfoAdapter(this);

        ListViewCompat playerListView = (ListViewCompat) findViewById(R.id.game_info_list_view);
        if (playerListView != null) {
            playerListView.setAdapter(playersGameInfoAdapter);
        }

        for (Player player : players) {
            playersGameInfoAdapter.add(player);
        }

        CheckBox gameMasterCheckbox = (CheckBox) findViewById(R.id.game_info_game_master_checkbox);
        if (gameMasterCheckbox != null) {
            gameMasterCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    playersGameInfoAdapter.setGameMasterMode(isChecked);
                }
            });
        }
    }
}
