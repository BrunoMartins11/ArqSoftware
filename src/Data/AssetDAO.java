package Data;

import BusinessModel.Assets.Asset;
import BusinessModel.Assets.AssetType;
import BusinessModel.Trading.Portfolio;
import BusinessModel.User.Investor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetDAO implements DAO<Asset> {
    private Connection con;

    @Override
    public Asset get(int id) {
        return null;
    }

    @Override
    public List<Asset> getAll() {
        List<Asset> assets = new ArrayList<>();
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Asset");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    //TODO verificar que é assim que se vai buscar um enum ou mudar na BD o tipo
                    if(rs.getString("Type").equals("coin")){
                        assets.add(new Asset(rs.getInt("idAsset"), rs.getDouble("Value"),
                              rs.getString("Company"), AssetType.COIN));
                    } else{
                        if(rs.getString("Type").equals("stock")){
                          assets.add(new Asset(rs.getInt("idAsset"), rs.getDouble("Value"),
                                   rs.getString("Company"), AssetType.STOCK));
                        } else {
                            assets.add(new Asset(rs.getInt("idAsset"), rs.getDouble("Value"),
                                    rs.getString("Company"), AssetType.COMMODITY));
                        }
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return assets;
    }

    @Override
    public void save(Asset asset) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into Asset VALUES (?,?,?,?)");
                pStm.setInt(1,asset.getId());
                pStm.setString(2,asset.getCompany());

                if(asset.getType() == AssetType.COIN){
                    pStm.setString(3, "coin");
                }
                if(asset.getType() == AssetType.COMMODITY){
                    pStm.setString(3, "commodity");
                }
                if(asset.getType() == AssetType.STOCK){
                    pStm.setString(3, "stock");
                }
                pStm.setDouble(4, asset.getValue());

                pStm.execute();

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void update(Asset asset) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("update  Asset set Company=?, Type=?, Value=? " +
                                                                 "where idAsset=?");
                pStm.setString(1,asset.getCompany());
                if(asset.getType() == AssetType.COIN){
                    pStm.setString(2, "coin");
                }
                if(asset.getType() == AssetType.COMMODITY){
                    pStm.setString(2, "commodity");
                }
                if(asset.getType() == AssetType.STOCK){
                    pStm.setString(2, "stock");
                }
                pStm.setDouble(3, asset.getValue());
                pStm.setInt(4,asset.getId());

                pStm.execute();

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void delete(Asset asset) {

    }
}
