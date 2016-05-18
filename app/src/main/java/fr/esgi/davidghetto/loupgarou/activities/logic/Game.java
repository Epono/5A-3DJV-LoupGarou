package fr.esgi.davidghetto.loupgarou.activities.logic;

import java.util.List;

public class Game {
    private Player gameMaster;
    private List<Player> players;
    private int turn;
    private int numberOfHumansAlive;
    private int numberOfWerewolvesAlive;

    // Le maitre de jeu ne fait pas partie de la liste "players"
    public Game(User gameMaster, List<Player> players) {
        this.gameMaster = new Player(gameMaster, Role.GAME_MASTER);
        this.players = players;
        this.turn = 0;
        this.numberOfHumansAlive = 0;
        this.numberOfWerewolvesAlive = 0;

        for (Player player : players) {
            if (player.getRole() == Role.WEREWOLF) {
                this.numberOfWerewolvesAlive++;
            } else {
                this.numberOfHumansAlive++;
            }
        }

        // TODO: Election du maire

        // Tant qu'il reste au moins une personne dans chaque equipe
        while (numberOfHumansAlive > 0 && numberOfWerewolvesAlive > 0) {

            // Chaque joueur vivant peut rejouer et voter
            for (Player player : players) {
                if (player.isAlive()) {
                    player.setCanPlayThisTurn(true);
                    player.setCanVoteThisTurn(true);
                }
            }

            // TODO: Action des loups garous
            for (Player player : players) {
                if (player.getRole() == Role.WEREWOLF) {
                    // TODO: demander qui ils veulent tuer
                    player.setCanPlayThisTurn(false);
                }
            }

            // TODO: Evenement de nuit (prendre un joueur au hasard qui peut jouer de nuit ?)
            for (Player player : players) {
                if (player.canPlayAtNight()) {
                    switch (player.getRole()) {
                        case HUNTER:
                            break;
                        case MEDIUM:
                            break;
                        case WITCH:
                            break;
                    }
                }
                player.setCanPlayThisTurn(false);
            }

            // Fin de tour / Jour
            // TODO: Vote

            // Incrémentation du nombre de tour
            turn++;
        }

        if (numberOfHumansAlive == 0) {
            //TODO: les loups ont gagné
        } else {
            // TODO: les humains ont gagné
        }

        // TODO: sauvegarder les scores ?

        // TODO: proposer de rejouer
    }
}
