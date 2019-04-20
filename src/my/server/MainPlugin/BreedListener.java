package my.server.MainPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BreedListener implements Listener {
    @EventHandler
    public void onBedEnter(EntityBreedEvent event) {
        event.setExperience(0);
    }
}
