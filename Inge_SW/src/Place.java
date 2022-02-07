import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place extends Father implements Serializable {

    @XmlTransient
    private String name;
    @XmlTransient
    private Boolean checked = false;
    @XmlTransient
    private int token;

    @Override
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @XmlTransient
    @Override
    public Boolean getChecked() {
        return checked;
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

    @XmlAttribute(name = "Token", required = true)
    @Override
    public int getToken() {
        return token;
    }

    @Override
    public void setToken(int token) {
        this.token = token;
    }

    public Place() {
    }

    /**
     * @param net
     * @param transCreated
     */
    public void createTransForPlace(Net net, ArrayList<Transition> transCreated) {

        /*Creo un array di stringhe di nomi di transizioni che utilizzo come supporto per verificare che il nome della transizione
         inserita dal configuratore non sia già stata utilizzata */
        ArrayList<String> connections = new ArrayList<>();
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
                if (Utility.nameNotUsedFatherList(net.getAllFather(), s)) {
                    Transition trans = new Transition();
                    trans.setName(s);
                    net.getAllFather().add(trans);
                    Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
                    c.setFirst(this);
                    c.setSecond(trans);
                    net.getFlux().add(c);
                    connections.add(s);
                    transCreated.add(trans);
                } else {
                    Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
                    c.setFirst(this);
                    c.setSecond(Utility.pickFather(net.getAllFather(), s));
                    net.getFlux().add(c);
                    connections.add(s);
                }
            }
        }
        if (Utility.nameNotUsedFatherList(net.getAllFather(), this.getName()))
            net.getAllFather().add(this);

        this.setChecked(true);
    }

}
