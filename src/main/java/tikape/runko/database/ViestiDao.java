package tikape.runko.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tikape.runko.domain.Keskustelu;
import tikape.runko.domain.Viesti;

public class ViestiDao {

    private String tietokantaosoite;

    public ViestiDao(String tietokantaosoite) {
        this.tietokantaosoite = tietokantaosoite;
    }

    public void luoViesti(String viesti, int keskusteluId, String nimi) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Viesti(keskustelutunnus, viesti, nimimerkki) "
                + "VALUES (" + keskusteluId + ", '" + viesti+ "', '" +nimi+"')");

        conn.close();

    }

    public List<Viesti> haeKeskustelunVt(int keskId) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Viesti WHERE keskustelutunnus = " + keskId);

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
        }

        conn.close();

        return viestit;
    }
    
     public List<Viesti> findAll() throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Viesti");

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
 
        }
        rs.close();
        stmt.close();
        conn.close();

        return viestit;
    }
     
      public String findOne(int tunnus) throws Exception {
        String v = null;

        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT viesti FROM Viesti WHERE viestitunnus = " + tunnus);

        while (result.next()) {
            v = result.getString("viesti");
        }

        conn.close();

        return v;
    }
      
      public List<Integer> laskeKeskustelunVt(int aiheId) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT k.keskustelu, COUNT(v.viesti) AS lkm FROM Viesti v,Keskustelu k WHERE k.keskustelutunnus=v.keskustelutunnus AND k.aihe = " + aiheId+" GROUP BY v.keskustelutunnus;");

        List<Integer> viestit = new ArrayList<>();

        while (rs.next()) {
            Integer lkm = rs.getInt("lkm");
            

            
            viestit.add(lkm);
        }

        conn.close();

        return viestit;
    }

//    @Override
//    public Viesti findOne(Integer key) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE viestitunnus = ?");
//        stmt.setObject(1, key);
//
//        ResultSet rs = stmt.executeQuery();
//        boolean hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
//        Integer viestitunnus = rs.getInt("viestitunnus");
//        Integer keskustelutunnus = rs.getInt("keskustelutunnus");
//        Integer viestinro = rs.getInt("viestinro");
//        String viesti = rs.getString("viesti");
//        String nimimerkki = rs.getString("nimimerkki");
//        Date pvm = rs.getDate("pvm");
//        Timestamp pvm_ja_aika = rs.getTimestamp("pvm_ja_aika");
//
//        Viesti v = new Viesti(viestitunnus, keskustelutunnus, viestinro, viesti, nimimerkki, pvm, pvm_ja_aika);
//
//        rs.close();
//
//        stmt.close();
//
//        connection.close();
//
//        return v;
//    }
//
//    @Override
//    public List<Viesti> findAll() throws SQLException {
//
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti");
//        ResultSet rs = stmt.executeQuery();
//
//        Map<Integer, List<Viesti>> KeskustelujenViestit = new HashMap<>();
//
//        List<Viesti> viestit = new ArrayList<>();
//        while (rs.next()) {
//            Integer viestitunnus = rs.getInt("viestitunnus");
//            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
//            Integer viestinro = rs.getInt("viestinro");
//            String viesti = rs.getString("viesti");
//            String nimimerkki = rs.getString("nimimerkki");
//            Date pvm = rs.getDate("pvm");
//            Timestamp pvm_ja_aika = rs.getTimestamp("pvm_ja_aika");
//
//            Viesti v = new Viesti(viestitunnus, keskustelutunnus, viestinro, viesti, nimimerkki, pvm, pvm_ja_aika);
//            viestit.add(v);
//
//            Integer tunnus = rs.getInt("keskustelutunnus");
//
//            if (!KeskustelujenViestit.containsKey(tunnus)) {
//                KeskustelujenViestit.put(tunnus, new ArrayList<>());
//            }
//            KeskustelujenViestit.get(tunnus).add(v);
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//
//        for (Keskustelu tunnus : this.keskusteluDao.findAllIn(KeskustelujenViestit.keySet())) {
//            for (Viesti viesti: KeskustelujenViestit.get(tunnus.getKeskustelutunnus())) {
//                viesti.setKeskustelutunnus(tunnus);
//            }
//        }
//
//        return viestit;
//    }
//    @Override
//    public void delete(Integer key) throws SQLException {
//        // ei toteutettu
//    }
}
