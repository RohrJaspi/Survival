package de.rohrjaspi.survivalv2main.inventories.Shop;

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

public class ShopKaufMenu implements Listener {
		private Inventory inventory;
		private static ItemStack item;
		private static long price;

		private int itemCount;

		public ShopKaufMenu() {
		}

		public static ShopKaufMenu setKaufMenu(ShopKaufMenu shopKaufMenu, ItemStack item, long price) {
			shopKaufMenu.item = item;
			shopKaufMenu.price = price;



			shopKaufMenu.inventory = Bukkit.createInventory(null, 9 * 3, "Shop Bestätigung");

			for(int i = 0; i < shopKaufMenu.inventory.getSize(); i++) {
				shopKaufMenu.inventory.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
			}

			ItemStack kaufen = new ItemCreator().material(Material.GREEN_WOOL).displayName("§aKaufen").build();
			ItemStack abbrechen = new ItemCreator().material(Material.RED_WOOL).displayName("§cAbbrechen").build();
			ItemStack addItems = new ItemCreator().material(Material.GREEN_STAINED_GLASS_PANE).displayName("§a64").build();
			ItemStack itemCopy = new ItemCreator().material(shopKaufMenu.item.getType()).displayName("§a§lKaufe für §f" + price + " §a§lMünzen").build();

			shopKaufMenu.inventory.setItem(10, abbrechen);
			shopKaufMenu.inventory.setItem(13, itemCopy);
			shopKaufMenu.inventory.setItem(15, addItems);
			shopKaufMenu.inventory.setItem(16, kaufen);


			return shopKaufMenu;
		}

		public void open(Player player) {
			player.openInventory(inventory);
		}

		@EventHandler
		public void InvClick(InventoryClickEvent e) {
			if (!(e.getView().getTitle().equals("Shop Bestätigung"))) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 15) {
				itemCount = item.getAmount();
				int toAdd = 64;
				itemCount = itemCount + toAdd;

			}
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

