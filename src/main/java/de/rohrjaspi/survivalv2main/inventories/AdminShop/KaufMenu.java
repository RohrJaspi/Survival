package de.rohrjaspi.survivalv2main.inventories.AdminShop;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KaufMenu implements Listener {

	private Inventory inventory;
	private static ItemStack item;
	private static long price;

	public KaufMenu() {
	}

	public static KaufMenu setKaufMenu(KaufMenu kaufMenu, ItemStack item, long price) {
		kaufMenu.item = item;
		kaufMenu.price = price;



		kaufMenu.inventory = Bukkit.createInventory(null, 9 * 3, "Kaufen");

		for(int i = 0; i < kaufMenu.inventory.getSize(); i++) {
			kaufMenu.inventory.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
		}

		ItemStack kaufen = new ItemCreator().material(Material.GREEN_WOOL).displayName("§aKaufen").build();
		ItemStack abbrechen = new ItemCreator().material(Material.RED_WOOL).displayName("§cAbbrechen").build();
		ItemStack itemCopy = new ItemCreator().material(kaufMenu.item.getType()).displayName("§a§lKaufe für §f" + price + " §a§lEuro").build();

		kaufMenu.inventory.setItem(10, abbrechen);
		kaufMenu.inventory.setItem(13, itemCopy);
		kaufMenu.inventory.setItem(16, kaufen);


		return kaufMenu;
	}

	public void open(Player player) {
		player.openInventory(inventory);
	}

	@EventHandler
	public void InvClick(InventoryClickEvent e) {
		if (!(e.getView().getTitle().equals("Kaufen"))) {
			return;
		}
			e.setCancelled(true);
		if (e.getSlot() == 16) {
			Player p = (Player) e.getWhoClicked();
			UUID uuid = p.getUniqueId();

			if (item == null) {
				System.out.println("Item is null!");
				return;
			}

			if (PlayerSQL.getCoins(uuid) >= price) {
				PlayerSQL.removeCoins(uuid, price);

				p.getInventory().addItem(item);

				p.closeInventory();


				p.sendMessage(S.P + "§aDu hast " + "§f§l" + item.getType().toString() + " gekauft.");
			} else {
				p.sendMessage(S.P + "§4Du hast nicht genügend Geld.");
			}
		} else if (e.getSlot() == 10) {
			Player p = (Player) e.getWhoClicked();

			p.closeInventory();
			
		}
	}

}
