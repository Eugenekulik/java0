package by.training.task8.service;

import by.training.task8.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class GemHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(GemHandler.class);
    private Set<Gem> gems;
    private Gem current = null;
    private GemEnum currentEnum = null;
    private EnumSet<GemEnum> withText;

    public GemHandler() {
        gems = new HashSet<>();
        withText = EnumSet.range(GemEnum.GEMS, GemEnum.CREATIONPLACE);
    }

    public Set<Gem> getGems() {
        return gems;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (localName) {
            case "naturalGem":
                current = new NaturalGem();
                try {
                    current.setId(Integer.parseInt(attrs.getValue("id")));
                } catch (NumberFormatException e) {
                    LOGGER.warn("invalid id");
                    throw new UnsupportedOperationException(e);
                }
                break;
            case "processedGem":
                current = new ProcessedGem();
                try {
                    current.setId(Integer.parseInt(attrs.getValue("id")));
                } catch (NumberFormatException e) {
                    LOGGER.warn("invalid id");
                    throw new UnsupportedOperationException(e);
                }
                break;
            case "artificialGem":
                current = new ArtificialGem();
                try {
                    current.setId(Integer.parseInt(attrs.getValue("id")));
                } catch (NumberFormatException e) {
                    LOGGER.warn("invalid id");
                    throw new UnsupportedOperationException(e);
                }
                break;
            case "gems":
                break;
            default:
                GemEnum temp = GemEnum.valueOf(localName.toUpperCase());
                if(withText.contains(temp)){
                    currentEnum = temp;
                }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("naturalGem".equals(localName)
                || "processedGem".equals(localName) ||
                "artificialGem".equals(localName)) {
            gems.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case VALUE:
                    try {
                        current.setValue(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        current.setValue(0);
                        LOGGER.warn("invalid gem's value");
                    }
                    break;
                case DATE:
                    Date date = new Date();
                    date.createFromPattern(s);
                    current.setDate(date);
                    break;
                case PRECIOUSNESS:
                    current.setPreciousness(s);
                    break;
                case TRANSPARENCY:
                    try {
                        current.setTransparency(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        current.setTransparency(0);
                        LOGGER.warn("invalid gem's transparency");
                    }
                    break;
                case COLOR:
                    current.setColor(s);
                    break;
                case FACETEDNESS:
                    try {
                        current.setFacetedness(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        LOGGER.warn("invalid facetedness");
                        current.setFacetedness(3);
                    }
                    break;
                case ORIGIN:
                    NaturalGem naturalGem = (NaturalGem) current;
                    naturalGem.setOrigin(s);
                    break;
                case PROCESSINGPLACE:
                    ProcessedGem processedGem = (ProcessedGem) current;
                    processedGem.setProcessingPlace(s);
                    break;
                case CREATIONPLACE:
                    ArtificialGem artificialGem = (ArtificialGem) current;
                    artificialGem.setCreationPlace(s);
                    break;
                case VISUAL:
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(),
                            currentEnum.name());
            }
            currentEnum = null;
        }
    }
}