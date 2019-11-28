package Data;

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
                        users.add(new Admin(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                                rs.getString("Password"), rs.getString("Password")));
                    } else users.add(new Investor(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                            rs.getString("Password"), (new PortfolioDAO()).get(rs.getInt("Portfolio_idPortfolio"))
                            , rs.getDouble("Credit")));
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
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into User(idUser, Username, Email, Password, isAdmin, Credit, Portfolio_idPortfolio) values (?,?,?,?,?,?,?) ");
                pStm.setInt(1, user.getId());
                pStm.setString(2, user.getUsername());
                pStm.setString(3, user.getEmail());
                pStm.setString(4, user.getPassword());
                if(user instanceof Investor){
                    Investor i = (Investor) user;
                    pStm.setInt(5,1);
                    pStm.setDouble(6, i.getCredit());
                    pStm.setInt(7, i.getPortfolioId());
                } else{
                    if(user instanceof Admin) {
                        Admin a = (Admin) user;
                        pStm.setInt(5, 0);
                        pStm.setDouble(6, 0);
                        pStm.setInt(7, 0);
                    }
                }
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void update(User user) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("update  User set Credit=? where idUser=?");
                if(user instanceof Investor) {
                    Investor i = (Investor) user;
                    pStm.setDouble(1, i.getCredit());
                    pStm.setInt(2, i.getId());
                    pStm.execute();
                }

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void delete(User user) {

    }
}
