import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType
public class Couple {


    private Father first;
    private Father second;
    private int weight = 1;

    public Couple() {
    }

    @XmlElements({
            @XmlElement(name = "Transition", type = Transition.class),
            @XmlElement(name = "Place", type = Place.class)
    })
    public Father getFirst() {
        return first;
    }

    public void setFirst(Father first) {
        this.first = first;
    }

    @XmlElements({
            @XmlElement(name = "Transition", type = Transition.class),
            @XmlElement(name = "Place", type = Place.class)

    })
    public Father getSecond() {
        return second;
    }

    public void setSecond(Father second) {
        this.second = second;
    }

    @XmlElement(name = "Weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                first.getName() +
                ", " + second.getName() +
                '}';
    }

}
