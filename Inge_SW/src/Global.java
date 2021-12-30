import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Global {

    @XmlTransient
    private ArrayList<Net> netSaved = new ArrayList<>();

    @XmlElementWrapper(name = "Reti")
    @XmlElement(name = "Rete")
    public ArrayList<Net> getNetSaved() {
        return netSaved;
    }

    public void setNetSaved(ArrayList<Net> netSaved) {
        this.netSaved = netSaved;
    }

    /**
     * Controllo se la coppia "a" è contenuta nel set "coupleSet"
     *
     * @param coupleSet Insieme di partenza
     * @param a Coppia da verificare se presente
     * @return vero se la coppia è presente nel set, falso altrimenti
     */
    private  boolean containCouple(HashSet<Couple> coupleSet, Couple a) {
        boolean found = false;
        for(Couple c : coupleSet){
            if (sameCouple(c, a)){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Confronto se due coppie sono uguali sia nel nome che nella classe
     * @param a prima coppia
     * @param b seconda coppia
     * @return vero se le due coppie sono uguali, falso altrimenti
     */
    private  boolean sameCouple(Couple a, Couple b) {
        return a.getFirst().getName().equals(b.getFirst().getName()) &&
                a.getSecond().getName().equals(b.getSecond().getName()) && a.getFirst().getClass().equals(b.getFirst().getClass()) &&
                a.getSecond().getClass().equals(b.getSecond().getClass());
    }

    public  boolean sameFlux(HashSet<Couple> flux1, HashSet<Couple> flux2) {
        for (Couple c1 : flux1) {
            if(!containCouple(flux2, c1))
                return false;
        }
        return true;
    }

    //tostring ad hoc per visualizzare tutte le reti
    @Override
    public String toString() {
        return "Global{" +
                "netSaved=" + netSaved +
                '}';
    }
}
