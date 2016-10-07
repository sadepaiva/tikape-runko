/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author iidapiip
 */
public class Viesti {

    private String aihe;
    private String keskustelu;
    private Integer keskustelutunnus;
    private Integer viestinro;
    private String viesti;
    private Date pvm;
    private Timestamp pvm_ja_aika;

    public Viesti(String aihe, String keskustelu, Integer keskustelutunnus, Integer viestinro, String viesti, Date pvm, Timestamp pvm_ja_aika) {
        this.aihe = aihe;
        this.keskustelu = keskustelu;
        this.keskustelutunnus = keskustelutunnus;
        this.viestinro = viestinro;
        this.viesti = viesti;
        this.pvm = pvm;
        this.pvm_ja_aika = pvm_ja_aika;

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

    public Integer getViestinro() {
        return viestinro;
    }

    public void setViestinro(Integer viestinro) {
        this.viestinro = viestinro;
    }

    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
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
}
