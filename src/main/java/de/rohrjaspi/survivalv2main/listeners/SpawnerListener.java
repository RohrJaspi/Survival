package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class SpawnerListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack itemHand = p.getItemInHand();
        ItemMeta im = itemHand.getItemMeta();
        Random rndm = new Random();
        int i = rndm.nextInt(100);


        if (im==null) return;
        if (!im.getEnchants().containsKey(Enchantment.SILK_TOUCH)) return;
        if (!(e.getBlock().getType().equals(Material.SPAWNER))) return;
        if (i == 42) {
            Item dropItem = p.getWorld().dropItem(p.getLocation(), new ItemCreator().material(Material.SPAWNER).displayName("§bSpawner").withGlow(true).build());
            dropItem.setPickupDelay(0);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§8§m--------------§8[§c§lOuterdepths§8]§8§m--------------");
                all.sendMessage("");
                all.sendMessage("  §7Der Spieler §c" + p.getName() + "§7 hat soeben einen Spawner");
                all.sendMessage("  §7gefunden! §7§o(0,01%)");
                all.sendMessage("");
                all.sendMessage("§8§m--------------§8[§c§lOuterdepths§8]§8§m--------------");
                all.playSound(all.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 1);
            }
        }
    }
}
