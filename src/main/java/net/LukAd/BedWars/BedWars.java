package net.LukAd.BedWars;

import net.LukAd.BedWars.Game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class BedWars extends JavaPlugin {

    private GameManager gameManager;

    public void onEnable() {
        this.gameManager = new GameManager(this);
        this.gameManager.registerGames();
        Bukkit.getLogger().info("[BedWars] Plugin is Enabled");
    }

    public void onDisable() {
        Bukkit.getLogger().info("[BedWars] Plugin is Disabled");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
