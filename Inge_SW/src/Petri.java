import java.util.HashMap;
import java.util.Map;

public class Petri extends Net {


    public Petri() {
        //Chiedi all'utente quali pesi inserire nel flusso
        //
    }

    public Petri(Net fatherNet) {
        HashMap<Integer, Couple> map = new HashMap<>();
        int i = 1;
        for (Couple c : fatherNet.getFlux()) {
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
            newWeight = Utility.readPositiveIntNot0(Utility.INSERT_WEIGHT);
            map.get(number).setWeight(newWeight);
        }

    }

}

