package me.massal.events;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import com.massivecraft.factions.*;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import io.github.invvk.actionbar.api.ActionBarAPI;
import me.massal.item.ItemManager;
import org.bukkit.*;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;


import me.massal.inventaire.Inventaire;
import me.massal.jobscustom.JobsCustom;
import me.massal.sqlmanager.SQLManager;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

public class Events implements Listener{

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

        if (!(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }
    private final JobsCustom plugin;
    public SQLManager sqlmanager;

    public  Events(JobsCustom m) {
        this.plugin = m;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

    }


    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.GOLD_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.IRON_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.COAL_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.REDSTONE_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.LAPIS_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
        if (event.getBlock().getType() == Material.EMERALD_ORE) {
            GameMode gam = player.getGameMode();
            if (gam == GameMode.SURVIVAL) {
                player.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas placer de "+ event.getBlock().getType() + " pour eviter le boost exp !");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventory( InventoryClickEvent event) {

        Inventaire gui = new Inventaire((Player) event.getWhoClicked(),plugin);
        String invname = event.getInventory().getName();
        if(invname.equalsIgnoreCase("Menu Metiers") || invname.equals("Info Mineur") ||  invname.equals("Info Chasseur") || invname.equals("Info Farm") || invname.equals("Reward Metier")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem()==null) {return;}
            if(event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)){
                player.openInventory(gui.getInvMineur());
            }
            if(event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){
                player.openInventory(gui.getInvChasseur());
            }
            if(event.getCurrentItem().getType().equals(Material.DIAMOND_HOE)){
                player.openInventory(gui.getInvFarm());
            }
            if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.openInventory(gui.getInventory());
            }
            if(event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
                player.openInventory(gui.getShop());
            }
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("reward 10")){
                if(player.hasPermission("farm.10.perm")){
                    player.getInventory().addItem(ItemManager.Fragment);
                    removeperm(player,"farm10");
                }
            }
        }

    }

    public void removeperm(Player player,String perm){

        HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
        PermissionAttachment attachment = player.addAttachment(plugin);
        perms.put(player.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(player.getUniqueId());
        pperms.setPermission("permission.here", true);
        perms.get(player.getUniqueId()).unsetPermission(perm);
    }

    @EventHandler
    @Deprecated
    public void expDestroyEvent(BlockBreakEvent event) {
        if (event.getPlayer() != null) {
            Block block = event.getBlock();
            BlockState state = block.getState();
            MaterialData data = state.getData();
            Player player = event.getPlayer();
            int expneed = 0;
            int playerexp = 0; //exp if the player
            int lvl = 0;
            String expmetier = null;
            String expneedmetier = null;
            String lvlmetier = null;
            if (block.getType().equals(Material.STONE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 1;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.GOLD_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 5;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.IRON_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 5;
                    this.sqlmanager.setExp(player,expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.LAPIS_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 3;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.REDSTONE_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 3;
                    this.sqlmanager.setExp(player,expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.COAL_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 3;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.DIAMOND_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 10;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (block.getType().equals(Material.EMERALD_ORE)) {
                try {
                    expmetier = "expmineur";
                    expneedmetier = "needexpmineur";
                    lvlmetier = "lvlmineur";
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                    playerexp = playerexp + 25;
                    this.sqlmanager.setExp(player, expmetier, playerexp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (data instanceof NetherWarts) {
                NetherWarts netherWarts = (NetherWarts) data;
                if (netherWarts.getState() == NetherWartsState.RIPE) {
                    try {
                        expmetier = "expfarm";
                        expneedmetier = "needexpfarm";
                        lvlmetier = "lvlfarm";
                        expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                        playerexp = this.sqlmanager.getExp(player, expmetier);
                        lvl = this.sqlmanager.getLevel(player, lvlmetier);
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player,expmetier, playerexp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (block.getType().equals((Material.CARROT))) {
                if (block.getData() == 7) {
                    try {
                        expmetier = "expfarm";
                        expneedmetier = "needexpfarm";
                        lvlmetier = "lvlfarm";
                        expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                        playerexp = this.sqlmanager.getExp(player, expmetier);
                        lvl = this.sqlmanager.getLevel(player, lvlmetier);
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player,expmetier, playerexp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (block.getType().equals((Material.POTATO))) {
                if (block.getData() == 7) {
                    try {
                        expmetier = "expfarm";
                        expneedmetier = "needexpfarm";
                        lvlmetier = "lvlfarm";
                        expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                        playerexp = this.sqlmanager.getExp(player, expmetier);
                        lvl = this.sqlmanager.getLevel(player, lvlmetier);
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player,expmetier, playerexp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (data instanceof Crops) {
                Crops crops = (Crops) data;
                if (crops.getState() == CropState.RIPE) {
                    try {
                        expmetier = "expfarm";
                        expneedmetier = "needexpfarm";
                        lvlmetier = "lvlfarm";
                        expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                        playerexp = this.sqlmanager.getExp(player, expmetier);
                        lvl = this.sqlmanager.getLevel(player, lvlmetier);
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player,expmetier, playerexp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(expmetier != null) {
                if (playerexp >= expneed) {
                    levelup(player, lvlmetier, expmetier, expneedmetier, expneed, playerexp);
                } else {
                    String metier;
                    if(expmetier.equals("expfarm")){
                        metier =  "Farmeur";
                    }else{
                        metier = "Mineur";
                    }
                    ActionBarAPI.send(player, ChatColor.GREEN + metier +" Level " + lvl + " : " + ChatColor.YELLOW + playerexp + ChatColor.BOLD + ChatColor.GRAY + "/" + ChatColor.GOLD + expneed);
                }
            }
        }
    }

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }

    public void dropKey(EntityDeathEvent event,double proba,int randomNumber,double palier){
        if (randomNumber < proba+palier*20){
            if (hasAvaliableSlot(event.getEntity().getKiller())) {
                event.getEntity().getKiller().getInventory().addItem(ItemManager.Fragment);
            } else {
                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.Fragment);
            }
        }
    }


    @EventHandler
    public void killChasseur(EntityDeathEvent event) {
        this.sqlmanager = plugin.getSQLManager();
        if(event.getEntity().getKiller() != null) {
            if (event.getEntity() instanceof Player || event.getEntity() instanceof Cow || event.getEntity() instanceof Sheep || event.getEntity() instanceof Zombie || event.getEntity() instanceof Spider || event.getEntity() instanceof Skeleton || event.getEntity() instanceof Golem || event.getEntity() instanceof Creeper || event.getEntity() instanceof Enderman || event.getEntity() instanceof PigZombie) {
                Entity entite = event.getEntity();
                Player player = event.getEntity().getKiller();
                int expneed = 0;
                int playerexp = 0; //exp if the player
                int lvl = 0;
                String expmetier = "expchasseur";
                String lvlmetier = "lvlchasseur";
                String expneedmetier = "needexpchasseur";
                Random random = new Random();
                int randomNumber = random.nextInt(1000 + 1 - 1) + 1;
                try {
                    expneed = this.sqlmanager.getExpNeed(player, expneedmetier);
                    playerexp = this.sqlmanager.getExp(player, expmetier);
                    lvl = this.sqlmanager.getLevel(player, lvlmetier);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                double palier = Math.floor((((double)lvl/2)/10));
                if (entite instanceof Player) {
                    try {
                        playerexp = playerexp + 6;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        double proba = 10;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Cow) {
                    try {
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 2;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (entite instanceof Sheep) {

                    try {
                        playerexp = playerexp + 3;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 10;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Zombie) {

                    try {
                        playerexp = playerexp + 4;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 30;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Skeleton) {

                    try {
                        playerexp = playerexp + 4;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 30;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Golem) {

                    try {
                        playerexp = playerexp + 7;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 70;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Spider) {

                    try {
                        playerexp = playerexp + 4;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 30;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Creeper) {

                    try {
                        playerexp = playerexp + 5;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 50;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof Enderman) {

                    try {
                        playerexp = playerexp + 5;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 50;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (entite instanceof org.bukkit.entity.PigZombie) {

                    try {
                        playerexp = playerexp + 7;
                        this.sqlmanager.setExp(player, expmetier, playerexp);
                        int proba = 50;
                        dropKey(event,proba,randomNumber,palier);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (playerexp >= expneed) {
                    levelup(player, lvlmetier, expmetier, expneedmetier, expneed, playerexp);
                } else {
                    ActionBarAPI.send(player, ChatColor.GREEN + "Chasseur Level " + lvl + " : " + ChatColor.YELLOW + playerexp + ChatColor.BOLD + ChatColor.GRAY + "/" + ChatColor.GOLD + expneed);
                }
            }
        }
    }

    @EventHandler
    public void newPlayer(PlayerLoginEvent event) {
        this.sqlmanager = plugin.getSQLManager();
        Player player = event.getPlayer();
        try {
            if (!this.sqlmanager.pseudoExist(player)) {
                this.sqlmanager.setNewPlayer(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void levelup(Player player, String lvlmetier, String expmetier, String expneedmetier, int expneed,int playerexp) {
        this.sqlmanager = plugin.getSQLManager();
        int lvl = 0;
        try {
            lvl = this.sqlmanager.getLevel(player, lvlmetier);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        lvl++;
        try {
            this.sqlmanager.setLevel(player, lvlmetier, lvl);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        int newexp = playerexp - expneed ;
        try {
            this.sqlmanager.setExp(player, expmetier,newexp);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        int newexpneed = expneed * this.plugin.getCoeff();
        try {
            this.sqlmanager.setExpNeed(player, expneedmetier, newexpneed);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        String metier = "";
        switch(expneedmetier){
            case "needexpchasseur":
                metier = "Chasseur";
                break;

            case "needexpfarm":
                metier = "Paysan";
                break;

            case "needexpmineur":
                metier = "Mineur";
                break;
        }
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 1.0F);
        ActionBarAPI.send(player, ChatColor.GREEN + "Chasseur Level "+lvl+" : "  + ChatColor.YELLOW + newexp + ChatColor.BOLD + ChatColor.GRAY + "/" + ChatColor.GOLD + newexpneed );
        player.sendMessage(ChatColor.GREEN+"Tu est passé niveau "+ lvl + " dans le metier de "+metier);


    }

    @EventHandler
    public  void leftclickhoue(BlockBreakEvent event){
        try {

            if(event.getPlayer().getItemInHand().getItemMeta().equals(ItemManager.Houe.getItemMeta())){
                if(event.getPlayer().getItemInHand() != null){

                    Player player = event.getPlayer();
                    Block b = event.getBlock();
                    Location loca = b.getLocation();

                    //Verifie Worldguard Protection
                    WorldGuardPlugin worldGuard = getWorldGuard();
                    assert worldGuard != null;
                    RegionManager regionManager = worldGuard.getRegionManager(b.getWorld());
                    ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(loca);

                    ProtectedRegion r = regionManager.getRegion("wrz+spawn");
                    for(ProtectedRegion region : applicableRegionSet) {
                        assert r != null;
                        if(r.getId().equals(region.getId())) {
                            player.sendMessage(ChatColor.RED + "Tu ne peut pas utiliser cet Item ici ");
                            return;
                        }
                    }
                    //Recupere la faction du joueur
                    FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                    Faction playerfaction = fPlayer.getFaction();

                    //Recupere Faction � la position du bloc
                    Faction faction;
                    FLocation fLoc = new FLocation(loca);
                    faction = Board.getInstance().getFactionAt(fLoc);

                    //Recupere Faction Wilderness
                    Faction wild = Factions.getInstance().getByTag("Wilderness");

                    if( faction.equals(playerfaction) || (faction.equals(wild))){
                        //appel de la fonction farm
                        farmhoue(event,b);
                    }

                }
            }
        } catch (Exception ignored) {}
    }


    //Fonction Houe Ultime
    @EventHandler
    public void leftClickHoueUltime(BlockBreakEvent event){
        try {
            if(event.getPlayer().getItemInHand().getItemMeta().equals(ItemManager.HoueUltime.getItemMeta())) {
                if(event.getPlayer().getItemInHand() != null){

                    Player player = event.getPlayer();
                    Block b = event.getBlock();
                    Location loca = b.getLocation();

                    //Verifie Worldguard Protection
                    WorldGuardPlugin worldGuard = getWorldGuard();
                    assert worldGuard != null;
                    RegionManager regionManager = worldGuard.getRegionManager(b.getWorld());
                    ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(loca);

                    ProtectedRegion r = regionManager.getRegion("wrz+spawn");
                    for(ProtectedRegion region : applicableRegionSet) {
                        assert r != null;
                        if(r.getId().equals(region.getId())) {
                            player.sendMessage(ChatColor.RED + "Tu ne peut pas utiliser cet Item ici ");
                            return;
                        }
                    }
                    //Recupere la faction du joueur
                    FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                    Faction playerfaction = fPlayer.getFaction();

                    //Recupere Faction � la position du bloc
                    Faction faction;
                    FLocation fLoc = new FLocation(loca);
                    faction = Board.getInstance().getFactionAt(fLoc);

                    //Recupere Faction Wilderness
                    Faction wild = Factions.getInstance().getByTag("Wilderness");
                    if( faction.equals(playerfaction) || (faction.equals(wild))){
                        double x = b.getLocation().getBlockX();
                        double y = b.getLocation().getBlockY();
                        double z = b.getLocation().getBlockZ();
                        for(int i=-2;i<3;i++) {
                            for(int w =-2;w<3;w++) {
                                Location voisin = new Location(b.getWorld(),x+i,y,z+w);
                                farmhoue(event,voisin.getBlock());
                            }
                        }

                    }

                }
            }
        } catch (Exception ignored) {}

    }

    @EventHandler
    public void craftclee(CraftItemEvent event) {
        try{
            if (event.isCancelled() || !(event.getWhoClicked() instanceof Player)) {
                return;
            }
            CraftingInventory inventory = event.getInventory();
            ItemStack[] matrix = inventory.getMatrix();
            if(matrix[0].equals(ItemManager.Fragment) && matrix[1].equals(ItemManager.Fragment) && matrix[2].equals(ItemManager.Fragment) && matrix[3].equals(ItemManager.Fragment) && matrix[4].equals(ItemManager.Fragment)
                    && matrix[5].equals(ItemManager.Fragment)&& matrix[6].equals(ItemManager.Fragment)&& matrix[7].equals(ItemManager.Fragment) && matrix[8].equals(ItemManager.Fragment)){
            }else {
                event.getWhoClicked().sendMessage(ChatColor.RED+"Essaye de trouver les vrais cl�s au lieu de jouer au malin ;)");
                event.setCancelled(true);
                event.getWhoClicked().closeInventory();
            }
        }catch (Exception ignored) {}
    }


    //protection crochet
    @EventHandler

    public void protectioncrochet(BlockPlaceEvent event) {
        try {
            if(event.getPlayer() != null) {
                if(event.getPlayer().getItemInHand().getItemMeta().equals(ItemManager.Fragment.getItemMeta()) || event.getPlayer().getItemInHand().getItemMeta().equals(ItemManager.Clee.getItemMeta())) {
                    event.setCancelled(true);
                }
            }
        }catch (Exception ignored) {}

    }

    @Deprecated
    public void farmhoue(BlockBreakEvent event,Block b) {

        BlockState state = b.getState();
        MaterialData data = state.getData();
        Player player = event.getPlayer();
        //Verrue
        if (data instanceof NetherWarts)
        {
            NetherWarts netherWarts = (NetherWarts)data;
            if (netherWarts.getState() == NetherWartsState.RIPE)
            {
                netherWarts.setState(NetherWartsState.SEEDED);
                state.setData(netherWarts);
                state.update();
                Random random = new Random();
                int randomNumber = random.nextInt(4 + 1 - 2) + 2;
                for(int i=0;i<randomNumber+1;i++){
                    player.getInventory().addItem(new ItemStack(Material.NETHER_STALK));
                }
                event.setCancelled(true);
            }else {
                event.setCancelled(true);
            }
        }   //Ble

        if (b.getType().equals((Material.CARROT)))
        {
            if(b.getData()==7) {

                state.setRawData((byte) 0);
                state.update();
                Random random = new Random();
                int randomNumber = random.nextInt(3 + 1 - 2) + 2;
                for(int i=0;i<randomNumber+1;i++){
                    player.getInventory().addItem(new ItemStack(Material.CARROT_ITEM));
                }
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }else {
            event.setCancelled(true);
        }


        if (b.getType().equals((Material.POTATO)))
        {
            if(b.getData()==7) {

                state.setRawData((byte) 0);
                state.update();
                Random random = new Random();
                int randomNumber = random.nextInt(5 + 1 - 1) + 1;
                for(int i=0;i<randomNumber+1;i++){
                    player.getInventory().addItem(new ItemStack(Material.POTATO_ITEM));
                }
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }else {
            event.setCancelled(true);
        }


        if (data instanceof Crops) {

            Crops crops = (Crops)data;
            if (crops.getState() == CropState.RIPE){

                crops.setState(CropState.SEEDED);
                state.setData(crops);
                state.update();
                player.getInventory().addItem(new ItemStack(Material.WHEAT));

                event.setCancelled(true);
            }else {
                event.setCancelled(true);
            }
        }
    }



    //Fonction Constructeur
    @EventHandler
    public  void onRightClick(PlayerInteractEvent event) {
        try {
            if (event.getAction() != Action.RIGHT_CLICK_BLOCK){
                return;
            }
            if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Constructeur")) {
                if(event.getItem().getItemMeta().getLore().get(0).equals(ChatColor.LIGHT_PURPLE+"Fais Clique Droit avec cet item sur un block")) {
                    if(event.getItem().getType() != Material.DIAMOND_AXE) {
                        return;
                    }
                    if(event.getItem() != null) {
                        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){

                            Player player = event.getPlayer();
                            World world = player.getWorld();
                            Block b = event.getClickedBlock();
                            Location loca = b.getLocation();

                            //Verifie Worldguard Protection

                            WorldGuardPlugin worldGuard = getWorldGuard();
                            assert worldGuard != null;
                            RegionManager regionManager = worldGuard.getRegionManager(b.getWorld());
                            ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(loca);

                            ProtectedRegion r = regionManager.getRegion("wrz+spawn");
                            for(ProtectedRegion region : applicableRegionSet) {
                                assert r != null;
                                if(r.getId().equals(region.getId())) {
                                    player.sendMessage(ChatColor.RED + "Tu ne peut pas utiliser cet Item ici ");
                                    return;
                                }
                            }
                            //Verification Faction Protection

                            //Recupere la faction du joueur
                            FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                            Faction playerfaction = fPlayer.getFaction();

                            //Recupere Faction � la position du bloc
                            Faction faction;
                            FLocation fLoc = new FLocation(loca);
                            faction = Board.getInstance().getFactionAt(fLoc);

                            //Recupere Faction Wilderness
                            Faction wild = Factions.getInstance().getByTag("Wilderness");

                            if( faction.equals(playerfaction) || (faction.equals(wild))){


                                //Application de l'effet de l'item

                                int x = b.getLocation().getBlockX();
                                int z = b.getLocation().getBlockZ();

                                for(int i=0;i<256;i++){
                                    Location loc = new Location(world,x,i,z);
                                    //Empeche la Bedrock d'etre remplace
                                    if(!loc.getBlock().getType().equals(Material.BEDROCK)){
                                        loc.getBlock().setType(Material.OBSIDIAN);
                                    }
                                }
                                //Changement de lore et gere durabilite

                                ItemStack hand = event.getItem();
                                ItemMeta meta = hand.getItemMeta();

                                String[] tab =  meta.getLore().get(2).split("\\s+");
                                int dura = Integer.parseInt(tab[3]);
                                if(dura == 1) {

                                    player.setItemInHand(new ItemStack(Material.AIR));

                                }else{
                                    dura = dura-1;
                                    List<String> lore = new ArrayList<>();
                                    lore.add(ChatColor.LIGHT_PURPLE+"Fais Clique Droit avec cet item sur un block");
                                    lore.add(ChatColor.LIGHT_PURPLE+"pour cr�e une tour d'Obsidian de la couche 0 � 250");
                                    lore.add(ChatColor.GREEN+"Utilisation restante : "+dura);
                                    meta.setLore(lore);
                                    hand.setItemMeta(meta);
                                }

                            }else {
                                player.sendMessage(ChatColor.RED+"Tu ne peut pas utiliser cet item ici");
                            }
                        }
                    }

                }
            }
        } catch (Exception ignored) {}
    }

}
