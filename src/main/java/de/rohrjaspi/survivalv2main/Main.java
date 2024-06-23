package de.rohrjaspi.survivalv2main;

import de.rohrjaspi.survivalv2main.Utils.PlayerMenuUtility;
import de.rohrjaspi.survivalv2main.Utils.VanishManager;
import de.rohrjaspi.survivalv2main.commands.*;
import de.rohrjaspi.survivalv2main.economy.VaultAPI;
import de.rohrjaspi.survivalv2main.home.HomeListener;
import de.rohrjaspi.survivalv2main.inventories.AdminShop.AdminShopCommand;
import de.rohrjaspi.survivalv2main.inventories.AdminShop.KaufMenu;
import de.rohrjaspi.survivalv2main.inventories.Belohnung.Belohnung;
import de.rohrjaspi.survivalv2main.inventories.Belohnung.BelohnungCommand;
import de.rohrjaspi.survivalv2main.inventories.KistenShop.KistenShop;
import de.rohrjaspi.survivalv2main.inventories.KistenShop.KistenShopCommand;
import de.rohrjaspi.survivalv2main.inventories.RangInventar.RangCommand;
import de.rohrjaspi.survivalv2main.inventories.RangInventar.RangKaufMenu;
import de.rohrjaspi.survivalv2main.inventories.Shop.ShopKaufMenu;
import de.rohrjaspi.survivalv2main.inventories.SocialMenu.SocialMediaCommand;
import de.rohrjaspi.survivalv2main.listeners.*;
import de.rohrjaspi.survivalv2main.sql.MySQL;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private static Economy econ = new VaultAPI();
    private static Main instance;

    private LuckPerms luckPerms;
    private MySQL mysql;

    private VanishManager vanishManager;

    public static boolean sourrain = false;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.vanishManager = new VanishManager(this);
        luckPerms = LuckPermsProvider.get();
        setupMySQL();
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("ränge").setExecutor(new RangCommand());
        getCommand("social").setExecutor(new SocialMediaCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("invsee").setExecutor(new InvSeeCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("kistenshop").setExecutor(new KistenShopCommand());
        getCommand("belohnung").setExecutor(new BelohnungCommand());
        getCommand("adminshop").setExecutor(new AdminShopCommand());
        getCommand("sign").setExecutor(new SignCommand());
        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("antilag").setExecutor(new AnitLagCommand());
        getCommand("gems").setExecutor(new GemsCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SethomeCommand());
        getCommand("delhome").setExecutor(new DelhomeCommand());
        getCommand("vote").setExecutor(new VoteCommand());



        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new MenuListener(), this);
        pm.registerEvents(new KistenShop(), this);
        pm.registerEvents(new InvListener(),this);
        pm.registerEvents(new SpawnerListener(),this);
        pm.registerEvents(new PreviewListener(),this);
        pm.registerEvents(new Belohnung(),this);
        pm.registerEvents(new AsyncChatListener(),this);
        pm.registerEvents(new KaufMenu(),this);
        pm.registerEvents(new TreeChopListener(),this);
        pm.registerEvents(new OreMinerListener(),this);
        pm.registerEvents(new RangKaufMenu(),this);
        pm.registerEvents(new ShopKaufMenu(),this);
        pm.registerEvents(new HomeListener(),this);
        pm.registerEvents(new WeatherEventListener(),this);
        pm.registerEvents(new ThreeXThreePicke(),this);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (sourrain && !WeatherEventListener.isUnderRain(p)) {
                        double damageAmount = 1;
                        p.damage(damageAmount);
                    }
                }
            }
        }.runTaskTimer(this, 20, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupMySQL() {
        mysql = new MySQL();
        mysql.update("CREATE TABLE IF NOT EXISTS spieler(UUID varchar(64), NAME varchar(32), COINS bigint, ID int, IP varchar(32), GEMS bigint)");
        mysql.update("CREATE TABLE IF NOT EXISTS belohnung(UUID varchar(64), DAILY bigint, WEEKLY bigint, MONTHLY bigint)");
        mysql.update("CREATE TABLE IF NOT EXISTS homes(UUID varchar(64), HOMES varchar(9999))");
    }

    public Economy getEconomy() {
        return econ;
    }

    public static Main getInstance() {
        return instance;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public MySQL getMysql() {
        return mysql;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class,new VaultAPI(), this, ServicePriority.High);
        return econ != null;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {

            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }


    }

    public VanishManager getVanishManager() {
        return  vanishManager;
    }

}
