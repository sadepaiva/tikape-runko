package tikape.runko.domain;

public class Keskustelu {

    private String aihe;
<<<<<<< HEAD
    private Integer keskustelutunnus;
    private String keskustelu;
    

    public Keskustelu(String aihe,int keskustelutunnus, String keskustelu) {
=======
    private String keskustelu;
    private Integer keskustelutunnus;

    public Keskustelu(String aihe, String keskustelu, int keskustelutunnus) {
>>>>>>> ad0c5d18b1ee29c1d206803f961bef1eb0f2e120
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
