package net.LukAd.BedWars.Game;

import net.LukAd.BedWars.Game.Enums.GameState;
import net.LukAd.BedWars.Game.Player.PlayerData;
import net.LukAd.BedWars.Utils.Messages;
import net.LukAd.BedWars.Utils.Titles;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private String arenaName;

    private List<Team> teams = new ArrayList<Team>();
    private HashMap<Player, PlayerData> players = new HashMap<Player, PlayerData>();

    private GameManager gameManager;
    private int playerCount = 0;
    private Location localLobby;
    private Location lobby;

    private GameState gameState = GameState.WAITING;

    private int maxPlayersPerTeam;

    public Game(GameManager gameManager, String name, List<Team> teams, Location lobby, int maxPlayersPerTeam) {
        this.gameManager = gameManager;
        this.arenaName = name;
        this.teams = teams;
        this.localLobby = lobby;
        this.maxPlayersPerTeam = maxPlayersPerTeam;
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getName() {
        return arenaName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Location getLocalLobby() {
        return localLobby;
    }

    public void setLocalLobby(Location localLobby) {
        this.localLobby = localLobby;
    }

    public int getMaxPlayersPerTeam() {
        return maxPlayersPerTeam;
    }

    public HashMap<Player, PlayerData> getPlayers() {
        return players;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public int getMaxPlayers() {
        return maxPlayersPerTeam * teams.size();
    }

    public boolean isFull() {
        return getPlayers().size() >= getMaxPlayers();
    }

    public void joinPlayer(Player player) {
        if (gameManager.getGameByPlayer(player) != null) {
            player.sendMessage(Messages.PREFIX + Messages.PLAYER_IS_IN_GAME);
            return;
        }
        if (this.players.containsKey(player)) {
            player.sendMessage(Messages.PREFIX + Messages.PLAYER_IS_IN_THIS_GAME);
            return;
        }
        if (isFull()) {
            player.sendMessage(Messages.PREFIX + Messages.ARENA_FULL);
            return;
        }

        PlayerData playerData = new PlayerData(player);
        this.players.put(player, playerData);
        playerCount++;

        broadcastMessage("§7Player §a" + player.getName() + "§7 joined the game! §a(" + players.size() + ")", true);

    }

    public void leavePlayer(Player player) {
        if (!players.containsKey(player)) {
            player.sendMessage(Messages.PREFIX + Messages.PLAYER_NOT_IN_ARENA);
            return;
        }
        PlayerData pd = players.get(player);
        pd.restorePlayer();
        players.remove(player);
        broadcastMessage("§7Player §c" + player.getName() + "§7 left the game! §c(" + players.size() + ")", true);
    }

    public void broadcastMessage(String message, boolean enabledPrefix) {
        for (PlayerData pd : players.values()) {
            Player player = pd.getPlayer();
            if (enabledPrefix) {
                player.sendMessage(Messages.PREFIX + message);
                continue;
            }
            player.sendMessage(message);
        }
    }

    public void broadcastTitle(String title, String subtitle) {
        for (PlayerData pd : players.values()) {
            Player player = pd.getPlayer();
            Titles.send(player, title, subtitle);
        }
    }

    public void broadcastActionBar(String message) {
        for (PlayerData pd : players.values()) {
            Player player = pd.getPlayer();
            Titles.sendAction(player, message);
        }
    }

    public Team getTeamByBedLocation(Location location) {
        Block block = location.getBlock();
        for (Team team : teams) {
            Block teamBlock = team.getBedLocation().getBlock();
            if (block.equals(teamBlock)) return team;
        }
        return null;
    }

    public Team getTeamByName(String name) {
        for (Team team : teams) {
            if (name.equalsIgnoreCase(team.getTeamName())) return team;
        }
        return null;
    }

    public int getPlayerCount() {
        return playerCount;
    }


    public void setarenaName(String name) {
        this.arenaName = name;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setPlayers(HashMap<Player, PlayerData> players) {
        this.players = players;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }


    public void setMaxPlayersPerTeam(int maxPlayersPerTeam) {
        this.maxPlayersPerTeam = maxPlayersPerTeam;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }
}
