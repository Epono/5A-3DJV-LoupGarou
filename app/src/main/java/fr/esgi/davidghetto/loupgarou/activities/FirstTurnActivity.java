package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.esgi.davidghetto.loupgarou.R;

public class FirstTurnActivity extends AppCompatActivity implements View.OnClickListener{


    private Button NextTurn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_turn);

        NextTurn = (Button) findViewById(R.id.next_to_first_turn);
        if(NextTurn != null)
            NextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == NextTurn){
            Intent playIntent = new Intent(this, NormalNightTurnActivity.class);
            startActivity(playIntent);

            finish();
        }
    }
}
