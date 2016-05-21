package fr.esgi.davidghetto.loupgarou.utils;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class Helper {

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();

        Player bill = new Player("Bill");
        bill.setRole(Role.VILLAGER);
        bill.setCaptain(true);

        Player georges = new Player("Georges");
        georges.setRole(Role.VILLAGER);
        georges.setLover(true);

        Player wolfgang = new Player("Wolfgang");
        wolfgang.setRole(Role.WEREWOLF);
        wolfgang.setLover(true);

        Player biatch = new Player("biatch");
        wolfgang.setRole(Role.LITTLE_GIRL);
        biatch.setAlive(false);

        Player jacqueline = new Player("Jacqueline");
        jacqueline.setRole(Role.WEREWOLF);
        jacqueline.setAlive(true);

        Player josiane = new Player("Josiance");
        josiane.setRole(Role.FORTUNE_TELLER);
        josiane.setAlive(false);

        Player pupute = new Player("Pupute");
        pupute.setRole(Role.VILLAGER);
        pupute.setAlive(true);

        players.add(bill);
        players.add(georges);
        players.add(wolfgang);
        players.add(biatch);
        players.add(jacqueline);
        players.add(josiane);
        players.add(pupute);

        return players;
    }
}
