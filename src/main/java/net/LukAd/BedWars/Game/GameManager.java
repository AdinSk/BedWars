package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.BedWars;
import org.bukkit.Location;

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
        //TODO: load all games from config...
    }

    public void registerGame(String arenaName, List<Team> teams, Location lobby) {
        if (games.containsKey(arenaName)) return;
        Game game = new Game(this, arenaName, teams, lobby);
        games.put(arenaName, game);
    }

    public Location getLocalLobby() {
        return localLobby;
    }

    public BedWars getPlugin() {
        return plugin;
    }
}
