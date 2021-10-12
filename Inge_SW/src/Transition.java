import java.io.Serializable;
import java.util.ArrayList;


public class Transition extends Father implements Serializable {

    public Transition() {

    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }


    /**
     * @param net          rete alla quale aggiungere la transizione che stiamo creando
     * @param placeCreated lista di posti collegati alla transizione
     */
    public void createPlacesForTrans(Net net, ArrayList<Place> placeCreated) {

        ArrayList<String> connections = new ArrayList<>();

        // questo ciclo continua a chiedere se vuole creare altri posti alla transizione scelta
        while (Utility.continueWriting(Utility.CONTINUE_PLACE + this.getName() + "? " + Utility.CHOICE)) {

            String next = Utility.readString(Utility.USER_NEXT_PLACE + this.getName());
            Place p = new Place();
            p.setName(next);
            Couple c = new Couple(); //Creo la coppia posto-transizione da inserire nel flusso della rete
            c.setFirst(this);
            c.setSecond(p);

            if (Utility.nameNotUsedStringList(connections, next)) {
                net.getFlux().add(c);
                connections.add(next);
                placeCreated.add(p);
            } else System.out.println(Utility.ERROR_NAME);
        }

        net.getAllFather().add(this);

    }

}
