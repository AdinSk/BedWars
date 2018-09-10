package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.Game.Player.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;

    private Location bedLocation;

    private List<PlayerData> players = new ArrayList<PlayerData>();

    private Location spawnLocation;

    public Team(String teamName, Location bedLocation, Location spawnLocation) {
        this.teamName = teamName;
        this.bedLocation = bedLocation;
        this.spawnLocation = spawnLocation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void teleportAllPlayers() {
        for (PlayerData pd : players) {
            Player player = pd.getPlayer();
            player.teleport(spawnLocation);
            player.setFallDistance(0.0F);
            player.setHealth(20);
            player.setFoodLevel(20);
        }
    }

    public boolean isFull() {
        return false; // TODO : check: team is full.... for connect to team...
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
}
