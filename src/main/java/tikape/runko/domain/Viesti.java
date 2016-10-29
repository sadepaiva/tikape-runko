
package tikape.runko.domain;

import java.sql.Date;
import java.sql.Timestamp;


public class Viesti {

    private Integer viestitunnus;
    private Integer keskustelutunnus;
    private String viesti;
    private String nimimerkki;
    private Date pvm;
    private Timestamp pvm_ja_aika;

    public Viesti(Integer viestitunnus, Integer keskustelutunnus, String viesti, String nimimerkki, Date pvm, Timestamp pvm_ja_aika) {
        this.viestitunnus = viestitunnus;
        this.keskustelutunnus = keskustelutunnus;
        this.viesti = viesti;
        this.nimimerkki = nimimerkki;
        this.pvm = pvm;
        this.pvm_ja_aika = pvm_ja_aika;

    }

    public Integer getViestitunnus() {
        return viestitunnus;
    }

    public void setViestitunnus(Integer viestitunnus) {
        this.viestitunnus = viestitunnus;
    }

    public Integer getKeskustelutunnus() {
        return keskustelutunnus;
    }

    public void setKeskustelutunnus(Integer keskustelutunnus) {
        this.keskustelutunnus = keskustelutunnus;
    }

    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }
    
    public String getNimimerkki() {
        return nimimerkki;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }

    public Date getPvm() {
        return pvm;
    }

    public void setPvm(Date pvm) {
        this.pvm = pvm;
    }

    public Timestamp getPvm_ja_aika() {
        return pvm_ja_aika;
    }

    public void setPvm_ja_aika(Timestamp pvm_ja_aika) {
        this.pvm_ja_aika = pvm_ja_aika;
    }

    public void setKeskustelutunnus(Keskustelu tunnus) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
