package my.server.MainPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SleepListener implements Runnable {
    static HashMap<String,Boolean> restored = new HashMap<>();
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isSleeping()) {
                if (p.getMaxHealth()<20) {
                    if (p.getTotalExperience() > 300) {
                        p.sendMessage("Your Max Health is increasing");
                        p.setMaxHealth(p.getMaxHealth() + 1);
                        p.giveExp(-300);
                        restored.put(p.getUniqueId().toString(), true);
                    } else {
                        if (restored.get(p.getUniqueId().toString()) != null) {
                            if (restored.get(p.getUniqueId().toString())) {
                                p.sendMessage("Your Max health has increased.");
                                restored.put(p.getUniqueId().toString(), false);
                            } else {
                                p.sendMessage("Your exp level is not enough to increase your Max Health.");
                            }
                        } else {
                            p.sendMessage("Your exp level is not enough to increase your Max Health.");
                        }
                        p.wakeup(true);
                    }
                } else {
                    p.sendMessage("You have Max Health.");
                    p.wakeup(true);
                }
            }
        }
    }
}