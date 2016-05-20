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
import fr.esgi.davidghetto.loupgarou.adapter.PlayersVoteAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class VoteActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_VOTE = 1;

    private ListViewCompat playerListView;
    private Button confirmButton;
    private PlayersVoteAdapter playersVoteAdapter;

    public ArrayList<Role> roles;
    public ArrayList<Player> players;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        players = getIntent().getExtras().getParcelableArrayList("players");
//        players = new ArrayList<Player>();
//        players.add(new Player("bite"));
//        players.add(new Player("rteg"));
//        players.add(new Player("hgnfn"));
//        players.add(new Player("sdf"));
//        players.add(new Player("yuiuyo"));
//        players.add(new Player("iopiop"));
//        players.add(new Player("bibn,bnte"));

        playersVoteAdapter = new PlayersVoteAdapter(this);
        boolean captain = false;
        for (Player p : players) {
            if (p.isCaptain()) {
                captain = true;
                break;
            }
        }
        playersVoteAdapter.setNumberOfVotesMax(players.size() + (captain ? 1 : 0));
        playersVoteAdapter.setVoteActivity(this);
        playerListView = (ListViewCompat) findViewById(R.id.vote_players_list_view);
        playerListView.setAdapter(playersVoteAdapter);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = playersVoteAdapter.getItem(position);
                // Toast.makeText(VoteActivity.this, "PlayerGame clicked : " + player.getName(), Toast.LENGTH_LONG).show();
            }
        });

        for (Player player : players) {
            player.setVoteScore(0);
            playersVoteAdapter.add(player);
        }

        confirmButton = (Button) findViewById(R.id.vote_confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le player qui a le plus de votes
                int maxScore = -1;
                Player chosenPlayer = null;
                for (Player p : players) {
                    if (p.getVoteScore() > maxScore) {
                        maxScore = p.getVoteScore();
                        chosenPlayer = p;
                    }
                }

                System.out.println(chosenPlayer);

                Intent result = new Intent();
                result.putExtra("vote", chosenPlayer);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    public void scoreChanged() {
        if (playersVoteAdapter.getNumberOfVotesMax() == playersVoteAdapter.getNumberOfVotes()) {
            confirmButton.setEnabled(true);
        } else {
            confirmButton.setEnabled(false);
        }
    }
}
