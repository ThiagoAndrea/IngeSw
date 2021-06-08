import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class MarshallAndUnmarshall {

    public MarshallAndUnmarshall() {
    }

    String xmlNet = "Savings.xml";
    String xsdNet = "savings.xsd";

    //per prova passo solo una rete, poi vediamo per più reti
    //MARSHALL = SCRITTURA
    public void marshall(Net rete) throws SAXException, JAXBException {

        //SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        //Schema schema = schemaFactory.newSchema(new File(xsdNet));

        File xmlScritto = new File(xmlNet);

        JAXBContext jaxbContext = JAXBContext.newInstance(Net.class, Place.class,Transition.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.setSchema(schema);
        //Event handler

        marshaller.marshal(rete, new File(xmlNet));
    }

    public void unmarshall() throws JAXBException {

        //SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        //Schema schema = schemaFactory.newSchema(new File());

        JAXBContext jaxbContext = JAXBContext.newInstance(Net.class, Place.class,Transition.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //unmarshaller.getSchema();
        //unmarshaller.setEventHandler();

        Net proofOfWork = (Net) unmarshaller.unmarshal(new File("Savings.xml"));

        System.out.println(proofOfWork.toString());
    }
}
