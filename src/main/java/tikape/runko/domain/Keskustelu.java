package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Keskustelu {
    
    private Integer keskustelutunnus;
    private Integer aihe;
    private String keskustelu;
    private Integer yhteensa;
    private Timestamp uusin;
    

    public Keskustelu(int keskustelutunnus, String keskustelu ,int aihe, int yhteensa, Timestamp uusin) {
        this.aihe = aihe;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
        this.yhteensa=yhteensa;
        this.uusin=uusin;

        
    }

    public int getAihe() {
        return aihe;
    }

    public void setAihe(int alue) {
        this.aihe = aihe;
    }

    public String getKeskustelu() {
        return keskustelu;
    }

    public void setKeskustelu(String keskustelu) {
        this.keskustelu = keskustelu;
    }

    public Integer getKeskustelutunnus() {
        return keskustelutunnus;
    }

    public void setKeskustelutunnus(Integer keskustelutunnus) {
        this.keskustelutunnus = keskustelutunnus;
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

    
}
