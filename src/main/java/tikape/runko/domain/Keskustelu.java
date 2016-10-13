package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Keskustelu {

    private String aihe;
    private Integer keskustelutunnus;
    private String keskustelu;
    private List<Viesti> viestit;
    

    public Keskustelu(String aihe,int keskustelutunnus, String keskustelu) {
        this.aihe = aihe;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
        this.viestit= new ArrayList<>();
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
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

    public void setAihe(Aihe alue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
