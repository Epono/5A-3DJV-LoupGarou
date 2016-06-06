package fr.esgi.davidghetto.loupgarou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.activities.generic.VoteActivity;
import fr.esgi.davidghetto.loupgarou.models.Player;

public class PlayersVoteAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.item_player_vote;

    /* Joli hack mais vous perdez tout l'intérêt du recyclage vu que vous garder une référence vers
    les éléments vous auriez pu juste mettre un compteur et des booleens.
    De plus si vous avez suffisemment d'éléments pour que la liste scroll. Button sera attribué à plusieurs
     Players, ce qui risque de faire n'imp.

    Par exemple :
    private boolean canAddPlayer = true;

    puis dans le getView(){ ... plusButton.setEnable(canAddPlayer); }
    Et au moment on un changement se produit, appeler notifySetDataChanged(); Ce qui force l'actualisation
    de vos éléments. */

    private HashMap<Player, Button> plusButtons;
    private HashMap<Player, Button> minusButtons;
    private HashMap<Player, TextView> textViews;

    private HashMap<Button, Player> buttonsToPlayer;

    private int numberOfVotesMax;
    private int numberOfVotes;

    private VoteActivity voteActivity;

    public PlayersVoteAdapter(Context context) {
        super(context, layoutResource);
        plusButtons = new HashMap<>();
        minusButtons = new HashMap<>();
        textViews = new HashMap<>();

        buttonsToPlayer = new HashMap<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        numberOfVotes = 0;

        final Player player = getItem(position);

        TextView playerName = (TextView) convertView.findViewById(R.id.item_player_name_text_view);
        playerName.setText(player.getName());

        TextView textView = (TextView) convertView.findViewById(R.id.item_player_score);
        textView.setText("" + player.getVoteScore());
        textViews.put(player, textView);

        Button plusButton = (Button) convertView.findViewById(R.id.item_player_vote_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScore(v, 1);
            }
        });
        plusButtons.put(player, plusButton);
        buttonsToPlayer.put(plusButton, player);

        Button minusButton = (Button) convertView.findViewById(R.id.item_player_vote_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScore(v, -1);
            }
        });
        minusButtons.put(player, minusButton);
        buttonsToPlayer.put(minusButton, player);

        return convertView;
    }

    public void changeScore(View v, int modifier) {
        Player p = buttonsToPlayer.get(v);

        p.setVoteScore(p.getVoteScore() + modifier);
        textViews.get(p).setText("" + p.getVoteScore());

        numberOfVotes += modifier;

        if (numberOfVotes == numberOfVotesMax) {
            // désactiver tous les +
            for (Player temp : plusButtons.keySet()) {
                plusButtons.get(temp).setEnabled(false);
            }
        } else if (numberOfVotes == 0) {
            // désactiver tous les -
            for (Player temp : minusButtons.keySet()) {
                minusButtons.get(temp).setEnabled(false);
            }
        } else {
            for (Player temp : minusButtons.keySet()) {
                minusButtons.get(temp).setEnabled(temp.getVoteScore() != 0);
            }
            // activer les +
            for (Player temp : plusButtons.keySet()) {
                plusButtons.get(temp).setEnabled(true);
            }
        }

        voteActivity.scoreChanged();
    }

    public void setNumberOfVotesMax(int numberOfVotesMax) {
        this.numberOfVotesMax = numberOfVotesMax;
    }

    public int getNumberOfVotesMax() {
        return numberOfVotesMax;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setVoteActivity(VoteActivity voteActivity) {
        this.voteActivity = voteActivity;
    }
}
