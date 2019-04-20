package my.server.MainPlugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import de.butzlabben.world.WorldSystem;
import de.butzlabben.world.config.DependenceConfig;
import de.butzlabben.world.config.MessageConfig;
import de.butzlabben.world.config.PluginConfig;
import de.butzlabben.world.wrapper.SystemWorld;
import de.butzlabben.world.wrapper.WorldPlayer;
import de.butzlabben.world.wrapper.WorldTemplate;
import de.butzlabben.world.wrapper.WorldTemplateProvider;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static my.server.MainPlugin.Main.econ;


public class CommandClaim implements CommandExecutor {

    private void create(Player p, WorldTemplate template) {
        Bukkit.getScheduler().runTask(WorldSystem.getInstance(), () -> {
            if (SystemWorld.create(p, template))
                p.sendMessage(MessageConfig.getSettingUpWorld());
        });
    }

    private boolean expDraw (Player p,int expAmount) {
        if(p.getTotalExperience()>expAmount) {
            p.giveExp(-expAmount);
            return true;
        } else {
            p.sendMessage(p.getTotalExperience()+"");
            p.sendMessage("You don't have " + expAmount +" exp to claim");
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("command.claim")) {
                DependenceConfig dc = new DependenceConfig(p);
                if (!dc.hasWorld()) {
                    if (expDraw(p,500)) {
                        WorldTemplate template = WorldTemplateProvider.getInstace().getTemplate(PluginConfig.getDefaultWorldTemplate());
                        create(p, template);
                        p.sendMessage("You have claimed a world for 500 exp");
                    }
                } else {
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

                    if (p.getWorld().getName().equals("world") || p.getWorld().getName().equals("world_nether") || p.getWorld().getName().equals("world_the_end")) {
                        p.sendMessage("You cannot claim in this world");
                    } else {
                        RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));

                        String worldname = p.getWorld().getName();
                        WorldPlayer wp = new WorldPlayer(p, worldname);

                        if (args.length == 0) {
                            Chunk chunk = p.getLocation().getChunk();
                            int bx = chunk.getX() << 4;
                            int bz = chunk.getZ() << 4;
                            BlockVector3 pt1 = BlockVector3.at(bx, 0, bz);
                            BlockVector3 pt2 = BlockVector3.at(bx + 15, 256, bz + 15);
                            BlockVector3 loc = BlockVector3.at(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
                            if ((wp.isOwnerofWorld() || wp.isMemberofWorld(worldname))) {
                                if (regions == null) {
                                    p.sendMessage("Error");
                                    return false;
                                } else {
                                    ApplicableRegionSet set = regions.getApplicableRegions(loc);
                                    if (set.size() == 0) {
                                        if (regions.hasRegion(p.getUniqueId().toString())) {
                                            if (expDraw(p,1000)) {
                                                ProtectedRegion region = new ProtectedCuboidRegion(p.getUniqueId().toString() + chunk.getX() + chunk.getZ() + "", pt1, pt2);
                                                region.setFlag(Flags.BUILD, StateFlag.State.ALLOW);
                                                regions.addRegion(region);
                                                p.sendMessage(String.format("Chunk is claimed for %s coins", (int) 1000));
                                            }
                                        } else {
                                            ProtectedRegion region = new ProtectedCuboidRegion(p.getUniqueId().toString(), pt1, pt2);
                                            region.setFlag(Flags.BUILD, StateFlag.State.ALLOW);
                                            regions.addRegion(region);
                                            p.sendMessage("You have claimed this chunk");
                                        }
                                    } else {
                                        p.sendMessage("Chunk is already owned");
                                    }
                                }
                            } else {
                                p.sendMessage("You cannot claim a region in this world");
                            }
                        }
                    }
                }
                return true;
            } else {
                p.sendMessage("You don't have a permission to claim");
                return true;
            }

        }
        return false;
    }
}

