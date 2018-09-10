package net.LukAd.BedWars.Game;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;
    private List<Team> teams = new ArrayList<Team>();
    private List<PlayerData> players = new ArrayList<PlayerData>();

    private Location lobby;

    public Game(String name, List<Team> teams, Location lobby) {
        this.name = name;
        this.teams = teams;
        this.lobby = lobby;
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
