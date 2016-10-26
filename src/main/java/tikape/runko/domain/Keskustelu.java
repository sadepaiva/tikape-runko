package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Keskustelu {
    
    private Integer keskustelutunnus;
    private Integer aihe;
    private String keskustelu;
    private List<Viesti> viestit;
    

    public Keskustelu(int keskustelutunnus, String keskustelu,int aihe ) {
        this.aihe = aihe;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
        
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

    
}
