package de.rohrjaspi.survivalv2main.inventories.SocialMenu;

import de.rohrjaspi.survivalv2main.Utils.Menu;
import de.rohrjaspi.survivalv2main.Utils.PlayerMenuUtility;
import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SocialMenu extends Menu {

	public SocialMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	@Override
	public String getMenuName() {
		return "SocialMedia";
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
			case CRYING_OBSIDIAN:
				player.sendMessage("§a ");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage(S.P + " ");
				player.sendMessage(S.P + "Schau bei unserem §fTiktok §7vorbei:");
				player.sendMessage(S.P + "§c https://www.tiktok.com/@outerdepths.de");
				player.sendMessage(S.P + "");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage("§a ");
				break;
			case REDSTONE_BLOCK:
				player.sendMessage("§a ");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage(S.P + " ");
				player.sendMessage(S.P + "Schau bei unserem §fYouTube §7vorbei:");
				player.sendMessage(S.P + "§c https://www.youtube.com/@OuterDepths");
				player.sendMessage(S.P + "");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage("§a ");
				break;
			case LAPIS_BLOCK:
				player.sendMessage("§a ");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage(S.P + " ");
				player.sendMessage(S.P + "Schau bei unserem §fDiscord §7vorbei:");
				player.sendMessage(S.P + "§c https://discord.outerdepths.de");
				player.sendMessage(S.P + "");
				player.sendMessage(S.P + "§8§m*                                                  *");
				player.sendMessage("§a ");
				break;
		}

	}

	@Override
	public void setMenuItems() {

		ItemStack tiktok = new ItemStack(Material.CRYING_OBSIDIAN);
		ItemMeta tiktokMeta = tiktok.getItemMeta();
		tiktokMeta.setDisplayName("§x§0§0§D§1§D§9T§x§2§B§A§8§B§Bi§x§5§7§7§F§9§Ek§x§8§2§5§5§8§0T§x§A§E§2§C§6§3o§x§D§9§0§3§4§5k");
		tiktok.setItemMeta(tiktokMeta);

		ItemStack yt = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta ytMeta = yt.getItemMeta();
		ytMeta.setDisplayName("§cYou§fTube");
		yt.setItemMeta(ytMeta);

		ItemStack discord = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta discordMeta = discord.getItemMeta();
		discordMeta.setDisplayName("§9Discord");
		discord.setItemMeta(discordMeta);

		setFillerGlass();
		inventory.setItem(22, CLOSE);

		inventory.setItem(11, tiktok);
		inventory.setItem(13, yt);
		inventory.setItem(15, discord);

	}
}
