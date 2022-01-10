package net.frozenorb.guardian.listener;

import net.frozenorb.guardian.GuardianPlugin;
import net.frozenorb.guardian.violation.Violation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.GuardianEvent;
import org.bukkit.event.player.GuardianEvent.Cheat;

public class PlayerListener implements Listener {
   private GuardianPlugin plugin;

   @EventHandler
   public void onGuardian(GuardianEvent event) {
      Player player = event.getPlayer();
      Violation violation = this.plugin.getViolation(player);
      violation.setViolation(event.getCheat(), violation.getViolation(event.getCheat()) + 1);
      this.plugin.getServer().getOnlinePlayers().stream().filter((p) -> {
         return p.hasPermission("guardian.alerts");
      }).forEach((p) -> {
         p.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "⚠" + ChatColor.RED + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.GRAY + " might be using " + this.getCheatName(event.getCheat()) + " (" + event.getLevel().toString() + ") [" + violation.getViolation(event.getCheat()) + "]");
      });
      if (violation.getViolation(event.getCheat()) >= 10) {
         this.ban(player);
      }

   }

   private void ban(Player player) {
      this.plugin.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "-------------------------------------------");
      this.plugin.getServer().broadcastMessage(ChatColor.RED + "Guardian removed removed " + ChatColor.YELLOW + player.getName() + ChatColor.RED + " from the network.");
      this.plugin.getServer().broadcastMessage(ChatColor.RED + "Reason: " + ChatColor.YELLOW + "Unfair Advantage");
      this.plugin.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "-------------------------------------------");
      this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), "ban " + player.getName() + " Cheating -s");
   }

   private String getCheatName(Cheat cheat) {
      switch(cheat) {
      case REGENERATION:
         return "Regeneration";
      case DEBUG:
         return "Debug";
      case HOVER:
         return "Hover";
      case PHASE:
         return "Phase";
      case REACH:
         return "Reach";
      case TIMER:
         return "Timer";
      case GENERAL:
         return "General";
      case NO_FALL:
         return "No Fall";
      case FAST_USE:
         return "Fast Use";
      case CRITICALS:
         return "Criticals";
      case FLY_HACKS:
         return "Flight";
      case KILL_AURA:
         return "Kill Aura";
      case SPEED_HACKS:
         return "Speed";
      case AUTO_CLICKER:
         return "Auto Clicker";
      case CLIENT_MODIFICATIONS:
         return "Client Modifications";
      default:
         return "Unknown";
      }
   }

   public PlayerListener(GuardianPlugin plugin) {
      this.plugin = plugin;
   }
}
