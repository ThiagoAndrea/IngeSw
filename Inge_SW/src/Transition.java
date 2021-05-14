import java.util.ArrayList;
import java.util.HashMap;

public class Transition extends Father {

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
     *
     * @param net rete alla quale aggiungere la transizione che stiamo creando
     * @param placeCreated lista di posti collegati alla transizione
     */
    public void createPlacesForTrans(Net net, ArrayList<String> placeCreated) {

        // questo ciclo continua a chiedere se vuole creare altri posti alla transizione scelta
        while (Utility.continueWriting(Utility.CONTINUE_PLACE)) {
            String next = Utility.readString(Utility.USER_NEXT_TRANSITION + this.getName());
            if (!Utility.nameUsedStringList(this.getConnections(), next)) {
                this.getConnections().add(next);
                placeCreated.add(next);
            }
            else System.out.println(Utility.ERROR_NAME);
        }

        net.getAllFather().add(this);

    }

}
