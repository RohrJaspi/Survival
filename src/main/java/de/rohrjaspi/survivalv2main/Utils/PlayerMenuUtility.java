package de.rohrjaspi.survivalv2main.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerMenuUtility {

	private Player owner;
	private ItemStack itemToBuy;

	public PlayerMenuUtility(Player p) {
		this.owner = p;
	}

	public Player getOwner() {
		return owner;
	}

	public Player getItemToBuy() {
		return getItemToBuy();
	}

	public void setItemToBuy(ItemStack itemToBuy) {
		this.itemToBuy = itemToBuy;
	}
}