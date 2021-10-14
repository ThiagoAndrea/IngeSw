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
                Net net = new Net();
                break;
        }

        //util.unmarshall();
        util.marshall(net);
    }
}
