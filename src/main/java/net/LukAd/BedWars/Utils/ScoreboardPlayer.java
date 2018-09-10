package net.LukAd.BedWars.Utils;

import net.LukAd.BedWars.Game.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardPlayer {

    private Scoreboard scoreboard;
    private PlayerData player;
    private List<String> lines = new ArrayList<String>();

    public ScoreboardPlayer(PlayerData player) {
        this.player = player;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    }


}
