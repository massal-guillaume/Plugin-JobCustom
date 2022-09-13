package me.massal.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;


public class ItemManager {

    public static ItemStack Constructeur;
    public static ItemStack Houe;
    public static ItemStack HoueUltime;
    public static ItemStack PiocheDivine;
    public static ItemStack Trancheuse;
    public static ItemStack Fragment;
    public static ItemStack Clee;

    public static void init() {
        createConstructeur();
        createHoue();
        createHoueUltime();
        createFortune();
        createepee();
        createFragment();
        createClee();
    }


    public static  void createConstructeur() {

        ItemStack item = new ItemStack(Material.DIAMOND_AXE,1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setDisplayName(ChatColor.GOLD+"Constructeur");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Fais Clique Droit avec cet item sur un block");
        lore.add(ChatColor.LIGHT_PURPLE+"pour cr�e une tour d'Obsidian de la couche 0 � 250");
        lore.add(ChatColor.GREEN+"Utilisation restante : 5");

        meta.setLore(lore);
        item.setItemMeta(meta);
        Constructeur = item;



    }

    public static void createHoue() {

        ItemStack item = new ItemStack(Material.GOLD_HOE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Houe Enchant�e");
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Utilise la Houe Enchant�e pour farmer plus rapidement");
        lore.add(ChatColor.LIGHT_PURPLE+"Cet Houe replace automatiquement les cultures");
        lore.add(ChatColor.GREEN+"Rayon : 1x1 bloc");
        meta.setLore(lore);

        item.setItemMeta(meta);
        Houe = item;

    }

    public static void createHoueUltime() {

        ItemStack item = new ItemStack(Material.DIAMOND_HOE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Houe Ultime");
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Utilise la Houe Ultime pour farmer plus rapidement");
        lore.add(ChatColor.LIGHT_PURPLE+"Cet Houe replace automatiquement les cultures");
        lore.add(ChatColor.GREEN+"Rayon : 5x5 blocs");
        meta.setLore(lore);

        item.setItemMeta(meta);
        HoueUltime = item;

    }

    public static void createFortune() {

        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Pioche Divine");
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS,4,true);
        meta.addEnchant(Enchantment.DIG_SPEED,10,true);
        meta.addEnchant(Enchantment.DURABILITY,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Prend soin de cette Pioche elle est unique !");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PiocheDivine = item;


    }

    public static void createepee() {

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Trancheuse de Monstre");
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS,5,true);
        meta.addEnchant(Enchantment.DURABILITY,5,true);
        meta.addEnchant(Enchantment.DAMAGE_UNDEAD,10,true);
        meta.addEnchant(Enchantment.DAMAGE_ALL,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Prend soin de cette Ep�e elle est unique !");
        meta.setLore(lore);
        item.setItemMeta(meta);
        Trancheuse = item;


    }

    public static void createFragment() {

        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Fragment de cl�e");
        meta.addEnchant(Enchantment.LUCK,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Peut-�tre que si on en assemblait plusieurs");
        lore.add(ChatColor.GRAY+"on pourrait obtenir quelque chose ?");
        meta.setLore(lore);
        item.setItemMeta(meta);
        Fragment = item;

    }

    public static void createClee() {


        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Cl�e Enchant�e");
        meta.addEnchant(Enchantment.LUCK,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Cette cl�e permet d'obtenir de nombreuses r�compenses");
        meta.setLore(lore);
        item.setItemMeta(meta);
        Clee = item;

        //Craft
        ShapedRecipe sr = new ShapedRecipe(Clee);
        sr.shape("AAA","AAA","AAA");
        sr.setIngredient('A',new MaterialData(Material.TRIPWIRE_HOOK,ItemManager.Fragment.getData().getData()));
        Bukkit.addRecipe(sr);


    }


}
