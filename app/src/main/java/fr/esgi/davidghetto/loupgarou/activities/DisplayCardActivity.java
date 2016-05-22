package fr.esgi.davidghetto.loupgarou.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;

public class DisplayCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button displayCardButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);

        TextView displayCardTextView = (TextView) findViewById(R.id.display_card_text_view);
        ImageView displayCardImageView = (ImageView) findViewById(R.id.display_card_image_view);
        displayCardButton = (Button) findViewById(R.id.display_card_button);

        Player pickedPlayer = getIntent().getExtras().getParcelable(ExtraKeys.DISPLAY_PLAYER_KEY);
        if (pickedPlayer != null) {
            displayCardTextView.setText(getResources().getString(pickedPlayer.getRole().getNameRes()));
            displayCardImageView.setImageDrawable(getResources().getDrawable(pickedPlayer.getRole().getDrawableRes()));
        }

        if (displayCardButton != null) {
            displayCardButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == displayCardButton) {
            finish();
        }
    }
}
