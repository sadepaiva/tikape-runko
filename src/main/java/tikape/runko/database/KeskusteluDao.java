/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Keskustelu;

public class KeskusteluDao implements Dao<Keskustelu, Integer> {

    private Database database;

    public KeskusteluDao(Database database) {
        this.database = database;
    }

    @Override
    public Keskustelu findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu WHERE keskustelutunnus = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String aihe = rs.getString("aihe");
        Integer keskustelutunnus = rs.getInt("keskustelutunnus");
        String keskustelu = rs.getString("keskustelu");

        Keskustelu k = new Keskustelu(aihe,keskustelutunnus, keskustelu );

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    @Override
    public List<Keskustelu> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu");

        ResultSet rs = stmt.executeQuery();
        List<Keskustelu> keskustelu = new ArrayList<>();
        while (rs.next()) {
            String aihe = rs.getString("aihe");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            String keskustelut = rs.getString("keskustelu");

            keskustelu.add(new Keskustelu(aihe, keskustelutunnus, keskustelut));
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskustelu;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
