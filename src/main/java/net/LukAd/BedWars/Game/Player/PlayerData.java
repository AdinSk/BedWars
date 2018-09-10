package net.LukAd.BedWars.Game.Player;

import net.LukAd.BedWars.Game.Team;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerData {


    private Player player;
    private Team team;
    private boolean spectator;
    private PlayerBoard playerBoard;

    public PlayerData(Player player) {
        this.player = player;
        this.playerBoard = new PlayerBoard(this, "§a§lBedWars");
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
    public void setScoreboard(List<String> lines) {
        this.playerBoard.setBoard(lines);
    }
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }
}
