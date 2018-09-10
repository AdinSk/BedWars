package net.LukAd.BedWars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {


    public void onEnable() {
        Bukkit.getLogger().info("[BedWars] Plugin is Enabled");
    }

    public void onDisable() {
        Bukkit.getLogger().info("[BedWars] Plugin is Disabled");
    }

}
