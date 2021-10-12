import java.io.Serializable;

public abstract class Father implements Serializable {

    private String name;

    public Father() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
