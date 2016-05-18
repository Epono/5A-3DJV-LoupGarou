package fr.esgi.davidghetto.loupgarou.activities.logic;

public class Player {
    private User user;
    private Role role;
    private boolean isAlive;
    private boolean canPlayThisTurn;
    private boolean canVoteThisTurn;
    private boolean canPlayAtNight;

    public Player(User user, Role role) {
        this.user = user;
        this.role = role;
        this.isAlive = true;
        this.canPlayThisTurn = false;
        this.canVoteThisTurn = false;

        if (role == Role.WITCH || role == Role.WEREWOLF) {
            canPlayAtNight = true;
        } else {
            canPlayAtNight = false;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean canPlayThisTurn() {
        return canPlayThisTurn;
    }

    public void setCanPlayThisTurn(boolean canPlayThisTurn) {
        this.canPlayThisTurn = canPlayThisTurn;
    }

    public boolean canPlayAtNight() {
        return canPlayAtNight;
    }

    public void setCanPlayAtNight(boolean canPlayAtNight) {
        this.canPlayAtNight = canPlayAtNight;
    }

    public boolean isCanVoteThisTurn() {
        return canVoteThisTurn;
    }

    public void setCanVoteThisTurn(boolean canVoteThisTurn) {
        this.canVoteThisTurn = canVoteThisTurn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "user=" + user +
                ", role=" + role +
                ", isAlive=" + isAlive +
                ", canPlayThisTurn=" + canPlayThisTurn +
                '}';
    }
}
