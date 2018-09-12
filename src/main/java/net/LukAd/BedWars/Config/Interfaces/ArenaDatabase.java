package net.LukAd.BedWars.Config.Interfaces;

import net.LukAd.BedWars.Game.Game;

import java.util.HashMap;

public interface ArenaDatabase {

    HashMap<String, Game> findAllArenas();

    boolean saveArena(Game game);

    Game getArenaByName(String name);

}
