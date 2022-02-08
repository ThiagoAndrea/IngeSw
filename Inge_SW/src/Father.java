import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

public abstract class Father implements Serializable {


    public Father() {
    }

    public abstract String getName();

    public abstract int getToken();

    public abstract void setToken(int token);

    public abstract int getPriority();

    public abstract void setPriority(int priortiy);

    public abstract void setName(String name);

    public abstract Boolean getChecked();

    public abstract void setChecked(Boolean checked);
}