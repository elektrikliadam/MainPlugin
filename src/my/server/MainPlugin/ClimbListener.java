package my.server.MainPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ClimbListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev) {

        Block b1 = ev.getPlayer().getLocation().getBlock();

        if (b1.getType() != Material.AIR)
            return;

        Block b2 = b1.getRelative(BlockFace.UP);
        Location l = ev.getPlayer().getLocation();
        if (b1.getRelative(BlockFace.NORTH).getType().isBlock()
                || b1.getRelative(BlockFace.EAST).getType().isBlock()
                || b1.getRelative(BlockFace.SOUTH).getType().isBlock()
                || b1.getRelative(BlockFace.WEST).getType().isBlock()) {
            double y = l.getY();
            ev.getPlayer().sendBlockChange(b1.getLocation(), Material.VINE, (byte) 0);
            if (y % 1 > .40 && b2.getType() == Material.AIR) {
                ev.getPlayer().sendBlockChange(b2.getLocation(), Material.VINE, (byte) 0);
            }
        }
    }
}