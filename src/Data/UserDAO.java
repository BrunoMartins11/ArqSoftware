package Data;

import BusinessModel.Trading.Portfolio;
import BusinessModel.User.Investor;
import BusinessModel.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static Data.Connect.connect;

public class UserDAO implements DAO<User> {

    @Override
    public User get(int id) {
        try {
            Connection con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from User where idUser=?");
                pStm.setInt(1, (Integer) id);
                ResultSet rs = pStm.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("isAdmin") == 0) {
                        return new Investor(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                                rs.getString("Password"), (Portfolio) (new PortfolioDAO()).get(rs.getInt("idPortfolio"))
                                , rs.getDouble("Credit"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Investor();
    }

    @Override
    public List<User> getAll() {
        return null;
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
