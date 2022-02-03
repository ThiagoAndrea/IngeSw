import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;


public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {



        MarshallAndUnmarshall util = new MarshallAndUnmarshall();
        Net net = new Net();
        Global global = util.unmarshallReturn();

        int confOrUser = Utility.readInt01(Utility.CONFIGURATOR_OR_USER);
        if (confOrUser == 0) {
            Configurator configurator = new Configurator();
            configurator.menu(global, net, util);
        } else {
            User user = new User();
            Petri chosen = user.userChoosePetri(global);
            user.simulation(chosen);
        }
    }

}

