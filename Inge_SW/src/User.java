import java.util.ArrayList;

public class User {


    public User() {

    }

    public Petri userChoosePetri(Global global) {
        global.printAllPetriNets();
        return global.pickPetriNet(global.getPetriNetsSaved());
    }

    public Priority userChoosePriority(Global global) {
        global.printAllPriorityNets();
        return global.pickPriorityNet(global.getPriorityNetsSaved());
    }

    public int simulationPetri(Petri petriNet) {
        ArrayList<Transition> enabled = petriNet.transitionsEnabled();
        int simulation;
        int size = enabled.size();
        switch (size) {
            case 0 -> {
                System.out.println(Utility.ZERO_ENABLED);
                return 0;
            }
            case 1 -> {
                if (Utility.readInt01(enabled.get(0).getName() + " " + Utility.ONE_ENABLED) == 1) {
                    petriNet.nextStep(enabled.get(0));
                    petriNet.printPetriNet();
                }
                simulation = Utility.readInt01(Utility.CONTINUE_SIMULATION);
                return simulation;
            }
            default -> {
                System.out.println(Utility.MORE_ENABLED);
                int i = 0;
                for (Transition t : enabled) {
                    System.out.println(i++ + " -> " + t.getName());
                }
                int chosen = Utility.readPositiveIntWithMax(Utility.CHOOSE_ENABLED, enabled.size() - 1);
                petriNet.nextStep(enabled.get(chosen));
                petriNet.printPetriNet();
                simulation = Utility.readInt01(Utility.CONTINUE_SIMULATION);
                return simulation;
            }
        }

    }

    public int simulationPriority(Priority priority) {
        ArrayList<Transition> enabled = priority.transitionsEnabled();
        int simulation;
        int size = enabled.size();
        switch (size) {
            case 0 -> {
                System.out.println(Utility.ZERO_ENABLED);
                return 0;
            }
            case 1 -> {
                if (Utility.readInt01(enabled.get(0).getName() + " " + Utility.ONE_ENABLED) == 1) {
                    priority.nextStep(enabled.get(0));
                    priority.printPriorityNet();
                }
                simulation = Utility.readInt01(Utility.CONTINUE_SIMULATION);
                return simulation;
            }
            default -> {
                System.out.println(Utility.MORE_ENABLED_WITH_PRIORITY);
                ArrayList<Transition> enabledPriority = priority.transitionsEnabled();
                int i = 0;
                for (Transition t : priority.transWithPriority()) {
                    System.out.println(i++ + " -> " + t.getName());
                }
                int chosen = Utility.readPositiveIntWithMax(Utility.CHOOSE_ENABLED, enabled.size() - 1);
                priority.nextStep(enabled.get(chosen));
                priority.printPriorityNet();
                simulation = Utility.readInt01(Utility.CONTINUE_SIMULATION);
                return simulation;
            }
        }

    }

}
