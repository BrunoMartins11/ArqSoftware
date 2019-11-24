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
                    List<Integer> watchList = new ArrayList<>();
                    PreparedStatement pStm2 = con.prepareStatement("select * from Portfolio_has_Asset where Portfolio_idPortfolio=?");
                    pStm2.setInt(1, id);
                    ResultSet rs2 = pStm2.executeQuery();
                    while(rs2.next()){
                        watchList.add(rs2.getInt("Asset_idAsset"));
                    }
                    List<Integer> cfds = new ArrayList<>();
                    PreparedStatement pStm3 = con.prepareStatement("select * from CFD where Portfolio_idPortfolio=?");
                    pStm3.setInt(1, id);
                    ResultSet rs3 = pStm3.executeQuery();
                    while(rs3.next()){
                        cfds.add(rs3.getInt("idCFD"));
                    }
                    return new Portfolio(rs.getInt("idPortfolio"), cfds,watchList);
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
                    List<Integer> watchList = new ArrayList<>();
                    PreparedStatement pStm2 = con.prepareStatement("select * from Portfolio_has_Asset where Portfolio_idPortfolio=?");
                    pStm2.setInt(1, rs.getInt("idPortfolio"));
                    ResultSet rs2 = pStm2.executeQuery();
                    while(rs2.next()){
                        watchList.add(rs2.getInt("Asset_idAsset"));
                    }
                    List<Integer> cfds = new ArrayList<>();
                    PreparedStatement pStm3 = con.prepareStatement("select * from CFD where Portfolio_idPortfolio=?");
                    pStm3.setInt(1, rs.getInt("idPortfolio"));
                    ResultSet rs3 = pStm3.executeQuery();
                    while(rs3.next()){
                        cfds.add(rs3.getInt("idCFD"));
                    }
                    port.add(new Portfolio(rs.getInt("idPortfolio"), cfds,watchList));
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

    public void saveToPortfolioWL(int idPortfolio, int idAsset){
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into Portfolio_has_Asset values (?,?)");
                pStm.setInt(1, idPortfolio);
                pStm.setInt(2, idAsset);
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    public void deletePortfolioWLItem(int idPortfolio, int idAsset){
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("delete from Portfolio_has_Asset where Portfolio_idPortfolio=? " +
                        "and Asset_idAsset=?");
                pStm.setInt(1, idPortfolio);
                pStm.setInt(2, idAsset);
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }
}
