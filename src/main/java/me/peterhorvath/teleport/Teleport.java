package me.peterhorvath.teleport;

import me.peterhorvath.teleport.commands.AcceptRejectTeleportCommand;
import me.peterhorvath.teleport.commands.Tp;
import me.peterhorvath.teleport.events.InventoryClick;
import me.peterhorvath.teleport.utils.TeleportUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class Teleport extends JavaPlugin {
    public static File configDir;
    public static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        configDir = getDataFolder();
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new Tp());
        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new AcceptRejectTeleportCommand());
        Objects.requireNonNull(getCommand("tpaccept")).setTabCompleter(new AcceptRejectTeleportCommand());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        TeleportUtil.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
