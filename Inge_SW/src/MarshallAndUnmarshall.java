import org.xml.sax.SAXException;

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

    private final String xmlNet = "Savings.xml";
    private final String xmlProva = "TEST1_CONVERSIONE.xml";

    private final String xsdNet = "savings.xsd";

    //MARSHALL = SCRITTURA

    /**
     * @param
     * @throws SAXException
     * @throws JAXBException
     */
    public void marshall(Global global) throws SAXException, JAXBException {

        unmarshall();

        System.out.println("-------------------------------------------------------------");

        JAXBContext jaxbContext = JAXBContext.newInstance(Global.class, Net.class, Place.class, Transition.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Boolean.FALSE);

        //marshaller.setSchema(schema);

        //marshaller.setEventHandler(new NetValidationEventHandler());

        marshaller.marshal(global, new File(xmlNet));
        marshaller.marshal(global, System.out);

        System.out.println("-------------------------------------------------------------");
    }

    /**
     * @throws JAXBException
     */
    public void unmarshall() throws JAXBException {

        //SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        //Schema schema = schemaFactory.newSchema(new File());

        JAXBContext jaxbContext = JAXBContext.newInstance(Global.class, Net.class, Father.class, Place.class, Transition.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //unmarshaller.getSchema(); // Restituisce lo schema che è stato utilizzato per fare l'unmarshalling
        //unmarshaller.setEventHandler(new NetValidationEventHandler());

        Global proofOfWork = (Global) unmarshaller.unmarshal(new File(xmlProva));
        System.out.println("\t---\nUnmarshall eseguito\n\t---");
        //System.out.println(proofOfWork.toString());
    }
}