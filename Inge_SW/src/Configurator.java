import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public class Configurator {

    public Configurator() {

    }

    public void menu(Global global, Net net, MarshallAndUnmarshall util) throws JAXBException, SAXException {

        int start;
        do {
            start = Utility.readInt(Utility.MENU);
            switch (start) {

                case 1:
                    global.printAllNets();
                    break;

                case 2:
                    String netName = Utility.readString(Utility.NET_NAME);
                    net = new Net(netName);
                    if (Utility.readInt01(Utility.SAVING) == 1) {
                        if (global.confirmNet(global.getNetsSaved(), net)) {
                            global.getNetsSaved().add(net);
                            util.marshall(global);
                        }
                    } else
                        System.out.println(Utility.NOTSAVED);
                    break;

                case 3:
                    global.printAllNets();
                    Net picked = global.pickNet(global.getNetsSaved());
                    Petri p = new Petri(picked);
                    if (Utility.readInt01(Utility.SAVING) == 1) {
                        if (global.confirmPetriNet(global.getPetriNetsSaved(), p)) {
                            global.getPetriNetsSaved().add(p);
                            util.marshall(global);
                        }
                    }
                    break;

                case 4:
                    global.printAllPetriNets();
                    Petri petriPicked = global.pickPetriNet(global.getPetriNetsSaved());
                    Priority pr = new Priority(petriPicked);
                    if(Utility.readInt01(Utility.SAVING) == 1){
                        if(global.confirmPriorityNet(global.getPriorityNetsSaved(), pr)){
                            global.getPriorityNetsSaved().add(pr);
                            util.marshall(global);
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (start != 0);
    }
}

