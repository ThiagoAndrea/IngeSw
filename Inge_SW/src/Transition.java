import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Transition extends Father implements Serializable {

    @XmlTransient
    private String name;

    private Boolean checked = false;

    @XmlTransient
    private String time;

    public Transition() {
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

    @Override
    public Boolean getChecked() {
        return checked;
    }

    @Override
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @XmlAttribute(name = "Time", required = false)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @param net          rete alla quale aggiungere la transizione che stiamo creando
     * @param placeCreated lista di posti collegati alla transizione
     */
    public void createPlacesForTrans(Net net, ArrayList<Place> placeCreated) {

        ArrayList<String> connections = new ArrayList<>();

        // questo ciclo continua a chiedere se vuole creare altri posti alla transizione scelta
        if (Utility.continueWriting(Utility.CONTINUE_PLACE + this.getName() + "? " + Utility.CHOICE)) {
            ArrayList<String> connections2 = new ArrayList<>();
            do {
                connections2 = Utility.readNames(Utility.USER_NEXT_PLACE + this.getName());
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
                Place p = new Place();
                p.setName(s);
                Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
                c.setFirst(this);
                c.setSecond(p);
                net.getFlux().add(c);
                connections.add(s);
                placeCreated.add(p);
            }

            net.getAllFather().add(this);

        }
    }



}
