package net.frozenorb.guardian.violation;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.event.player.GuardianEvent.Cheat;

public class Violation {
   private Map<Cheat, Integer> cheatIntegerMap = new HashMap();

   public int getViolation(Cheat cheat) {
      return (Integer)this.cheatIntegerMap.getOrDefault(cheat, 0);
   }

   public void setViolation(Cheat cheat, int vl) {
      this.cheatIntegerMap.put(cheat, vl);
   }
}
