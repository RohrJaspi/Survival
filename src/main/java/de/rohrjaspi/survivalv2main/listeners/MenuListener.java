package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.Utils.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {

		InventoryHolder holder = e.getInventory().getHolder();

		if (holder instanceof Menu) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {
				return;
			}

			Menu menu = (Menu) holder;

			menu.handleMenu(e);
		}

	}
}