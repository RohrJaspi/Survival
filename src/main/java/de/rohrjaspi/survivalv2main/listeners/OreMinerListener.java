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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public class OreMinerListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

		if (itemInHand != null && itemInHand.hasItemMeta()) {
			ItemMeta meta = itemInHand.getItemMeta();
			if (meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == 1) {
				if (isOre(block.getType()) && isPickAxe(itemInHand)) {
					if (isInRegion(event.getPlayer(), "spawn")) {
						return;
					}
					breakOre(block, itemInHand, event);
				}
			}
		}
	}

	private boolean isOre(Material material) {
		switch (material) {
			case IRON_ORE:
			case DEEPSLATE_IRON_ORE:
			case DEEPSLATE_GOLD_ORE:
			case GOLD_ORE:
			case DIAMOND_ORE:
			case LAPIS_ORE:
			case DEEPSLATE_LAPIS_ORE:
			case DEEPSLATE_DIAMOND_ORE:
			case DEEPSLATE_COPPER_ORE:
			case COPPER_ORE:
			case DEEPSLATE_COAL_ORE:
			case COAL_ORE:
			case EMERALD_ORE:
			case DEEPSLATE_EMERALD_ORE:
			case NETHER_QUARTZ_ORE:
			case NETHER_GOLD_ORE:
			case ANCIENT_DEBRIS:
				return true;
			default:
				return false;
		}
	}

	private boolean isPickAxe(ItemStack item) {
		if (item == null || item.getType() != Material.DIAMOND_PICKAXE) {
			return false;
		}
		ItemMeta meta = item.getItemMeta();
		return meta != null && meta.getCustomModelData() == 1;
	}

	private void breakOre(Block startBlock, ItemStack pickaxe, BlockBreakEvent event) {
		Set<Block> blocksToBreak = new HashSet<>();
		findConnectedOres(startBlock, blocksToBreak);

		for (Block block : blocksToBreak) {
			block.breakNaturally();
			damagePickAxe(pickaxe, event);
			if (pickaxe.getType() == Material.AIR) {
				break;
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

	private void findConnectedOres(Block block, Set<Block> blocksToBreak) {
		if (!isOre(block.getType()) || blocksToBreak.contains(block)) {
			return;
		}

		blocksToBreak.add(block);

		for (Block relative : getAdjacentBlocks(block)) {
			findConnectedOres(relative, blocksToBreak);
		}
	}

	private Set<Block> getAdjacentBlocks(Block block) {
		Set<Block> adjacentBlocks = new HashSet<>();
		adjacentBlocks.add(block.getRelative(1, 0, 0));
		adjacentBlocks.add(block.getRelative(-1, 0, 0));
		adjacentBlocks.add(block.getRelative(0, 1, 0));
		adjacentBlocks.add(block.getRelative(0, -1, 0));
		adjacentBlocks.add(block.getRelative(0, 0, 1));
		adjacentBlocks.add(block.getRelative(0, 0, -1));
		return adjacentBlocks;
	}

	private void damagePickAxe(ItemStack pickaxe, BlockBreakEvent event) {
		ItemMeta meta = pickaxe.getItemMeta();
		if (meta instanceof Damageable) {
			Damageable damageable = (Damageable) meta;
			int newDamage = damageable.getDamage() + 1;
			if (newDamage >= pickaxe.getType().getMaxDurability()) {
				event.getPlayer().getInventory().removeItem(pickaxe);
			} else {
				damageable.setDamage(newDamage);
				pickaxe.setItemMeta(meta);
			}
		}
	}
}
