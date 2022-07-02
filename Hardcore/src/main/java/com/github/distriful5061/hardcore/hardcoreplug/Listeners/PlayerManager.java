package com.github.distriful5061.hardcore.hardcoreplug.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class PlayerManager implements Listener {
    private static HashMap<Player, Integer> LegsHealth = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){

    }
}
