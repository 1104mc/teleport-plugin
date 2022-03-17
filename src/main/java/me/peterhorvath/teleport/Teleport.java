package me.peterhorvath.teleport;

import me.peterhorvath.teleport.commands.Tp;
import me.peterhorvath.teleport.config.Config;
import me.peterhorvath.teleport.events.InventoryClick;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Teleport extends JavaPlugin {
    public static File configDir;
    public static final Config waypointConfig = new Config();

    @Override
    public void onEnable() {
        // Plugin startup logic
        configDir = getDataFolder();
        Objects.requireNonNull(getCommand("tp")).setExecutor(new Tp());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
