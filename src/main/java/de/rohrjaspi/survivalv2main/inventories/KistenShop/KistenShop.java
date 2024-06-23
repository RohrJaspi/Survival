package de.rohrjaspi.survivalv2main.inventories.KistenShop;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class KistenShop implements Listener {


    public static Inventory ChestPreview() {
        Inventory inv = Bukkit.createInventory(null, 9*3, S.P + "KistenShop");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
        }
        // Colorful Glasspanes
        inv.setItem(1, new ItemCreator().material(Material.LIGHT_BLUE_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(4, new ItemCreator().material(Material.LIME_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(7, new ItemCreator().material(Material.ORANGE_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(19, new ItemCreator().material(Material.LIGHT_BLUE_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(22, new ItemCreator().material(Material.LIME_STAINED_GLASS_PANE).displayName("§3 ").build());
        inv.setItem(25, new ItemCreator().material(Material.ORANGE_STAINED_GLASS_PANE).displayName("§3 ").build());

        // Items
        inv.setItem(10, new ItemCreator().material(Material.SCULK_SHRIEKER).lore(Arrays.asList("§7(§6i§7) Version §b1","§8➸ §bPreis:§7 60.000€")).displayName("§3§lWarden-Kiste").build());
        inv.setItem(13, new ItemCreator().material(Material.ENCHANTING_TABLE).lore(Arrays.asList("§7(§6i§7) Version §b1","§8➸ §bPreis:§7 30.000€")).displayName("§a§lSurvival-Kiste").build());
        inv.setItem(16, new ItemCreator().material(Material.CHEST).lore(Arrays.asList("§7(§6i§7) Version §b1","§8➸ §bPreis:§7 10.000€")).displayName("§6§lVote-Kiste").build());

        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().contains("KistenShop")) {
            e.setCancelled(true);

            switch (e.getRawSlot()) {
                case 10:
                    if (PlayerSQL.getCoins(p.getUniqueId()) >= 60000) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + p.getName() + " wardenkiste 1");
                        PlayerSQL.removeCoins(p.getUniqueId(), 60000);
                        p.sendMessage(S.P + "Du hast Erfolgreich die §3§lWarden-Kiste §7gekauft!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                        p.closeInventory();
                    } else {
                        p.sendMessage(S.P + "Du hast nicht genug Münzen!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        p.closeInventory();
                    }
                    break;
                case 13:
                    if (PlayerSQL.getCoins(p.getUniqueId()) >= 30000) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + p.getName() + " survivalkiste 1");
                        PlayerSQL.removeCoins(p.getUniqueId(), 30000);
                        p.sendMessage(S.P + "Du hast Erfolgreich die §a§lSurvival-Kiste §7gekauft!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                        p.closeInventory();
                    } else {
                        p.sendMessage(S.P + "Du hast nicht genug Münzen!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        p.closeInventory();
                    }
                    break;
                case 16:
                   if (PlayerSQL.getCoins(p.getUniqueId()) >= 10000) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + p.getName() + " vote 1");
                        PlayerSQL.removeCoins(p.getUniqueId(), 10000);
                        p.sendMessage(S.P + "Du hast Erfolgreich die §6Vote-Kiste §7gekauft!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                        p.closeInventory();
                    } else {
                        p.sendMessage(S.P + "Du hast nicht genug Münzen!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        p.closeInventory();
                    }
                    p.sendMessage(S.P + "Diese Kiste gibt es noch nicht!");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    p.closeInventory();
                    break;
            }
        }

    }

}
