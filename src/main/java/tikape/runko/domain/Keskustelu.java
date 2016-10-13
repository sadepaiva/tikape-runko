package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Keskustelu {

    private String alue;
    private Integer keskustelutunnus;
    private String keskustelu;
    private List<Viesti> viestit;
    

    public Keskustelu(String alue, String keskustelu, int keskustelutunnus) {
        this.alue = alue;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
        this.viestit= new ArrayList<>();
    }

    public String getAlue() {
        return alue;
    }

    public void setAlue(String alue) {
        this.alue = alue;
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

    public void setAihe(Aihe aih) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
