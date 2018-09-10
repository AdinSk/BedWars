package net.LukAd.BedWars.Game.Player;

import net.LukAd.BedWars.Game.Player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class PlayerBoard {

    private Scoreboard scoreboard;
    private Objective obj;
    private PlayerData player;
    private String title;

    public PlayerBoard(PlayerData player, String defaultTitle) {
        this.player = player;
        this.title = defaultTitle;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.obj = this.scoreboard.registerNewObjective("game", "dummy");
        this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);

    }

    public void setBoard(List<String> lines) {
        obj.setDisplayName(title);
        for(int x = lines.size() - 1; x != -1; x--) {
            obj.getScore(lines.get(x)).setScore(lines.size() - x);
        }
        player.getPlayer().setScoreboard(scoreboard);
    }

    public void setTitle(String title) {
        obj.setDisplayName(title);
    }

}
