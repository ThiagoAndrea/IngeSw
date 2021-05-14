import java.util.ArrayList;

public abstract class Father {

    private String name;
    private ArrayList<String> connections = new ArrayList<>();


    public Father(String name, ArrayList<String> connections) {
        this.name = name;
        this.connections = connections;
    }

    public Father() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnections(ArrayList<String> connection) {
        this.connections = connection;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }


}
