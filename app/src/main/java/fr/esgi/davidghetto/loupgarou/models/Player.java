package fr.esgi.davidghetto.loupgarou.models;

public class Player {
    private String name;
    private Role role;

    public Player(String name) {
        this.name = name;
        role = Role.VILLAGER;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
