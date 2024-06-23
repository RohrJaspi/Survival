package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.economy.VaultAPI;
import de.rohrjaspi.survivalv2main.sql.BelohnungSQL;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

	Economy econ = new VaultAPI();

	@EventHandler
	public void JoinMessage(PlayerJoinEvent event){
		event.setJoinMessage(null);
		Player p = event.getPlayer();
		if (!PlayerSQL.playerExist(p.getUniqueId()) || !p.hasPlayedBefore()) {
			PlayerSQL.createPlayer(p.getUniqueId());
			BelohnungSQL.createPlayer(p.getUniqueId());
		}
		if (!BelohnungSQL.playerExist(p.getUniqueId())) {
			BelohnungSQL.createPlayer(p.getUniqueId());
		}
		if (!econ.hasAccount(p)) {
			econ.createPlayerAccount(p);
		}

	}

	@EventHandler
	public void QuitMessage(PlayerQuitEvent event) {
		event.setQuitMessage(null);
	}

	@EventHandler
	public void OnJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		player.setPlayerListHeader("\n§f§m§l---§8§m§l]-§r " + "§f\uE817" + " §8§m§l-[§f§m§l---§r\n\n  §f۰•● §7§oᴡᴏ ᴅᴇɪɴ ᴀʙᴇɴᴛᴇᴜᴇʀ ʙᴇɢɪɴɴᴛ... §f●•۰  \n");
		player.setPlayerListFooter("\n §f§l❖ §4§lsᴏᴄɪᴀʟ ᴍᴇᴅɪᴀs §f§l❖\n\n §c§lᴅɪsᴄᴏʀᴅ §8§l| §f/discord\n§c§lᴛɪᴋᴛᴏᴋ §8§l| §f/tiktok\n\n§7§oᴍᴀᴅᴇ ᴡɪᴛʜ ʟᴏᴠᴇ! §4❤\n");

		if (player.hasPlayedBefore()) return;

		Location spawn = player.getWorld().getSpawnLocation();

		spawn.setYaw(270f);

		player.teleport(spawn);
	}

	@EventHandler
	public void OnDeath(PlayerDeathEvent event) {

		Player player = event.getEntity();

		if (!(player.getBedSpawnLocation() == null)) return;

		Location spawn = player.getWorld().getSpawnLocation();

		spawn.setYaw(270f);

		player.teleport(spawn);



	}


}
