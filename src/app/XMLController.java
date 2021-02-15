package app;

//import entities.Threat;

import entities.Cell;
import entities.Pathogen;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLController {

    private static ArrayList<Pathogen> pathogens = new ArrayList<>();
    private static ArrayList<Cell> cells = new ArrayList<>();
    private String xmlFileName;


    public XMLController() {
    }

    // read xml, not sure if this is needed, maybe just use as private
    public static ArrayList<Pathogen> readPathogenXML() {

        NodeList nodeList;
        String name;
        String description;
        String hint;
        String location;
        String question;
        String correctAnswer;
        int points;
        Pathogen path;

        ClassLoader cl = XMLController.class.getClassLoader();




        // push into collection
        try {
            InputStream xmlFile = cl.getResourceAsStream("resources/Pathogens.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
          //  File inputFile = new File("resources/Pathogens.xml");
            Document doc = db.parse(xmlFile);
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

                    pathogens.add(i, new Pathogen(name, description, hint, location, question, correctAnswer, points));
                    path = new Pathogen(name, description, hint, location, question, correctAnswer, points);
                    path.setDiseaseList(pathogens);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathogens;
    }

    public static ArrayList<Cell> readCellXML() {
        NodeList nodeList;
        String name;
        String description;
        String delayTemp;
        boolean delay;
        String selfDamageTemp;
        boolean selfDamage;
        String defeats;
        String location;
        Cell c;
        List<String> tempDefeatsArr = new ArrayList<>();

        ClassLoader cl = XMLController.class.getClassLoader();


        try {
            InputStream xmlFile = cl.getResourceAsStream("resources/Cell.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
           // File inputFile = new File("resources/Cell.xml");
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName("cell");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    delayTemp = eElement.getElementsByTagName("delay").item(0).getTextContent();
                    if (delayTemp.equals("True")) {
                        delay = true;
                    } else {
                        delay = false;
                    }
                    selfDamageTemp = eElement.getElementsByTagName("self-damage").item(0).getTextContent();
                    if (selfDamageTemp.equals("True")) {
                        selfDamage = true;
                    } else {
                        selfDamage = false;
                    }
                    defeats = eElement.getElementsByTagName("defeats").item(0).getTextContent();

                    location = eElement.getElementsByTagName("location").item(0).getTextContent();

                    tempDefeatsArr = Arrays.asList(defeats.split(","));
                    cells.add(i, new Cell(name,
                            description,
                            delay,
                            selfDamage,
                            (List) tempDefeatsArr,
                            location));
                    c = new Cell(name, description, delay, selfDamage, tempDefeatsArr, location);
                    c.setCellList(cells);

                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading the cell list from xml");
            e.printStackTrace();
        }
        //  System.out.println("here is the list of cells  "+cells);// TODO delete after testing
        return cells;
    }


}