package net.LukAd.BedWars.config;

import net.LukAd.BedWars.Config.Interfaces.ArenaDatabase;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import sun.plugin2.main.server.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaFileStorage implements ArenaDatabase {

    private FileConfiguration storage = net.LukAd.BedWars.Config.PluginConfig.getArenasConfiguration();
    private String teamColor;
    private int maxPlayers;

    @Override
    public HashMap<String, Game> findAllArenas() {

        if (storage.get("arenas") == null) return null;

        for (String arena : storage.getConfigurationSection("Arena").getKeys(false)) {
            String arenaName = arena;


            for (String team : storage.getConfigurationSection("Arena." + arenaName).getKeys(false)) {
                String teamName = team;

                for (String color : storage.getConfigurationSection("Arena." + arenaName + "." + teamName + ".color").getKeys(false)) {
                    teamColor = color;
                }

                for (int maxp : storage.getInt("Arena." + arenaName + "." + teamName + ".maxplayers")) {
                    maxPlayers = maxp;
                }

                double x = storage.getDouble("Arena." + arenaName + "." + teamName + ".teamspawn.x");
                double y = storage.getDouble("Arena." + arenaName + "." + teamName + ".teamspawn.y");
                double z = storage.getDouble("Arena." + arenaName + "." + teamName + ".teamspawn.z");
                float pitch = (float) storage.getDouble("Arena." + arenaName + "." + teamName + ".teamspawn.pitch");
                float yaw = (float) storage.getDouble("Arena." + arenaName + "." + teamName + ".teamspawn.yaw");
                World world = Bukkit.getWorld(storage.getString("Arena." + arenaName + "." + teamName + ".teamspawn.world"));

                Location teamSpawn = new Location(world,x,y,z);
                teamSpawn.setPitch(pitch);
                teamSpawn.setYaw(yaw);


                double x1 = storage.getDouble("Arena." + arenaName + "." + teamName + ".teambed.x");
                double y1 = storage.getDouble("Arena." + arenaName + "." + teamName + ".teambed.y");
                double z1 = storage.getDouble("Arena." + arenaName + "." + teamName + ".teambed.z");
                World world1 = Bukkit.getWorld(storage.getString("Arena." + arenaName + "." + teamName + ".teambed.world"));

                Location teamBed = new Location(world1,x1,y1,z1);


                double x2 = storage.getDouble("Arena." + arenaName + "." + teamName + ".shopvillager.x");
                double y2 = storage.getDouble("Arena." + arenaName + "." + teamName + ".shopvillager.y");
                double z2 = storage.getDouble("Arena." + arenaName + "." + teamName + ".shopvillager.z");
                float pitch2 = (float) storage.getDouble("Arena." + arenaName + "." + teamName + ".shopvillager.pitch");
                float yaw2 = (float) storage.getDouble("Arena." + arenaName + "." + teamName + ".shopvillager.yaw");
                World world2 = Bukkit.getWorld(storage.getString("Arena." + arenaName + "." + teamName + ".shopvillager.world"));

                Location teamVillager = new Location(world2,x2,y2,z2);
                teamVillager.setPitch(pitch2);
                teamVillager.setYaw(yaw2);



                List<Team> teamList = new ArrayList<>();
                teamList.add(new Team(teamName, teamBed, teamSpawn, teamColor, teamVillager, maxPlayers));
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
