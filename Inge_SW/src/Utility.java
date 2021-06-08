import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {

    private static final Scanner scanner = createScanner();

    private static final String ERROR_EMPTY = "Attenzione, stringa vuota";
    private static final String ERROR_FORMAT = "Attenzione, errore formato. Operazione non eseguita \n";
    public static final String NAME_PLACE = "Inserire il nome del posto:\n";
    public static final String NAME_TRANSITION = "Inserire il nome della transizione:\n";
    public static final String USER_NEXT_TRANSITION = "Inserire il nome della transizione collegata a ";
    public static final String USER_NEXT_PLACE = "Inserire il nome del posto collegato a ";
    public static final String ERROR_NAME = "ERRORE: nome già utilizzato. Operazione non eseguita.";
    public static final String ERROR_NUMBER = "ERRORE: numero inserito non coretto. Operazione non eseguita.\n";
    public static final String CONTINUE_TRANSITION = "Vuoi inserire una transizione collegata a ";
    public static final String CONTINUE_PLACE = "Vuoi inserire un posto collegato a ";
    public static final String CHOICE = "Premere:\n1 -> sì\n0 -> no";
    public static final String WELCOME = "Benvenuto nella creazione di una rete, premere:\n0 -> per iniziare da una transizione\n1 -> per iniziare da un posto\n";
    public static final String NET_NAME = "Inserisci un nome da assegnare alla tua rete:\n";


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

    public static int readInt01(String message){
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

    public static boolean nameUsedStringList(ArrayList<String> list, String name) {
        boolean ok = false;
        for (String s : list) {
            if (s.equals(name)) {
                ok = true;
                break;
            }
        }
        return ok;
    }

    public static boolean nameUsedFatherList(ArrayList<Father> list, String name) {
        boolean ok = false;
        for (Father f : list) {
            if (f.getName().equals(name)) {
                ok = true;
                break;
            }
        }
        return ok;
    }


}
