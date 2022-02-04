import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

@XmlAccessorType
public class Net implements Serializable {


    private String name;
    private ArrayList<Father> allFather = new ArrayList<>();
    private HashSet<Couple> flux = new HashSet<>();


    //@XmlElementWrapper(name = "Flusso")
    @XmlElement(name = "Flux")
    public HashSet<Couple> getFlux() {
        return flux;
    }

    public void setFlux(HashSet<Couple> flux) {
        this.flux = flux;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllFather(ArrayList<Father> allFather) {
        this.allFather = allFather;
    }

    @XmlElementWrapper(name = "Father")
    @XmlElements({
            @XmlElement(name = "Transition", type = Transition.class),
            @XmlElement(name = "Place", type = Place.class)
    })
    public ArrayList<Father> getAllFather() {
        return allFather;
    }

    public Net() {
    }

    public Net(String netName) {

        ArrayList<Place> placesToDo = new ArrayList<>();   //Array di supporto per la creazione della rete; mi ricordano quali elementi ho già creato e quali no
        ArrayList<Transition> transToDo = new ArrayList<>();

        this.setName(netName);
        int start = Utility.readInt01(Utility.WELCOME); //Il configuratore sceglie se iniziare da un posto o da una transizione
        if (start == 0) {
            Transition t1 = new Transition();
            t1.setName(Utility.readString(Utility.NAME_TRANSITION));
            t1.createPlacesForTrans(this, placesToDo);
        } else {
            Place p1 = new Place();
            p1.setName(Utility.readString(Utility.NAME_PLACE));
            p1.createTransForPlace(this, transToDo);
        }
        //Ciclo che si conclude quando il configuratore ha collegato tutti i possibili elementi di rete
        while (!placesToDo.isEmpty() || !transToDo.isEmpty()) {
            if (!placesToDo.isEmpty()) {
                for (Place place : placesToDo) {
                    if (!place.getChecked()) {
                        place.createTransForPlace(this, transToDo);
                    }
                }

                placesToDo.clear();
            } else {
                for (Transition transition : transToDo) {
                    if (!transition.getChecked()) {
                        transition.createPlacesForTrans(this, placesToDo);
                    }
                }
                transToDo.clear();
            }
        }
    }

    public void printFlux() {

        for (Couple c : this.getFlux()) {
            System.out.println(c.toString());
        }

    }

    public Net clone(Net cl) {
        Net cloned = new Net();

        for (Father f : cl.getAllFather()) {
            if (f.getClass().getName().equals("Transition")) {
                Transition clonedTrans = new Transition();
                clonedTrans.setName(f.getName());
                cloned.getAllFather().add(clonedTrans);
            } else {
                Place clonedPlace = new Place();
                clonedPlace.setName(f.getName());
                cloned.getAllFather().add(clonedPlace);
            }
        }

        for (Couple c : cl.getFlux()) {
            Couple clonedCouple = new Couple();
            for (Father f : cloned.getAllFather()) {
                if (f.getName().equals(c.getFirst().getName())) {
                    clonedCouple.setFirst(f);
                }
            }
            for (Father f : cloned.getAllFather()) {
                if (f.getName().equals(c.getSecond().getName())) {
                    clonedCouple.setSecond(f);
                }
            }
            cloned.getFlux().add(clonedCouple);
        }

        return cloned;
    }

    public void printnet() {
        ArrayList<String> print = new ArrayList<>();
        String s = "Nome della rete: " + this.getName() + "\nLista di elementi:\n";
        print.add(s);
        for (Father f : this.getAllFather()) {
            print.add((f.getClass().getName() + " " + f.getName()) + "  ");
        }
        String t = "\nFlusso della rete:\n";
        print.add(t);
        for (String st : print) {
            System.out.print(st);
        }
        printFlux();
    }
}