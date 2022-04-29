package me.peterhorvath.teleport;

import me.peterhorvath.teleport.commands.Reload;
import me.peterhorvath.teleport.commands.Tp;
import me.peterhorvath.teleport.events.InventoryClick;
import me.peterhorvath.teleport.utils.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.EOFException;
import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class Teleport extends JavaPlugin {
    public static File configDir;
    public static ConfigUtil waypointConfig;
    public static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        configDir = getDataFolder();
        waypointConfig = new ConfigUtil();
        Objects.requireNonNull(getCommand("tp")).setExecutor(new Tp());
        getCommand("reload").setExecutor(new Reload());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
