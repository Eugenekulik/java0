package by.training.task8.service;

import by.training.task8.bean.Gem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class GemSaxBuilder extends XMLGemBuilder {
    private static final Logger LOGGER = LogManager.getLogger(GemSaxBuilder.class);
    private Set<Gem> gems;
    private GemHandler gemHandler;
    private XMLReader xmlReader;
    public GemSaxBuilder(){
        gemHandler = new GemHandler();
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(gemHandler);
        } catch (SAXException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    public Set<Gem> getGems() {
        return gems;
    }

    public void buildSetGems(String fileName) throws ServiceException{
        try {
            xmlReader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("SAX parser error: " + e);
        } catch (IOException e) {
            System.err.print("I/O error: " + e);
        }
        gems = gemHandler.getGems();
    }
}
