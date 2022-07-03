package com.github.distriful5061.hardcore.hardcoreplug.Listeners;

import com.github.distriful5061.hardcore.hardcoreplug.HardcorePlug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class PlayerManager implements Listener {
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
    private static HashMap<Player, Integer> HeadHealth = new HashMap<>();
    private static HashMap<Player, Integer> ChestHealth = new HashMap<>();
    private static HashMap<Player, Integer> LeftArmHealth = new HashMap<>();
    private static HashMap<Player, Integer> RightArmHealth = new HashMap<>();
    private static HashMap<Player, Integer> StomachHealth = new HashMap<>();
    private static HashMap<Player, Integer> LegsHealth = new HashMap<>();
    private static HashMap<Player, Integer> Stamina = new HashMap<>();
    private static HashMap<Player, Integer> Water = new HashMap<>();
    private static HashMap<Player, Integer> Energy = new HashMap<>();

    private HashMap<Player, BukkitTask> players = new HashMap<>();

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent e){
        Player p = e.getPlayer();
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        createScoreBoard(e.getPlayer());
        start(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        if(players.containsKey(e.getPlayer())) {
            BukkitTask task = players.get(e.getPlayer());
            task.cancel();
            players.remove(e.getPlayer());
        }
    }

    public void start(Player player) {
        BukkitTask task = new BukkitRunnable() {
            int count = 0;

            public void run() {
                if(count == 7) {
                    count = 0;
                }


                count++;
            }
        }.runTaskTimer(HardcorePlug.getPlugin(), 0L, 10L);

        players.put(player,task);
    }

    public void createScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("Animation","dummy", "10000000");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(String str : HardcorePlug.getPlayerData(player)){
            HardcorePlug.getLogger(str);
        }

        Score score1 = objective.getScore("スコア1");
        score1.setScore(0);

        player.setScoreboard(board);
    }
}
