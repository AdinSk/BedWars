package net.LukAd.BedWars.Game;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.LukAd.BedWars.Game.enums.TeamType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class Team {
    private TeamType teamType;
    private List<Player> playersTeam = Lists.newArrayList();
    private HashMap<Player, Location> playersSpawns = Maps.newHashMap();


}
