
package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Aihe {
    
    private String aihe;
    private List<Keskustelu> keskustelut;

    public Aihe(String aihe) {
        this.aihe = aihe;
        this.keskustelut= new ArrayList<>();
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
     
    
}
