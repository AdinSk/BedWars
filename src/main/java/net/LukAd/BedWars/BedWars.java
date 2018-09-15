package net.LukAd.BedWars;

import net.LukAd.BedWars.Commands.BedWarsCommand;
import net.LukAd.BedWars.Config.PluginConfig;
import net.LukAd.BedWars.Game.GameManager;
import net.LukAd.BedWars.Game.Player.PlayerData;
import net.LukAd.BedWars.Listeners.PlayerListener;
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

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("bw").setExecutor(new BedWarsCommand(this));

        Bukkit.getLogger().info("[BedWars] Plugin is Enabled");

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
