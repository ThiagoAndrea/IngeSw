import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {
        final String xmlNet = "Savings.xml";

        MarshallAndUnmarshall util = new MarshallAndUnmarshall();
        Net net = new Net();
        Global global = util.unmarshallReturn(xmlNet);
        global.sameObject();

        int confOrUser = Utility.readInt01(Utility.CONFIGURATOR_OR_USER);
        if (confOrUser == 0) {
            Configurator configurator = new Configurator();
            configurator.menu(global, net, util);
        } else {
            User user = new User();
            int nextSimulation = 1;
            if(Utility.readInt01(Utility.PETRI_OR_PRIORITY) == 1) {
                Petri chosen = user.userChoosePetri(global);
                while (nextSimulation == 1)
                    nextSimulation = user.simulationPetri(chosen);
            }
            else {
                Priority chosen = user.userChoosePriority(global);
                while (nextSimulation == 1)
                    nextSimulation = user.simulationPriority(chosen);

            }
        }
    }

}

