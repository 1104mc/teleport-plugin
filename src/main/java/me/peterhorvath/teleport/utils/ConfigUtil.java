package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.Waypoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigUtil {
    private File file;
    private FileConfiguration config;

    public ConfigUtil() {
        file = new File(Teleport.configDir, "waypoints.yml");
        if(!Teleport.configDir.exists()) Teleport.configDir.mkdir();
        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    private Material getMaterialByString(String val){
        switch (val.toLowerCase()){
            case "iron":
                return Material.IRON_INGOT;
            case "red_bed":
                return Material.RED_BED;
            case "zombie_head":
                return Material.ZOMBIE_HEAD;
            case "bell":
                return Material.BELL;
            case "sword":
                return Material.NETHERITE_SWORD;
            default:
                return Material.STRUCTURE_BLOCK;
        }
    }

    private Location getLocationBySection(ConfigurationSection sect){
        String world = sect.getString("world");
        int x = sect.getInt("x");
        int y = sect.getInt("y");
        int z = sect.getInt("z");
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public ArrayList<Waypoint> getAllWaypoints(){
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        this.config.getConfigurationSection("places").getKeys(false).forEach(waypoint -> {
            String wp_path = "places." + waypoint;
            Waypoint wp = new Waypoint(this.getMaterialByString(this.config.getString(wp_path + ".type")),
                    ColorManager.getColorByString(this.config.getString(wp_path + ".color")),
                    this.getLocationBySection(this.config.getConfigurationSection(wp_path + ".location")),
                    this.config.getString(wp_path + ".name"), this.config.getString(wp_path+".id"));
            waypoints.add(wp);
        });
        return waypoints;
    }

    public void reload(){
        try {
            this.config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
