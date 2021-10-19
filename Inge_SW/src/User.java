import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class User {

    XMLInputFactory xmlif = null;
    XMLStreamReader xmlr = null;

    public User(String file_Name) {

        initReader(file_Name);

        //lettore user
    }

    public void initReader(String nome_file) {

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(new FileInputStream(nome_file));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
        }
    }

    //lettore sax per eventi
    public void readXml(){}
}
