package de.rohrjaspi.survivalv2main.inventories.Belohnung;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import de.rohrjaspi.survivalv2main.sql.BelohnungSQL;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Belohnung implements Listener {


    public static Inventory RewardInv() {
        Inventory inv = Bukkit.createInventory(null, 9*3, S.P + "Belohnungen");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
        }

        inv.setItem(1, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(4, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(7, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(19, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(22, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(25, new ItemCreator().material(Material.CYAN_STAINED_GLASS_PANE).displayName("§3 ").build());

        inv.setItem(10, new ItemCreator().material(Material.DISC_FRAGMENT_5).displayName("§3Tägliche Belohnung").build());
        inv.setItem(13, new ItemCreator().material(Material.ECHO_SHARD).displayName("§3Wöchent§bliche §3Belohnung").build());
        inv.setItem(16, new ItemCreator().material(Material.RECOVERY_COMPASS).displayName("§3§lMonatliche §b§lBelohnung").build());

        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Long penis = System.currentTimeMillis();

        if (e.getView().getTitle().contains("Belohnungen")) {
            e.setCancelled(true);

            if (e.getRawSlot() == 10) {
                if ((BelohnungSQL.getDaily(p.getUniqueId())+86400000L) <= penis) {
                    BelohnungSQL.setDaily(p.getUniqueId(), penis);
                    Lol(p, 1000);
                    PlayerSQL.addCoins(p.getUniqueId(), 1000);
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                } else {
                    long remaining = System.currentTimeMillis() - BelohnungSQL.getDaily(p.getUniqueId());
                    long h = (86400000L-remaining)/3600000L;
                  p.sendMessage(S.P + "Du kannst die nächste Belohnung in §c" + h + "h §7einsammeln!");
                  p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                }
            } else if (e.getRawSlot() == 13) {
                if ((BelohnungSQL.getWeekly(p.getUniqueId())+(86400000L*7)) <= penis) {
                    BelohnungSQL.setWeekly(p.getUniqueId(), penis);
                    Lol(p, 10000);
                    PlayerSQL.addCoins(p.getUniqueId(), 10000);
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                } else {
                    long remaining = System.currentTimeMillis() - BelohnungSQL.getWeekly(p.getUniqueId());
                    long d = ((86400000L*7)-remaining)/86400000L;
                    long h = (((86400000L*7)-remaining)%86400000L)/3600000L;
                    p.sendMessage(S.P + "Du kannst die nächste Belohnung in §c"+ d + "d " + h + "h §7einsammeln!");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                }
            } else if (e.getRawSlot() == 16) {
                if ((BelohnungSQL.getMonthly(p.getUniqueId())+(86400000L *30)) <= penis) {
                    BelohnungSQL.setMonthly(p.getUniqueId(), penis);
                    Lol(p, 20000);
                    PlayerSQL.addCoins(p.getUniqueId(), 20000);
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                } else {
                    long remaining = System.currentTimeMillis() - BelohnungSQL.getMonthly(p.getUniqueId());
                    long d = ((86400000L*30)-remaining)/86400000L;
                    long h = (((86400000L*30)-remaining)%86400000L)/3600000L;
                    p.sendMessage(S.P + "Du kannst die nächste Belohnung in §c"+ d + "d " + h + "h §7einsammeln!");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                }
            }
        }

    }

    private static void Lol(Player all, int coins) {
        all.sendMessage("§8§m--------------§8[§c§lOuterdepths§8]§8§m--------------");
        all.sendMessage("");
        all.sendMessage("  §7Du hast deine Belohnung eingesammelt!");
        all.sendMessage("  §7Du hast §6" + coins  + "€§7 bekommen!");
        all.sendMessage("");
        all.sendMessage("§8§m--------------§8[§c§lOuterdepths§8]§8§m--------------");
    }
}
