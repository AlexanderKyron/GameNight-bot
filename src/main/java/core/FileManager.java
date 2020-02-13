package core;

import data.GameData;
import data.Mode;
import data.RoleData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    static FileManager instance;
    public static FileManager getInstance() {
        if(instance == null)
            instance = new FileManager();
        return instance;
    }

    /**
     * loadRolesFromFile()
     * Loads in the roles from a file formatted as name:id with one role per line.
     * @author Alexander Kyron
     * @return
     * 13/02/2020
     */
    public ArrayList<RoleData> loadRolesFromFile() {
        try {
            Scanner fileScanner = new Scanner(new File("roles.txt"));
            ArrayList<RoleData> list = new ArrayList<RoleData>();
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(":");
                /System.out.println(line);
                RoleData r = new RoleData(data[0],data[1]);
                list.add(r);
            }
            fileScanner.close();
            return list;
        } catch (FileNotFoundException e) {
            return null;
        }

    }

    /**
     * loadGamesFromFile()
     * Loads in the games from a file formatted as such:
     * Title;Mode.map:Mode.map
     * with unlimited repetitions of map within the scope of a mode, and unlimited modes
     * @author Alexander Kyron
     * @return
     * 13/02/2020
     */
    public ArrayList<GameData> loadGamesFromFile() {
        try {
            Scanner fileScanner = new Scanner(new File("games.txt"));
            ArrayList<GameData> list = new ArrayList<GameData>();
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");
                String gameTitle = data[0];
                //System.out.println(gameTitle);
                String[] gameInfo = data[1].split(":");
                ArrayList<Mode> modes = new ArrayList<Mode>();
                for(String info:gameInfo) {
                    //System.out.println("Mode Information: " + info);
                    Mode m = new Mode();
                    String[] segments = info.split("\\.");
                    //System.out.println(segments.length);
                    for(int i = 0; i < segments.length; i++) {
                        System.out.println("Segmentinf: " + segments[i]);
                        if(i == 0) {
                            m.setTitle(segments[i]);
                        } else {
                            m.getMaps().add(segments[i]);
                            //System.out.println("Mode: " + segments[0] + "\nMap: " + segments[i]);
                        }
                    }
                    modes.add(m);
                }
                //System.out.println(line);
                GameData game = new GameData();
                game.setTitle(gameTitle);
                game.setModes(modes);
                list.add(game);
            }
            fileScanner.close();
            return list;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
