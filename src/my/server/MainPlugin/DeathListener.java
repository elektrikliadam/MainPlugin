package my.server.MainPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getDeathMessage().endsWith("suffocated in a wall")) {
            Player p = event.getEntity();
            if (p.getMaxHealth() > 2) {
                p.setMaxHealth(p.getMaxHealth() - 1);
                p.sendMessage(ChatColor.RED + "You've lost half hearth because of dying");
                if (p.getKiller() != null) {
                    Player killer = p.getKiller();
                    if (killer.getMaxHealth() < 20) {
                        killer.setMaxHealth(killer.getMaxHealth() + 1);
                        killer.sendMessage(ChatColor.RED + "You've earned half hearth because of killing");
                    }
                }
            }
        }
    }
}