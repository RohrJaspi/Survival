package de.rohrjaspi.survivalv2main.home;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.commands.HomeCommand;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
public class HomeListener implements Listener {

    @EventHandler
    public void onHome(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().contains("Homes")) {
            e.setCancelled(true);
            if(e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE) || e.getCurrentItem().getType().equals(Material.AIR)) return;
            Home home = PlayerSQL.getHome(p.getUniqueId(), ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
            if (e.isLeftClick()) {
                p.teleport(home.getLoc());
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1, 1);
                p.sendMessage(S.P + "Du wurdest an dein Home teleportiert!");
            }
            if (e.isRightClick()) {
                String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                if(PlayerSQL.getHome(p.getUniqueId(),name) != null) {
                    PlayerSQL.removeHome(p.getUniqueId(), name);
                    p.sendMessage(S.P + "Dein Home §e" + name + " §7wurde erfolgreich gelöscht.");
                    p.openInventory(HomeCommand.homes(p));
                }
            }
        }
    }
}
