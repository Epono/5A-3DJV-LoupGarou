package fr.esgi.davidghetto.loupgarou.models;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    private List<Player> players;
    private int turn;
    private int numberOfHumansAlive;
    private int numberOfWerewolvesAlive;

    public Game(List<Player> players) {
        this.players = players;
        this.turn = 0;
        this.numberOfHumansAlive = 0;
        this.numberOfWerewolvesAlive = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getNumberOfHumansAlive() {
        return numberOfHumansAlive;
    }

    public void setNumberOfHumansAlive(int numberOfHumansAlive) {
        this.numberOfHumansAlive = numberOfHumansAlive;
    }

    public int getNumberOfWerewolvesAlive() {
        return numberOfWerewolvesAlive;
    }

    public void setNumberOfWerewolvesAlive(int numberOfWerewolvesAlive) {
        this.numberOfWerewolvesAlive = numberOfWerewolvesAlive;
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", turn=" + turn +
                ", numberOfHumansAlive=" + numberOfHumansAlive +
                ", numberOfWerewolvesAlive=" + numberOfWerewolvesAlive +
                '}';
    }
}
