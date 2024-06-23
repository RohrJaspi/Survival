package de.rohrjaspi.survivalv2main.inventories.RangInventar;

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

public class RangKaufMenu implements Listener {

	private Inventory rangInventory;
	private static ItemStack rangItem;
	private static long price;
	private static String rang;

	public RangKaufMenu() {
	}

	public static RangKaufMenu setRangKaufMenu(RangKaufMenu rangKaufMenu, ItemStack item, long price, String rang) {
		rangKaufMenu.rangItem = item;
		rangKaufMenu.price = price;
		rangKaufMenu.rang = rang;



		rangKaufMenu.rangInventory = Bukkit.createInventory(null, 9 * 3, "Range Kaufen");

		for(int i = 0; i < rangKaufMenu.rangInventory.getSize(); i++) {
			rangKaufMenu.rangInventory.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§3 ").build());
		}

		ItemStack kaufen = new ItemCreator().material(Material.GREEN_WOOL).displayName("§aKaufen").build();
		ItemStack abbrechen = new ItemCreator().material(Material.RED_WOOL).displayName("§cAbbrechen").build();
		ItemStack itemCopy = new ItemCreator().material(rangKaufMenu.rangItem.getType()).displayName("§a§lKaufe für §f" + price + " §a§lGems").build();

		rangKaufMenu.rangInventory.setItem(10, abbrechen);
		rangKaufMenu.rangInventory.setItem(13, itemCopy);
		rangKaufMenu.rangInventory.setItem(16, kaufen);


		return rangKaufMenu;
	}

	public void open(Player player) {
		player.openInventory(rangInventory);
	}

	@EventHandler
	public void InvClick(InventoryClickEvent e) {
		if (!(e.getView().getTitle().equals("Range Kaufen"))) {
			return;
		}
			e.setCancelled(true);
		if (e.getSlot() == 16) {
			Player p = (Player) e.getWhoClicked();
			UUID uuid = p.getUniqueId();

			if (rangItem == null) {
				System.out.println("Item is null!");
				return;
			}

			if (PlayerSQL.getGems(uuid) >= price) {
				PlayerSQL.removeGems(uuid, price);

				String luckPermsCommand = "lp user " + p.getDisplayName() + " parent set " + rang;

				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), luckPermsCommand);

				p.closeInventory();


				p.sendMessage(S.P + "§aDu hast " + "§f§l" + rang + " gekauft.");
			} else {
				p.sendMessage(S.P + "§fDu hast nicht genügend §4Gems.");
			}
		} else if (e.getSlot() == 10) {
			Player p = (Player) e.getWhoClicked();

			p.closeInventory();
			
		}
	}

}
