package me.massal.jobscustom;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.massal.commandes.CommandesMetier;
import me.massal.events.Events;
import me.massal.item.ItemManager;
import me.massal.sqlmanager.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class JobsCustom extends JavaPlugin {

    private int coefflvl;

    ItemManager im;

    private SQLManager sql;
    public WorldGuardPlugin plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = WGBukkit.getPlugin();
        getWorldEdit();
        System.out.println("[MetierCustom] Lancement du Plugin !");
        getServer().getPluginManager().registerEvents(new Events(this), this);
        getCommand("jobs").setExecutor(new CommandesMetier(this));
        getCommand("giveh").setExecutor(new CommandesMetier(this));
        getCommand("givehoue").setExecutor(new CommandesMetier(this));
        getCommand("givehultime").setExecutor(new CommandesMetier(this));
        getCommand("givehoueultime").setExecutor(new CommandesMetier(this));
        getCommand("givec").setExecutor(new CommandesMetier(this));
        getCommand("giveconstru").setExecutor(new CommandesMetier(this));
        getCommand("givepiochedivine").setExecutor(new CommandesMetier(this));
        getCommand("givetrancheuse").setExecutor(new CommandesMetier(this));
        this.coefflvl = 2;
        initDatabase();
        im = new ItemManager();
        ItemManager.init();

    }
    @Override
    public void onDisable() {
        this.sql.onDisable();
    }

    private void initDatabase() {
        this.sql = new SQLManager(this);
    }

    public SQLManager getSQLManager() {
        return this.sql;
    }


    public int getCoeff() {
        return this.coefflvl;
    }

    private WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        // WorldGuard may not be loaded
        if (!(plugin instanceof WorldEditPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldEditPlugin) plugin;
    }

}

