import javax.lang.model.type.ArrayType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
@XmlAccessorType (XmlAccessType.FIELD)
public class Petri extends Net {


    public Petri() {
        //Chiedi all'utente quali pesi inserire nel flusso
        //
    }

    public Petri(Net fatherNet) {
        Net n = clone(fatherNet);
        this.setFlux(n.getFlux());
        this.setAllFather(n.getAllFather());
        this.setName(Utility.readString(Utility.PETRI_NET_NAME));

        HashMap<Integer, Couple> map = new HashMap<>();
        int i = 1;
        for (Couple c : this.getFlux()) {
            map.put(i, c);
            i++;
        }
        int number = 1;
        int newWeight;
        while (number != 0) {
            for (Map.Entry<Integer, Couple> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "-->  " + entry.getValue().toString() + " peso: " + entry.getValue().getWeight());
            }
            number = Utility.readPositiveIntWithMax(Utility.CHOOSE_COUPLE, map.size());
            if (number != 0) {
                newWeight = Utility.readPositiveIntNot0(Utility.INSERT_WEIGHT);
                map.get(number).setWeight(newWeight);
            }
        }
        // Inserimento dei token

        ArrayList<Place> places = Utility.getPlacesFromFathers(this.getAllFather());
        int j, number2=1, newToken;
        while(number2!=0) {
            j = 1;
            for(Place p : places){
                System.out.println(j++ + ": " + p.getName() + " -> " + p.getToken());
            }
                number2 = (Utility.readPositiveIntWithMax(Utility.CHOOSE_PLACE, places.size()));
            if (number2 != 0) {
                newToken = Utility.readPositiveIntNot0(Utility.INSERT_TOKEN);
                places.get(number2-1).setToken(newToken);
            }

        }
    }


}

