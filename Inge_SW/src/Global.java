import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Global implements Serializable {

    @XmlTransient
    private ArrayList<Net> netsSaved = new ArrayList<>();
    @XmlTransient
    private ArrayList<Petri> petriNetsSaved = new ArrayList<>();
    @XmlTransient
    private ArrayList<Priority> priorityNetsSaved = new ArrayList<>();

    public Global() {
    }

    @XmlElementWrapper(name = "Reti")
    @XmlElement(name = "Rete")
    public ArrayList<Net> getNetsSaved() {
        return netsSaved;
    }

    @XmlElementWrapper(name = "Reti_di_Petri")
    @XmlElement(name = "Rete_di_Petri")
    public ArrayList<Petri> getPetriNetsSaved() {
        return petriNetsSaved;
    }

    @XmlElementWrapper(name = "Reti_di_Petri_con_priorità")
    @XmlElement(name = "Rete_di_Petri_con_priorità")
    public ArrayList<Priority> getPriorityNetsSaved() {
        return priorityNetsSaved;
    }

    public void setPriorityNetsSaved(ArrayList<Priority> priorityNetsSaved) {
        this.priorityNetsSaved = priorityNetsSaved;
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
                if (Objects.equals(p1.getName(), p2.getName())) {
                    if (p1.getToken() != p2.getToken()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean sameTransitionPriority(ArrayList<Transition> array1, ArrayList<Transition> array2) {
        for (Transition t1 : array1) {
            for (Transition t2 : array2) {
                if (Objects.equals(t1.getName(), t2.getName())) {
                    if (t1.getPriority() != t2.getPriority()) {
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

    public boolean confirmPriorityNet(ArrayList<Priority> allPriority, Priority priority) {
        for (Priority p : allPriority) {
            if (sameFlux(p.getFlux(), priority.getFlux()) &&
                    samePlacesToken(Utility.getPlacesFromFathers(p.getAllFather()), Utility.getPlacesFromFathers(priority.getAllFather())) &&
                            sameTransitionPriority(Utility.getTransitionsFromFathers(p.getAllFather()), Utility.getTransitionsFromFathers(priority.getAllFather())))
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

    public void printAllPriorityNets() {
        int a = 1;
        for (Priority p : this.priorityNetsSaved) {
            System.out.println("<->-<->-<->-<->-<->");
            System.out.println(a++);
            p.printPriorityNet();
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
        } while (number <= 0 || number > list.size());

        number--;

        return list.get(number);
    }

    public Priority pickPriorityNet(ArrayList<Priority> list) {
        int number;
        do {
            number = Utility.readInt(Utility.CHOOSE_NET);
        } while (number <= 0 || number > list.size());

        number--;

        return list.get(number);
    }

    public void sameObject(){

        for (Net n : this.getNetsSaved()){
            for (Couple c : n.getFlux()){
                for (Father f : n.getAllFather()){
                    if(f.getName().equals(c.getFirst().getName())){
                        c.setFirst(f);
                    }
                }
                for (Father f : n.getAllFather()){
                    if(f.getName().equals(c.getSecond().getName())){
                        c.setSecond(f);
                    }
                }
            }
        }
        for(Petri p : this.getPetriNetsSaved()){
            for (Couple c : p.getFlux()){
                for (Father f : p.getAllFather()){
                    if(f.getName().equals(c.getFirst().getName())){
                        c.setFirst(f);
                    }
                }
                for (Father f : p.getAllFather()){
                    if(f.getName().equals(c.getSecond().getName())){
                        c.setSecond(f);
                    }
                }
            }
        }
        for(Priority pr : this.getPriorityNetsSaved()){
            for (Couple c : pr.getFlux()){
                for (Father f : pr.getAllFather()){
                    if(f.getName().equals(c.getFirst().getName())){
                        c.setFirst(f);
                    }
                }
                for (Father f : pr.getAllFather()){
                    if(f.getName().equals(c.getSecond().getName())){
                        c.setSecond(f);
                    }
                }
            }
        }

        }

}
