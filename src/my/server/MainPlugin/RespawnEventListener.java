package my.server.MainPlugin;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;


public class RespawnEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawnEvent(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals("world")) {
            World w = p.getWorld();
            event.setRespawnLocation(new RandomSpawn().randomLocation(w));
            List biomes = Arrays.asList(Biome.OCEAN, Biome.DEEP_OCEAN, Biome.FROZEN_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.COLD_OCEAN,
                    Biome.DEEP_COLD_OCEAN, Biome.LUKEWARM_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.WARM_OCEAN, Biome.DEEP_WARM_OCEAN);
            if (biomes.contains(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()))) {
                if (!p.getInventory().containsAtLeast(new ItemStack(Material.LEGACY_BOAT),1)) {
                    p.getInventory().addItem(new ItemStack(Material.LEGACY_BOAT,1));
                }
            }
        }
    }
}
