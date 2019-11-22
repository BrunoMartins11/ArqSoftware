package Data;

import BusinessModel.Report.Bug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static Data.Connect.connect;

public class BugDAO implements DAO<Bug> {
    Connection conn;

    @Override
    public Bug get(int id) {
        return null;
    }

    @Override
    public List<Bug> getAll() {
        List<Bug> bugs = new ArrayList<>();
        try {
            conn = connect();
            if (conn != null) {
                PreparedStatement pStm = conn.prepareStatement("select * from Bug");
                ResultSet rs = pStm.executeQuery();
                while (rs.next()) {
                    bugs.add(new Bug(rs.getInt("idBug"), rs.getString("Error"), LocalDateTime.parse(rs.getString("Date")),
                            rs.getInt("User_idUser")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public void save(Bug bug) {
        try {
            conn = connect();
            if (conn != null) {
                PreparedStatement pStm = conn.prepareStatement("insert into Bug values(?,?,?,?)");
                pStm.setInt(1,bug.getId());
                pStm.setString(2, bug.getError());
                pStm.setString(3, bug.getDate().toString());
                pStm.setInt(4, bug.getIdClient());
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Bug bug) {

    }

    @Override
    public void delete(Bug bug) {

    }
}
