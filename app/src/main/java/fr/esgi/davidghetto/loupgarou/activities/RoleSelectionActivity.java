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

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Role;
import fr.esgi.davidghetto.loupgarou.utils.ExtraKeys;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button confirmRolesButton;
    private Button useCardButton;
    private TextView titleCardTextView;
    private TextView descriptionCardTextView;

    private int idPickedRole;
    private int idConfirmedRole;

    private ArrayList<Role> roles;

    // Fonctionnel mais pas très opti en perf.
    // J'aurais ajouté les vues dynamiquement.
    // Ce qui permet même de faire passer la vue de en haut à en bas quand vous la sélectionnée
    // (Sans la recréer)
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

    // Attention, lorsque vous venez de selectionner un role ou lorsque vous les avez tous séléctionnés, sont affichés les textes par défaut.
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

        if (confirmRolesButton != null)
            confirmRolesButton.setOnClickListener(this);
        if (useCardButton != null)
            useCardButton.setOnClickListener(this);

        if (cupidonImageView != null)
            cupidonImageView.setOnClickListener(this);
        if (cupidonChosenImageView != null)
            cupidonChosenImageView.setOnClickListener(this);
        if (voyanteImageView != null)
            voyanteImageView.setOnClickListener(this);
        if (voyanteChosenImageView != null)
            voyanteChosenImageView.setOnClickListener(this);
        if (hunterImageView != null)
            hunterImageView.setOnClickListener(this);
        if (hunterChosenImageView != null)
            hunterChosenImageView.setOnClickListener(this);
        if (litteGirlImageView != null)
            litteGirlImageView.setOnClickListener(this);
        if (litteGirlChosenImageView != null)
            litteGirlChosenImageView.setOnClickListener(this);
        if (witchImageView != null)
            witchImageView.setOnClickListener(this);
        if (witchChosenImageView != null)
            witchChosenImageView.setOnClickListener(this);

        roles = new ArrayList<Role>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_roles_button:
                Intent confirmRolesIntent = new Intent();

                confirmRolesIntent.putParcelableArrayListExtra(ExtraKeys.ROLES_LIST_KEY, roles);
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
                initValues(R.id.cupidon_image_view, R.id.cupidon_chosen_image_view);

                titleCardTextView.setText(R.string.role_cupid_label);
                descriptionCardTextView.setText(R.string.role_cupid_card_description);
                break;
            case R.id.cupidon_chosen_image_view:
                // Oui ça marche mais vous faites le travail en double.
                // cupidonImageView.setVisibility(View.VISIBLE); vu que vous l'avez déjà récupéré.
                findViewById(R.id.cupidon_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.cupidon_chosen_image_view).setVisibility(View.GONE);
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i) == Role.CUPID) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.voyante_image_view:
                initValues(R.id.voyante_image_view, R.id.voyante_chosen_image_view);

                titleCardTextView.setText(R.string.role_fortune_teller_label);
                descriptionCardTextView.setText(R.string.role_fortune_teller_card_description);
                break;
            case R.id.voyante_chosen_image_view:
                findViewById(R.id.voyante_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.voyante_chosen_image_view).setVisibility(View.GONE);
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i) == Role.FORTUNE_TELLER) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.hunter_image_view:
                initValues(R.id.hunter_image_view, R.id.hunter_chosen_image_view);

                titleCardTextView.setText(R.string.role_hunter_label);
                descriptionCardTextView.setText(R.string.role_hunter_card_description);
                break;
            case R.id.hunter_chosen_image_view:
                findViewById(R.id.hunter_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.hunter_chosen_image_view).setVisibility(View.GONE);
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i) == Role.HUNTER) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.little_girl_image_view:
                initValues(R.id.little_girl_image_view, R.id.little_girl_chosen_image_view);

                titleCardTextView.setText(R.string.role_little_girl_label);
                descriptionCardTextView.setText(R.string.role_little_girl_card_description);
                break;
            case R.id.little_girl_chosen_image_view:
                findViewById(R.id.little_girl_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.little_girl_chosen_image_view).setVisibility(View.GONE);
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i) == Role.LITTLE_GIRL) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            case R.id.witch_image_view:
                initValues(R.id.witch_image_view, R.id.witch_chosen_image_view);

                titleCardTextView.setText(R.string.role_witch_label);
                descriptionCardTextView.setText(R.string.role_witch_card_description);
                break;
            case R.id.witch_chosen_image_view:
                findViewById(R.id.witch_image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.witch_chosen_image_view).setVisibility(View.GONE);
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i) == Role.WITCH) {
                        roles.remove(i);
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    private void initValues(int id, int idChosen) {
        idPickedRole = id;
        idConfirmedRole = idChosen;
        useCardButton.setEnabled(true);
    }

    private void resetValues() {
        idPickedRole = 0;
        idConfirmedRole = 0;
        useCardButton.setEnabled(false);
        titleCardTextView.setText(null);
        descriptionCardTextView.setText(null);
    }

    private void addCardToList(int id) {
        switch (id) {
            case R.id.cupidon_image_view:
                roles.add(Role.CUPID);
                break;
            case R.id.voyante_image_view:
                roles.add(Role.FORTUNE_TELLER);
                break;
            case R.id.hunter_image_view:
                roles.add(Role.FORTUNE_TELLER);// Ah je me retrouve qu'avec des voyante. Je comprends pourquoi. Erreur ou pas implémenté ?
                break;
            case R.id.little_girl_image_view:
                roles.add(Role.FORTUNE_TELLER);
                break;
            case R.id.witch_image_view:
                roles.add(Role.FORTUNE_TELLER);
                break;
            default:
                break;
        }
    }
}
