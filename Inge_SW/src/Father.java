import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlSeeAlso({Place.class,Transition.class,Couple.class})
public abstract class Father implements Serializable {


    public Father() {
    }

    public abstract String getName();

    @XmlTransient
    public abstract int getToken();

    public abstract void setToken(int token);

    public abstract void setName(String name);

    @XmlTransient
    public abstract Boolean getChecked();

    public abstract void setChecked (Boolean checked);
}