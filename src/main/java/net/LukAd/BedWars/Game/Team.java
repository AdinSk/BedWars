package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.Game.Player.PlayerData;
import net.LukAd.BedWars.Utils.Messages;
import net.LukAd.BedWars.Utils.Titles;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private String color;

    private Location bedLocation;
    private Location villagerLocation;

    private List<PlayerData> players = new ArrayList<PlayerData>();

    private Location spawnLocation;

    public Team(String teamName, Location bedLocation, Location spawnLocation, String color, Location villagerLocation) {
        this.teamName = teamName;
        this.bedLocation = bedLocation;
        this.spawnLocation = spawnLocation;
        this.color = color;
        this.villagerLocation = villagerLocation;
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

    public void broadcastMessage(String message, boolean enabledPrefix) {
        for (PlayerData pd : players) {
            Player player = pd.getPlayer();
            if (enabledPrefix) {
                player.sendMessage(Messages.PREFIX + message);
                continue;
            }
            player.sendMessage(message);
        }
    }

    public void broadcastTitle(String title, String subtitle) {
        for (PlayerData pd : players) {
            Player player = pd.getPlayer();
            Titles.send(player, title, subtitle);
        }
    }

    public void broadcastActionBar(String message) {
        for (PlayerData pd : players) {
            Player player = pd.getPlayer();
            Titles.sendAction(player, message);
        }
    }

}
