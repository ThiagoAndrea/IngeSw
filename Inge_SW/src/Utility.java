import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {

    private static final Scanner scanner = createScanner();

    private static final String ERROR_EMPTY = "Attenzione, stringa vuota";
    private static final String ERROR_FORMAT = "Attenzione, errore formato";
    public static final String NAME_PLACE = "Inserire il nome del posto:\n";
    public static final String NAME_TRANSITION = "Inserire il nome della transizione:\n";
    public static final String USER_NEXT_TRANSITION = "Inserire il nome della transizione collegata a ";
    public static final String ERROR_NAME = "ERRORE: nome già utilizzato. Operazione non eseguita.";
    public static final String CONTINUE_TRANSITION = "Vuoi inserire una transizione?\nPremere un numero per continuare, 0 per fermarsi\n";
    public static final String CONTINUE_PLACE = "Vuoi inserire un posto?\nPremere un numero per continuare, 0 per fermarsi\n";
    public static final String WELCOME = "Benvenuto nella creazione di una rete: digitare un numero per iniziare da un posto, 0 per iniziare da una transizione\n";


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
        stop = readInt(message) != 0;
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
