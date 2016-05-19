package fr.esgi.davidghetto.loupgarou.models;

import android.os.Parcel;
import android.os.Parcelable;

import fr.esgi.davidghetto.loupgarou.R;

public enum Role implements Parcelable {
    WEREWOLF(R.drawable.loup_garou, R.string.role_werewolf),
    SEER(R.drawable.voyante, R.string.role_seer),
    VILLAGER(R.drawable.paysan, R.string.role_villager),
    CUPIDON(R.drawable.cupidon, R.string.role_cupidon),
    HUNTER(R.drawable.hunter, R.string.role_hunter),
    WITCH(R.drawable.witch, R.string.role_witch),
    LITTLE_GIRL(R.drawable.little_girl, R.string.role_little_girl);

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
