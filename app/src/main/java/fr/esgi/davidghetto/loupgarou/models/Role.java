package fr.esgi.davidghetto.loupgarou.models;

import android.os.Parcel;
import android.os.Parcelable;

import fr.esgi.davidghetto.loupgarou.R;

public enum Role implements Parcelable {
    WEREWOLF(R.drawable.loup_garou, R.string.role_werewolf_label),
    FORTUNE_TELLER(R.drawable.voyante, R.string.role_fortune_teller_label),
    VILLAGER(R.drawable.paysan, R.string.role_villager_label),
    CUPIDON(R.drawable.cupidon, R.string.role_cupid_label),
    HUNTER(R.drawable.hunter, R.string.role_hunter_label),
    WITCH(R.drawable.witch, R.string.role_witch_label),
    LITTLE_GIRL(R.drawable.little_girl, R.string.role_little_girl_label);

    private int drawableRes;
    private int nameRes;

    Role(int drawableRes, int nameRes) {
        this.drawableRes = drawableRes;
        this.nameRes = nameRes;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public int getNameRes() {
        return nameRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public void setNameRes(int nameRes) {
        this.nameRes = nameRes;
    }

    public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>() {
        public Role createFromParcel(Parcel in) {
            Role role = Role.values()[in.readInt()];
            role.setDrawableRes(in.readInt());
            role.setNameRes(in.readInt());
            return role;
        }

        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
        out.writeInt(drawableRes);
        out.writeInt(nameRes);
    }
}
