import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {

        Net net1 = new Net();
        net1.printFlux();
        Net net2 = new Net();
        net2.printFlux();

        if(Utility.sameFlux(net1.getFlux(), net2.getFlux()))
            System.out.println("Hanno stesso flusso");
        else System.out.println("Non hanno stesso flusso");

    }
}
