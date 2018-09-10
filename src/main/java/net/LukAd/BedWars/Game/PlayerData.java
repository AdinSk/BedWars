package net.LukAd.BedWars.Game;

import org.bukkit.entity.Player;

public class PlayerData {


    private Player player;
    private Team team;
    private boolean spectator;

    public PlayerData(Player player) {
        this.player = player;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public Player getPlayer() {
        return player;
    }
}
