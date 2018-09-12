package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Game.Player.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private BedWars plugin;

    private Location localLobby;

    public GameManager(BedWars plugin) {
        this.plugin = plugin;
    }

    private HashMap<String, Game> games = new HashMap<String, Game>();

    public HashMap<String, Game> getGames() {
        return games;
    }

    public void registerGames() {
        //TODO: load all games from Config...
    }

    public void registerGame(String arenaName, List<Team> teams, Location lobby, int maxPlayersPerTeam) {
        if (games.containsKey(arenaName)) return;
        Game game = new Game(this, arenaName, teams, lobby, maxPlayersPerTeam);
        games.put(arenaName, game);
    }

    public Location getLocalLobby() {
        return localLobby;
    }

    public BedWars getPlugin() {
        return plugin;
    }

    public Game getGameByPlayer(Player player) {
        for (Game g : games.values()) {
            if (g.getPlayers().containsKey(player)) {
                return g;
            }
        }
        return null;
    }
}
