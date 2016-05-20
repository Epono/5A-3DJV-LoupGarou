package fr.esgi.davidghetto.loupgarou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button confirmRolesButton;
    private Button useCardButton;
    private TextView titleCardTextView;
    private TextView descriptionCardTextView;

    private int idPickedRole;
    private int idConfirmedRole;

    private ArrayList<Role> roles;

    private ImageView cupidonImageView;
    private ImageView cupidonChosenImageView;
    private ImageView voyanteImageView;
    private ImageView voyanteChosenImageView;
    private ImageView hunterImageView;
    private ImageView hunterChosenImageView;
    private ImageView litteGirlImageView;
    private ImageView litteGirlChosenImageView;
    private ImageView witchImageView;
    private ImageView witchChosenImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_selection);

        confirmRolesButton = (Button) findViewById(R.id.confirm_roles_button);
        useCardButton = (Button) findViewById(R.id.use_role_card_button);

        titleCardTextView = (TextView) findViewById(R.id.title_card_text);
        descriptionCardTextView = (TextView) findViewById(R.id.card_description_text);

        cupidonImageView = (ImageView) findViewById(R.id.cupidon_image_view);
        cupidonChosenImageView = (ImageView) findViewById(R.id.cupidon_chosen_image_view);
        voyanteImageView = (ImageView) findViewById(R.id.voyante_image_view);
        voyanteChosenImageView = (ImageView) findViewById(R.id.voyante_chosen_image_view);
        hunterImageView = (ImageView) findViewById(R.id.hunter_image_view);
        hunterChosenImageView = (ImageView) findViewById(R.id.hunter_chosen_image_view);
        litteGirlImageView = (ImageView) findViewById(R.id.little_girl_image_view);
        litteGirlChosenImageView = (ImageView) findViewById(R.id.little_girl_chosen_image_view);
        witchImageView = (ImageView) findViewById(R.id.witch_image_view);
        witchChosenImageView = (ImageView) findViewById(R.id.witch_chosen_image_view);

        if(confirmRolesButton != null)
            confirmRolesButton.setOnClickListener(this);
        if(useCardButton != null)
            useCardButton.setOnClickListener(this);

        if(cupidonImageView != null)
            cupidonImageView.setOnClickListener(this);
        if(cupidonChosenImageView != null)
            cupidonChosenImageView.setOnClickListener(this);
        if(voyanteImageView != null)
            voyanteImageView.setOnClickListener(this);
        if(voyanteChosenImageView != null)
            voyanteChosenImageView.setOnClickListener(this);
        if(hunterImageView != null)
            hunterImageView.setOnClickListener(this);
        if(hunterChosenImageView != null)
            hunterChosenImageView.setOnClickListener(this);
        if(litteGirlImageView != null)
            litteGirlImageView.setOnClickListener(this);
        if(litteGirlChosenImageView != null)
            litteGirlChosenImageView.setOnClickListener(this);
        if(witchImageView != null)
            witchImageView.setOnClickListener(this);
        if(witchChosenImageView != null)
            witchChosenImageView.setOnClickListener(this);

        roles = new ArrayList<Role>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_roles_button:
                Intent confirmRolesIntent = new Intent();

                confirmRolesIntent.putParcelableArrayListExtra(AddPlayersActivity.ROLE_LIST_KEY, roles);
                setResult(RESULT_OK, confirmRolesIntent);
                finish();
                break;
            case R.id.use_role_card_button:
                findViewById(idConfirmedRole).setVisibility(View.VISIBLE);
                findViewById(idPickedRole).setVisibility(View.GONE);

                addCardToList(idPickedRole);
                resetValues();
                break;
            case R.id.cupidon_image_view:
                idPickedRole = R.id.cupidon_image_view;
                idConfirmedRole = R.id.cupidon_chosen_image_view;

                titleCardTextView.setText(R.string.role_cupidon);
                descriptionCardTextView.setText(R.string.cupion_card_description);
                break;
            case R.id.cupidon_chosen_image_view:
                findViewById(R.id.cupidon_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.cupidon_chosen_image_view).setVisibility(View.GONE);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i) == Role.CUPIDON) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.voyante_image_view:
                idPickedRole = R.id.voyante_image_view;
                idConfirmedRole = R.id.voyante_chosen_image_view;

                titleCardTextView.setText(R.string.role_seer);
                descriptionCardTextView.setText(R.string.voyante_card_description);
                break;
            case R.id.voyante_chosen_image_view:
                findViewById(R.id.voyante_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.voyante_chosen_image_view).setVisibility(View.GONE);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i) == Role.SEER) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.hunter_image_view:
                idPickedRole = R.id.hunter_image_view;
                idConfirmedRole = R.id.hunter_chosen_image_view;

                titleCardTextView.setText(R.string.role_hunter);
                descriptionCardTextView.setText(R.string.hunter_card_description);
                break;
            case R.id.hunter_chosen_image_view:
                findViewById(R.id.hunter_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.hunter_chosen_image_view).setVisibility(View.GONE);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i) == Role.HUNTER) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.little_girl_image_view:
                idPickedRole = R.id.little_girl_image_view;
                idConfirmedRole = R.id.little_girl_chosen_image_view;

                titleCardTextView.setText(R.string.role_little_girl);
                descriptionCardTextView.setText(R.string.little_girl_card_description);
                break;
            case R.id.little_girl_chosen_image_view:
                findViewById(R.id.little_girl_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.little_girl_chosen_image_view).setVisibility(View.GONE);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i) == Role.LITTLE_GIRL) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.witch_image_view:
                idPickedRole = R.id.witch_image_view;
                idConfirmedRole = R.id.witch_chosen_image_view;

                titleCardTextView.setText(R.string.role_witch);
                descriptionCardTextView.setText(R.string.witch_card_description);
                break;
            case R.id.witch_chosen_image_view:
                findViewById(R.id.witch_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.witch_chosen_image_view).setVisibility(View.GONE);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i) == Role.WITCH) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    private void resetValues() {
        idPickedRole = 0;
        idConfirmedRole = 0;
        titleCardTextView.setText(null);
        descriptionCardTextView.setText(null);
    }

    private void addCardToList(int id) {
        switch (id){
            case R.id.cupidon_image_view:
                roles.add(Role.CUPIDON);
                break;
            case R.id.voyante_image_view:
                roles.add(Role.SEER);
                break;
            case R.id.hunter_image_view:
                roles.add(Role.SEER);
                break;
            case R.id.little_girl_image_view:
                roles.add(Role.SEER);
                break;
            case R.id.witch_image_view:
                roles.add(Role.SEER);
                break;
            default:
                break;
        }
    }
}
