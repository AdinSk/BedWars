package net.LukAd.BedWars.Config;

import net.LukAd.BedWars.BedWars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private static File arenas;
    private static File messages;

    private static FileConfiguration arenasConfiguration;
    private static FileConfiguration messagesConfiguration;
    private static FileConfiguration settingsConfiguration;

    private static BedWars plugin;

    public static void loadConfigs(BedWars plugin) {
        PluginConfig.plugin = plugin;
        arenas = new File(plugin.getDataFolder(), "arenas.yml");
        messages = new File(plugin.getDataFolder(), "messages.yml");

        if (!arenas.exists()) {
            try {
                arenas.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        if (!messages.exists()) {
            try {
                messages.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        arenasConfiguration = YamlConfiguration.loadConfiguration(arenas);
        messagesConfiguration = YamlConfiguration.loadConfiguration(messages);

        plugin.saveConfig();
        settingsConfiguration = plugin.getConfig();

    }

    public static FileConfiguration getArenasConfiguration() {
        return arenasConfiguration;
    }

    public static FileConfiguration getMessagesConfiguration() {
        return messagesConfiguration;
    }
    public static void saveArenasConfiguration() {
        if (arenasConfiguration != null) {
            try {
                arenasConfiguration.save(arenas);
                arenasConfiguration = YamlConfiguration.loadConfiguration(arenas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void saveMessagesConfiguration() {
        if (messagesConfiguration != null) {
            try {
                messagesConfiguration.save(messages);
                messagesConfiguration = YamlConfiguration.loadConfiguration(messages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void saveSettingsConfiguration() {
        if (settingsConfiguration != null) {
            plugin.saveConfig();
            plugin.reloadConfig();
        }
    }

}
