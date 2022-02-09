import javax.xml.bind.JAXBException;

public class ImportFile {


    public ImportFile() {
    }


    public void acceptFile(Global savedGlobal) throws JAXBException {
        Global global = new Global();
        MarshallAndUnmarshall marshall = new MarshallAndUnmarshall();

        int filechosen = Utility.readPositiveIntWithMax(Utility.GET_NET_FROM_FILE, 2);
        String nameFile ;
        boolean ok;
        switch (filechosen) {

            case 0:
                do{
                    ok = true;
                try {
                    nameFile = Utility.readString(Utility.INSERT_FILE);
                    global = marshall.unmarshallReturn(nameFile);
                }
                catch (Exception e){
                    ok = false;
                    System.out.println(Utility.ERROR_NAME_FILE);
                }
                }while(!ok);
                if (savedGlobal.confirmNet(savedGlobal.getNetsSaved(), global.getNetsSaved().get(0)))
                    savedGlobal.getNetsSaved().add(global.getNetsSaved().get(0));
                else System.out.println(Utility.NET_ALREADY_USED);
            case 1:
                do{
                    ok = true;
                    try {
                        nameFile = Utility.readString(Utility.INSERT_FILE);
                        global = marshall.unmarshallReturn(nameFile);
                    }
                    catch (Exception e){
                        ok = false;
                        System.out.println(Utility.ERROR_NAME_FILE);
                    }
                }while(!ok);
                Petri petri = global.getPetriNetsSaved().get(0);
                Net topology = petri.cloneNetFromPetri();
                if (global.confirmNet(savedGlobal.getNetsSaved(), topology)) {
                    System.out.println(Utility.WRONG_NET_TOPOLOGY);
                } else {
                    if (savedGlobal.confirmPetriNet(savedGlobal.getPetriNetsSaved(), global.getPetriNetsSaved().get(0)))
                        savedGlobal.getNetsSaved().add(global.getNetsSaved().get(0));
                    else System.out.println(Utility.NET_ALREADY_USED);
                }
                break;
            case 2:
                do{
                    ok = true;
                    try {
                        nameFile = Utility.readString(Utility.INSERT_FILE);
                        global = marshall.unmarshallReturn(nameFile);
                    }
                    catch (Exception e){
                        ok = false;
                        System.out.println(Utility.ERROR_NAME_FILE);
                    }
                }while(!ok);
                Priority priority = global.getPriorityNetsSaved().get(0);
                Petri topology2 = priority.clonePetriFromPriority();
                if (global.confirmPetriNet(savedGlobal.getPetriNetsSaved(), topology2)) {
                    System.out.println(Utility.WRONG_PETRI_TOPOLOGY);
                } else {
                    if (savedGlobal.confirmPriorityNet(savedGlobal.getPriorityNetsSaved(), global.getPriorityNetsSaved().get(0)))
                        savedGlobal.getNetsSaved().add(global.getPetriNetsSaved().get(0));
                    else System.out.println(Utility.NET_ALREADY_USED);
                }
            default:
                break;
        }
    }

}
