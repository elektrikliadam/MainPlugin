package my.server.MainPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedListener implements Listener {
    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        //
    }
    @EventHandler
    public void onBedExit(PlayerBedLeaveEvent event) {
        event.setSpawnLocation(false);
    }
}
