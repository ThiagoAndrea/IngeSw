import java.io.Serializable;
import java.util.ArrayList;


public class Place extends Father implements Serializable {


    //da controllare se eredita o no
    //da aggiungere nel caso anche il codice
    public Place(String name, ArrayList<String> connection) {
        super(name, connection);
    }

    public Place() {
        super();
    }


    public void createTransForPlace(Net net, ArrayList<Transition> transCreated) {

        /*Creo un array di stringhe di nomi di transizioni che utilizzo come supporto per verificare che il nome della transizione
         inserita dal configuratore non sia già stata utilizzata */
        ArrayList<String> connections = new ArrayList<>();
        // questo ciclo continua a chiedere se vuole creare altre tranzioni al posto scelto
        while (Utility.continueWriting(Utility.CONTINUE_TRANSITION + this.getName() + "? " + Utility.CHOICE)) {
            String next = Utility.readString(Utility.USER_NEXT_TRANSITION + this.getName());
            Transition trans = new Transition();
            trans.setName(next);
            Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
            c.setFirst(this);
            c.setSecond(trans);
            if (Utility.nameNotUsedStringList(connections, next)) {
                net.getFlux().add(c);
                connections.add(next);
                transCreated.add(trans);
            } else System.out.println(Utility.ERROR_NAME);
        }
        net.getAllFather().add(this);
    }


}
