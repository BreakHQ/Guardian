package net.frozenorb.guardian;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.frozenorb.guardian.listener.PlayerListener;
import net.frozenorb.guardian.violation.Violation;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GuardianPlugin extends JavaPlugin {
   private Map<UUID, Violation> violationMap = new HashMap();

   public void onEnable() {
      this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
   }

   public Violation getViolation(Player player) {
      return (Violation)this.violationMap.getOrDefault(player.getUniqueId(), new Violation());
   }
}
