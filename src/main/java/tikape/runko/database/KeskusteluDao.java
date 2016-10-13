
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Keskustelu;

public class KeskusteluDao implements Dao<Keskustelu, Integer> {

    private Database database;
    private Dao<Aihe, String> AiheDao;

    public KeskusteluDao(Database database, Dao<Aihe, String> aiheDao) {
        this.database = database;
        this.AiheDao = aiheDao;
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

        Keskustelu k = new Keskustelu(aihe, keskustelutunnus, keskustelu);

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

        Map<String, List<Keskustelu>> keskustelujenAiheet = new HashMap<>();

        List<Keskustelu> keskustelu = new ArrayList<>();

        while (rs.next()) {

            String aihe = rs.getString("aihe");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            String keskustelut = rs.getString("keskustelu");

            Keskustelu k = new Keskustelu(aihe, keskustelutunnus, keskustelut);
            keskustelu.add(k);

            String alue = rs.getString("alue");

            if (!keskustelujenAiheet.containsKey(alue)) {
                keskustelujenAiheet.put(alue, new ArrayList<>());
            }
            keskustelujenAiheet.get(alue).add(k);
        }

        rs.close();
        stmt.close();
        connection.close();

        for (Aihe alue: this.AiheDao.findAllIn(keskustelujenAiheet.keySet())) {           
            for (Keskustelu keskusteluu : keskustelujenAiheet.get(alue.getAihe())) {
                keskusteluu.setAihe(alue);
            }
        }
        
        return keskustelu;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    @Override
    public List<Keskustelu> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        
        List<Keskustelu> keskustelu = new ArrayList<>();

        while (rs.next()) {

            String aihe = rs.getString("aihe");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            String keskustelut = rs.getString("keskustelu");

            Keskustelu k = new Keskustelu(aihe, keskustelutunnus, keskustelut);
            keskustelu.add(k);
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskustelu;
    }


}
