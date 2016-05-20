package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersPickAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class PickActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PICK = 2;

    public static final String PICK_ACTIVITY_KEY = "pick";

    private ListViewCompat playerListView;
    private Button confirmButton;
    private PlayersPickAdapter playersPickAdapter;

    public ArrayList<Role> roles;
    public ArrayList<Player> players;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        players = getIntent().getExtras().getParcelableArrayList("players");
//        players = new ArrayList<Player>();
//        players.add(new Player("bite"));
//        players.add(new Player("rteg"));
//        players.add(new Player("hgnfn"));
//        players.add(new Player("sdf"));
//        players.add(new Player("yuiuyo"));
//        players.add(new Player("iopiop"));
//        players.add(new Player("bibn,bnte"));

        playersPickAdapter = new PlayersPickAdapter(this);
        playerListView = (ListViewCompat) findViewById(R.id.pick_players_list_view);
        playerListView.setAdapter(playersPickAdapter);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = playersPickAdapter.getItem(position);
                // Toast.makeText(VoteActivity.this, "PlayerGame clicked : " + player.getName(), Toast.LENGTH_LONG).show();
                System.out.println(player);
                Intent result = new Intent();
                result.putExtra(PICK_ACTIVITY_KEY, player);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        for (Player player : players) {
            playersPickAdapter.add(player);
        }
    }
}
