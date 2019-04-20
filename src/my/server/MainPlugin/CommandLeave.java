package my.server.MainPlugin;

import de.butzlabben.world.config.MessageConfig;
import de.butzlabben.world.config.PluginConfig;
import de.butzlabben.world.wrapper.SystemWorld;
import de.butzlabben.world.wrapper.WorldPlayer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            String worldname = p.getWorld().getName();
            WorldPlayer wp = new WorldPlayer(p, worldname);

            if (wp.isOnSystemWorld()) {
                // Extra safety for #2
                if (PluginConfig.getSpawn().getWorld() == null) {
                    Bukkit.getConsoleSender().sendMessage(PluginConfig.getPrefix() + "§cThe spawn is not properly set");
                    p.sendMessage(PluginConfig.getPrefix() + "§cThe spawn is not properly set");
                    return true;
                }

                new RandomSpawn().teleport(p);
                p.setGameMode(PluginConfig.getSpawnGamemode());
                World w = Bukkit.getWorld(p.getWorld().getName());
                SystemWorld.tryUnloadLater(w);
            } else {
                p.sendMessage(MessageConfig.getNotOnWorld());
            }
        }
        return  true;
    }
}


