package my.server.MainPlugin;

import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Arrays;

public class SpawnerListener implements Listener {
    @EventHandler
    public void onCreatureSpawnEvent (CreatureSpawnEvent event) {
        final String[] worlds = {"world","world_nether","world_the_end"};
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
            event.getEntity().remove();
        }
        if (Arrays.asList(worlds).contains(event.getLocation().getWorld().getName())) {
//            if (event.getEntity() instanceof Animals) {
//                event.getEntity().remove();
//            }
        } else {
            if (event.getEntity() instanceof Monster) {
                event.getEntity().remove();
            }
        }
    }
}
