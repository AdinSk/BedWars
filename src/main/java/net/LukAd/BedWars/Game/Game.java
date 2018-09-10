package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.Game.Enums.GameState;
import net.LukAd.BedWars.Game.Player.PlayerData;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;

    private List<Team> teams = new ArrayList<Team>();
    private List<PlayerData> players = new ArrayList<PlayerData>();

    private GameManager gameManager;

    private Location lobby;

    private GameState gameState = GameState.WAITING;

    public Game(GameManager gameManager, String name, List<Team> teams, Location lobby) {
        this.gameManager = gameManager;
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
