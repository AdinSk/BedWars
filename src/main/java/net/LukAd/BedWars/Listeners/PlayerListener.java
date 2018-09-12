package net.LukAd.BedWars.Listeners;

import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Game game = GameManager.findEmpty().
    }
}
