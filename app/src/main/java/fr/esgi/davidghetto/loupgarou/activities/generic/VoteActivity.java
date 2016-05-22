package fr.esgi.davidghetto.loupgarou.activities.generic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.adapter.PlayersVoteAdapter;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;
import fr.esgi.davidghetto.loupgarou.utils.Helper;

public class VoteActivity extends AppCompatActivity {

    private Button confirmButton;
    private PlayersVoteAdapter playersVoteAdapter;

    public ArrayList<Player> players;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        String header;

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(ExtraKeys.PLAYERS_LIST_KEY)) {
            players = getIntent().getExtras().getParcelableArrayList(ExtraKeys.PLAYERS_LIST_KEY);
        } else {
            players = Helper.getPlayers(false);
        }
        if (extras != null && extras.containsKey(ExtraKeys.VOTE_ACTIVITY_HEADER)) {
            header = getIntent().getExtras().getString(ExtraKeys.VOTE_ACTIVITY_HEADER);
        } else {
            header = "Vote time !";
        }
        TextView headerTextView = (TextView) findViewById(R.id.vote_header);
        if (headerTextView != null) {
            headerTextView.setText(header);
        }

        boolean captain = false;
        for (Player p : players) {
            if (p.isCaptain()) {
                captain = true;
                break;
            }
        }

        playersVoteAdapter = new PlayersVoteAdapter(this);
        playersVoteAdapter.setNumberOfVotesMax(players.size() + (captain ? 1 : 0));
        playersVoteAdapter.setVoteActivity(this);

        ListViewCompat playerListView = (ListViewCompat) findViewById(R.id.vote_players_list_view);
        if (playerListView != null) {
            playerListView.setAdapter(playersVoteAdapter);
        }

        for (Player player : players) {
            player.setVoteScore(0);
            playersVoteAdapter.add(player);
        }

        confirmButton = (Button) findViewById(R.id.vote_confirm_button);
        if (confirmButton != null) {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int maxScore = -1;
                    Player chosenPlayer = null;
                    for (Player p : players) {
                        if (p.getVoteScore() > maxScore) {
                            maxScore = p.getVoteScore();
                            chosenPlayer = p;
                        }
                    }

                    Intent result = new Intent();
                    result.putExtra(ExtraKeys.VOTE_ACTIVITY_KEY, chosenPlayer);
                    setResult(RESULT_OK, result);
                    finish();
                }
            });
        }
    }

    public void scoreChanged() {
        if (playersVoteAdapter.getNumberOfVotesMax() == playersVoteAdapter.getNumberOfVotes()) {
            confirmButton.setEnabled(true);
        } else {
            confirmButton.setEnabled(false);
        }
    }
}
