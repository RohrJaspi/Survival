package de.rohrjaspi.survivalv2main.inventories.RangInventar;

import de.rohrjaspi.survivalv2main.Utils.Menu;
import de.rohrjaspi.survivalv2main.Utils.PlayerMenuUtility;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RängeMenu extends Menu {


	public RängeMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	@Override
	public String getMenuName() {
		return "Ränge";
	}

	@Override
	public int getSlots() {
		return 27;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {

		Player player = (Player) e.getWhoClicked();

		switch (e.getCurrentItem().getType()) {
			case BARRIER:
				e.getWhoClicked().closeInventory();
				break;
			case GOLD_INGOT:
				ItemStack aero = new ItemCreator().material(Material.GOLD_INGOT).build();

				long price = 500L;
				RangKaufMenu a = new RangKaufMenu();
				RangKaufMenu.setRangKaufMenu(a, aero, price, "aero");

				a.open(player);
				break;
			case REDSTONE:
				ItemStack matrix = new ItemCreator().material(Material.REDSTONE).build();

				long matrixPrice = 1000L;
				RangKaufMenu b = new RangKaufMenu();
				RangKaufMenu.setRangKaufMenu(b, matrix, matrixPrice, "matrix");

				b.open(player);
				break;
			case DIAMOND:
				ItemStack depths = new ItemCreator().material(Material.REDSTONE).build();

				long depthsPrice = 1500L;
				RangKaufMenu c = new RangKaufMenu();
				RangKaufMenu.setRangKaufMenu(c, depths, depthsPrice, "depths");

				c.open(player);
				break;
			case MAP:
				ItemStack adventure = new ItemCreator().material(Material.REDSTONE).build();

				long adventurePrice = 2000L;
				RangKaufMenu d= new RangKaufMenu();
				RangKaufMenu.setRangKaufMenu(d, adventure, adventurePrice, "adventure");

				d.open(player);
				break;
		}

	}

	@Override
	public void setMenuItems() {

		ItemStack aero = new ItemStack(Material.GOLD_INGOT);
		ItemMeta aeroMeta = aero.getItemMeta();
		aeroMeta.setDisplayName("§x§F§B§B§C§0§0A§x§F§C§9§E§0§0e§x§F§E§8§1§0§0r§x§F§F§6§3§0§0o");
		aero.setItemMeta(aeroMeta);

		ItemStack matrix = new ItemStack(Material.REDSTONE);
		ItemMeta matrixMeta = matrix.getItemMeta();
		matrixMeta.setDisplayName("§x§B§2§F§B§0§0M§x§C§1§E§C§0§0a§x§D§1§D§D§0§0t§x§E§0§C§E§0§0r§x§F§0§B§F§0§0i§x§F§F§B§0§0§0x");
		matrix.setItemMeta(matrixMeta);

		ItemStack depths = new ItemStack(Material.DIAMOND);
		ItemMeta depthsMeta = depths.getItemMeta();
		depthsMeta.setDisplayName("§x§F§B§0§0§0§0D§x§F§C§3§1§3§1e§x§F§D§6§3§6§3p§x§F§D§9§4§9§4t§x§F§E§C§6§C§6h§x§F§F§F§7§F§7s");
		depths.setItemMeta(depthsMeta);

		ItemStack adventure = new ItemStack(Material.MAP);
		ItemMeta adventureMeta = adventure.getItemMeta();
		adventureMeta.setDisplayName("§x§3§A§0§0§F§BA§x§6§1§0§0§F§Cd§x§8§7§0§0§F§Dv§x§A§E§0§0§F§Ee§x§D§4§0§0§F§Fn§x§A§E§0§0§F§Ft§x§8§8§0§0§F§Fu§x§6§1§0§0§F§Fr§x§3§B§0§0§F§Fe");
		adventure.setItemMeta(adventureMeta);


		setFillerGlass();
		inventory.setItem(22, CLOSE);

		inventory.setItem(10, aero);
		inventory.setItem(12, matrix);
		inventory.setItem(14, depths);
		inventory.setItem(16, adventure);


	}
}
