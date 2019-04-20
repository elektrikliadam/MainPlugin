package my.server.MainPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    public static Economy econ = null;
    public Plugin wg;

    @Override
    public void onEnable() {
        plugin = this;
        setupEconomy();
        getWorldGuard();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MAIN PLUGIN");
        this.getCommand("randomspawn").setExecutor(new CommandTest());
        this.getCommand("claim").setExecutor(new CommandClaim());
        this.getCommand("test").setExecutor(new CommandTest());
        this.getCommand("leave").setExecutor(new CommandLeave());
        this.getCommand("home").setExecutor(new CommandHome());
        this.getCommand("join").setExecutor(new CommandJoin());


        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnerListener(),this);
        getServer().getPluginManager().registerEvents(new DeathListener(),this);
        getServer().getPluginManager().registerEvents(new RespawnEventListener(),this);
        getServer().getPluginManager().registerEvents(new BedListener(),this);
        getServer().getPluginManager().registerEvents(new BlockEventListener(),this);
        getServer().getPluginManager().registerEvents(new BreedListener(),this);




        getServer().getScheduler().scheduleSyncRepeatingTask(this,new SleepListener(),0L,20L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this,new ServerEvents(),0L,900L);

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ!=null;
    }

    public WorldGuardPlugin getWorldGuard() {
        wg = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (wg == null || !(wg instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) wg;
    }




}
