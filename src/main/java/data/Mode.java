package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Mode implements Serializable {
    private String title = "";
    private ArrayList<String> maps = new ArrayList<String>();

    public void addMap(String newMapName) {
        maps.add(newMapName);
    }

    public void removeMap(String mapName) {
        if(maps.contains(mapName))
            maps.remove(maps.indexOf(mapName));
    }
    public ArrayList<String> getMaps() {
        return maps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
