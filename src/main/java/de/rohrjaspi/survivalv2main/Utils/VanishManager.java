package de.rohrjaspi.survivalv2main.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {

	private final Plugin plugin;
	private final List<Player> vanished;

	public VanishManager(Plugin plugin) {
		this.plugin = plugin;
		this.vanished = new ArrayList<>();
	}

	public List<Player> getVanished() {
		return vanished;
	}

	public boolean isVanished(Player player) {
		return vanished.contains(player);
	}

	public void setVanished(Player player, boolean bool) {
		if (bool) {
			vanished.add(player);
		} else {
			vanished.remove(player);
		}


		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			if (player.equals(onlinePlayer)) continue;

			if (bool) {
				onlinePlayer.hidePlayer(plugin, player);
			} else {
				onlinePlayer.showPlayer(plugin, player);
			}
		}
	}

	public void hideAll(Player player) {
		vanished.forEach(player1 -> player.hidePlayer(plugin, player1));
	}


}
