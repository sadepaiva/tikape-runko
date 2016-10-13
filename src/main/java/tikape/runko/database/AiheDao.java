
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tikape.runko.domain.Aihe;

public class AiheDao implements Dao<Aihe, String> {
    private Database database;

    public AiheDao(Database database) {
        this.database = database;
    }

    @Override
    public Aihe findOne(String key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE aihe = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        
        String aihe = rs.getString("aihe");

        Aihe a = new Aihe(aihe);

        rs.close();
        stmt.close();
        connection.close();

        return a;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheet = new ArrayList<>();
        while (rs.next()) {
            String aihe = rs.getString("aihe");

            aiheet.add(new Aihe(aihe));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aiheet;
    }

    @Override
    public void delete(String key) throws SQLException {
        // ei toteutettu
    }
    
    @Override
    public List<Aihe> findAllIn(Collection<String> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");
        int laskuri = 1;
        for (String key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheet = new ArrayList<>();
        while (rs.next()) {
            String aihe = rs.getString("aihe");

            aiheet.add(new Aihe(aihe));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aiheet;
    }

}

    

