package Data;

import BusinessModel.Trading.Portfolio;
import BusinessModel.User.Admin;
import BusinessModel.User.Investor;
import BusinessModel.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Data.Connect.connect;

public class UserDAO implements DAO<User> {
    private Connection con;

    @Override
    public User get(int id) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from User where idUser=?");
                pStm.setInt(1, id);
                ResultSet rs = pStm.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("isAdmin") == 0) {
                        return new Investor(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                                rs.getString("Password"), (new PortfolioDAO()).get(rs.getInt("Portfolio_idPortfolio"))
                                , rs.getDouble("Credit"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return new Investor();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from User");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    if (rs.getInt("isAdmin") == 0) {
                        users.add(new Investor(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                                rs.getString("Password"), (new PortfolioDAO()).get(rs.getInt("Portfolio_idPortfolio"))
                                , rs.getDouble("Credit")));
                    } else users.add(new Admin(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                            rs.getString("Password"), rs.getString("Password")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return users;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
