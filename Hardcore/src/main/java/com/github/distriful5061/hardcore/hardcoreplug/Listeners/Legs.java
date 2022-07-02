package com.github.distriful5061.hardcore.hardcoreplug.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Legs implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player player = Bukkit.getPlayer(e.getEntity().getName());
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL){

            }
        }
    }
}
