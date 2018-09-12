package net.LukAd.BedWars.Listeners;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.GameManager;
import net.LukAd.BedWars.Game.Player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private BedWars plugin;

    public PlayerListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerData playerData = new PlayerData(player);

        if (playerData != null) {
            Game game = plugin.getGameManager().findEmpty();
        } else return;

    }
}
