package by.training.task8.bean;

public enum GemEnum {
    GEMS("gems"),
    NATURALGEM("naturalGem"),
    PROCESSEDGEM("processedGem"),
    ARTIFICIALGEM("artificialGem"),
    NAME("name"),
    VISUAL("visual"),
    COLOR("color"),
    FACETEDNESS("facetedness"),
    TRANSPARENCY("transparency"),
    VALUE("value"),
    DATE("date"),
    PRECIOUSNESS("preciousness"),
    PROCESSINGPLACE("processingPlace"),
    ORIGIN("origin"),
    CREATIONPLACE("creationPlace");
    private String value;
    private GemEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
