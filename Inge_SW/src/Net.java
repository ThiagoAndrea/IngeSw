import java.util.ArrayList;

public class Net {

    private String name;
    private ArrayList<Father> allFather = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Father> getAllFather() {
        return allFather;
    }

    public Net() {
    }


    public void printNet() {
        System.out.println("La rete creata è la seguente: ");
        for (Father f : this.allFather) {
            System.out.print(f.getName() + ":");
            for (String conn : f.getConnections()) {
                System.out.print(conn + " ");
            }
            System.out.print("\n");
        }

    }
}
