package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.Waypoint;
import org.bukkit.Material;
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

    public Waypoint[] getAllWaypoints(){
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        this.config.getConfigurationSection("places").getKeys(false).forEach(waypoint -> {
            String wp_path = "places." + waypoint;
            Waypoint wp = new Waypoint(Material.getMaterial(this.config.getString(wp_path + ".type")),
                    ColorManager.getColorByString(this.config.getString(wp_path + ".color")),
                    this.config.getLocation(wp_path + ".location"),
                    this.config.getString(wp_path + ".name"));
            waypoints.add(wp);
        });
        return (Waypoint[]) waypoints.toArray();
    }

    public void reload(){
        try {
            this.config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
