import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;


@XmlRootElement(name = "Rete")
@XmlAccessorType(XmlAccessType.FIELD)
public class Net implements Serializable {

    @XmlAttribute
    private String name;
    //da aggiungere codice??

    @XmlElementWrapper(name = "Lista")
    @XmlElement(name = "Postotransizione")
    private ArrayList<Father> allFather = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Father> getAllFather() {
        return allFather;
    }

    public Net() {

        ArrayList<String> placesToDo = new ArrayList<>();
        ArrayList<String> transToDo = new ArrayList<>();

        this.setName(Utility.readString(Utility.NET_NAME));
        int start = Utility.readInt01(Utility.WELCOME);
        if (start == 0) {
            Transition t1 = new Transition();
            t1.setName(Utility.readString(Utility.NAME_TRANSITION));
            t1.createPlacesForTrans(this, placesToDo);
        } else {
            Place p1 = new Place();
            p1.setName(Utility.readString(Utility.NAME_PLACE));
            p1.createTransForPlace(this, transToDo);
        }

        while (!placesToDo.isEmpty() || !transToDo.isEmpty()) {
            if (!placesToDo.isEmpty()) {
                for (String place : placesToDo) {
                    if (!Utility.nameUsedFatherList(this.getAllFather(), place)) {
                        Place p = new Place();
                        p.setName(place);
                        p.createTransForPlace(this, transToDo);
                    }
                }
                placesToDo.clear();
            } else {
                for (String transition : transToDo) {
                    if (!Utility.nameUsedFatherList(this.getAllFather(), transition)) {
                        Transition t = new Transition();
                        t.setName(transition);
                        t.createPlacesForTrans(this, placesToDo);
                    }
                }
                transToDo.clear();
            }
        }

    }


    public void printNet() {
        System.out.println("La rete creata è la seguente: ");
        for (Father f : this.allFather) {
            System.out.print(f.getName() + " --> ");
            for (String conn : f.getConnections()) {
                System.out.print(conn + " ");
            }
            System.out.print("\n");
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
