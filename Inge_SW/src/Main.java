import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;


public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {

        int start = Utility.readInt01(Utility.START);
        switch (start) {
            case 0:
                // Visualizzazione delle reti salvate
                break;

            case 1:
                Net net = new Net();
                break;
        }
    }
}
