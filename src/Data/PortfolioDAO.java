package Data;

import BusinessModel.Trading.Portfolio;
import BusinessModel.User.Investor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static Data.Connect.connect;

public class PortfolioDAO implements DAO<Portfolio> {
    Connection con;

    @Override
    public Portfolio get(int id) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Portfolio where idPortfolio=?");
                pStm.setInt(1, id);
                ResultSet rs = pStm.executeQuery();
                if (rs.next()) {
                    return new Portfolio();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return new Portfolio();
    }

    @Override
    public List<Portfolio> getAll() {
        return null;
    }

    @Override
    public void save(Portfolio portfolio) {

    }

    @Override
    public void update(Portfolio portfolio) {

    }

    @Override
    public void delete(Portfolio portfolio) {

    }
}
