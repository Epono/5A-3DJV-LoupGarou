package fr.esgi.davidghetto.loupgarou.models;

import fr.esgi.davidghetto.loupgarou.R;

public enum Role {
    WEREWOLF(R.drawable.loup_garou, R.string.role_werewolf),
    SEER(R.drawable.loup_garou, R.string.role_seer),
    VILLAGER(R.drawable.loup_garou, R.string.role_villager);

    private final int drawableRes;
    private final int name;

    Role(int drawableRes, int name) {
        this.drawableRes = drawableRes;
        this.name = name;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public int getName() {
        return name;
    }
}
