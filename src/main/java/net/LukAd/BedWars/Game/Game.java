package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.Game.enums.GameState;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<PlayerData> players = new ArrayList<PlayerData>();

    private Location lobby;

    private GameState gameState = GameState.WAITING;

    public Game(String name, List<Team> teams, Location lobby) {
        this.name = name;
        this.teams = teams;
        this.lobby = lobby;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Location getLobby() {
        return lobby;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }
}
