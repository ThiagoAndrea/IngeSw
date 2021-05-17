import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Net net = new Net();
        ArrayList<String> placesToDo = new ArrayList<>();
        ArrayList<String> transToDo = new ArrayList<>();

        //Creazione della net

        int start = Utility.readInt01(Utility.WELCOME);

        if (start == 0) {
            Transition t1 = new Transition();
            t1.setName(Utility.readString(Utility.NAME_TRANSITION));
            t1.createPlacesForTrans(net, placesToDo);
        } else {
            Place p1 = new Place();
            p1.setName(Utility.readString(Utility.NAME_PLACE));
            p1.createTransForPlace(net, transToDo);
        }

        while (!placesToDo.isEmpty() || !transToDo.isEmpty()) {
            if (!placesToDo.isEmpty()) {
                for (String place : placesToDo) {
                    if (!Utility.nameUsedFatherList(net.getAllFather(), place)) {
                        Place p = new Place();
                        p.setName(place);
                        p.createTransForPlace(net, transToDo);
                    }
                }
                placesToDo.clear();
            } else {
                for (String transition : transToDo) {
                    if (!Utility.nameUsedFatherList(net.getAllFather(), transition)) {
                        Transition t = new Transition();
                        t.setName(transition);
                        t.createPlacesForTrans(net, placesToDo);
                    }
                }
                transToDo.clear();
            }
        }
    net.printNet();

    }
}
