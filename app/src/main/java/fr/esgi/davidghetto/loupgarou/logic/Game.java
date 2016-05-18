package fr.esgi.davidghetto.loupgarou.logic;

import java.util.List;

public class Game {
    private PlayerGame gameMaster;
    private List<PlayerGame> playerGames;
    private int turn;
    private int numberOfHumansAlive;
    private int numberOfWerewolvesAlive;

    // Le maitre de jeu ne fait pas partie de la liste "playerGames"
    public Game(User gameMaster, List<PlayerGame> playerGames) {
        this.gameMaster = new PlayerGame(gameMaster, Role.GAME_MASTER);
        this.playerGames = playerGames;
        this.turn = 0;
        this.numberOfHumansAlive = 0;
        this.numberOfWerewolvesAlive = 0;

        for (PlayerGame playerGame : playerGames) {
            if (playerGame.getRole() == Role.WEREWOLF) {
                this.numberOfWerewolvesAlive++;
            } else {
                this.numberOfHumansAlive++;
            }
        }

        // TODO: Election du maire

        // Tant qu'il reste au moins une personne dans chaque equipe
        while (numberOfHumansAlive > 0 && numberOfWerewolvesAlive > 0) {

            // Chaque joueur vivant peut rejouer et voter
            for (PlayerGame playerGame : playerGames) {
                if (playerGame.isAlive()) {
                    playerGame.setCanPlayThisTurn(true);
                    playerGame.setCanVoteThisTurn(true);
                }
            }

            // TODO: Action des loups garous
            for (PlayerGame playerGame : playerGames) {
                if (playerGame.getRole() == Role.WEREWOLF) {
                    // TODO: demander qui ils veulent tuer
                    playerGame.setCanPlayThisTurn(false);
                }
            }

            // TODO: Evenement de nuit (prendre un joueur au hasard qui peut jouer de nuit ?)
            for (PlayerGame playerGame : playerGames) {
                if (playerGame.canPlayAtNight()) {
                    switch (playerGame.getRole()) {
                        case HUNTER:
                            break;
                        case MEDIUM:
                            break;
                        case WITCH:
                            break;
                    }
                }
                playerGame.setCanPlayThisTurn(false);
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
