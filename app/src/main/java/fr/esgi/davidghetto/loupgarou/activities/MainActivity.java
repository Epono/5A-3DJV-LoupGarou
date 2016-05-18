package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.esgi.davidghetto.loupgarou.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.play_button);
        if(playButton != null)
            playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == playButton){
            Intent playIntent = new Intent(this, AddPlayersActivity.class);
            startActivity(playIntent);

            finish();
        }
    }
}
