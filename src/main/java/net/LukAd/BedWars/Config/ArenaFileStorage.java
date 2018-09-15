package net.LukAd.BedWars.Config;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Config.Interfaces.ArenaDatabase;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.GameManager;
import net.LukAd.BedWars.Game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaFileStorage implements ArenaDatabase {

    private FileConfiguration storage;

    private BedWars plugin;

    private GameManager gameManager;

    public ArenaFileStorage(BedWars plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        storage = PluginConfig.getArenasConfiguration();
    }

    @Override
    public HashMap<String, Game> findAllArenas() {
        HashMap<String, Game> games = new HashMap<>();
        if (storage.get("arenas") == null) return null;

        for (String arena : storage.getConfigurationSection("arenas").getKeys(false)) {
            String arenaName = arena;
            int maxPlayersPerTeam = storage.getInt("arenas." + arenaName + ".maxPlayersPerTeam");
            List<Team> teamList = new ArrayList<>();

            Location lobby = null; //DOPLNIT

            for (String team : storage.getConfigurationSection("arenas." + arenaName + ".teams").getKeys(false)) {
                String teamName = team;
                String colorCode = storage.getString("arenas." + arenaName + ".teams." + teamName + ".color");

                double x = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".spawn.x");
                double y = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".spawn.y");
                double z = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".spawn.z");
                float pitch = (float) storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".spawn.pitch");
                float yaw = (float) storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".spawn.yaw");
                World world = Bukkit.getWorld(storage.getString("arenas." + arenaName + ".teams." + teamName + ".spawn.world"));

                Location teamSpawn = new Location(world,x,y,z);
                teamSpawn.setPitch(pitch);
                teamSpawn.setYaw(yaw);


                double x1 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".bed.x");
                double y1 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".bed.y");
                double z1 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".bed.z");
                World world1 = Bukkit.getWorld(storage.getString("arenas." + arenaName + ".teams." + teamName + ".bed.world"));

                Location teamBed = new Location(world1,x1,y1,z1);


                double x2 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".villager.x");
                double y2 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".villager.y");
                double z2 = storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".villager.z");
                float pitch2 = (float) storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".villager.pitch");
                float yaw2 = (float) storage.getDouble("arenas." + arenaName + ".teams." + teamName + ".villager.yaw");
                World world2 = Bukkit.getWorld(storage.getString("arenas." + arenaName + ".teams." + teamName + ".villager.world"));

                Location teamVillager = new Location(world2,x2,y2,z2);
                teamVillager.setPitch(pitch2);
                teamVillager.setYaw(yaw2);

                teamList.add(new Team(teamName, teamBed, teamSpawn, colorCode, teamVillager, maxPlayersPerTeam));
            }
            Game game = new Game(gameManager, arenaName, teamList, lobby, maxPlayersPerTeam);
            games.put(arenaName, game);
        }

        return games;
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
