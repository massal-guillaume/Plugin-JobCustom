package me.massal.inventaire;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.massal.jobscustom.JobsCustom;
import me.massal.sqlmanager.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Inventaire implements InventoryHolder {


    private final Inventory inv;
    private Inventory invfarm;
    private Inventory invchasseur;
    private Inventory invmineur;
    private Inventory shop;
    public SQLManager sqlmanager;

    public Inventaire(Player player, JobsCustom m) {
        inv = Bukkit.createInventory(null, 54, "Menu Metiers");
        sqlmanager = m.getSQLManager();
        init(player);

    }

    public Inventory getInvMineur() {
        invmineur = Bukkit.createInventory(null, 54, "Info Mineur");
        this.initMineur();
        return this.invmineur;

    }

    public Inventory getInvFarm() {
        invfarm = Bukkit.createInventory(null, 36, "Info Farm");
        this.initFarm();
        return this.invfarm;
    }

    public Inventory getInvChasseur() {
        invchasseur = Bukkit.createInventory(null, 54, "Info Chasseur");
        this.initChasseur();
        return this.invchasseur;
    }

    public Inventory getShop() {
        shop = Bukkit.createInventory(null, 54, "Reward Metier");
        this.initShop();
        return shop;
    }

    private void init(Player player) {
        try {
            ItemStack houe = new ItemStack(Material.DIAMOND_HOE, 1);
            ItemMeta houeM = houe.getItemMeta();
            houeM.addEnchant(Enchantment.LUCK, 1, true);
            houeM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            houeM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            houeM.setDisplayName(ChatColor.GOLD + "Farmeur");
            List<String> loref = new ArrayList<>();
            loref.add(ChatColor.GREEN + "Clique pour avoir des détails sur le metier de Farmeur");
            loref.add(ChatColor.LIGHT_PURPLE + "Niveau actuel : " + ChatColor.YELLOW + this.sqlmanager.getLevel(player, "lvlfarm"));
            loref.add(ChatColor.LIGHT_PURPLE + "Experience actuel : " + ChatColor.YELLOW + this.sqlmanager.getExp(player, "expfarm") + ChatColor.BOLD + ChatColor.GRAY + " / " + ChatColor.GOLD + this.sqlmanager.getExpNeed(player, "needexpfarm"));
            houeM.setLore(loref);
            houe.setItemMeta(houeM);
            inv.setItem(20, houe);

            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta swordM = sword.getItemMeta();
            swordM.setDisplayName(ChatColor.GOLD + "Chasseur");
            swordM.addEnchant(Enchantment.LUCK, 1, true);
            swordM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            swordM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            List<String> lores = new ArrayList<>();
            lores.add(ChatColor.GREEN + "Clique pour avoir des détails sur le metier de Chasseur");
            lores.add(ChatColor.LIGHT_PURPLE + "Niveau actuel : " + ChatColor.YELLOW + this.sqlmanager.getLevel(player, "lvlchasseur"));
            lores.add(ChatColor.LIGHT_PURPLE + "Experience actuel : " + ChatColor.YELLOW + this.sqlmanager.getExp(player, "expchasseur") + ChatColor.BOLD + ChatColor.GRAY + " / " + ChatColor.GOLD + this.sqlmanager.getExpNeed(player, "needexpchasseur"));
            swordM.setLore(lores);
            sword.setItemMeta(swordM);
            this.inv.setItem(22, sword);

            ItemStack pioche = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            ItemMeta piocheM = pioche.getItemMeta();
            piocheM.setDisplayName(ChatColor.GOLD + "Mineur");
            piocheM.addEnchant(Enchantment.LUCK, 1, true);
            piocheM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            piocheM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            List<String> lorem = new ArrayList<>();
            lorem.add(ChatColor.GREEN + "Clique pour avoir des détails sur le metier de Mineur");
            lorem.add(ChatColor.LIGHT_PURPLE + "Niveau actuel : " + ChatColor.YELLOW + this.sqlmanager.getLevel(player, "lvlmineur"));
            lorem.add(ChatColor.LIGHT_PURPLE + "Experience actuel : " + ChatColor.YELLOW + this.sqlmanager.getExp(player, "expmineur") + ChatColor.BOLD + ChatColor.GRAY + " / " + ChatColor.GOLD + this.sqlmanager.getExpNeed(player, "needexpmineur"));
            piocheM.setLore(lorem);
            pioche.setItemMeta(piocheM);
            this.inv.setItem(24, pioche);

            ItemStack shop = new ItemStack(Material.GOLD_INGOT, 1);
            ItemMeta shopM = shop.getItemMeta();
            shopM.setDisplayName(ChatColor.GOLD + "Rewards");
            shopM.addEnchant(Enchantment.LUCK, 1, true);
            shopM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            shopM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            List<String> loresh = new ArrayList<>();
            loresh.add(ChatColor.LIGHT_PURPLE + "Clique pour récuperer tes récompenses");
            shopM.setLore(loresh);
            shop.setItemMeta(shopM);
            this.inv.setItem(40, shop);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initShop() {

        ItemStack chestfarm1 = new ItemStack(Material.CHEST, 1);
        ItemMeta chestfarm1M = chestfarm1.getItemMeta();
        chestfarm1M.addEnchant(Enchantment.LUCK, 1, true);
        chestfarm1M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chestfarm1M.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        chestfarm1M.setDisplayName(ChatColor.GOLD + "Reward 10");
        List<String> lorechestfarm1 = new ArrayList<>();
        lorechestfarm1.add(ChatColor.LIGHT_PURPLE + "Cliquez pour recuperer la recompense du LvL 10");
        chestfarm1M.setLore(lorechestfarm1);
        chestfarm1.setItemMeta(chestfarm1M);
        invfarm.setItem(10,chestfarm1);


    }

    private void initFarm() {

        ItemStack verrue = new ItemStack(Material.NETHER_STALK, 1);
        ItemMeta verrueM = verrue.getItemMeta();
        verrueM.addEnchant(Enchantment.LUCK, 1, true);
        verrueM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        verrueM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        verrueM.setDisplayName(ChatColor.GOLD + "Verrues du Nether");
        List<String> loreverrue = new ArrayList<>();
        loreverrue.add(ChatColor.LIGHT_PURPLE + "Experience par Verrue : 3");
        verrueM.setLore(loreverrue);
        verrue.setItemMeta(verrueM);
        invfarm.setItem(10, verrue);

        ItemStack ble = new ItemStack(Material.WHEAT, 1);
        ItemMeta bleM = ble.getItemMeta();
        bleM.addEnchant(Enchantment.LUCK, 1, true);
        bleM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bleM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        bleM.setDisplayName(ChatColor.GOLD + "Blé");
        List<String> loreble = new ArrayList<>();
        loreble.add(ChatColor.LIGHT_PURPLE + "Experience par Blé : 3");
        bleM.setLore(loreble);
        ble.setItemMeta(bleM);
        invfarm.setItem(12, ble);

        ItemStack carotte = new ItemStack(Material.CARROT_ITEM, 1);
        ItemMeta carotteM = carotte.getItemMeta();
        carotteM.addEnchant(Enchantment.LUCK, 1, true);
        carotteM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        carotteM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        carotteM.setDisplayName(ChatColor.GOLD + "Carotte");
        List<String> lorecarotte = new ArrayList<>();
        lorecarotte.add(ChatColor.LIGHT_PURPLE + "Experience par Carotte : 3");
        carotteM.setLore(lorecarotte);
        carotte.setItemMeta(carotteM);
        invfarm.setItem(14, carotte);

        ItemStack patate = new ItemStack(Material.POTATO_ITEM, 1);
        ItemMeta patateM = patate.getItemMeta();
        patateM.addEnchant(Enchantment.LUCK, 1, true);
        patateM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        patateM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        patateM.setDisplayName(ChatColor.GOLD + "Pomme de terre");
        List<String> lorepatate = new ArrayList<>();
        lorepatate.add(ChatColor.LIGHT_PURPLE + "Experience par Pomme de terre : 3");
        patateM.setLore(lorepatate);
        patate.setItemMeta(patateM);
        invfarm.setItem(16, patate);

        ItemStack retour = new ItemStack(Material.BARRIER, 1);
        ItemMeta retourM = retour.getItemMeta();
        retourM.addEnchant(Enchantment.LUCK, 1, true);
        retourM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        retourM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        retourM.setDisplayName(ChatColor.GOLD + "Retour Menu Metier");
        List<String> loreretour = new ArrayList<>();
        loreretour.add(ChatColor.LIGHT_PURPLE + "Clique pour revenir au Menu Metier");
        retourM.setLore(loreretour);
        retour.setItemMeta(retourM);
        invfarm.setItem(35, retour);
    }

    private void initChasseur() {

        //Player
        ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta playerheadM =  playerhead.getItemMeta();
        playerheadM.addEnchant(Enchantment.LUCK, 1, true);
        playerheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        playerheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        playerheadM.setDisplayName(ChatColor.GOLD + "Joueur");
        List<String> loreplayerhead = new ArrayList<>();
        loreplayerhead.add(ChatColor.LIGHT_PURPLE + "Experience pour chaque Joueur Kill : 6");
        playerheadM.setLore(loreplayerhead);
        playerhead.setItemMeta(playerheadM);
        invchasseur.setItem(4, playerhead);

        //Cow
        ItemStack cowhead = getSkull("http://textures.minecraft.net/texture/be8456155142cbe4e61353ffbaff304d3d9c4bc9247fc27b92e33e6e26067edd");
        ItemMeta cowheadM = cowhead.getItemMeta();
        cowheadM.addEnchant(Enchantment.LUCK, 1, true);
        cowheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        cowheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        cowheadM.setDisplayName(ChatColor.GOLD + "Vache");
        List<String> lorecowhead = new ArrayList<>();
        lorecowhead.add(ChatColor.LIGHT_PURPLE + "Experience par Vache : 3");
        cowheadM.setLore(lorecowhead);
        cowhead.setItemMeta(cowheadM);
        invchasseur.setItem(12,cowhead);


        //Mouton
        ItemStack sheephead = getSkull("http://textures.minecraft.net/texture/292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4");
        ItemMeta sheepheadM =  sheephead.getItemMeta();
        sheepheadM.addEnchant(Enchantment.LUCK, 1, true);
        sheepheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sheepheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sheepheadM.setDisplayName(ChatColor.GOLD + "Mouton");
        List<String> loresheephead = new ArrayList<>();
        loresheephead.add(ChatColor.LIGHT_PURPLE + "Experience par Mouton : 3");
        sheepheadM.setLore(loresheephead);
        sheephead.setItemMeta(sheepheadM);
        invchasseur.setItem(14,sheephead);

        ItemStack zombiehead = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta zombieheadM =  zombiehead.getItemMeta();
        zombieheadM.addEnchant(Enchantment.LUCK, 1, true);
        zombieheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        zombieheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        zombieheadM.setDisplayName(ChatColor.GOLD + "Zombie");
        List<String> lorezombiehead = new ArrayList<>();
        lorezombiehead.add(ChatColor.LIGHT_PURPLE + "Experience par Zombie : 4");
        zombieheadM.setLore(lorezombiehead);
        zombiehead.setItemMeta(zombieheadM);
        invchasseur.setItem(20,zombiehead);

        ItemStack skullhead = new ItemStack(Material.SKULL_ITEM);
        ItemMeta skullheadM = skullhead.getItemMeta();
        skullheadM.addEnchant(Enchantment.LUCK, 1, true);
        skullheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        skullheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        skullheadM.setDisplayName(ChatColor.GOLD + "Squelette");
        List<String> loreskullhead = new ArrayList<>();
        loreskullhead.add(ChatColor.LIGHT_PURPLE + "Experience par Squelette : 4");
        skullheadM.setLore(loreskullhead);
        skullhead.setItemMeta(skullheadM);
        invchasseur.setItem(22,skullhead);

        ItemStack spiderhead  = getSkull("http://textures.minecraft.net/texture/c87a96a8c23b83b32a73df051f6b84c2ef24d25ba4190dbe74f11138629b5aef");
        ItemMeta spiderheadM =  spiderhead.getItemMeta();
        spiderheadM.addEnchant(Enchantment.LUCK, 1, true);
        spiderheadM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spiderheadM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        spiderheadM.setDisplayName(ChatColor.GOLD + "Araignée");
        List<String> lorespiderhead = new ArrayList<>();
        lorespiderhead.add(ChatColor.LIGHT_PURPLE + "Experience par Araignée : 4");
        spiderheadM.setLore(lorespiderhead);
        spiderhead.setItemMeta(spiderheadM);
        invchasseur.setItem(24,spiderhead);

        //Enderman
        ItemStack Enderman = getSkull("http://textures.minecraft.net/texture/79dfde4f3387e4eeac45a839405aaa00dfbd630f35b6af5e3504395a95e2aecc");
        ItemMeta EndermanM = Enderman.getItemMeta();
        EndermanM.addEnchant(Enchantment.LUCK, 1, true);
        EndermanM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        EndermanM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        EndermanM.setDisplayName(ChatColor.GOLD + "EnderMan");
        List<String> loreEnderman = new ArrayList<>();
        loreEnderman.add(ChatColor.LIGHT_PURPLE + "Experience par EnderMan : 5");
        EndermanM.setLore(loreEnderman);
        Enderman.setItemMeta(EndermanM);
        invchasseur.setItem(28, Enderman);

        ItemStack creeper = getSkull("http://textures.minecraft.net/texture/f4254838c33ea227ffca223dddaabfe0b0215f70da649e944477f44370ca6952");
        ItemMeta creeperM = creeper.getItemMeta();
        creeperM.addEnchant(Enchantment.LUCK, 1, true);
        creeperM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        creeperM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        creeperM.setDisplayName(ChatColor.GOLD + "Creeper");
        List<String> lorecreeper = new ArrayList<>();
        lorecreeper.add(ChatColor.LIGHT_PURPLE + "Experience par Creeper : 5");
        creeperM.setLore(lorecreeper);
        creeper.setItemMeta(creeperM);
        invchasseur.setItem(30, creeper);

        ItemStack pigman = getSkull("http://textures.minecraft.net/texture/1ed80bf99dfb075192b02a5dd6bf91eddd4cb1f481892321211fe2142d9df22c");
        ItemMeta pigmanM = pigman.getItemMeta();
        pigmanM.addEnchant(Enchantment.LUCK, 1, true);
        pigmanM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pigmanM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pigmanM.setDisplayName(ChatColor.GOLD + "Cochons-Zombies");
        List<String> lorepigman = new ArrayList<>();
        lorepigman.add(ChatColor.LIGHT_PURPLE + "Experience par Cochons-Zombies : 7");
        pigmanM.setLore(lorepigman);
        pigman.setItemMeta(pigmanM);
        invchasseur.setItem(32,pigman);

        ItemStack golem = getSkull("http://textures.minecraft.net/texture/e13f34227283796bc017244cb46557d64bd562fa9dab0e12af5d23ad699cf697");
        ItemMeta golemM = golem .getItemMeta();
        golemM.addEnchant(Enchantment.LUCK, 1, true);
        golemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        golemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        golemM.setDisplayName(ChatColor.GOLD + "Golem de Fer");
        List<String> loregolem = new ArrayList<>();
        loregolem.add(ChatColor.LIGHT_PURPLE + "Experience par Golem de Fer : 7");
        golemM.setLore(loregolem);
        golem.setItemMeta(golemM);
        invchasseur.setItem(34,golem);

        ItemStack retour = new ItemStack(Material.BARRIER, 1);
        ItemMeta retourM = retour.getItemMeta();
        retourM.addEnchant(Enchantment.LUCK, 1, true);
        retourM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        retourM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        retourM.setDisplayName(ChatColor.GOLD + "Retour Menu Metier");
        List<String> loreretour = new ArrayList<>();
        loreretour.add(ChatColor.LIGHT_PURPLE + "Clique pour revenir au Menu Metier");
        retourM.setLore(loreretour);
        retour.setItemMeta(retourM);
        invchasseur.setItem(53, retour);



    }

    private void initMineur() {

        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta bookM = book.getItemMeta();
        bookM.addEnchant(Enchantment.LUCK, 1, true);
        bookM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bookM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        bookM.setDisplayName(ChatColor.GOLD + "Information");
        List<String> lorebook = new ArrayList<>();
        lorebook.add(ChatColor.LIGHT_PURPLE + "La pose des Bloc de Minerais est désactivée");
        lorebook.add(ChatColor.GREEN + "Pour eviter le farm abusif des métiers ");
        lorebook.add(ChatColor.GREEN + "en posant des bloc de minerais,");
        lorebook.add(ChatColor.GREEN + "nous avons décider de désactive");
        lorebook.add(ChatColor.GREEN + "la possibilité de les poser");
        bookM.setLore(lorebook);
        book.setItemMeta(bookM);
        invmineur.setItem(4, book);

        ItemStack stone = new ItemStack(Material.STONE, 1);
        ItemMeta stoneM = stone.getItemMeta();
        stoneM.addEnchant(Enchantment.LUCK, 1, true);
        stoneM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stoneM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stoneM.setDisplayName(ChatColor.GOLD + "Roche");
        List<String> lorestone = new ArrayList<>();
        lorestone.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 1");
        stoneM.setLore(lorestone);
        stone.setItemMeta(stoneM);
        invmineur.setItem(20, stone);

        ItemStack charbon = new ItemStack(Material.COAL_ORE, 1);
        ItemMeta charbonM = charbon.getItemMeta();
        charbonM.addEnchant(Enchantment.LUCK, 1, true);
        charbonM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        charbonM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        charbonM.setDisplayName(ChatColor.GOLD + "Minerais de Charbon");
        List<String> lorec = new ArrayList<>();
        lorec.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 3");
        charbonM.setLore(lorec);
        charbon.setItemMeta(charbonM);
        invmineur.setItem(22, charbon);

        ItemStack lapis = new ItemStack(Material.LAPIS_ORE, 1);
        ItemMeta lapisM = lapis.getItemMeta();
        lapisM.addEnchant(Enchantment.LUCK, 1, true);
        lapisM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lapisM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lapisM.setDisplayName(ChatColor.GOLD + "Minerais de Lapis Lazuli");
        List<String> lorelapis = new ArrayList<>();
        lorelapis.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 3");
        lapisM.setLore(lorelapis);
        lapis.setItemMeta(lapisM);
        invmineur.setItem(24, lapis);

        ItemStack redstone = new ItemStack(Material.REDSTONE_ORE, 1);
        ItemMeta redstoneM = redstone.getItemMeta();
        redstoneM.addEnchant(Enchantment.LUCK, 1, true);
        redstoneM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        redstoneM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        redstoneM.setDisplayName(ChatColor.GOLD + "Minerais de Redstone");
        List<String> loreredstone = new ArrayList<>();
        loreredstone.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 3");
        redstoneM.setLore(loreredstone);
        redstone.setItemMeta(redstoneM);
        invmineur.setItem(30, redstone);

        ItemStack fer = new ItemStack(Material.IRON_ORE, 1);
        ItemMeta ferM = fer.getItemMeta();
        ferM.addEnchant(Enchantment.LUCK, 1, true);
        ferM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ferM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ferM.setDisplayName(ChatColor.GOLD + "Minerais de Fer");
        List<String> lorefer = new ArrayList<>();
        lorefer.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 5");
        ferM.setLore(lorefer);
        fer.setItemMeta(ferM);
        invmineur.setItem(32, fer);

        ItemStack gold = new ItemStack(Material.GOLD_ORE, 1);
        ItemMeta goldM = gold.getItemMeta();
        goldM.addEnchant(Enchantment.LUCK, 1, true);
        goldM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        goldM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        goldM.setDisplayName(ChatColor.GOLD + "Minerais D'Or");
        List<String> loregold = new ArrayList<>();
        loregold.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 5");
        goldM.setLore(loregold);
        gold.setItemMeta(goldM);
        invmineur.setItem(38, gold);

        ItemStack diams = new ItemStack(Material.DIAMOND_ORE, 1);
        ItemMeta diamsM = diams.getItemMeta();
        diamsM.addEnchant(Enchantment.LUCK, 1, true);
        diamsM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        diamsM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        diamsM.setDisplayName(ChatColor.GOLD + "Minerais de Diamant");
        List<String> lorediams = new ArrayList<>();
        lorediams.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 10");
        diamsM.setLore(lorediams);
        diams.setItemMeta(diamsM);
        invmineur.setItem(40, diams);

        ItemStack emeraude = new ItemStack(Material.EMERALD_ORE, 1);
        ItemMeta emeraudeM = emeraude.getItemMeta();
        emeraudeM.addEnchant(Enchantment.LUCK, 1, true);
        emeraudeM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        emeraudeM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        emeraudeM.setDisplayName(ChatColor.GOLD + "Minerais d'émeraude");
        List<String> loreemeraude = new ArrayList<>();
        loreemeraude.add(ChatColor.LIGHT_PURPLE + "Experience par bloc : 25");
        emeraudeM.setLore(loreemeraude);
        emeraude.setItemMeta(emeraudeM);
        invmineur.setItem(42, emeraude);

        ItemStack retour = new ItemStack(Material.BARRIER, 1);
        ItemMeta retourM = retour.getItemMeta();
        retourM.addEnchant(Enchantment.LUCK, 1, true);
        retourM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        retourM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        retourM.setDisplayName(ChatColor.GOLD + "Retour Menu Metier");
        List<String> loreretour = new ArrayList<>();
        loreretour.add(ChatColor.LIGHT_PURPLE + "Clique pour revenir au Menu Metier");
        retourM.setLore(loreretour);
        retour.setItemMeta(retourM);
        invmineur.setItem(53, retour);
    }

    public static ItemStack getSkull(String url){
        ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short)3);
        if(url.isEmpty())
            return head;
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try{
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        }catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1){
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }


    @Override
    public Inventory getInventory() {
        // TODO Auto-generated method stub
        return inv;
    }
}