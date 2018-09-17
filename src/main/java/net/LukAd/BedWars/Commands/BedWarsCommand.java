package net.LukAd.BedWars.Commands;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Config.PluginConfig;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.Team;
import net.LukAd.BedWars.Utils.Messages;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class BedWarsCommand implements CommandExecutor {

    private BedWars plugin;
    private HashMap<String, Game> games = new HashMap<String, Game>();


    public BedWarsCommand(BedWars plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou can not send command from console!");
            return false;
        }

        Player player = (Player) sender;

        //plugin.getGameManager().getArenaDatabase().saveArena(nazev_hashMapu.get(NAZEV_ARENY));

        if (!player.isOp()) {
            player.sendMessage(Messages.PREFIX + Messages.PLAYER_NO_PERMISSIONS);
            return false;
        }


        String w = player.getWorld().getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        float pitch = player.getLocation().getPitch();
        float yaw = player.getLocation().getYaw();
        Location playerloc = new Location(player.getWorld(), x, y, z);
        playerloc.setPitch(pitch);
        playerloc.setYaw(yaw);

        FileConfiguration storage = net.LukAd.BedWars.Config.PluginConfig.getArenasConfiguration();

        if (args.length == 0) {
            showHelp(player);
            return true;
        } else if (args.length == 1) {
                showHelp(player);
            return true;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("createarena")) {
                String arenaName = args[1];

                // public Game(GameManager gameManager, String name, List<Team> teams, Location lobby, int maxPlayersPerTeam) {
                Game game = new Game(null, arenaName, new ArrayList<>(), null, 0);
                games.put(arenaName, game);

            } else if (args[0].equalsIgnoreCase("setlocallobby")) {
                String arenaName = args[1];

                if (games.containsKey(arenaName)) {
                    Game game = games.get(arenaName);
                    game.setLocalLobby(playerloc);
                    plugin.getGameManager().getArenaDatabase().saveArena(game, null);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }

            } else if (args[0].equalsIgnoreCase("save")) {
                String arenaName = args[1];
                if (games.containsKey(arenaName)) {
                    Game game = games.get(arenaName);
                    plugin.getGameManager().getArenaDatabase().saveArena(game, null);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else if (args[0].equalsIgnoreCase("setlobby")) {
                    String arenaName = args[1];

                if (games.containsKey(arenaName)) {
                    Game game = games.get(arenaName);
                    game.setLobby(playerloc);
                    plugin.getGameManager().getArenaDatabase().saveArena(game, null);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else {
                showHelp(player);
            }
            return true;
        } else if (args.length == 3) {
            String arenaName = args[1];
            String teamName = args[2];
            if (args[0].equalsIgnoreCase("setteammaxplayers")) {
                String maxplayers = args[2];

                if (games.containsKey(arenaName)) {
                    Game game = games.get(arenaName);

                    game.setMaxPlayersPerTeam(Integer.valueOf(maxplayers));
                    plugin.getGameManager().getArenaDatabase().saveArena(game, null);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else if (args[0].equalsIgnoreCase("setteamspawn")) {

                if (games.containsKey(arenaName) && games.containsKey(teamName)) {
                    Game game = games.get(arenaName);
                    Team team = game.getTeamByName(teamName);

                    team.setSpawnLocation(playerloc);

                    plugin.getGameManager().getArenaDatabase().saveArena(game, team);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else if (args[0].equalsIgnoreCase("setteambed")) {

                if (games.containsKey(arenaName) && games.containsKey(teamName)) {
                    Game game = games.get(arenaName);
                    Team team = game.getTeamByName(teamName);

                    team.setBedLocation(playerloc);

                    plugin.getGameManager().getArenaDatabase().saveArena(game, team);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }

            } else if (args[0].equalsIgnoreCase("addshopvillager")) {

                if (games.containsKey(arenaName) && games.containsKey(teamName)) {
                    Game game = games.get(arenaName);
                    Team team = game.getTeamByName(teamName);

                    team.setVillagerLocation(playerloc);

                    plugin.getGameManager().getArenaDatabase().saveArena(game, team);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else {
                showHelp(player);
            }
            return true;
        } else if (args.length == 4) {
            String arenaName = args[1];
            String teamName = args[2];
            String color = args[3];
            if (args[0].equalsIgnoreCase("newteam")) {

                if (games.containsKey(arenaName) && !games.containsKey(teamName)) {
                    Game game = games.get(arenaName);
                    Team team = new Team(teamName, null, null, color, null);

                    game.getTeams().add(team);
                    plugin.getGameManager().getArenaDatabase().saveArena(game, team);
                    player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
                } else {
                    player.sendMessage("§cThis arena is not created!");
                }
            } else {
                showHelp(player);
            }
            return true;
        } else {
            showHelp(player);
            return true;
        }
    }

    public HashMap<String, Game> getGames() {
        return games;
    }

    private void showHelp(Player player) {
        player.sendMessage("§7===== §a§lHELP §7=====");
        player.sendMessage("§r");
        player.sendMessage("§7/bw setLocalLobby");
        player.sendMessage("§r§7/bw createarena");
        player.sendMessage("§r");
        player.sendMessage("§7/bw setLobby <arenaName>");
        player.sendMessage("§r");
        player.sendMessage("§7/bw newTeam <arenaName> <teamName> <color>");
        player.sendMessage("§7/bw setTeamMaxPlayers <arenaName> <maxPlayers>");
        player.sendMessage("§7/bw setTeamSpawn <arenaName> <teamName>");
        player.sendMessage("§7/bw setTeamBed <arenaName> <teamName>");
        player.sendMessage("§7/bw addShopVillager <arenaName>");
        player.sendMessage("§7/bw setPos <arenaName> <1/2>");
        player.sendMessage("§r");
        player.sendMessage("§7======================");
    }
}
