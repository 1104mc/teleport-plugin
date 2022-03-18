package me.peterhorvath.teleport;

import me.peterhorvath.teleport.commands.Tp;
import me.peterhorvath.teleport.config.Config;
import me.peterhorvath.teleport.events.InventoryClick;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.EOFException;
import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class Teleport extends JavaPlugin {
    public static File configDir;
    //public static Config waypointConfig;
    public static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        configDir = getDataFolder();
        /*try {
            waypointConfig = new Config();
        } catch (EOFException e) {
            e.printStackTrace();
        }*/
        Objects.requireNonNull(getCommand("tp")).setExecutor(new Tp());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
