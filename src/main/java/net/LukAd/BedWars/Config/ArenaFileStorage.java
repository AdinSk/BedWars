package net.LukAd.BedWars.Config;

import net.LukAd.BedWars.Config.Interfaces.ArenaDatabase;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.Team;
import org.bukkit.configuration.file.FileConfiguration;
import sun.plugin2.main.server.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaFileStorage implements ArenaDatabase {

    private FileConfiguration storage = PluginConfig.getArenasConfiguration();

    @Override
    public HashMap<String, Game> findAllArenas() {

        if (storage.get("arenas") == null) return null;

        for (String arena : storage.getConfigurationSection("arenas").getKeys(false)) {
            String name = arena;

            List<Team> teamList = new ArrayList<>();

            for (String arenaTeam : storage.getConfigurationSection("arenas." + name + ".teams").getKeys(false)) {


            }

        }

        return null;
    }

    @Override
    public boolean saveArena(Game game) {
        return false;
    }

    @Override
    public Game getArenaByName(String name) {
        return null;
    }

}
