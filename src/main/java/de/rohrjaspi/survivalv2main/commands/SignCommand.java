package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.luckperms.api.LuckPermsProvider;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SignCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        User user = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId());
        String prefix  = user.getCachedData().getMetaData().getPrefix();

        ZoneId berlinZone = ZoneId.of("Europe/Berlin");
        LocalDate currentDate = LocalDate.now(berlinZone);
        LocalTime currentTime = LocalTime.now(berlinZone);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        ItemStack i = p.getInventory().getItemInMainHand();
        ItemMeta im = i.getItemMeta();

        ArrayList<String> lore = new ArrayList();
        String message = "";

        if (!p.hasPermission("survival.command.sign")) {
            p.sendMessage(S.P + "Dazu bist du nicht Berechtigt");
            return false;
        }

        for(int j = 0; j != args.length; j++) {
            message += args[j] + " ";
        }

        if (im == null) {
            p.sendMessage(S.P + "Du musst ein Item in der Hand halten, um diesen Command ausführen zu dürfen!");
            return false;
        }

        lore.add("");
        lore.add("§7Dieses Item wurde von " + prefix + "§7" + p.getName() + "§7 am §c" + currentDate.format(dateFormatter) + "§7 um §c" + currentTime.format(timeFormatter) + "§7 Uhr signiert.");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7" + message));
        if (args.length != 0) lore.add("");
        im.setLore(lore);
        i.setItemMeta(im);
        p.getInventory().setItemInMainHand(i);
        p.sendMessage(S.P + "Du hast das Item Erfolgreich signiert!");
        return false;
    }
}


