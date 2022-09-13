package me.massal.commandes;

import me.massal.inventaire.Inventaire;
import me.massal.item.ItemManager;
import me.massal.jobscustom.JobsCustom;
import me.massal.sqlmanager.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;


public class CommandesMetier implements CommandExecutor {

    private final JobsCustom plugin;
    public SQLManager sqlmanager;

    public  CommandesMetier(JobsCustom m) {
        this.plugin = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        // Commande : /jobs set chasseur lvl 0 womegazell
        if(cmd.getName().equalsIgnoreCase("jobs")){
            this.sqlmanager = plugin.getSQLManager();
            if(sender instanceof Player) {
                if(args.length == 0) {
                    Player player = ((Player) sender).getPlayer();
                    player.sendMessage(ChatColor.DARK_RED+"Commande inconnu, essayez /jobs menu ");
                    return false;
                }
                if(args[0].equals("set")){
                    if(sender.isOp()){
                        Player player = Bukkit.getPlayer(args[4]);
                        switch(args[1]){
                            case "chasseur":
                                if(args[2].equals("lvl")) {
                                    try {
                                        this.sqlmanager.setLevel(player,"lvlchasseur", Integer.parseInt(args[3]) );
                                        int newexpneed = 10;
                                        for(int i=0;i<Integer.parseInt(args[3]);i++) {
                                            newexpneed = newexpneed*this.plugin.getCoeff();
                                        }
                                        this.sqlmanager.setExpNeed(player,"needexpchasseur", newexpneed);
                                        player.sendMessage(ChatColor.RED + "Le level du metier chasseur pour "+player.getName()+" a bien été  mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if(args[2].equals("exp")) {
                                    try {
                                        this.sqlmanager.setExp(player,"expchasseur",Integer.parseInt(args[3]));
                                        player.sendMessage(ChatColor.RED + "L'exp du metier chasseur pour "+player.getName()+" a bien été  mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }

                                }
                                break;

                            case "farm":
                                if(args[2].equals("lvl")) {
                                    try {
                                        this.sqlmanager.setLevel(player,"lvlfarm", Integer.parseInt(args[3]) );
                                        int newexpneed = 10;
                                        for(int i=0;i<Integer.parseInt(args[3]);i++) {
                                            newexpneed = newexpneed*this.plugin.getCoeff();
                                        }
                                        this.sqlmanager.setExpNeed(player,"needexpfarm", newexpneed);
                                        player.sendMessage(ChatColor.RED + "Le level du metier Farmeur pour "+player.getName()+" a bien été  mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(args[2].equals("exp")) {
                                    try {
                                        this.sqlmanager.setLevel(player,"expfarm", Integer.parseInt(args[3]) );
                                        player.sendMessage(ChatColor.RED + "L'exp du metier farm pour "+player.getName()+" a bien été  mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "mineur":
                                if(args[2].equals("lvl")) {
                                    try {
                                        this.sqlmanager.setLevel(player,"lvlmineur", Integer.parseInt(args[3]) );
                                        int newexpneed = 10;
                                        for(int i=0;i<Integer.parseInt(args[3]);i++) {
                                            newexpneed = newexpneed*this.plugin.getCoeff();
                                        }
                                        this.sqlmanager.setExpNeed(player,"needexpmineur", newexpneed);
                                        player.sendMessage(ChatColor.RED + "Le level du metier Mineur pour "+player.getName()+" a bien été mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(args[2].equals("exp")) {
                                    try {
                                        this.sqlmanager.setLevel(player,"expmineur", Integer.parseInt(args[3]) );
                                        player.sendMessage(ChatColor.RED + "L'exp du metier expmineur pour "+player.getName()+" a bien été  mis à jour");
                                    }catch  (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;

                        }

                    }
                }
                if(args[0].equals("help")){
                    Player player = ((Player) sender).getPlayer();
                    if(player.isOp()) {
                        player.sendMessage(ChatColor.GREEN+"----"+ChatColor.GOLD+"MetierCustom Help"+ChatColor.GREEN+"----");
                        player.sendMessage(ChatColor.GREEN+"Commandes Disponible :");
                        player.sendMessage(ChatColor.GREEN+"/Jobs set (metier) (lvl/exp) (valeur) (pseudo)");
                    }
                }
                if(args[0].equals("menu")) {
                    Player player = ((Player) sender).getPlayer();
                    Inventaire gui = new Inventaire(player,plugin);
                    player.openInventory(gui.getInventory());

                }

            }

        }

        //Consol give
        if(cmd.getName().equalsIgnoreCase("giveconstru")){
            if(sender instanceof ConsoleCommandSender) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null) {
                    target.getInventory().addItem(ItemManager.Constructeur);
                    target.sendMessage(ChatColor.GOLD + "Tu viens de recevoir un Constructeur !");
                }

            }else {
                sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
            }
        }

        //Consol give
        if(cmd.getName().equalsIgnoreCase("givepiochedivine")){
            if(sender instanceof ConsoleCommandSender) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null) {
                    target.getInventory().addItem(ItemManager.PiocheDivine);
                    target.sendMessage(ChatColor.GOLD + "Tu viens de recevoir une Pioche Ultime !");
                }

            }else {
                sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
            }
        }

        //Consol give
        if(cmd.getName().equalsIgnoreCase("givetrancheuse")){
            if(sender instanceof ConsoleCommandSender) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null) {
                    target.getInventory().addItem(ItemManager.Trancheuse);
                    target.sendMessage(ChatColor.GOLD + "Tu viens de recevoir une Trancheuse de Monstre !");
                }

            }else {
                sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
            }
        }

        //Command give Item Constructeur
        if(cmd.getName().equalsIgnoreCase("givec")){
            Player player = (Player) sender;
            if (args.length > 0) {
                sender.sendMessage(ChatColor.RED + "To many arguments.");
                return false;
            }else{
                if (player.isOp()){
                    player.getInventory().addItem(ItemManager.Constructeur);
                }else {
                    sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
                }
            }
        }


        if(cmd.getName().equalsIgnoreCase("givehoue")){
            if(sender instanceof ConsoleCommandSender) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null) {
                    target.getInventory().addItem(ItemManager.Houe);
                    target.sendMessage(ChatColor.GOLD + "Tu viens de recevoir une houe Enchant�e !");
                }

            }else {
                sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
            }
        }

        //Consol give
        if(cmd.getName().equalsIgnoreCase("givehoueultime")){
            if(sender instanceof ConsoleCommandSender) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null) {
                    target.getInventory().addItem(ItemManager.HoueUltime);
                    target.sendMessage(ChatColor.GOLD + "Tu viens de recevoir une houe Ultime !");
                }

            }else {
                sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
            }
        }

        //Command give Item Houe
        if(cmd.getName().equalsIgnoreCase("giveh")){
            Player player = (Player) sender;
            if (args.length > 0) {
                sender.sendMessage(ChatColor.RED + "To many arguments.");
                return false;
            }else{
                if (player.isOp()){
                    player.getInventory().addItem(ItemManager.Houe);
                }else {
                    sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
                }
            }
        }


        //Command give Item Ultime
        if(cmd.getName().equalsIgnoreCase("givehultime")){
            Player player = (Player) sender;
            if (args.length > 0) {
                sender.sendMessage(ChatColor.RED + "To many arguments.");
                return false;
            }else{
                if (player.isOp()){
                    player.getInventory().addItem(ItemManager.HoueUltime);
                }else {
                    sender.sendMessage(ChatColor.RED + "Tu n'a pas la permission d'utiliser cette commande");
                }
            }
        }

        return false;

    }
}
