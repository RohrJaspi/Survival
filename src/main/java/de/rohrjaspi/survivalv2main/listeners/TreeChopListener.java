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

import java.util.HashSet;
import java.util.Set;

public class TreeChopListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        
        if (itemInHand != null && itemInHand.hasItemMeta()) {
            ItemMeta meta = itemInHand.getItemMeta();
            if (meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == 1) {
                if (isLog(block.getType()) && isAxe(itemInHand)) {
                    if (isInRegion(event.getPlayer(), "spawn")) {
                        return;
                    }
                    breakTree(block, itemInHand, event);
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


    private boolean isLog(Material material) {
        switch (material) {
            case OAK_WOOD:
            case OAK_LOG:
            case SPRUCE_WOOD:
            case SPRUCE_LOG:
            case DARK_OAK_LOG:
            case DARK_OAK_WOOD:
            case CHERRY_WOOD:
            case CHERRY_LOG:
            case MANGROVE_LOG:
            case MANGROVE_WOOD:
            case BIRCH_WOOD:
            case BIRCH_LOG:

                return true;
            default:
                return false;
        }
    }

    private boolean isAxe(ItemStack item) {
        if (item == null || item.getType() != Material.DIAMOND_AXE) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.getCustomModelData() == 1;
    }

    private void breakTree(Block startBlock, ItemStack axe, BlockBreakEvent event) {
        Set<Block> blocksToBreak = new HashSet<>();
        findConnectedLogs(startBlock, blocksToBreak);

        for (Block block : blocksToBreak) {
            block.breakNaturally();
            damageAxe(axe, event);
            if (axe.getType() == Material.AIR) {
                break;
            }
        }
    }

    private void findConnectedLogs(Block block, Set<Block> blocksToBreak) {
        if (!isLog(block.getType()) || blocksToBreak.contains(block)) {
            return;
        }

        blocksToBreak.add(block);
        for (Block relative : getAdjacentBlocks(block)) {
            if (blocksToBreak.size() <= 14) {
                findConnectedLogs(relative, blocksToBreak);
            }
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

    private void damageAxe(ItemStack axe, BlockBreakEvent event) {
        short durability = axe.getDurability();
        durability++;
        if (durability >= axe.getType().getMaxDurability()) {
            axe.setAmount(0);
            event.getPlayer().getInventory().removeItem(axe);
        } else {
            axe.setDurability(durability);
        }
    }

}