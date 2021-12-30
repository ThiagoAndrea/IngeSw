import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;


public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {

        MarshallAndUnmarshall util = new MarshallAndUnmarshall();

        Net net = null;
        Global global = util.unmarshallReturn();

        int start = Utility.readInt01(Utility.START);
        switch (start) {
            case 0:

                global.printAllNets();
                break;

            case 1:
                String netName = Utility.readString(Utility.NET_NAME);
                net = new Net(netName);
                if (Utility.readInt01(Utility.SAVING) == 1) {
                    if (global.confirmNet(global.getNetSaved(), net)) {
                        global.getNetSaved().add(net);
                        util.marshall(global);
                    }
                } else
                    System.out.println(Utility.NOTSAVED);
                break;
        }
        //util.unmarshall();

    }
}
