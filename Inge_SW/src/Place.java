import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place extends Father implements Serializable {


    //da controllare se eredita o no
    //da aggiungere nel caso anche il codice
    public Place(String name, ArrayList<String> connection) {
        super(name, connection);
    }

    public Place() {
        super();
    }


    public void createTransForPlace(Net net, ArrayList<String> transCreated) {

        // questo ciclo continua a chiedere se vuole creare altre tranzioni al posto scelto
        while (Utility.continueWriting(Utility.CONTINUE_TRANSITION + this.getName() + "? " + Utility.CHOICE)) {
            String next = Utility.readString(Utility.USER_NEXT_TRANSITION + this.getName());
            if (!Utility.nameUsedStringList(this.getConnections(), next)) {
                this.getConnections().add(next);
                transCreated.add(next);
            } else System.out.println(Utility.ERROR_NAME);
        }
        net.getAllFather().add(this);
    }


}
