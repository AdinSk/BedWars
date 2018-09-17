package net.LukAd.BedWars.Config.Interfaces;

import net.LukAd.BedWars.Game.Game;
import net.LukAd.BedWars.Game.Team;

import javax.annotation.Nonnull;
import java.util.HashMap;

public interface ArenaDatabase {

    HashMap<String, Game> findAllArenas();

    boolean saveArena(Game game);

    Game getArenaByName(String name);

}
