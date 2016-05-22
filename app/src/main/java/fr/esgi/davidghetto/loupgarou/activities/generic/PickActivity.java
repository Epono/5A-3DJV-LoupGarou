package fr.esgi.davidghetto.loupgarou.activities.generic;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersPickAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;
import fr.esgi.davidghetto.loupgarou.utils.Helper;

public class PickActivity extends AppCompatActivity {

    private PlayersPickAdapter playersPickAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        ArrayList<Player> players;
        String header;

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(ExtraKeys.PLAYERS_LIST_KEY)) {
            players = getIntent().getExtras().getParcelableArrayList(ExtraKeys.PLAYERS_LIST_KEY);
        } else {
            players = Helper.getPlayers(false);
        }

        if (extras != null && extras.containsKey(ExtraKeys.PICK_ACTIVITY_HEADER)) {
            header = getIntent().getExtras().getString(ExtraKeys.PICK_ACTIVITY_HEADER);
        } else {
            header = "Pick time !";
        }
        TextView headerTextView = (TextView) findViewById(R.id.pick_header);
        if (headerTextView != null) {
            headerTextView.setText(header);
        }

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
