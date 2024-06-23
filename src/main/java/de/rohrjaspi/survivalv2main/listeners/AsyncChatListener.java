package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.Main;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        User user = Main.getInstance().getLuckPerms().getUserManager().getUser(e.getPlayer().getUniqueId());
        String prefix = user.getCachedData().getMetaData().getPrefix();
        String suffix = user.getCachedData().getMetaData().getSuffix();
        if (suffix == null) suffix = "";
        String prename= ChatColor.translateAlternateColorCodes('&', prefix) + "§7" + e.getPlayer().getName() + suffix + " §8»§f ";
        if (e.getMessage().startsWith("@clan")) return;

        String format;
        if (e.getPlayer().hasPermission("survival.big")) {
            format = "§7\n" + prename + ChatColor.translateAlternateColorCodes('&', "%2$s") + "\n§7";
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if (e.getPlayer().hasPermission("survival.chat")) {
            format = prename + ChatColor.translateAlternateColorCodes('&', "%2$s");
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else {
            format = prename + "%2$s";
        }
        e.setFormat(format);

    }
}
