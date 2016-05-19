package fr.esgi.davidghetto.loupgarou.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.esgi.davidghetto.loupgarou.R;

/**
 * Created by Lucas on 19/05/2016.
 */
public class NormalDayTurnActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_normal_turn);
    }

    @Override
    public void onClick(View v)
    {

    }
}
