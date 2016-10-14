
package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Aihe {
    private Integer aihe_id;
    private String aihe;
//    private List<Keskustelu> keskustelut;

    public Aihe(int aihe_id, String aihe) {
        this.aihe_id = aihe_id;
        this.aihe = aihe;
        
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

    @Override
    public String toString() {
        return super.toString(); 
    }
    
     
    
}
