import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
public class Petri extends Net implements Serializable {


    public Petri() {
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
            number = Utility.readPositiveIntWithMax(Utility.CHOOSE_COUPLE, map.size());
            if (number != 0) {
                newWeight = Utility.readPositiveIntNot0(Utility.INSERT_WEIGHT);
                map.get(number).setWeight(newWeight);
            }
        }
        // Inserimento dei token

        ArrayList<Place> places = Utility.getPlacesFromFathers(this.getAllFather());
        int j, number2 = 1, newToken;
        while (number2 != 0) {
            j = 1;
            for (Place p : places) {
                System.out.println(j++ + ": " + p.getName() + " -> " + p.getToken());
            }
            number2 = (Utility.readPositiveIntWithMax(Utility.CHOOSE_PLACE, places.size()));
            if (number2 != 0) {
                newToken = Utility.readPositiveIntNot0(Utility.INSERT_TOKEN);
                places.get(number2 - 1).setToken(newToken);
            }

        }
    }

    public void printFluxPetri() {
        for (Couple c : this.getFlux()) {
            System.out.println(c.toString() + ", peso: " + c.getWeight());
        }
    }

    public void printPetriNet() {

        ArrayList<String> print = new ArrayList<>();
        String s = "Nome della rete di Petri: " + this.getName() + "\nLista di elementi:\n";
        print.add(s);
        for (Father f : this.getAllFather()) {
            print.add((f.getClass().getName() + " " + f.getName()) + "  ");
        }
        String pl = "\nNumero di token per ogni posto:\n";
        print.add(pl);
        for (Place p : Utility.getPlacesFromFathers(this.getAllFather())) {
            print.add(p.getName() + " -> " + p.getToken() + "\n");
        }
        String t = "\nFlusso della rete:\n";
        print.add(t);
        for (String st : print) {
            System.out.print(st);
        }
        printFluxPetri();
    }

    public boolean isEnabled(Transition t1) {
        for (Couple c : this.getFlux()) {
            if (c.getSecond().getName().equals(t1.getName())) {
                Place first = (Place) c.getFirst();
                if (first.getToken() < c.getWeight())
                    return false;
            }
        }
        return true;
    }

    public ArrayList<Transition> transitionsEnabled() {
        ArrayList<Transition> transitions = new ArrayList<>();
        for (Transition t1 : Utility.getTransitionsFromFathers(this.getAllFather())) {
            if (isEnabled(t1))
                transitions.add(t1);
        }
        return transitions;
    }

    public void nextStep(Transition t1) {
        for (Couple c : this.getFlux()) {
            if (c.getFirst().getName().equals(t1.getName())) {
                c.getSecond().setToken(c.getSecond().getToken() + c.getWeight());
            }
            if (c.getSecond().getName().equals(t1.getName())) {
                c.getFirst().setToken(c.getFirst().getToken() - c.getWeight());
            }
        }
    }
}


