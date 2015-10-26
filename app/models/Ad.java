package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Ad {
    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String description;
    private List<Instrument> visibleInstruments;
    private List<String> desiredStyles;
    private List<String> undesirebaleStyles;
    private int casualOrBand;

    public Ad(String title, String description, List<Instrument> visibleInstruments, List<String> desiredStyles,
              List<String> undesirebaleStyles, int casualOrBand) {
        this.title = title;
        this.description = description;
        this.visibleInstruments = visibleInstruments;
        this.desiredStyles = desiredStyles;
        this.undesirebaleStyles = undesirebaleStyles;
        this.casualOrBand = casualOrBand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Instrument> getVisibleInstruments() {
        return visibleInstruments;
    }

    public void setVisibleInstruments(List<Instrument> visibleInstruments) {
        this.visibleInstruments = visibleInstruments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDesiredStyles() {
        return desiredStyles;
    }

    public void setDesiredStyles(List<String> desiredStyles) {
        this.desiredStyles = desiredStyles;
    }

    public List<String> getUndesirebaleStyles() {
        return undesirebaleStyles;
    }

    public void setUndesirebaleStyles(List<String> undesirebaleStyles) {
        this.undesirebaleStyles = undesirebaleStyles;
    }

    public int getCasualOrBand() {
        return casualOrBand;
    }

    public void setCasualOrBand(int casualOrBand) {
        this.casualOrBand = casualOrBand;
    }
}
