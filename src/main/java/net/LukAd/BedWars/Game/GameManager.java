package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.config.ArenaFileStorage;
import net.LukAd.BedWars.Config.Interfaces.ArenaDatabase;
import net.LukAd.BedWars.Game.Enums.GameState;
import net.LukAd.BedWars.Game.Player.PlayerData;
import net.LukAd.BedWars.config.ArenaFileStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private BedWars plugin;
    private GameManager instance;

    private ArenaDatabase arenaDatabase;

    private Location localLobby;

    public GameManager(BedWars plugin) {
        this.plugin = plugin;
        arenaDatabase = new ArenaFileStorage(plugin);
    }

    public ArenaDatabase getArenaDatabase() {
        return arenaDatabase;
    }

    private HashMap<String, Game> games = new HashMap<String, Game>();

    public HashMap<String, Game> getGames() {
        return games;
    }

    public Game findEmpty() {
        for (Game game : games.values()) {
            if (game.getGameState() != GameState.PLAYING && !game.isFull()) {
                return game;
            }
        }
        return null;
    }

    public void registerGames() {
        if (plugin.getConfig().get("Arena") == null) return;

        for (String game : plugin.getConfig().getConfigurationSection("Arena").getKeys(false)) {
        }
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

    public GameManager getInstance() {
        return instance;
    }
}
