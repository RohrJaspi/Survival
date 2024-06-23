package de.rohrjaspi.survivalv2main.economy;

import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class VaultAPI extends AbstractEconomy {


    @Override
    public boolean isEnabled() {
        return Main.getInstance().isEnabled();
    }

    @Override
    public String getName() {
        return Main.getInstance().getDescription().getName();
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.getDefault());
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbol);
        String formatted = df.format(v);

        return "§e" + formatted + " Coins§7";
    }

    @Override
    public String currencyNamePlural() {
        return "Coins";
    }

    @Override
    public String currencyNameSingular() {
        return "Coin";
    }

    @Override
    public boolean hasAccount(String player) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player);
        return PlayerSQL.playerExist(p.getUniqueId());
    }

    @Override
    public boolean hasAccount(String player, String world) {
        return hasAccount(player);
    }

    @Override
    public double getBalance(String player) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player);
        return PlayerSQL.getCoins(p.getUniqueId());
    }

    @Override
    public double getBalance(String player, String world) {
        return getBalance(player);
    }

    @Override
    public boolean has(String player, double amount) {
        return getBalance(player) >= amount;
    }

    @Override
    public boolean has(String player, String world, double amount) {
        return has(player, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player);
        if (!hasAccount(player))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "The Player has no Account!");
        double money = getBalance(player);
        money -= amount;
        long setcoins = (long) money;
        PlayerSQL.setCoins(p.getUniqueId(), setcoins);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, String world, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player);
        if (!hasAccount(player))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "The Player has no Account!");
        double money = getBalance(player);
        money += amount;
        long setcoins = (long) money;
        PlayerSQL.setCoins(p.getUniqueId(), setcoins);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String player, String world, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String player) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player);
        PlayerSQL.createPlayer(p.getUniqueId());
        return true;
    }

    @Override
    public boolean createPlayerAccount(String player, String world) {
        return createPlayerAccount(player);
    }
}
