package de.rohrjaspi.survivalv2main.inventories.AdminShop;

import de.rohrjaspi.survivalv2main.Utils.Menu;
import de.rohrjaspi.survivalv2main.Utils.PlayerMenuUtility;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AdminShop extends Menu {
	public AdminShop(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	@Override
	public String getMenuName() {
		return "AdminShop";
	}

	@Override
	public int getSlots() {
		return 54;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {

		Player player = (Player) e.getWhoClicked();

		switch (e.getCurrentItem().getType()) {
			case BARRIER:
				player.closeInventory();
				break;
			case SPAWNER:
					ItemStack spawner = new ItemCreator().material(Material.SPAWNER).build();
					ItemMeta spawnerMeta = spawner.getItemMeta();
					long price = 3000000L;
					KaufMenu a = new KaufMenu();
					KaufMenu.setKaufMenu(a, spawner, price);


					a.open(player);
				break;
			case DIAMOND_AXE:
				ItemStack treeChoper = new ItemCreator().material(Material.DIAMOND_AXE).displayName("§b✪ §e§lHolzfäller Tool §b✪").lore(Arrays.asList("Baue ganze Bäume auf einmal ab.")).build();
				ItemMeta treeMeta = treeChoper.getItemMeta();
				treeMeta.setCustomModelData(1);
				treeMeta.addEnchant(Enchantment.MENDING, 1, false);
				treeMeta.addEnchant(Enchantment.DURABILITY, 10, true);
				treeMeta.setLore(Arrays.asList("" + "§7Baumfäller I " , " " ,
						"§8➸ §7Entchants: §4Admin"  ,
						"§8➸ §7Sepcial: §aBaumfäller " , " " ,
						"§8(§6i§8) §7Fällt einen ganzen Baum mit einem Klick " , " " ,
						"§8➸ §7Signiert vom §a§lHolzfäller§r §7am §b19.05.2024"));
				treeChoper.setItemMeta(treeMeta);

				long treeChoperPrice = 1500000L;
				KaufMenu b = new KaufMenu();
				KaufMenu.setKaufMenu(b, treeChoper, treeChoperPrice);

				b.open(player);
				break;
			case DIAMOND_PICKAXE:
				ItemStack OreMiner = new ItemCreator().material(Material.DIAMOND_PICKAXE).displayName("§b✪ §e§lErzezerstörer Tool §b✪").lore(Arrays.asList("Baue ganze Bäume auf einmal ab.")).build();
				ItemMeta minerMeta = OreMiner.getItemMeta();
				minerMeta.setCustomModelData(1);
				minerMeta.addEnchant(Enchantment.MENDING, 1, false);
				minerMeta.addEnchant(Enchantment.DURABILITY, 5, true);
				minerMeta.setLore(Arrays.asList("" + "§7Erzesämmler I " , " " ,
						"§8➸ §7Entchants: §4Admin"  ,
						"§8➸ §7Sepcial: §aErzesamler " , " " ,
						"§8(§6i§8) §7Baut eine ganze Erzquelle mit einem Klick " , " " ,
						"§8➸ §7Signiert vom §a§lErzesamller§r §7am §b19.05.2024"));
				OreMiner.setItemMeta(minerMeta);

				long oreMinerPrice = 2000000L;
				KaufMenu y = new KaufMenu();
				KaufMenu.setKaufMenu(y, OreMiner, oreMinerPrice);

				y.open(player);
				break;
			case BEACON:
				ItemStack beacon = new ItemCreator().material(Material.BEACON).build();
				long beaconPrice = 450000L;
				KaufMenu c = new KaufMenu();
				KaufMenu.setKaufMenu(c, beacon, beaconPrice);

				beacon.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + beaconPrice));

				c.open(player);
				break;
			case BLAZE_SPAWN_EGG:
				ItemStack blaze = new ItemCreator().material(Material.BLAZE_SPAWN_EGG).build();
				long blazePrice = 275000L;
				KaufMenu d = new KaufMenu();
				KaufMenu.setKaufMenu(d, blaze, blazePrice);

				blaze.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + blazePrice));

				d.open(player);
				break;
			case CREEPER_SPAWN_EGG:
				ItemStack crepper = new ItemCreator().material(Material.CREEPER_SPAWN_EGG).build();
				long creeperPrice = 350000L;
				KaufMenu f = new KaufMenu();
				KaufMenu.setKaufMenu(f, crepper, creeperPrice);

				crepper.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + creeperPrice));

				f.open(player);
				break;
			case CHICKEN_SPAWN_EGG:
				ItemStack chicken = new ItemCreator().material(Material.CHICKEN).build();
				long chickenPrice = 150000L;
				KaufMenu g = new KaufMenu();
				KaufMenu.setKaufMenu(g, chicken, chickenPrice);

				chicken.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + chickenPrice));

				g.open(player);
				break;
			case GLOW_SQUID_SPAWN_EGG:
				ItemStack glow = new ItemCreator().material(Material.GLOW_SQUID_SPAWN_EGG).build();
				long glowPrice = 300000L;
				KaufMenu h = new KaufMenu();
				KaufMenu.setKaufMenu(h, glow, glowPrice);

				glow.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + glowPrice));

				h.open(player);
				break;
			case SHULKER_BOX:
				ItemStack shulkerBox = new ItemCreator().material(Material.SHULKER_BOX).build();
				long shulkerBoxPrice = 80000L;
				KaufMenu i = new KaufMenu();
				KaufMenu.setKaufMenu(i, shulkerBox, shulkerBoxPrice);

				shulkerBox.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + shulkerBoxPrice));

				i.open(player);
				break;
			case SHULKER_SHELL:
				ItemStack shulker = new ItemCreator().material(Material.SHULKER_SHELL).build();
				long shulkerPrice = 40000L;
				KaufMenu j = new KaufMenu();
				KaufMenu.setKaufMenu(j, shulker, shulkerPrice);

				shulker.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + shulkerPrice));

				j.open(player);
				break;
			case DRAGON_EGG:
				ItemStack dragon = new ItemCreator().material(Material.DRAGON_EGG).build();
				long dragonPrice = 400000L;
				KaufMenu k = new KaufMenu();
				KaufMenu.setKaufMenu(k, dragon, dragonPrice);

				dragon.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + dragonPrice));

				k.open(player);
				break;
			case NETHER_STAR:
				ItemStack nether_star = new ItemCreator().material(Material.NETHER_STAR).build();
				long nether_starPrice = 500000L;
				KaufMenu l = new KaufMenu();
				KaufMenu.setKaufMenu(l, nether_star, nether_starPrice);

				nether_star.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + nether_starPrice));

				l.open(player);
				break;
			case ELYTRA:
				ItemStack elytra = new ItemCreator().material(Material.ELYTRA).build();
				long elytraPrice = 70000L;
				KaufMenu m = new KaufMenu();
				KaufMenu.setKaufMenu(m, elytra, elytraPrice);

				elytra.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + elytraPrice));

				m.open(player);
				break;
			case VILLAGER_SPAWN_EGG:
				ItemStack villigar = new ItemCreator().material(Material.VILLAGER_SPAWN_EGG).build();
				long villigarPrice = 550000L;
				KaufMenu n = new KaufMenu();
				KaufMenu.setKaufMenu(n, villigar, villigarPrice);

				villigar.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + villigarPrice));

				n.open(player);
				break;
			case GHAST_SPAWN_EGG:
				ItemStack ghast = new ItemCreator().material(Material.GHAST_SPAWN_EGG).build();
				long ghastPrice = 200000L;
				KaufMenu o = new KaufMenu();
				KaufMenu.setKaufMenu(o, ghast, ghastPrice);

				ghast.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + ghastPrice));


				o.open(player);
				break;
			case END_CRYSTAL:
				ItemStack end_crystal = new ItemCreator().material(Material.END_CRYSTAL).build();
				long end_crystalPrice = 75000L;
				KaufMenu p = new KaufMenu();
				KaufMenu.setKaufMenu(p, end_crystal, end_crystalPrice);

				end_crystal.getItemMeta().setLore(Arrays.asList("§f§lKosten: §a" + end_crystalPrice));

				p.open(player);
				break;
		}

	}

	@Override
	public void setMenuItems() {

		ItemStack spawner = new ItemCreator().material(Material.SPAWNER).lore(Arrays.asList("Spawnt verschieden Tiere/Mobs.")).build();
		ItemStack beacon = new ItemCreator().material(Material.BEACON).build();
		ItemStack blaze = new ItemCreator().material(Material.BLAZE_SPAWN_EGG).build();
		ItemStack creeper = new ItemCreator().material(Material.CREEPER_SPAWN_EGG).build();
		ItemStack chicken = new ItemCreator().material(Material.CHICKEN_SPAWN_EGG).build();
		ItemStack glow = new ItemCreator().material(Material.GLOW_SQUID_SPAWN_EGG).build();
		ItemStack shulkerbox = new ItemCreator().material(Material.SHULKER_BOX).build();
		ItemStack shulker = new ItemCreator().material(Material.SHULKER_SHELL).build();
		ItemStack dragon = new ItemCreator().material(Material.DRAGON_EGG).build();
		ItemStack nether_star = new ItemCreator().material(Material.NETHER_STAR).build();
		ItemStack elytra = new ItemCreator().material(Material.ELYTRA).build();
		ItemStack villigar = new ItemCreator().material(Material.VILLAGER_SPAWN_EGG).build();
		ItemStack ghast = new ItemCreator().material(Material.GHAST_SPAWN_EGG).build();
		ItemStack end_crystal = new ItemCreator().material(Material.END_CRYSTAL).build();
		ItemStack treeChoper = new ItemCreator().material(Material.DIAMOND_AXE).displayName("§b✪ §e§lHolzfäller Tool §b✪").lore(Arrays.asList("Baue ganze Bäume auf einmal ab.")).build();
		ItemMeta treeMeta = treeChoper.getItemMeta();
		treeMeta.setCustomModelData(1);
		treeMeta.addEnchant(Enchantment.MENDING, 1, false);
		treeMeta.addEnchant(Enchantment.DURABILITY, 10, true);
		treeMeta.setLore(Arrays.asList("" + "§7Baumfäller I " , " " ,
				"§8➸ §7Entchants: §4Admin"  ,
				"§8➸ §7Sepcial: §aBaumfäller " , " " ,
				"§8(§6i§8) §7Fällt einen ganzen Baum mit einem Klick " , " " ,
				"§8➸ §7Signiert vom §a§lHolzfäller§r §7am §b19.05.2024"));
		treeChoper.setItemMeta(treeMeta);

		ItemStack OreMiner = new ItemCreator().material(Material.DIAMOND_PICKAXE).displayName("§b✪ §e§lErzezerstörer Tool §b✪").lore(Arrays.asList("Baue ganze Bäume auf einmal ab.")).build();
		ItemMeta minerMeta = OreMiner.getItemMeta();
		minerMeta.setCustomModelData(1);
		minerMeta.addEnchant(Enchantment.MENDING, 1, false);
		minerMeta.addEnchant(Enchantment.DURABILITY, 5, true);
		minerMeta.setLore(Arrays.asList("" + "§7Erzesämmler I " , " " ,
				"§8➸ §7Entchants: §4Admin"  ,
				"§8➸ §7Sepcial: §aErzesamler " , " " ,
				"§8(§6i§8) §7Baut eine ganze Erzquelle mit einem Klick " , " " ,
				"§8➸ §7Signiert vom §a§lErzesamller§r §7am §b19.05.2024"));
		OreMiner.setItemMeta(minerMeta);

		setFillerGlass();
		inventory.setItem(49, CLOSE);

		inventory.setItem(10, spawner);
		inventory.setItem(12, treeChoper);
		inventory.setItem(14, OreMiner);
		inventory.setItem(16, beacon);
		inventory.setItem(19, blaze);
		inventory.setItem(21, creeper);
		inventory.setItem(23, chicken);
		inventory.setItem(25, glow);
		inventory.setItem(28, shulkerbox);
		inventory.setItem(30, shulker);
		inventory.setItem(32, dragon);
		inventory.setItem(34, nether_star);
		inventory.setItem(37, elytra);
		inventory.setItem(39, villigar);
		inventory.setItem(41, ghast);
		inventory.setItem(43, end_crystal);



	}


}
