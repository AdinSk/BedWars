package net.LukAd.BedWars.Commands;

import net.LukAd.BedWars.BedWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        Player player = (Player)sender;

        if (args.length == 0) {
            showHelp(player);
            return true;
        }

        return false;
    }

    private void showHelp(Player player) {
        player.sendMessage("§7===== §a§lHELP §7=====");
        player.sendMessage("§r");
        player.sendMessage("§7/bw setLocalLobby");
        player.sendMessage("§r");
        player.sendMessage("§7/bw create <arenaName>");
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
