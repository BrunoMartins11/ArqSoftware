package Data;

import BusinessModel.Trading.CFD;
import BusinessModel.Trading.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Data.Connect.connect;

public class CFDdao implements DAO<CFD> {
    Connection con;

    @Override
    public CFD get(int id) {
        return null;
    }

    @Override
    public List<CFD> getAll() {
        List<CFD> cfds = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm1 = con.prepareStatement("select * from CFD");
                ResultSet rs1 = pStm1.executeQuery();

                while(rs1.next()) {
                    if(rs1.getString("Position").equals("short")) {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date")),
                                Position.SHORT, rs1.getInt("Asset_idAsset")));
                    } else {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date")),
                                Position.LONG, rs1.getInt("Asset_idAsset")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return cfds;
    }

    @Override
    public void save(CFD cfd) {

    }

    public List<CFD> getAllByPortfolio(int idPortfolio) {
        List<CFD> cfds = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm1 = con.prepareStatement("select * from CFD where Portfolio_idPortfolio=?");
                pStm1.setInt(1, idPortfolio);
                ResultSet rs1 = pStm1.executeQuery();

                while(rs1.next()) {
                    if(rs1.getString("Position").equals("short")) {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date"), formatter),
                                Position.SHORT, rs1.getInt("Asset_idAsset")));
                    } else {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date"), formatter),
                                Position.LONG, rs1.getInt("Asset_idAsset")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return cfds;
    }

    public List<CFD> getAllWithTPSL(){
        List<CFD> cfds = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm1 = con.prepareStatement("select * from CFD where StopLoss>0 and TakeProfit>0 ");
                ResultSet rs1 = pStm1.executeQuery();

                while(rs1.next()) {
                    if(rs1.getString("Position").equals("short")) {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date"), formatter),
                                Position.SHORT, rs1.getInt("Asset_idAsset")));
                    } else {
                        cfds.add(new CFD(rs1.getInt("idCFD"),
                                rs1.getDouble("TakeProfit"), rs1.getDouble("StopLoss"),
                                rs1.getDouble("AquisitionPrice"), rs1.getDouble("Quantity"),
                                LocalDateTime.parse(rs1.getString("Date"), formatter),
                                Position.LONG, rs1.getInt("Asset_idAsset")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return cfds;
    }

    public void saveToPortfolio(CFD cfd, int idPortfolio) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into CFD VALUES (?,?,?,?,?,?,?,?,?)");
                pStm.setInt(1,cfd.getId());
                pStm.setDouble(2, cfd.getPriceAcquisition());

                if(cfd.getPosition() == Position.SHORT) {
                    pStm.setString(3, "short");
                }
                if(cfd.getPosition() == Position.LONG) {
                    pStm.setString(3, "long");
                }
                pStm.setDouble(4, cfd.getTP());
                pStm.setDouble(5, cfd.getSL());
                pStm.setString(6, cfd.getDate().toString());
                pStm.setDouble(7, cfd.getQuantity());
                pStm.setInt(8, idPortfolio);
                pStm.setInt(9, cfd.getAssetID());

                pStm.execute();

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }
    @Override
    public void update(CFD cfd) {

    }

    @Override
    public void delete(CFD cfd) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("delete from CFD where idCFD=?");
                pStm.setInt(1,cfd.getId());
                pStm.execute();
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }
}
