import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

@XmlRootElement(name = "Global")
public class Global implements Serializable {

    private ArrayList<Net> netsSaved = new ArrayList<>();
    private ArrayList<Petri> petriNetsSaved = new ArrayList<>();

    public Global() {
    }

    @XmlElement(name = "Rete")
    public ArrayList<Net> getNetsSaved() {
        return netsSaved;
    }

    @XmlElement(name = "Rete_di_Petri")
    public ArrayList<Petri> getPetriNetsSaved() {
        return petriNetsSaved;
    }

    public void setPetriNetsSaved(ArrayList<Petri> petriNetsSaved) {
        petriNetsSaved = petriNetsSaved;
    }

    public void setNetsSaved(ArrayList<Net> netsSaved) {
        this.netsSaved = netsSaved;
    }

    /**
     * Controllo se la coppia "a" è contenuta nel set "coupleSet"
     *
     * @param coupleSet Insieme di partenza
     * @param a         Coppia da verificare se presente
     * @return vero se la coppia è presente nel set, falso altrimenti
     */
    private boolean containCouple(HashSet<Couple> coupleSet, Couple a) {
        boolean found = false;
        for (Couple c : coupleSet) {
            if (sameCouple(c, a)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Confronto se due coppie sono uguali sia nel nome che nella classe
     *
     * @param a prima coppia
     * @param b seconda coppia
     * @return vero se le due coppie sono uguali, falso altrimenti
     */
    private boolean sameCouple(Couple a, Couple b) {
        return a.getFirst().getName().equals(b.getFirst().getName()) &&
                a.getSecond().getName().equals(b.getSecond().getName()) && a.getFirst().getClass().equals(b.getFirst().getClass()) &&
                a.getSecond().getClass().equals(b.getSecond().getClass()) && (a.getWeight() == b.getWeight());
    }

    public boolean sameFlux(HashSet<Couple> flux1, HashSet<Couple> flux2) {
        for (Couple c1 : flux1) {
            if (!containCouple(flux2, c1))
                return false;
        }
        return true;
    }

    public boolean samePlacesToken(ArrayList<Place> array1, ArrayList<Place> array2) {
        for (Place p1 : array1) {
            for (Place p2 : array2) {
                if (p1.getName() == p2.getName()) {
                    if (p1.getToken() != p2.getToken()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean confirmNet(ArrayList<Net> allNets, Net net) {
        for (Net n : allNets) {
            if (sameFlux(n.getFlux(), net.getFlux()))
                return false;
        }
        return true;
    }

    public boolean confirmPetriNet(ArrayList<Petri> allPetri, Petri singlePetri) {
        for (Petri p : allPetri) {
            if (sameFlux(p.getFlux(), singlePetri.getFlux()) &&
                    samePlacesToken(Utility.getPlacesFromFathers(p.getAllFather()), Utility.getPlacesFromFathers(singlePetri.getAllFather())))
                return false;
        }
        return true;
    }

    public void printAllNets() {
        int a = 1;
        for (Net n : this.netsSaved) {
            System.out.println("<->-<->-<->-<->-<->");
            System.out.println(a++);
            n.printnet();
            System.out.println();
        }
    }

    public void printAllPetriNets() {
        int a = 1;
        for (Petri p : this.petriNetsSaved) {
            System.out.println("<->-<->-<->-<->-<->");
            System.out.println(a++);
            p.printPetriNet();
            System.out.println();
        }

    }

    public Net pickNet(ArrayList<Net> list) {
        int number;
        do {
            number = Utility.readInt(Utility.CHOOSE_NET);
        } while (number < 0 || number > list.size());

        number--;

        return list.get(number);
    }

    public Petri pickPetriNet(ArrayList<Petri> list) {
        int number;
        do {
            number = Utility.readInt(Utility.CHOOSE_NET);
        } while (number < 0 || number > list.size());

        number--;

        return list.get(number);
    }
}
