package net.LukAd.BedWars;

import net.LukAd.BedWars.Commands.BedWarsCommand;
import net.LukAd.BedWars.Config.PluginConfig;
import net.LukAd.BedWars.Game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class BedWars extends JavaPlugin {

    private GameManager gameManager;
    private static BedWars instance;

    public void onEnable() {

        PluginConfig.loadConfigs(this);

        this.gameManager = new GameManager(this);
        this.gameManager.registerGames();

        Bukkit.getLogger().info("[BedWars] Plugin is Enabled");
        getCommand("bw").setExecutor(new BedWarsCommand(this));
    }

    public void onDisable() {
        Bukkit.getLogger().info("[BedWars] Plugin is Disabled");
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static BedWars getInstance() {
        return instance;
    }
}
