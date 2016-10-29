package tikape.runko.database;

import java.sql.Connection;
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
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Keskustelu;

public class KeskusteluDao {

    private String tietokantaosoite;

    public KeskusteluDao(String tietokantaosoite) {
        this.tietokantaosoite = tietokantaosoite;
    }

    public void luoKeskustelu(String keskustelu, int aiheId) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Keskustelu(aihe, keskustelu) "
                + "VALUES (" + aiheId + ", '" + keskustelu + "')");

        conn.close();

    }

    public List<Keskustelu> haeAiheenKt(int alueId) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Keskustelu WHERE aihe = " + alueId);

        List<Keskustelu> keskustelu = new ArrayList<>();

        while (rs.next()) {
            int aihe = rs.getInt("aihe");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            String keskustelut = rs.getString("keskustelu");
            int viesteja = rs.getInt("yhteensa");
            Timestamp uusin = rs.getTimestamp("uusin");

            Keskustelu k = new Keskustelu(keskustelutunnus, keskustelut, aihe, viesteja, uusin);
            keskustelu.add(k);
        }

        conn.close();

        return keskustelu;
    }

    public List<Keskustelu> findAll() throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Keskustelu");

        List<Keskustelu> keskustelu = new ArrayList<>();

        while (rs.next()) {
            int aihe = rs.getInt("aihe");
            Integer keskustelutunnus = rs.getInt("keskustelutunnus");
            String keskustelut = rs.getString("keskustelu");
            int viesteja = rs.getInt("yhteensa");
            Timestamp uusin = rs.getTimestamp("uusin");

            Keskustelu k = new Keskustelu(aihe, keskustelut, keskustelutunnus, viesteja, uusin);
            keskustelu.add(k);

        }
        rs.close();
        stmt.close();
        conn.close();

        return keskustelu;
    }

    public List<Keskustelu> keskustelunViestit(int aiheId) throws Exception {

        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT k.keskustelutunnus, k.aihe, k.keskustelu, COUNT(v.viesti) AS Viesteja_yhteensa, v.pvm_ja_aika AS Viimeisin_viesti\n"
                + "FROM Keskustelu k\n"
                + "LEFT JOIN Viesti v ON k.keskustelutunnus = v.keskustelutunnus \n"
                + "WHERE k.aihe="+aiheId+" \n"
                + "GROUP BY k.keskustelu\n"
                + "ORDER BY Viimeisin_viesti DESC;");

        List<Keskustelu> keskustelu = new ArrayList<>();

        while (result.next()) {

            int keskustelutunnus = result.getInt("keskustelutunnus");
            String keskustelut = result.getString("keskustelu");
            int aihe = result.getInt("aihe");
            int viesteja = result.getInt("Viesteja_yhteensa");
            Timestamp uusin = result.getTimestamp("Viimeisin_viesti");

            Keskustelu k = new Keskustelu(keskustelutunnus, keskustelut, aihe, viesteja, uusin);
            k.setYhteensa(viesteja);
            keskustelu.add(k);
        }

        conn.close();
        return keskustelu;
    }

    public int findOne(String keskustelu) throws Exception {
        int k = 0;

        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT keskustelutunnus FROM Keskustelu WHERE keskustelu = '" + keskustelu + "'");

        while (result.next()) {
            k = result.getInt("keskustelutunnus");
        }

        conn.close();

        return k;
    }

    public void poista(String id) throws Exception {
        Connection conn = DriverManager.getConnection(tietokantaosoite);
        Statement stmt = conn.createStatement();
        try {
            Integer.parseInt(id);
        } catch (Throwable t) {
            return;
        }
        // 1%20OR%201=1
        // 1 OR 1=1
        // DELETE FROM Todo WHERE id = 1 OR 1=1
        stmt.execute("DELETE FROM Keskustelu WHERE keskustelutunnus = " + id);
        conn.close();
    }

}
