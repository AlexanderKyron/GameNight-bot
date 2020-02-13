package data;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {

    private String title = "";

    private ArrayList<Mode> modes = new ArrayList<Mode>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addMode(Mode newMode) {
        getModes().add(newMode);
    }

    public void removeMode(Mode mode) {
        if(getModes().contains(mode))
            getModes().remove(getModes().indexOf(mode));
    }
    public ArrayList<Mode> getModes() {
        return modes;
    }

    public void setModes(ArrayList<Mode> modes) {
        this.modes = modes;
    }
}
