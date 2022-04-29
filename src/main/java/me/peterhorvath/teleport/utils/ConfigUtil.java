package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public void reload(){
        try {
            this.config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(){
        try {
            this.config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
