package app;

//import entities.Threat;

import java.io.File;

import entities.Pathogen;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.*;

public class XMLController {

    private ArrayList<Pathogen> pathogens = new ArrayList<>();
    private String xmlFileName;


    public XMLController() {
    }

    // read xml, not sure if this is needed, maybe just use as private
    public ArrayList<Pathogen> readXML() {
        Pathogen threat = new Pathogen();

        NodeList nodeList;
        String name;
        String description;
        String hint;
        String location;
        String question;
        String correctAnswer;
        int points;

        // push into collection
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File inputFile = new File("resources/Pathogens.xml");
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();
           // System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
            nodeList = doc.getElementsByTagName("pathogen");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    hint = eElement.getElementsByTagName("hint").item(0).getTextContent();
                    location = eElement.getElementsByTagName("location").item(0).getTextContent();
                    question = eElement.getElementsByTagName("question").item(0).getTextContent();
                    correctAnswer = eElement.getElementsByTagName("correctAnswer").item(0).getTextContent();
                    points = Integer.parseInt(eElement.getElementsByTagName("points").item(0).getTextContent());

                    pathogens.add(i, new Pathogen( name,  description, hint, location, question, correctAnswer, points));

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(diseases);
        // System.out.println("\n\n"+ diseases.get(0).name + " array 0 name");     this was a test
//        System.out.println(diseases+"  at line 74");
//        System.out.println(threat.getDiseaseList().size() + " list size at line 71");
//
//        threat.setDiseaseList( diseases);

        return pathogens;

    }
    // Parse entities
//    public Collection<Threat> parseXML(){
//        //readXML(this.filename)
//        return null;
//    }
//    public String getXmlFileName() {
//        return xmlFileName;
//    }
//    public void setXmlFileName(String xmlFileName) {
//        this.xmlFileName = xmlFileName;
//    }


}