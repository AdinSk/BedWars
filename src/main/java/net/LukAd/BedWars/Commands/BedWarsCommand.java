package net.LukAd.BedWars.Commands;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Config.PluginConfig;
import net.LukAd.BedWars.Utils.Messages;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class BedWarsCommand implements CommandExecutor {

    private BedWars plugin;

    public BedWarsCommand(BedWars plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou can not send command from console!");
            return false;
        }

        Player player = (Player) sender;

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

        FileConfiguration storage = PluginConfig.getArenasConfiguration();

        if (args.length == 0) {
            showHelp(player);
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("setlocallobby")) {
                storage.set("locallobby.world", w);
                storage.set("locallobby.x", x);
                storage.set("locallobby.y", y);
                storage.set("locallobby.z", z);
                storage.set("locallobby.pitch", pitch);
                storage.set("localloby.yaw", yaw);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            }else if (args[0].equalsIgnoreCase("save")) {
                PluginConfig.saveArenasConfiguration();
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_SAVE_CONFIG);
            } else {
                showHelp(player);
            }
            return true;
        } else if (args.length == 2) {
            String arenaName = args[1];
            if (args[0].equalsIgnoreCase("create")) {
                storage.set("Arena.", arenaName);
            } else if (args[0].equalsIgnoreCase("addshopvillager")) {
                storage.set("Arena." + arenaName  + ".shopvillager.world", w);
                storage.set("Arena." + arenaName  + ".shopvillager.x", x);
                storage.set("Arena." + arenaName  + ".shopvillager.y", y);
                storage.set("Arena." + arenaName  + ".shopvillager.z", z);
                storage.set("Arena." + arenaName  + ".shopvillager.pitch", pitch);
                storage.set("Arena." + arenaName  + ".shopvillager.yaw", yaw);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else if (args[0].equalsIgnoreCase("setlobby")) {
                storage.set("Arena." + arenaName  + ".lobby.world", w);
                storage.set("Arena." + arenaName  + ".lobby.x", x);
                storage.set("Arena." + arenaName  + ".lobby.y", y);
                storage.set("Arena." + arenaName  + ".lobby.z", z);
                storage.set("Arena." + arenaName  + ".lobby.pitch", pitch);
                storage.set("Arena." + arenaName  + ".lobby.yaw", yaw);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else {
                showHelp(player);
            }
                return true;
        } else if (args.length == 3) {
            String arenaName = args[1];
            String teamName = args[2];
            if (args[0].equalsIgnoreCase("setteammaxplayers")) {
                String maxplayers = args[2];
                storage.set("Arena." + arenaName + ".maxplayers", maxplayers);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else if (args[0].equalsIgnoreCase("setteamspawn")) {
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.world", w);
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.x", x);
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.y", y);
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.z", z);
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.pitch", pitch);
                storage.set("Arena." + arenaName + "." + teamName + ".teamspawn.yaw", yaw);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else if (args[0].equalsIgnoreCase("setteambed")) {
                storage.set("Arena." + arenaName + "." + teamName + ".teambed.world", w);
                storage.set("Arena." + arenaName + "." + teamName + ".teambed.x", x);
                storage.set("Arena." + arenaName + "." + teamName + ".teambed.y", y);
                storage.set("Arena." + arenaName + "." + teamName + ".teambed.z", z);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else {
                showHelp(player);
            }
            return true;
        } else if (args.length == 4) {
            String arenaName = args[1];
            String teamName = args[2];
            String color = args[3];
            if (args[0].equalsIgnoreCase("newteam")) {
                storage.set("Arena." + arenaName + "." + teamName + ".color", color);
                player.sendMessage(Messages.PREFIX + Messages.PLAYER_WRITE_TO_CONFIG);
            } else {
                showHelp(player);
            }
            return true;
        } else {
            showHelp(player);
            return true;
        }
    }

    private void showHelp(Player player) {
        player.sendMessage("§7===== §a§lHELP §7=====");
        player.sendMessage("§r");
        player.sendMessage("§7/bw setLocalLobby");
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
