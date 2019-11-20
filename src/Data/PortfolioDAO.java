package Data;

import BusinessModel.Trading.Portfolio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    List<Integer> idCFDs = new ArrayList<>();
                    PreparedStatement pStm2 = con.prepareStatement("select * from Portfolio_has_Asset where Portfolio_idPortfolio=?");
                    pStm2.setInt(1, id);
                    ResultSet rs2 = pStm2.executeQuery();
                    while(rs2.next()){
                        idCFDs.add(rs2.getInt("Asset_idAsset"));
                    }
                    return new Portfolio(rs.getInt("idPortfolio"), idCFDs);
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
        List<Portfolio> port = new ArrayList<>();
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Portfolio");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    List<Integer> idCFDs = new ArrayList<>();
                    PreparedStatement pStm2 = con.prepareStatement("select * from Portfolio_has_Asset where Portfolio_idPortfolio=?");
                    pStm2.setInt(1, rs.getInt("idPortfolio"));
                    ResultSet rs2 = pStm2.executeQuery();
                    while(rs2.next()){
                        idCFDs.add(rs2.getInt("Asset_idAsset"));
                    }
                    port.add(new Portfolio(rs.getInt("idPortfolio"), idCFDs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return port;
    }

    @Override
    public void save(Portfolio portfolio) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into Portfolio values (?)");
                pStm.setInt(1, portfolio.getId());
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void update(Portfolio portfolio) {

    }

    @Override
    public void delete(Portfolio portfolio) {

    }
}
