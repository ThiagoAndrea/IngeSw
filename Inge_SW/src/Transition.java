import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@XmlAccessorType(XmlAccessType.FIELD)
public class Transition extends Father implements Serializable {

    //nel caso aggiungere nome e codice come precedenti
    public Transition(String name, ArrayList<String> connection) {
        super(name, connection);
    }

    public Transition() {

    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setConnections(ArrayList<String> connection) {
        super.setConnections(connection);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public ArrayList<String> getConnections() {
        return super.getConnections();
    }

    /**
     * @param net          rete alla quale aggiungere la transizione che stiamo creando
     * @param placeCreated lista di posti collegati alla transizione
     */
    public void createPlacesForTrans(Net net, ArrayList<String> placeCreated) {

        // questo ciclo continua a chiedere se vuole creare altri posti alla transizione scelta
        while (Utility.continueWriting(Utility.CONTINUE_PLACE + this.getName() + "? " + Utility.CHOICE)) {
            String next = Utility.readString(Utility.USER_NEXT_PLACE + this.getName());
            if (!Utility.nameUsedStringList(this.getConnections(), next)) {
                this.getConnections().add(next);
                placeCreated.add(next);
            } else System.out.println(Utility.ERROR_NAME);
        }

        net.getAllFather().add(this);

    }

}
