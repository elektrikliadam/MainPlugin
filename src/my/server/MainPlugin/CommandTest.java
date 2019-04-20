package my.server.MainPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandTest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            p.getInventory().addItem(new ItemStack(Material.DIAMOND,1));
            p.sendMessage("You took 1 diamond for completing a achievement.");
//            p.setMaxHealth(20.0);
////            p.performCommand("ws leave");
//            //WorldPlayer wp = new WorldPlayer(p, worldname);
//            //World w = Bukkit.getWorld(p.getWorld().getName());
////            new RandomSpawn().teleport(p);
//            World w = p.getWorld();
//            Location test = new Location(w,356,0,396);
//            p.teleport(new Location(w,356,w.getHighestBlockYAt(test),396));
           // SystemWorld.tryUnloadLater(w);

        }
        return true;
    }
}
