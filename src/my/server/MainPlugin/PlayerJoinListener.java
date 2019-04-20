package my.server.MainPlugin;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        //event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
        Player p = event.getPlayer();

        if (p.hasPlayedBefore()) {
            if (p.getWorld().getName().equals("world")) {
                new RandomSpawn().teleport(p);
                List biomes = Arrays.asList(Biome.OCEAN, Biome.DEEP_OCEAN, Biome.FROZEN_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.COLD_OCEAN,
                        Biome.DEEP_COLD_OCEAN, Biome.LUKEWARM_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.WARM_OCEAN, Biome.DEEP_WARM_OCEAN);
                if (biomes.contains(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()))) {
                    p.getInventory().addItem(new ItemStack(Material.LEGACY_BOAT, 1));
                }
            }
        }
    }
}
