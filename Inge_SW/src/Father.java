import java.io.Serializable;


public abstract class Father implements Serializable {


    public Father() {
    }

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Boolean getChecked();

    public abstract void setChecked (Boolean checked);
}