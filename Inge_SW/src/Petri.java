import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Petri extends Net {


    public Petri() {
        //Chiedi all'utente quali pesi inserire nel flusso
        //
    }

    public Petri(Net fatherNet) {
        Net n = clone(fatherNet);
        this.setFlux(n.getFlux());
        this.setAllFather(n.getAllFather());
        this.setName(Utility.readString(Utility.PETRI_NET_NAME));

        HashMap<Integer, Couple> map = new HashMap<>();
        int i = 1;
        for (Couple c : this.getFlux()) {
            map.put(i, c);
            i++;
        }
        int number = 1;
        int newWeight;
        while (number != 0) {
            for (Map.Entry<Integer, Couple> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "-->  " + entry.getValue().toString() + " peso: " + entry.getValue().getWeight());
            }
            number = Utility.readInt(Utility.CHOOSE_COUPLE);
            if (number != 0) {
                newWeight = Utility.readPositiveIntNot0(Utility.INSERT_WEIGHT);
                map.get(number).setWeight(newWeight);
            }
        }

    }


}

