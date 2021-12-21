import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;


public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {

        MarshallAndUnmarshall util = new MarshallAndUnmarshall();

        Net net = null;

        int start = Utility.readInt01(Utility.START);
        switch (start) {
            case 0:
                // Visualizzazione delle reti salvate
                break;

            case 1:
                String netName = Utility.readString(Utility.NET_NAME);
                net = new Net(netName);
                if(Utility.readInt01(Utility.SAVING) == 1)
                util.marshall(net);
                else
                    System.out.println(Utility.NOTSAVED);
                break;
        }
        //util.unmarshall();

    }
}
