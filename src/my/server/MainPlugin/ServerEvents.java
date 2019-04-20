package my.server.MainPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.broadcastMessage;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Bukkit.getServer;

public class ServerEvents implements Runnable {
    static boolean eventActive = false;
    private String[] events = {"Spider Kill Event","Creeper Kill Event","Zombie Kill Event"};
    static String selectedEvent;
    @Override
    public void run() {
        Random rand = new Random();
        int n = rand.nextInt(3);
        selectedEvent = events[n];
        broadcastMessage(String.format("EVENT %s STARTED, /join",selectedEvent));
        eventActive = true;
        getScheduler().runTaskLater(Main.plugin, new BukkitRunnable() {
            @Override
            public void run() {
                broadcastMessage(String.format("%s is finished",selectedEvent));
                eventActive = false;
            }
        },600L);
    }
}