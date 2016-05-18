package fr.esgi.davidghetto.loupgarou.models;

import fr.esgi.davidghetto.loupgarou.R;

public enum Role {
    WEREWOLF(R.drawable.loup_garou),
    SEER(R.drawable.loup_garou),
    VILLAGER(R.drawable.loup_garou);

    private final int drawableRes;

    Role(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public int getDrawableRes() {
        return drawableRes;
    }
}
