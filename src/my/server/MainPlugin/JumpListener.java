package my.server.MainPlugin;

import com.sk89q.worldedit.event.platform.PlayerInputEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class JumpListener implements Listener {
    @EventHandler
    public void onBedEnter(PlayerToggleFlightEvent event) {
        if(event.isFlying()) {
            event.getPlayer().sendMessage("Double");
        }
    }
}
