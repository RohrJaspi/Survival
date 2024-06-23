package de.rohrjaspi.survivalv2main.inventories.Shop;

import de.rohrjaspi.survivalv2main.Utils.Menu;
import de.rohrjaspi.survivalv2main.Utils.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopMenu extends Menu {
	public ShopMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	@Override
	public String getMenuName() {
		return "Shop";
	}

	@Override
	public int getSlots() {
		return 54;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {

	}

	@Override
	public void setMenuItems() {

	}
}
