import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place extends Father implements Serializable {

    @XmlTransient
    private String name;

    @XmlTransient
    private int weight;

    public Place() {
    }

    @XmlAttribute(name = "Name", required = true)
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "Weight", required = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @param net
     * @param transCreated
     */
    public void createTransForPlace(Net net, ArrayList<Transition> transCreated) {

        /*Creo un array di stringhe di nomi di transizioni che utilizzo come supporto per verificare che il nome della transizione
         inserita dal configuratore non sia già stata utilizzata */
        ArrayList<String> connections = new ArrayList<>();
        // questo ciclo continua a chiedere se vuole creare altre transizioni al posto scelto
        if (Utility.continueWriting(Utility.CONTINUE_TRANSITION + this.getName() + "? " + Utility.CHOICE)) {
            ArrayList<String> connections2 = new ArrayList<>();
            do {
                connections2 = Utility.readNames(Utility.USER_NEXT_TRANSITION + this.getName());
                for (String s : connections2) {
                    System.out.print("[");
                    System.out.print(s);
                    System.out.print("]");
                }
                System.out.print(Utility.CONFIRM);
            } while (Utility.readInt01(Utility.CHOICE) != 1);
            for (String s : connections2) {
                // Se il nome è già utilizzato, cambio il nome subito
                while (!Utility.nameNotUsedStringList(connections, s)) {
                    s = Utility.readString(Utility.ERROR_NAME2);
                }
                Transition trans = new Transition();
                trans.setName(s);
                Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
                c.setFirst(this);
                c.setSecond(trans);
                net.getFlux().add(c);
                connections.add(s);
                transCreated.add(trans);
            }

        }

        net.getAllFather().add(this);
    }

}
