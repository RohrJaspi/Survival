package de.rohrjaspi.survivalv2main.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ThreeXThreePicke implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		if (isInRegion(e.getPlayer(), "spawn")) {
			return;
		}

		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInMainHand();

		if (item == null) return;

		if (item != null && item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == 4) {

					Block b = e.getBlock();


					int startX = b.getX() - 1;
					int startY = b.getY() - 1;
					int startZ = b.getZ() - 1;

					for (int x = startX; x <= startX + 2; x++) {
						for (int z = startZ; z <= startZ + 2; z++) {
							for (int y = startY; y <= startY + 3; y++) {
								Block tb = b.getWorld().getBlockAt(x, y, z);
								if (tb.getType() != Material.AIR && tb != b) {
									tb.breakNaturally(item);
								}
							}
						}
					}
				}
			}
		}


	public boolean isInRegion(Player player, String regionName) {
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld()));
		if (regions != null) {
			ApplicableRegionSet set = regions.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()));
			for (ProtectedRegion region : set) {
				if (region.getId().equalsIgnoreCase(regionName)) {
					return true;
				}
			}
		}
		return false;
	}

}
