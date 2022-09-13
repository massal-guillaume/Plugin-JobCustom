package me.massal.sqlmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.massal.jobscustom.JobsCustom;
import org.bukkit.entity.Player;

import me.massal.connectionpoolmanager.ConnectionPoolManager;


public class SQLManager {

    private final JobsCustom plugin;
    private final ConnectionPoolManager pool;

    public SQLManager(JobsCustom plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
        makeTable();
    }

    private void makeTable() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `tablemetier` " +
                            "(" +
                            "name varchar(30)," +
                            "lvlfarm INTEGER,"+
                            "lvlmineur INTEGER,"+
                            "lvlchasseur INTEGER,"+
                            "expfarm INTEGER,"+
                            "expmineur INTEGER,"+
                            "expchasseur INTEGER,"+
                            "needexpfarm INTEGER,"+
                            "needexpmineur INTEGER,"+
                            "needexpchasseur INTEGER"+
                            ")"
            );
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public void onDisable() {
        pool.closePool();
    }

    public boolean pseudoExist(Player player) throws SQLException {
        Connection connection = this.pool.getConnection();
        PreparedStatement stat = connection.prepareStatement("SELECT name FROM " + this.pool.getTablename() + " WHERE name = ?");
        stat.setString(1, player.getName());
        ResultSet result = stat.executeQuery();
        if(result.next()){
            connection.close();
            return true;
        }else {
            connection.close();
            return false;
        }
    }


    public int getCoeff() {
        return this.plugin.getCoeff();
    }


    //Create new Player in Table
    public void setNewPlayer(Player player) throws SQLException {
        Connection connection = this.pool.getConnection();
        PreparedStatement stat = connection.prepareStatement("INSERT INTO "+ this.pool.getTablename() +" (`name`, `lvlfarm`, `lvlmineur`, `lvlchasseur`, `expfarm`, `expmineur`, `expchasseur`, `needexpfarm`, `needexpmineur`, `needexpchasseur`) VALUES (?, '0', '0', '0', '0', '0', '0', '10', '10', '10')");
        stat.setString(1, player.getName());
        stat.executeUpdate();
        connection.close();
    }

    //Get exp of the player for the job selected
    public int getExp(Player player,String expmetier) throws SQLException {
        Connection connection = this.pool.getConnection();
        int exp= -1;
        PreparedStatement stat = connection.prepareStatement("SELECT "+expmetier+" FROM " + this.pool.getTablename() + " WHERE name = ?");
        stat.setString(1, player.getName());
        ResultSet result = stat.executeQuery();
        while (result.next())
            exp = result.getInt(expmetier);
        connection.close();
        return exp;
    }
    //Get lvl of the player for the job selected
    public int getLevel(Player player,String lvlmetier)  throws SQLException {
        Connection connection = this.pool.getConnection();
        int exp= -1;
        PreparedStatement stat = connection.prepareStatement("SELECT "+lvlmetier+" FROM " + this.pool.getTablename() + " WHERE name = ?");
        stat.setString(1, player.getName());
        ResultSet result = stat.executeQuery();
        while (result.next())
            exp = result.getInt(lvlmetier);
        connection.close();
        return exp;

    }
    //Set exp of the player for the job selected
    public void setExp(Player player,String expmetier,int exp) throws SQLException {
        Connection connection = this.pool.getConnection();
        PreparedStatement stat = connection.prepareStatement("UPDATE " + this.pool.getTablename()+ " SET "+expmetier+ "= ? WHERE name = ?");
        stat.setString(2, player.getName());
        stat.setInt(1, exp);
        stat.executeUpdate();
        connection.close();
    }
    //Set lvl of the player for the job selected
    public void setLevel(Player player, String lvlmetier,int lvl) throws SQLException {
        Connection connection = this.pool.getConnection();
        PreparedStatement stat = connection.prepareStatement("UPDATE " + this.pool.getTablename() + " SET "+ lvlmetier + "= ? WHERE name = ?");
        stat.setString(2, player.getName());
        stat.setInt(1,lvl);
        stat.executeUpdate();
        connection.close();
    }


    //Get exp that player need for level up, for the job selected
    public int getExpNeed(Player player,String expneedmetier)  throws SQLException {
        Connection connection = this.pool.getConnection();
        int exp= -1;
        PreparedStatement stat = connection.prepareStatement("SELECT "+expneedmetier+" FROM " + this.pool.getTablename() + " WHERE name = ?");
        stat.setString(1, player.getName());
        ResultSet result = stat.executeQuery();
        while (result.next())
            exp = result.getInt(expneedmetier);
        connection.close();
        return exp;
    }
    //Set exp that player need for level up, for the job selected
    public void setExpNeed(Player player,String expneedmetier, int expneed) throws SQLException {
        Connection connection = this.pool.getConnection();
        PreparedStatement stat = connection.prepareStatement("UPDATE " + this.pool.getTablename() + " SET "+ expneedmetier + "= ? WHERE name = ?");
        stat.setString(2, player.getName());
        stat.setInt(1,expneed);
        stat.executeUpdate();
        connection.close();

    }



}