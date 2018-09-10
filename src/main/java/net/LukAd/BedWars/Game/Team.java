package net.LukAd.BedWars.Game;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;

    private Location bedLocation;

    private List<PlayerData> players = new ArrayList<PlayerData>();
    private List<Location> spawns = new ArrayList<Location>();

    public Team(String teamName, List<Location> spawns, Location bedLocation) {
        this.teamName = teamName;
        this.spawns.addAll(spawns);
        this.bedLocation = bedLocation;
    }

    public String getTeamName() {
        return teamName;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public List<Location> getSpawns() {
        return spawns;
    }
}
