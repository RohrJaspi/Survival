package de.rohrjaspi.survivalv2main.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvListener implements Listener {

	@EventHandler
	public void OnClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (event.getView().getTitle() == "Player") {
			if (!(player.hasPermission("survivalV2.commands.invsee.admin"))) {
				event.setCancelled(true);
			}
		}
	}

}
