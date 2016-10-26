package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Aihe {

    private Integer aihe_id;
    private String aihe;
    private Integer yhteensa;
    private Timestamp uusin;
//    private List<Keskustelu> keskustelut;

    public Aihe(int aihe_id, String aihe, int yhteensa, Timestamp uusin) {
        this.aihe_id = aihe_id;
        this.aihe = aihe;
        this.yhteensa=yhteensa;
        this.uusin=uusin;

    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public int getAiheId() {
        return aihe_id;
    }

    public void setAiheId(int aiheId) {
        this.aihe_id = aiheId;
    }
    
    public int getYhteensa() {
        return yhteensa;
    }

    public void setYhteensa(int yhteensa) {
        this.yhteensa = yhteensa;
    }
    
    public Timestamp getUusin() {
        return this.uusin;
    }

    public void setPvm_ja_aika(Timestamp uusin) {
        this.uusin = uusin;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
