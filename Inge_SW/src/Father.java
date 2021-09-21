import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Father implements Serializable {

    @XmlAttribute
    private String name;

    public Father(String name, ArrayList<String> connections) {
        this.name = name;
    }

    public Father() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
