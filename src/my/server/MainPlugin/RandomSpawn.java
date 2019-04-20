package my.server.MainPlugin;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;

public class RandomSpawn {
    private Chunk chunk;

    public void teleport (Player p) {
        if (p.teleport(randomLocation(getServer().getWorld("world")))) {
            System.out.println("RANDOMSPAWN");
        } else {
            System.out.println("SSSS");
        }
    }

    public Location randomLocation(World w) {

        final int minDistance = 100;
        final int maxDistance = 700;

        int x = generateInt(minDistance, maxDistance);
        int z = generateInt(minDistance, maxDistance);
        int y = 0;

        Location loc = new Location(w, x, y, z);
        chunk = loc.getChunk();

        if (!chunk.isLoaded()) {
            chunk.load(true);
        }

        loc.setY(w.getHighestBlockYAt(loc));

        Block under = w.getBlockAt(loc);

        if (under.getType() == Material.LAVA) {
            under.setType(Material.STONE);
            loc.setY(loc.getY() + 1);
        }

        return loc;
    }


     private int generateInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
