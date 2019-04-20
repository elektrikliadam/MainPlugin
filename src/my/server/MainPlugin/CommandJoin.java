package my.server.MainPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static my.server.MainPlugin.ServerEvents.eventActive;
import static my.server.MainPlugin.ServerEvents.selectedEvent;
import static org.bukkit.Bukkit.getServer;

public class CommandJoin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (eventActive) {
                getServer().dispatchCommand(getServer().getConsoleSender(),"manuaddp " + p.getName()+ " quests.take");
                p.performCommand("quests take "+ selectedEvent);
                getServer().dispatchCommand(getServer().getConsoleSender(),"manudelp " + p.getName()+ " quests.take");
            } else {
                p.sendMessage("There is no event right now");
            }
        }
        return true;
    }
}
