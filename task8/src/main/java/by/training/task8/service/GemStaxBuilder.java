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
import java.util.Set;

public class GemStaxBuilder {
    private static final Logger LOGGER = LogManager.getLogger(GemStaxBuilder.class);
    private Set<Gem> gems;
    private Gem gem = null;
    private XMLInputFactory inputFactory;

    public GemStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
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

    public String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
