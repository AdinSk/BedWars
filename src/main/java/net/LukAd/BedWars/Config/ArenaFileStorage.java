package net.LukAd.BedWars.Config;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Commands.BedWarsCommand;
import net.LukAd.BedWars.Config.Interfaces.ArenaDatabase;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.GameManager;
import net.LukAd.BedWars.Game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import java.beans.BeanDescriptor;
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

        storage = net.LukAd.BedWars.Config.PluginConfig.getArenasConfiguration();

    }

    @Override
    public HashMap<String, Game> findAllArenas() {
        HashMap<String, Game> games = new HashMap<>();
        if (storage.get("arenas") == null) return null;

        for (String arena : storage.getConfigurationSection("arenas").getKeys(false)) {
            String arenaName = arena;
            int maxPlayersPerTeam = storage.getInt("arenas." + arenaName + ".maxPlayersPerTeam");
            List<Team> teamList = new ArrayList<>();


            double x3 = storage.getDouble("arenas." + arenaName + ".spawn.x");
            double y3 = storage.getDouble("arenas." + arenaName +".spawn.y");
            double z3 = storage.getDouble("arenas." + arenaName + ".spawn.z");
            float pitch3 = (float) storage.getDouble("arenas." + arenaName + ".spawn.pitch");
            float yaw3 = (float) storage.getDouble("arenas." + arenaName + ".spawn.yaw");
            World world3 =  Bukkit.getWorld(storage.getString("arenas." + arenaName + ".spawn.world"));
            Location lobby = new Location(world3, x3, y3, z3);
            lobby.setPitch(pitch3);
            lobby.setYaw(yaw3);

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

                teamList.add(new Team(teamName, teamBed, teamSpawn, colorCode, teamVillager));
            }
            Game game = new Game(gameManager, arenaName, teamList, lobby, maxPlayersPerTeam);
            games.put(arenaName, game);
        }

        return games;
    }

@Override
public boolean saveArena(Game game,Team team) {

        storage.set("locallobby.world", game.getLocalLobby().getWorld());
        storage.set("locallobby.x", game.getLocalLobby().getX());
        storage.set("locallobby.y", game.getLocalLobby().getY());
        storage.set("locallobby.z", game.getLocalLobby().getZ());
        storage.set("locallobby.pitch", game.getLocalLobby().getPitch());
        storage.set("locallobby.yaw", game.getLocalLobby().getYaw());

        storage.set("arenas." + game.getName() + "lobby.world", game.getLobby().getWorld());
        storage.set("arenas." + game.getName() + "lobby.x", game.getLobby().getX());
        storage.set("arenas." + game.getName() + "lobby.y", game.getLobby().getY());
        storage.set("arenas." + game.getName() + "lobby.z", game.getLobby().getZ());
        storage.set("arenas." + game.getName() + "lobby.pitch", game.getLobby().getPitch());
        storage.set("arenas." + game.getName() + "lobby.yaw", game.getLobby().getYaw());

        if (team == null) return false;

        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".color", team.getColor());

        storage.set("arenas." + game.getName() + ".maxplayers", game.getMaxPlayersPerTeam());

        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.world", team.getSpawnLocation().getWorld());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.x", team.getSpawnLocation().getX());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.y", team.getSpawnLocation().getY());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.z", team.getSpawnLocation().getZ());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.pitch", team.getSpawnLocation().getPitch());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".spawn.yaw", team.getSpawnLocation().getYaw());

        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".bed.world", team.getSpawnLocation().getWorld());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".bed.x", team.getSpawnLocation().getX());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".bed.y", team.getSpawnLocation().getY());
        storage.set("arenas." + game.getName() + ".teams." + team.getTeamName() + ".bed.z", team.getSpawnLocation().getZ());

        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.world", team.getVillagerLocation().getWorld());
        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.x",team.getVillagerLocation().getX());
        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.y", team.getVillagerLocation().getY());
        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.z", team.getVillagerLocation().getZ());
        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.pitch", team.getVillagerLocation().getPitch());
        storage.set("Arena." + game.getName() + ".teams." + team.getTeamName() + ".villager.yaw", team.getVillagerLocation().getYaw());

        return false;
    }

    @Override
    public Game getArenaByName(String name) {
        return null;
    }

}
