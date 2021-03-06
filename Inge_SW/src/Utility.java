import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {

    private static final Scanner scanner = createScanner();

    private static final String ERROR_EMPTY = "Attenzione, stringa vuota";
    private static final String ERROR_FORMAT = "Attenzione, errore formato. Operazione non eseguita \n";
    public static final String NAME_PLACE = "Inserire il nome del posto:\n";
    public static final String NAME_TRANSITION = "Inserire il nome della transizione:\n";
    public static final String NEW_NAME = "Inserire un altro nome:\n";
    public static final String USER_NEXT_TRANSITION = "Inserire i nomi delle transizioni collegate a ";
    public static final String USER_NEXT_PLACE = "Inserire i nomi dei posti collegati a ";
    public static final String ERROR_NAME = "ERRORE: nome già utilizzato. Operazione non eseguita.";
    public static final String ERROR_NAME2 = "ERRORE: nome già utilizzato. \nInserisci un nuovo nome: ";
    public static final String ERROR_NUMBER = "ERRORE: numero inserito non coretto. Operazione non eseguita.\n";
    public static final String CONTINUE_TRANSITION = "Vuoi inserire delle transizioni collegati a ";
    public static final String CONTINUE_PLACE = "Vuoi inserire dei posti collegati a ";
    public static final String CONFIRM = " sono i nomi inseriti. Confermi la scelta?\n";
    public static final String CHOICE = "Premere:\n1 -> sì\n0 -> no";
    public static final String SAVING = "Premere 1 se vuoi salvare la rete appena creata in modo permanente, altrimenti premere 0.";
    public static final String NOTSAVED = "La rete creata non è stata salvata come da te richiesto.";
    public static final String WELCOME = "Benvenuto nella creazione di una rete, premere:\n0 -> per iniziare da una transizione\n1 -> per iniziare da un posto\n";
    public static final String NET_NAME = "Inserisci un nome da assegnare alla tua rete:\n";
    public static final String PETRI_NET_NAME = "Inserisci un nome da assegnare alla tua rete di Petri:\n";
    public static final String PRIORITY_NET_NAME = "Inserisci un nome da assegnare alla tua rete di Petri con priorità:\n";
    public static final String BACK = "\nInserire 0 per terminare.";
    public static final String MENU = "\n\nPremere:\n1 -> per visualizzare le reti salvate\n2 -> per creare una nuova rete\n3 -> per trasformare una rete salvata in una rete di Petri\n4 -> per trasformare una rete di Petri in una rete di Petri con priorità\n5 -> per importare una rete da file esterno seguendo il manuale d'istruzioni allegato\n\n0 -> per terminare il programma";
    public static final String INSERT_WEIGHT = "Inserire il valore del nuovo peso: ";
    public static final String INSERT_TOKEN = "Inserire il valore del token: ";
    public static final String INSERT_PRIORITY = "Inserire il valore dela priorità: ";
    public static final String CHOOSE_COUPLE = "Digitare il numero della coppia della quale vuoi modificare il peso (premere 0 se hai finito le modifiche): ";
    public static final String CHOOSE_NET = "Inserire il numero della rete da modificare:\n";
    public static final String CHOOSE_PLACE = "Inserire il numero del posto di cui vuoi modificare il token (premere 0 se hai finito le modifiche):\n";
    public static final String CHOOSE_TRANSITION= "Inserire il numero della transizione di cui vuoi modificare la priorità (premere 0 se hai finito le modifiche):\n";
    public static final String CONFIGURATOR_OR_USER = "Premere:\n0 -> se sei il configuratore\n1 -> se sei il fruitore\n";
    public static final String ZERO_ENABLED = "Non ci sono transizioni abilitate, la simulazione è interrotta.";
    public static final String ONE_ENABLED = " è l'unica transizione abilitata. Vuoi procedere?\n1 -> sì\n0 -> no\n";
    public static final String MORE_ENABLED = "Queste sono le transizioni abilitate:";
    public static final String MORE_ENABLED_WITH_PRIORITY = "Ci sono più transizioni abilitate, ma considerando la priorità, quelle abilitate sono: ";
    public static final String CHOOSE_ENABLED = "Digitare il numero corrispondente alla transizione che si vuole abilitare:\n";
    public static final String CONTINUE_SIMULATION = "Vuoi procedere con la simulazione? Premere:\n1 -> si\n0 -> no";
    public static final String PETRI_OR_PRIORITY = "Premere:\n1 -> Se vuoi simulare una rete di Petri\n0 -> Se vuoi simulare una rete di Petri con priorità";
    public static final String GET_NET_FROM_FILE = "Premere:\n0 -> Se vuoi importare una rete\n1 -> Se vuoi importare una rete di Petri\n2 -> Se vuoi importare una rete di Petri con priorità";
    public static final String INSERT_FILE = "Inserisci il nome del file, ricordati di inserire '.xml''";
    public static final String WRONG_NET_TOPOLOGY = "La topologia di rete non è già presente in global";
    public static final String WRONG_PETRI_TOPOLOGY = "La topologia di rete di petri non è già presente in global";
    public static final String NET_ALREADY_USED = "La rete è già presente.";
    public static final String ERROR_NAME_FILE = "Il nome del file non è stato inserito correttamente";


    private static Scanner createScanner() {
        Scanner created = new Scanner(System.in);
        created.useDelimiter(System.getProperty("line.separator"));
        return created;
    }

    public static String readString(String message) {
        boolean end = false;
        String string = null;
        do {
            System.out.println(message);
            string = scanner.nextLine();
            string = string.trim();
            if (string.length() > 0)
                end = true;
            else
                System.out.println(ERROR_EMPTY);
        } while (!end);

        return string;
    }

    public static int readInt01(String message) {
        boolean end = false;
        int value = 0;
        Scanner read = new Scanner(System.in);
        do {
            System.out.println(message);
            try {
                value = read.nextInt();
                if (value == 0 || value == 1)
                    end = true;
                else
                    System.out.println(ERROR_NUMBER);
            } catch (InputMismatchException e) {
                System.out.println(ERROR_FORMAT);
                String trash = read.next();
            }
        } while ((!end));
        return value;

    }

    public static int readPositiveIntNot0(String message) {
        boolean end = false;
        int value = 0;
        Scanner read = new Scanner(System.in);
        do {
            System.out.println(message);
            try {
                value = read.nextInt();
                if (value > 0)
                    end = true;
                else
                    System.out.println(ERROR_NUMBER);
            } catch (InputMismatchException e) {
                System.out.println(ERROR_FORMAT);
                String trash = read.next();
            }
        } while ((!end));
        return value;

    }

    public static int readPositiveIntWithMax(String message, int max) {
        boolean end = false;
        int value = 0;
        Scanner read = new Scanner(System.in);
        do {
            System.out.println(message);
            try {
                value = read.nextInt();
                if (value >= 0 && value <= max)
                    end = true;
                else
                    System.out.println(ERROR_NUMBER);
            } catch (InputMismatchException e) {
                System.out.println(ERROR_FORMAT);
                String trash = read.next();
            }
        } while ((!end));
        return value;

    }

    public static int readPositiveIntnot0WithMax(String message, int max) {
        boolean end = false;
        int value = 0;
        Scanner read = new Scanner(System.in);
        do {
            System.out.println(message);
            try {
                value = read.nextInt();
                if (value > 0 && value <= max)
                    end = true;
                else
                    System.out.println(ERROR_NUMBER);
            } catch (InputMismatchException e) {
                System.out.println(ERROR_FORMAT);
                String trash = read.next();
            }
        } while ((!end));
        return value;

    }

    public static ArrayList<String> readNames(String message) {
        ArrayList<String> list = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        System.out.println(message);
        String line = read.nextLine();
        Scanner read2 = new Scanner(line);
        while (read2.hasNext()) {
            String a = read2.next();
            list.add(a);
        }
        return list;
    }

    public static int readInt(String message) {
        boolean end = false;
        int value = 0;
        Scanner read = new Scanner(System.in);

        do {
            System.out.println(message);
            try {
                value = read.nextInt();
                end = true;
            } catch (InputMismatchException e) {
                System.out.println(ERROR_FORMAT);
                String trash = read.next();
            }
        } while ((!end));
        return value;

    }

    public static boolean continueWriting(String message) {
        boolean stop;
        stop = readInt01(message) != 0;
        return stop;

    }

    public static boolean nameNotUsedStringList(ArrayList<String> list, String name) {
        boolean ok = false;
        for (String s : list) {
            if (s.equals(name)) {
                ok = true;
                break;
            }
        }
        return !ok;
    }

    public static boolean nameNotUsedFatherList(ArrayList<Father> list, String name) {
        boolean ok = false;
        for (Father f : list) {
            if (f.getName().equals(name)) {
                ok = true;
                break;
            }
        }
        return !ok;
    }

    public static Father pickFather(ArrayList<Father> array, String name) {

        for (Father f : array) {
            if (f.getName().equals(name))
                return f;
        }
        return null;
    }

    public static ArrayList<Place> getPlacesFromFathers(ArrayList<Father> fathers) {
        ArrayList<Place> places = new ArrayList<>();
        for (Father f : fathers) {
            if (f.getClass().getName().equals("Place")) {
                places.add((Place) f);
            }
        }
        return places;
    }

    public static ArrayList<Transition> getTransitionsFromFathers(ArrayList<Father> fathers) {
        ArrayList<Transition> trans = new ArrayList<>();
        for (Father f : fathers) {
            if (f.getClass().getName().equals("Transition")) {
                trans.add((Transition) f);
            }
        }
        return trans;
    }

}
