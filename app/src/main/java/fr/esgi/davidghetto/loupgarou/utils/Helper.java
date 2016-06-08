package fr.esgi.davidghetto.loupgarou.utils;

import java.util.ArrayList;

import fr.esgi.davidghetto.loupgarou.models.Player;
import fr.esgi.davidghetto.loupgarou.models.Role;

public class Helper {

    /* AHHHHH trop pratique, j'aurais voulu m'en rendre compte plus tôt.
     Bon reflexe cela dit. Je n'ai pas eu l'occasion de vous parler des flavors.
     Mais c'est un système qui vous permet de faire plusieurs version de l'appli. Genre en prod
     en dev, etc ... ne pouvoir spécifié que les différences. Dans votre cas, cette méthode aurait
     pu être comme ça en dev et vide en prod. Ce qui évite de laisser des outils de test en prod.
     Mais vu que je n'ai pas eu le temps. Très bien ! */
    public static ArrayList<Player> getPlayers(boolean iSeeDeadPeople) {
        ArrayList<Player> players = new ArrayList<>();

        Player bill = new Player("Bill");
        bill.setRole(Role.VILLAGER);
        bill.setCaptain(true);

        Player georges = new Player("Georges");
        georges.setRole(Role.VILLAGER);
        georges.setLover(true);

        Player wolfgang = new Player("WOLFgang");
        wolfgang.setRole(Role.WEREWOLF);
        wolfgang.setLover(true);

        Player biatch = new Player("Biatch");
        biatch.setRole(Role.LITTLE_GIRL);
        biatch.setAlive(!iSeeDeadPeople);

        Player loupiote = new Player("LOUPiote");
        loupiote.setRole(Role.WEREWOLF);

        Player josiane = new Player("Josiane");
        josiane.setRole(Role.FORTUNE_TELLER);
        josiane.setAlive(!iSeeDeadPeople);

        Player pupute = new Player("Pupute");
        pupute.setRole(Role.VILLAGER);

        players.add(bill);
        players.add(georges);
        players.add(wolfgang);
        players.add(biatch);
        players.add(loupiote);
        players.add(josiane);
        players.add(pupute);

        return players;
    }
}
