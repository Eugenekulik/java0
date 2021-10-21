package by.training.task8.service;

import by.training.task8.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GemDomBuilder extends XMLGemBuilder {
    private static final Logger LOGGER = LogManager.getLogger(GemDomBuilder.class);
    private Set<Gem> gems;
    private DocumentBuilder documentBuilder;
    public GemDomBuilder() {
        gems = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e){
            LOGGER.error("Parser configuration error");
        }
    }
    public Set<Gem> getGems(){
        return gems;
    }
    public void buildSetGems(String filename)throws ServiceException{
        Document doc = null;
        try {
            doc = documentBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList stoneList = root.getElementsByTagName("naturalGem");
            for(int i = 0; i<stoneList.getLength();i++){
                Element stoneElement = (Element)stoneList.item(i);
                Gem gem = buildGem(stoneElement);
                gems.add(gem);
            }
            stoneList = root.getElementsByTagName("processedGem");
            for(int i = 0; i<stoneList.getLength();i++){
                Element stoneElement = (Element)stoneList.item(i);
                Gem gem = buildGem(stoneElement);
                gems.add(gem);
            }
            stoneList = root.getElementsByTagName("artificialGem");
            for(int i = 0; i<stoneList.getLength();i++){
                Element stoneElement = (Element)stoneList.item(i);
                Gem gem = buildGem(stoneElement);
                gems.add(gem);
            }
        } catch (IOException e) {
            LOGGER.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            LOGGER.error("Parsing failure: " + e);
        }
    }
    public Gem buildGem(Element gemElement){

        if(gemElement.getTagName() == "naturalGem"){
            NaturalGem gem = new NaturalGem();
            createCommonData(gemElement,gem);
            gem.setOrigin(gemElement.getAttribute("origin"));
            return gem;
        }
        else if(gemElement.getTagName() == "processedGem") {
            ProcessedGem gem = new ProcessedGem();
            createCommonData(gemElement,gem);
            gem.setProcessingPlace(gemElement.getAttribute("processingPlace"));
            gem.setOrigin(gemElement.getAttribute("origin"));
            return gem;
        }
        else {
            ArtificialGem gem = new ArtificialGem();
            createCommonData(gemElement,gem);
            gem.setCreationPlace(gemElement.getAttribute("creationPlace"));
            return gem;
        }
    }
    private static String getElementTextContent(Element element, String elementName){
        NodeList nList = element.getElementsByTagName(elementName);
        if(nList.getLength()!=0) {
            Node node = nList.item(0);
            return node.getTextContent();
        }
        return "-";
    }
    private void createCommonData(Element gemElement, Gem gem){
        if(gemElement.getAttribute("id") !=null) {
            try {
                gem.setId(Integer.parseInt(gemElement.getAttribute("id")));
            } catch (NumberFormatException e) {
                LOGGER.warn("invalid id");
                throw new UnsupportedOperationException("invalid id of gem");
            }
        }
        gem.setName(getElementTextContent(gemElement, "name"));
        try {
            gem.setValue(Integer.parseInt(getElementTextContent(gemElement, "value")));
        } catch (NumberFormatException e) {
            gem.setValue(0);
        }
        NodeList visuals = gemElement.getElementsByTagName("visual");
        if(visuals.getLength()!=0) {
            Element visual = (Element) visuals.item(0);
            gem.setColor(getElementTextContent(visual, "color"));
            try {
                gem.setFacetedness(Integer
                        .parseInt(getElementTextContent(visual, "facetedness")));
            }
            catch (NumberFormatException e) {
                gem.setFacetedness(3);
            }
            try {
                gem.setTransparency(Integer.parseInt(getElementTextContent(visual, "transparency")));
            } catch (NumberFormatException e){
                gem.setTransparency(0);
            }
        }
        Date date = new Date();
        date.createFromPattern(getElementTextContent(gemElement,"date"));
        gem.setDate(date);
        gem.setPreciousness(getElementTextContent(gemElement,"preciousness"));
    }
}
