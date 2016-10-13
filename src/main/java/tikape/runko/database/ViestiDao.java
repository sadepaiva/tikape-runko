package tikape.runko.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tikape.runko.domain.Keskustelu;
import tikape.runko.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;
    private Dao<Keskustelu, Integer> keskusteluDao;

    public ViestiDao(Database database, Dao<Keskustelu, Integer> keskusteluDao) {
        this.database = database;
        this.keskusteluDao = keskusteluDao;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE viestitunnus = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer viestitunnus = rs.getInt("viestitunnus");
        Integer keskustelutunnus = rs.getInt("keskustelutunnus");
        Integer viestinro = rs.getInt("viestinro");
        String viesti = rs.getString("viesti");
        String nimimerkki = rs.getString("nimimerkki");
        Date pvm = rs.getDate("pvm");
        Timestamp pvm_ja_aika = rs.getTimestamp("pvm_ja_aika");

        Viesti v = new Viesti(viestitunnus, keskustelutunnus, viestinro, viesti, nimimerkki, pvm, pvm_ja_aika);

        rs.close();

        stmt.close();

        connection.close();

        return v;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti");
        ResultSet rs = stmt.executeQuery();

        Map<Integer, List<Viesti>> KeskustelujenViestit = new HashMap<>();

        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            Integer viestitunnus = rs.getInt("viestitunnus");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            Integer viestinro = rs.getInt("viestinro");
            String viesti = rs.getString("viesti");
            String nimimerkki = rs.getString("nimimerkki");
            Date pvm = rs.getDate("pvm");
            Timestamp pvm_ja_aika = rs.getTimestamp("pvm_ja_aika");

            Viesti v = new Viesti(viestitunnus, keskustelutunnus, viestinro, viesti, nimimerkki, pvm, pvm_ja_aika);
            viestit.add(v);

            Integer tunnus = rs.getInt("keskustelutunnus");

            if (!KeskustelujenViestit.containsKey(tunnus)) {
                KeskustelujenViestit.put(tunnus, new ArrayList<>());
            }
            KeskustelujenViestit.get(tunnus).add(v);
        }

        rs.close();
        stmt.close();
        connection.close();

        for (Keskustelu tunnus : this.keskusteluDao.findAllIn(KeskustelujenViestit.keySet())) {
            for (Viesti viesti: KeskustelujenViestit.get(tunnus.getKeskustelutunnus())) {
                viesti.setKeskustelutunnus(tunnus);
            }
        }

        return viestit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

    @Override
    public List<Viesti> findAllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
