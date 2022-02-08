import java.io.Serializable;
import java.util.ArrayList;

public class Priority extends Petri implements Serializable {

    public Priority() {
    }

    public Priority(Petri fatherPetri) {
        Petri petri = clone(fatherPetri);
        this.setFlux(petri.getFlux());
        this.setAllFather(petri.getAllFather());
        this.setName(Utility.readString(Utility.PRIORITY_NET_NAME));

        ArrayList<Transition> transitions = Utility.getTransitionsFromFathers(this.getAllFather());
        int j, number2 = 1, newPriority;
        while (number2 != 0) {
            j = 1;
            for (Transition t : transitions) {
                System.out.println(j++ + ": " + t.getName() + " -> " + t.getPriority());
            }
            number2 = (Utility.readPositiveIntWithMax(Utility.CHOOSE_TRANSITION, transitions.size()));
            if (number2 != 0) {
                newPriority = Utility.readPositiveIntNot0(Utility.INSERT_PRIORITY);
                transitions.get(number2 - 1).setPriority(newPriority);
            }
        }
    }

    public void printPriorityNet() {

        ArrayList<String> print = new ArrayList<>();
        String s = "Nome della rete di Petri: " + this.getName() + "\n\nLista di elementi:\n";
        print.add(s);
        for (Father f : this.getAllFather()) {
            print.add((f.getClass().getName() + " " + f.getName()) + "  ");
        }
        String pl = "\n\nNumero di token per ogni posto:\n";
        print.add(pl);
        for (Place p : Utility.getPlacesFromFathers(this.getAllFather())) {
            print.add(p.getName() + " -> " + p.getToken() + "\n");
        }
        String pr = "\n\nPriorità di ogni transizione:\n";
        print.add(pr);
        for (Transition tr : Utility.getTransitionsFromFathers(this.getAllFather())) {
            print.add(tr.getName() + " -> " + tr.getPriority() + "\n");
        }
        String t = "\nFlusso della rete:\n";
        print.add(t);
        for (String st : print) {
            System.out.print(st);
        }
        printFluxPetri();
    }

    public ArrayList<Transition> transWithPriority() {

        ArrayList<Transition> bestPriority = new ArrayList<>();
        ArrayList<Transition> trans = Utility.getTransitionsFromFathers(this.getAllFather());
        int max = 1;

        for (Transition t : trans) {
            if (t.getPriority() >= max)
                max = t.getPriority();
        }

        for (Transition t : trans) {
            if (t.getPriority() == max)
                bestPriority.add(t);
        }

        return bestPriority;
    }


}
