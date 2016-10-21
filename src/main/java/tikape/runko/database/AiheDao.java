package tikape.runko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tikape.runko.domain.Aihe;


public class AiheDao {

    private String tietokantaosoite;

    public AiheDao(String tietokantaosoite) {
        this.tietokantaosoite = tietokantaosoite;
    }

    public void luoAihe(String aihe) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Aihe(aihe) "
                + "VALUES ('" + aihe + "')");

        conn.close();

    }

    public List<Aihe> findAll() throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Aihe");

        List<Aihe> aiheet = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt("aihe_id");
            String aihe = result.getString("aihe");

            Aihe aihet = new Aihe(id, aihe);
            aiheet.add(aihet);
        }

        conn.close();

        return aiheet;
    }

    public int findOne(String aihe) throws Exception {
        int a = 0;

        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT aihe_id FROM Aihe WHERE aihe = '" + aihe+"'");

        while (result.next()) {
            a = result.getInt("aihe_id");
        }

        conn.close();

        return a;
    }


}

////    @Override
//    public List<Aihe> findAll() throws SQLException {
//
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");
//
//        ResultSet rs = stmt.executeQuery();
//        List<Aihe> aiheet = new ArrayList<>();
//        while (rs.next()) {
//            String aihe = rs.getString("aihe");
//
//            aiheet.add(new Aihe(aihe));
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//
//        return aiheet;
//    }
//    @Override
//    public void delete(String key) throws SQLException {
//        // ei toteutettu
//    }
//    
//    @Override
//    public List<Aihe> findAllIn(Collection<String> keys) throws SQLException {
//        if (keys.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        StringBuilder muuttujat = new StringBuilder("?");
//        for (int i = 1; i < keys.size(); i++) {
//            muuttujat.append(", ?");
//        }
//
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");
//        int laskuri = 1;
//        for (String key : keys) {
//            stmt.setObject(laskuri, key);
//            laskuri++;
//        }
//
//        ResultSet rs = stmt.executeQuery();
//        List<Aihe> aiheet = new ArrayList<>();
//        while (rs.next()) {
//            String aihe = rs.getString("aihe");
//
//            aiheet.add(new Aihe(aihe));
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//
//        return aiheet;
//    }
//
//}
//
//    

