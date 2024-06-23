package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.Utils.Maths;
import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class PreviewListener implements Listener {
    @EventHandler
    public void onPreview(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof Player && p.isSneaking()) {
            Inventory inv = Bukkit.createInventory(null, 9*3, S.P + "Spieler-Preview");
            Player t = (Player) e.getRightClicked();
            ItemStack Skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) Skull.getItemMeta();
            User user = Main.getInstance().getLuckPerms().getUserManager().getUser(t.getUniqueId());
            String prefix = user.getCachedData().getMetaData().getPrefix();
            meta.setOwner(t.getName());
            meta.setLore(Arrays.asList("","§8➸ §6" + Maths.asString(PlayerSQL.getCoins(t.getUniqueId())) + "€", ""));
            meta.setDisplayName(prefix.replace("&", "§") + "§7" + t.getName());
            Skull.setItemMeta(meta);

            for(int i = 0; i < inv.getSize(); i++) {
                inv.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
            }
            inv.setItem(0, new ItemCreator().material(Material.RED_STAINED_GLASS_PANE).displayName("§c ").build());
            inv.setItem(8, new ItemCreator().material(Material.RED_STAINED_GLASS_PANE).displayName("§c ").build());
            inv.setItem(18, new ItemCreator().material(Material.RED_STAINED_GLASS_PANE).displayName("§c ").build());
            inv.setItem(26, new ItemCreator().material(Material.RED_STAINED_GLASS_PANE).displayName("§c ").build());
            inv.setItem(14, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Main-Hand: §7Leer ").build());
            inv.setItem(10, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Helm-Slot: §7Leer ").build());
            inv.setItem(11, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Brustplaten-Slot: §7Leer ").build());
            inv.setItem(12, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Hosen-Slot: §7Leer ").build());
            inv.setItem(13, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Schuh-Slot: §7Leer ").build());
            inv.setItem(16, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§8Off-Hand: §7Leer ").build());

            if (t.getInventory().getItemInOffHand().getType() != Material.AIR) {
                ItemStack OffHand = t.getInventory().getItemInOffHand();
                inv.setItem(16, OffHand);
            }
            if (t.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack MainHand = t.getInventory().getItemInMainHand();
                inv.setItem(14, MainHand);
            }
            if (t.getInventory().getHelmet() != null) {
                ItemStack Helmet = t.getInventory().getHelmet();
                inv.setItem(10, Helmet);
            }
            if (t.getInventory().getChestplate() != null) {
                ItemStack Chestplate = t.getInventory().getChestplate();
                inv.setItem(11, Chestplate);
            }
            if (t.getInventory().getLeggings() != null) {
                ItemStack Leggings = t.getInventory().getLeggings();
                inv.setItem(12, Leggings);
            }
            if (t.getInventory().getBoots() != null) {
                ItemStack Boots = t.getInventory().getBoots();
                inv.setItem(13, Boots);
            }
            inv.setItem(15, Skull);
            p.openInventory(inv);

        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getView().getTitle().contains("Spieler-Preview")) {
            e.setCancelled(true);
        }
    }

}
