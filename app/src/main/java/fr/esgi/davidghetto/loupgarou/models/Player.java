package fr.esgi.davidghetto.loupgarou.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private String name;
    private Role role;
    private boolean isAlive;
    private boolean isLover;

    public Player(String name) {
        this.name = name;
        role = Role.VILLAGER;
        isAlive = true;
        isLover = false;
    }

    protected Player(Parcel in) {
        name = in.readString();
        role = (Role) in.readValue(Role.class.getClassLoader());
        isAlive = in.readByte() != 0x00;
        isLover = in.readByte() != 0x00;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAlive() { return isAlive; }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isLover() {
        return isLover;
    }

    public void setLover(boolean lover) {
        isLover = lover;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(role);
        dest.writeByte((byte) (isAlive ? 0x01 : 0x00));
        dest.writeByte((byte) (isLover ? 0x01 : 0x00));
    }
}
