package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
       
        
//        lista.add("CREATE TABLE Keskustelu (alue varchar(50), keskustelu varchar(50),keskustelutunnus integer PRIMARY KEY,FOREIGN KEY (alue) REFERENCES Aihe(aihe));");
//        lista.add("INSERT INTO Keskustelu VALUES ('Ohjelmointi','Java',1);");
//        lista.add("CREATE TABLE Viesti (viestitunnus integer PRIMARY KEY, keskustelu varchar(50), keskustelutunnus integer,viestinro integer, viesti varchar(2500) NOT NULL, nimimerkki varchar(50) NOT NULL,pvm date, pvm_ja_aika timestamp,FOREIGN KEY (keskustelutunnus) REFERENCES Keskustelu(keskustelutunnus));");
//      

        return lista;
    }
}
