package net.LukAd.BedWars.Game.Player;

import net.LukAd.BedWars.BedWars;
import net.LukAd.BedWars.Game.Team;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Collection;
import java.util.List;

public class PlayerData {


    private Player player;
    private Team team;
    private boolean spectator;
    private PlayerBoard playerBoard;

    private ItemStack[] savedInventoryContents;
    private ItemStack[] savedInventoryArmor;
    private float savedExp;
    private int savedLevel;
    private int savedFoodLevel;
    private double savedHealth;
    private double savedMaxHealth;
    private Collection<PotionEffect> savedPotionEffects;



    public PlayerData(Player player) {
        this.player = player;
        this.playerBoard = new PlayerBoard(this, "§a§lBedWars");
        savePlayer();
    }

    public void savePlayer() {
        this.savedInventoryContents = player.getInventory().getContents();
        this.savedInventoryArmor = player.getInventory().getArmorContents();
        this.savedExp = player.getExp();
        this.savedLevel = player.getLevel();
        this.savedFoodLevel = player.getFoodLevel();
        this.savedHealth = player.getHealth();
        this.savedMaxHealth = player.getMaxHealth();
        this.savedPotionEffects = player.getActivePotionEffects();

        clearPlayer();
    }

    public void clearPlayer() {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0.0F);
        player.setLevel(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        for (PotionEffect type : player.getActivePotionEffects()) {
            player.removePotionEffect(type.getType());
        }
        player.updateInventory();
    }

    public void restorePlayer() {
        clearPlayer();
        player.getInventory().setArmorContents(savedInventoryArmor);
        player.getInventory().setArmorContents(savedInventoryContents);
        player.setMaxHealth(savedMaxHealth);
        player.setHealth(savedHealth);
        player.setFoodLevel(savedFoodLevel);
        player.setLevel(savedLevel);
        player.setExp(savedExp);
        for (PotionEffect type : savedPotionEffects) {
            player.addPotionEffect(type);
        }

        player.updateInventory();
    }

    public Team setTeam(Team team) {
        Team oldTeam = this.team;
        this.team = team;
        if (oldTeam != null) {
            oldTeam.getPlayers().remove(this);
        }
        if (team != null) {
            team.getPlayers().add(this);
        }
        return this.team;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public Player getPlayer() {
        return player;
    }

    public void setScoreboard(List<String> lines) {
        this.playerBoard.setBoard(lines);
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }
}
