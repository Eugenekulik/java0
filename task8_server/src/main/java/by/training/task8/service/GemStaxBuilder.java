package by.training.task8.service;

import by.training.task8.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GemStaxBuilder extends XMLGemBuilder{
    private static final Logger LOGGER = LogManager.getLogger(GemStaxBuilder.class);
    private Set<Gem> gems;
    private Gem gem = null;
    private XMLInputFactory inputFactory;

    public GemStaxBuilder() {
        gems = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    public  Set<Gem> getGems(){
        return gems;
    }
    public void buildSetGems(String file) throws ServiceException {
        XMLStreamReader reader = null;
        FileInputStream inputStream = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(file));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.NATURALGEM) {
                        gem = new NaturalGem();
                        buildGem(reader);
                        gems.add(gem);
                        gem = null;
                    }
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.PROCESSEDGEM) {
                        gem = new ProcessedGem();
                        buildGem(reader);
                        gems.add(gem);
                        gem = null;
                    }
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.ARTIFICIALGEM) {
                        gem = new ArtificialGem();
                        buildGem(reader);
                        gems.add(gem);
                        gem = null;
                    }
                }

            }
        } catch (XMLStreamException e) {
            LOGGER.error(e);
            throw new ServiceException();
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file " + file + " : " + e);
            }
        }
    }

    public void buildGem(XMLStreamReader reader) throws ServiceException, XMLStreamException {
        try {
            gem.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        } catch (NumberFormatException e) {
            LOGGER.warn("invalid id");
            throw new ServiceException(e);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            gem.setName(getXMLText(reader));
                            break;
                        case PRECIOUSNESS:
                            gem.setPreciousness(getXMLText(reader));
                            break;
                        case VALUE:
                            try{
                                gem.setValue(Integer.parseInt(getXMLText(reader)));
                            } catch (NumberFormatException e) {
                                gem.setValue(0);
                            }
                            break;
                        case ORIGIN:
                            NaturalGem naturalGem = (NaturalGem) gem;
                            naturalGem.setOrigin(getXMLText(reader));
                            break;
                        case PROCESSINGPLACE:
                            ProcessedGem processedGem = (ProcessedGem) gem;
                            processedGem.setProcessingPlace(getXMLText(reader));
                            break;
                        case CREATIONPLACE:
                            ArtificialGem artificialGem = (ArtificialGem) gem;
                            artificialGem.setCreationPlace(getXMLText(reader));
                            break;
                        case DATE:
                            Date date = new Date();
                            date.createFromPattern(getXMLText(reader));
                            gem.setDate(date);
                            break;
                        case VISUAL:
                            visualBuilder(reader);
                            break;

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.NATURALGEM) {
                        return;
                    }
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.ARTIFICIALGEM) {
                        return;
                    }
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.PROCESSEDGEM) {
                        return;
                    }
                    break;
            }
        }
    }
    public void visualBuilder(XMLStreamReader reader) throws XMLStreamException, ServiceException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case TRANSPARENCY:
                            try {
                                gem.setTransparency(Integer.parseInt(getXMLText(reader)));
                            } catch (NumberFormatException e) {
                                LOGGER.warn("invalid transparency");
                                gem.setTransparency(0);
                            }
                            break;
                        case FACETEDNESS:
                            try {
                                gem.setFacetedness(Integer.parseInt(getXMLText(reader)));
                            } catch (NumberFormatException e) {
                                LOGGER.warn("invalid facetedness");
                                gem.setFacetedness(3);
                            }
                            break;
                        case COLOR:
                            gem.setColor(getXMLText(reader));
                            break;
                        default:
                            LOGGER.warn("Unknown element in visual");
                            throw new ServiceException("Unknown element in visual");
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.VISUAL) {
                        return;
                    }
            }
        }
    }
    public String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
