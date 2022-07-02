package com.github.distriful5061.hardcore.hardcoreplug;

import com.github.distriful5061.hardcore.hardcoreplug.Listeners.Legs;
import com.github.distriful5061.hardcore.hardcoreplug.Listeners.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class HardcorePlug extends JavaPlugin {
    private static HardcorePlug plugin;
    private FileConfiguration config = null;
    private static Logger getLogger;
    public static HardcorePlug getPlugin(){
        return plugin;
    }

    public static void getLogger(String content){
        getLogger.info(content);
    }

    @Override
    public void onEnable() {
        plugin = this;
        getLogger = getLogger();
        getLogger("Welcome...");

        getPlug().registerEvents(new PlayerManager(), this);
        getPlug().registerEvents(new Legs(), this);

        load();
        getLogger("Ended loading Process");
    }

    public void load(){
        plugin.saveDefaultConfig();
        if (config != null) { // configが非null == リロードで呼び出された
            plugin.reloadConfig();
        }
        config = getConfig();

        for (String key : config.getConfigurationSection("MapKeys").getKeys(false)) {
            getLogger().info(key + config.getString("MapKeys." + key));
        }
    }

    public PluginManager getPlug(){
        return Bukkit.getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
