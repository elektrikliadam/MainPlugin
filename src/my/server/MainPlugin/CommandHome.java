package my.server.MainPlugin;

import de.butzlabben.world.config.DependenceConfig;
import de.butzlabben.world.config.MessageConfig;
import de.butzlabben.world.wrapper.SystemWorld;
import de.butzlabben.world.wrapper.WorldPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            String worldname = p.getWorld().getName();

            DependenceConfig dc = new DependenceConfig(p);
            if (!dc.hasWorld()) {
                p.sendMessage(MessageConfig.getNoWorldOwn());
                return true;
            }
            WorldPlayer wp = new WorldPlayer(p, worldname);
            if (wp.isOnSystemWorld()) {
                SystemWorld.tryUnloadLater(Bukkit.getWorld(worldname));
            }
            SystemWorld sw = SystemWorld.getSystemWorld(dc.getWorldname());
            if (sw == null) {
                p.sendMessage(MessageConfig.getNoWorldOwn());
                return true;
            }
            if (sw.isLoaded()) {
                sw.teleportToWorldSpawn(p);
            } else {
                sw.load(p);
            }
        }
        return  true;
    }
}
