package my.server.MainPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener
{
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent hitEvent)
    {
        //if (hitEvent.getEntity() != null && hitEvent.getDamager() != null ) {

        Entity damagedE = hitEvent.getEntity();
        Entity damagerE = hitEvent.getDamager();

            if (damagerE instanceof Player && damagedE instanceof Player) {

                Player damager = (Player) hitEvent.getEntity();
                Player damaged = (Player) hitEvent.getEntity();

                if (damaged.getMaxHealth() == 2) {
                    damager.sendRawMessage("You can't hit players with 1 health");
                    hitEvent.setCancelled(true);
                } else if (damager.getMaxHealth() == 2) {
                    damager.sendRawMessage("You can't hit other players while you have 1 health");
                }
            }
        //}
    }
}