package my.server.MainPlugin;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import de.butzlabben.world.wrapper.WorldPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class BlockEventListener implements Listener {
    private WorldGuardPlugin wg = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
    @EventHandler
    public void onBlockBreak (BlockBreakEvent event) {
        Player p = event.getPlayer();
        String worldname = p.getWorld().getName();
        WorldPlayer wp = new WorldPlayer(p, worldname);
        if (wp.isOnSystemWorld()) {
            if ((wp.isOwnerofWorld() || wp.isMemberofWorld(worldname))) {
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));
                Location blockLoc = event.getBlock().getLocation();
                BlockVector3 loc = BlockVector3.at(blockLoc.getX(), blockLoc.getY(), blockLoc.getZ());
                if (regions != null) {
                    ApplicableRegionSet set = regions.getApplicableRegions(loc);
                    if (!(set.size() > 0)) {
                        p.sendMessage("You have to claim this place to build");
                        event.setCancelled(true);
                    }
                }
            }
        } else {
            List blocks = Arrays.asList(Material.STONE,Material.DIRT,Material.GRASS_BLOCK,Material.PODZOL,Material.MYCELIUM);
            if (blocks.contains(event.getBlock().getType()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {
        Player p = event.getPlayer();
        String worldname = p.getWorld().getName();
        WorldPlayer wp = new WorldPlayer(p, worldname);
        if (wp.isOnSystemWorld()) {
            if ((wp.isOwnerofWorld() || wp.isMemberofWorld(worldname))) {
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));
                Location blockLoc = event.getBlock().getLocation();
                BlockVector3 loc = BlockVector3.at(blockLoc.getX(), blockLoc.getY(), blockLoc.getZ());
                if (regions != null) {
                    ApplicableRegionSet set = regions.getApplicableRegions(loc);
                    if (!(set.size() > 0)) {
                        p.sendMessage("You have to claim this place to build");
                        event.setCancelled(true);
                    }
                }
            }
        } else {
            event.setCancelled(true);
            List blocks = Arrays.asList(Material.LADDER);
            if (blocks.contains(event.getBlock().getType()))
                event.setCancelled(false);
        }
    }
}
