package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class FirstTurnActivity extends AppCompatActivity implements View.OnClickListener{

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
        first_turn_text.setText("Cupidon se réveille et désigne deux amoureux");

        NextTurn = (Button) findViewById(R.id.next_to_first_turn);
        if(NextTurn != null)
            NextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        cpt++;
        if(v == NextTurn && cpt==1)
        {
            first_turn_text.setText("Les amoureux se réveille et se regardent");
        }
        else if(v == NextTurn && cpt==2)
        {
                Intent playIntent = new Intent(this, NormalNightTurnActivity.class);
                playIntent.putParcelableArrayListExtra("players", players);
                playIntent.putParcelableArrayListExtra("roles", activeRoles);
                startActivity(playIntent);

                finish();
        }
    }

}
