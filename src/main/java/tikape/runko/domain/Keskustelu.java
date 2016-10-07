
package tikape.runko.domain;


public class Keskustelu {
    private String aihe;
    private String keskustelu;
    private Integer keskustelutunnus;

    public Keskustelu(String aihe, String keskustelu, int keskustelutunnus) {
        this.aihe = aihe;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
}
