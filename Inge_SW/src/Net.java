import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

@XmlRootElement(name = "Net")
public class Net implements Serializable {

    private String name;

    private ArrayList<Father> allFather = new ArrayList<>();

    private HashSet<Couple> flux = new HashSet<>();


    @XmlElementWrapper(name = "Flusso", required = true)
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

    @XmlElementWrapper(name = "Padri", required = true)
    @XmlElement(name = "Padre", required = true)
    public ArrayList<Father> getAllFather() {
        return allFather;
    }

    public void Net() {
    }

    //ATTENZIONE HO COMMENTATO PER POTER USARE IL COSTRUTTORE NET COMPLETAMENTE VUOTO

    public Net() {

        ArrayList<Place> placesToDo = new ArrayList<>();   //Array di supporto per la creazione della rete; mi ricordano quali elementi ho già creato e quali no
        ArrayList<Transition> transToDo = new ArrayList<>();

        this.setName(Utility.readString(Utility.NET_NAME));
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
                    if (Utility.nameNotUsedFatherList(this.getAllFather(), place.getName())) {
                        place.createTransForPlace(this, transToDo);
                    }
                }
                placesToDo.clear();
            } else {
                for (Transition transition : transToDo) {
                    if (Utility.nameNotUsedFatherList(this.getAllFather(), transition.getName())) {
                        transition.createPlacesForTrans(this, placesToDo);
                    }
                }
                transToDo.clear();
            }
        }
    }


    public void printFlux() {

        System.out.println("Il flusso della rete creata è il seguente: ");
        for (Couple c : this.getFlux()) {
            System.out.println(c.toString());
        }

    }

    @Override
    public String toString() {
        return "Net{" +
                "name='" + name + '\'' +
                ", allFather=" + allFather +
                '}';
    }
}