package com.github.distriful5061.hardcore.hardcoreplug;

import com.github.distriful5061.hardcore.hardcoreplug.Listeners.Legs;
import com.github.distriful5061.hardcore.hardcoreplug.Listeners.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class HardcorePlug extends JavaPlugin {
    /* 仕様 ,後ろは壊死時に発生する仕様
     * 頭の耐久値(Max100), 耐久0で死亡
     * 胸の耐久値(Max100), 耐久0で死亡
     * 左腕の耐久値(Max100), 5秒ごとに。ダメージが0.7倍され、壊死していない部位へ分散
     * 右腕の耐久値(Max100), 弓のADS時に、1秒ごとに画面ブレ。雪玉やエンダーパールを持っている場合は、3秒ごとにブレる。ダメージが0.7倍され、壊死していない部位へ分散
     * お腹の耐久値(Max100),　水分、エネルギー減少速度上昇。ピッチ150のゾンビのうめき声が自分から発生する。ダメージが1.5倍され、壊死していない部位へ分散
     * 足の耐久値(Max100), ジャンプ時のスタミナ減少増加。走れなくなる。ダメージが1.0倍され、壊死していない部位へ分散
     * スタミナ(Max100(内部1000)), 0で、8秒ごとに大きく画面がブレる。走る、ジャンプができなくなる。
     * 水分(Max100), 0で走れなくなる
     * エネルギー(Max100), 0で走れなくなる
     */

    private static HardcorePlug plugin;
    private static FileConfiguration config = null;
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

        /*
        for (String key : config.getConfigurationSection("MapKeys").getKeys(false)) {
            getLogger().info(key + config.getString("MapKeys." + key));
        }
         */
    }

    public PluginManager getPlug(){
        return Bukkit.getServer().getPluginManager();
    }

    public static String[] getPlayerData(Player p){
        String[] result = new String[9];
        String PlayerUuid = p.getUniqueId().toString();
        /* 仕様 ,後ろは壊死時に発生する仕様
         * 頭の耐久値(Max100), 耐久0で死亡
         * 胸の耐久値(Max100), 耐久0で死亡
         * 左腕の耐久値(Max100), 5秒ごとに。ダメージが0.7倍され、壊死していない部位へ分散
         * 右腕の耐久値(Max100), 弓のADS時に、1秒ごとに画面ブレ。雪玉やエンダーパールを持っている場合は、3秒ごとにブレる。ダメージが0.7倍され、壊死していない部位へ分散
         * お腹の耐久値(Max100),　水分、エネルギー減少速度上昇。ピッチ150のゾンビのうめき声が自分から発生する。ダメージが1.5倍され、壊死していない部位へ分散
         * 足の耐久値(Max100), ジャンプ時のスタミナ減少増加。走れなくなる。ダメージが1.0倍され、壊死していない部位へ分散
         * スタミナ(Max100(内部1000)), 0で、8秒ごとに大きく画面がブレる。走る、ジャンプができなくなる。
         * 水分(Max100), 0で走れなくなる
         * エネルギー(Max100), 0で走れなくなる
         */


        result[0] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".HeadHealth", 100)));
        result[1] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".ChestHealth", 100)));
        result[2] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".LeftArmHealth", 100)));
        result[3] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".RightArmHealth", 100)));
        result[4] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".StomachHealth", 100)));
        result[5] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".LegsHeath", 100)));
        result[6] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".Stamina", 1000)));
        result[7] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".Water", 100)));
        result[8] = (String.valueOf(config.getInt("MapKeys."+PlayerUuid+".Energy", 100)));

        return result;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
