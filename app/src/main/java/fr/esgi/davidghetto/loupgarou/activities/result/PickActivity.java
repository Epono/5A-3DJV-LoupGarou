package fr.esgi.davidghetto.loupgarou.activities.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersPickAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;

public class PickActivity extends AppCompatActivity {

    private PlayersPickAdapter playersPickAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        ArrayList<Player> players = getIntent().getExtras().getParcelableArrayList(ExtraKeys.PLAYERS_LIST_KEY);

        playersPickAdapter = new PlayersPickAdapter(this);

        ListViewCompat playerListView = (ListViewCompat) findViewById(R.id.pick_players_list_view);
        if (playerListView != null) {
            playerListView.setAdapter(playersPickAdapter);
            playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Player player = playersPickAdapter.getItem(position);
                    Intent result = new Intent();
                    result.putExtra(ExtraKeys.PICK_ACTIVITY_KEY, player);
                    setResult(RESULT_OK, result);
                    finish();
                }
            });
        }

        for (Player player : players) {
            playersPickAdapter.add(player);
        }
    }
}
