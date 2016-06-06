package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.activities.generic.PickActivity;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;
import fr.esgi.davidghetto.loupgarou.utils.RequestCodes;

public class FirstTurnActivity extends AppCompatActivity implements View.OnClickListener {

    public int cpt;
    private Button NextTurn;
    public ArrayList<Player> players;
    public ArrayList<Role> activeRoles;
    public TextView first_turn_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_turn);

        players = getIntent().getExtras().getParcelableArrayList("players");
        activeRoles = getIntent().getExtras().getParcelableArrayList("roles");
        first_turn_text = (TextView) findViewById(R.id.first_turn_text);
        first_turn_text.setText("Cupidon se réveille et désigne deux amoureux"); // Choose your captain, Cupidon se réveille ..., franglais powaa

        NextTurn = (Button) findViewById(R.id.next_to_first_turn);
        if (NextTurn != null)
            NextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        cpt++;
        if (v == NextTurn && cpt == 1) {
            Intent lover1Intent = new Intent(this, PickActivity.class);
            lover1Intent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, players);
            startActivityForResult(lover1Intent, RequestCodes.REQUEST_CODE_PICK);
        } else if (v == NextTurn && cpt == 2) { // Fonctionnel mais vous auriez pu faire une liste avec deux sélections.
            // En rajoutant : android:choiceMode="multipleChoice" à votre listView dans le xml. + Le comportement adéquat
            Intent lover1Intent = new Intent(this, PickActivity.class);
            lover1Intent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, players);
            ArrayList<Player> playersTemp = new ArrayList<Player>();
            for (Player player : players) {
                if (!player.isLover())
                    playersTemp.add(player);
            }
            lover1Intent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, playersTemp);
            startActivityForResult(lover1Intent, RequestCodes.REQUEST_CODE_PICK);
        } else if (v == NextTurn && cpt == 3) {
            first_turn_text.setText("Les amoureux se réveillent et se regardent");
        } else if (v == NextTurn && cpt == 4) {
            Intent playIntent = new Intent(this, NormalTurnActivity.class);
            playIntent.putParcelableArrayListExtra(ExtraKeys.PLAYERS_LIST_KEY, players);
            playIntent.putParcelableArrayListExtra(ExtraKeys.ROLES_LIST_KEY, activeRoles);
            startActivity(playIntent);

            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCodes.REQUEST_CODE_PICK:
                if (resultCode == RESULT_OK) {
                    // afficher le lover
                    Player p = data.getExtras().getParcelable("pick");
                    for (Player temp : players) {
                        if (p.getName().equals(temp.getName())) {
                            temp.setLover(true);
                        }
                    }
                    if (cpt == 1) {
                        first_turn_text.setText("Premier amoureux : " + p.getName());
                    } else {
                        first_turn_text.setText("Deuxième amoureux : " + p.getName());
                    }
                } else {
                    System.out.println("ERREUR, pas de lover ! AAAAAAAAAAAAAAAA");
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
