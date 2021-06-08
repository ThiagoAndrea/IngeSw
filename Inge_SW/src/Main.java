import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JAXBException, SAXException {

        Net net = new Net();
       System.out.println(net.toString());

     //   util.marshall(net);
        //    util.unmarshall();
    }
}
