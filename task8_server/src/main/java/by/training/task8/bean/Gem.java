package by.training.task8.bean;

public abstract class Gem {
    protected String name;
    protected int value;
    protected Date date;
    protected String color;
    protected int facetedness;
    protected int transparency;
    private String preciousness;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(String preciousness) {
        this.preciousness = preciousness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFacetedness() {
        return facetedness;
    }

    public void setFacetedness(int facetedness) {
        this.facetedness = facetedness;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Preciousness: " + preciousness + "\n" +
                "Value: " + value + "\n" +
                "Date: " + date.toString() + "\n" +
                "Color: " + color + "\n" +
                "Facetedness: " + facetedness + "\n" +
                "Transparency: " + transparency + "\n";
    }
}
