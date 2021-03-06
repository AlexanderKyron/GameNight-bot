package core;

import commands.CmdListGames;
import commands.HelpCommand;
import commands.RolesCommands;
import commands.RollGames;
import data.GameData;
import data.RoleData;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.ExceptionLogger;

import java.util.ArrayList;

public class Bot {
    //Main instance for backend use such as token retrieval
    Main main = Main.getInstance();
    //FileManager for loading and saving games and roles
    FileManager fm = FileManager.getInstance();
    //Instance of bot for singleton purposes. There really should only be one bot running.
    static Bot instance;
    //List of games stored in bot
    private ArrayList<GameData> games = new ArrayList<GameData>();
    //List of roles stored in bot
    private ArrayList<RoleData> roles = new ArrayList<RoleData>();

    /**
     * getInstance
     * Returns instance of singleton bot
     *
     * @return instance
     * @author Alexander Kyron
     * 13/02/2020
     */
    public static Bot getInstance() {
        if (instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    /**
     * login()
     * Initializes the bot and sets up listeners.
     *
     * @author Alexander Kyron
     * 13/02/2020
     */
    public void login() {
        //This is a test role, added so that the roles functionality can be used without a role.
        //roles.add(new RoleData("Xonotic","676591549253156885"));
        ArrayList<RoleData> rolesTemp = fm.loadRolesFromFile();
        if (rolesTemp != null) {
            roles = rolesTemp;
        }
        ArrayList<GameData> gamesTemp = fm.loadGamesFromFile();
        if (gamesTemp != null) {
            games = gamesTemp;
        }
        //Gets the token from the main class.
        String token = main.getToken();
        //Login to the bot.
        new DiscordApiBuilder().setToken(token).login().thenAccept(api -> {
            //Add message listeners for the commands
            api.addMessageCreateListener(new CmdListGames());
            api.addMessageCreateListener(new HelpCommand());
            api.addMessageCreateListener(new RolesCommands());
            api.addMessageCreateListener(new RollGames());
        }).exceptionally(ExceptionLogger.get());
    }

    /**
     * getGames()
     * games list getter
     *
     * @return games
     * 13/02/2020
     * @author Alexander Kyron
     */
    public ArrayList<GameData> getGames() {
        return games;
    }

    /**
     * setGames()
     * games list setter
     *
     * @param games 13/02/2020
     * @author Alexander Kyron
     */
    public void setGames(ArrayList<GameData> games) {
        this.games = games;
    }

    /**
     * getRoles()
     * Roles list getter
     *
     * @return roles
     * 13/02/2020
     * @author Alexander Kyron
     */
    public ArrayList<RoleData> getRoles() {
        return roles;
    }


    /**
     * setRomes()
     * roles list setter
     *
     * @param roles 13/02/2020
     * @author Alexander Kyron
     */
    public void setRoles(ArrayList<RoleData> roles) {
        this.roles = roles;
    }

    /**
     * searchRoles(String name)
     * <p>
     * Searches for the appropriate role by name an returns it - otherwise,
     * returns null.
     *
     * @param name
     * @return role
     * @author Alexander Kyron
     * 13/02/2020
     */
    public RoleData searchRoles(String name) {
        //Iterate roles and return if true.
        for (RoleData r : roles) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        //return null if not found
        return null;
    }

    /**
     * searchGames(String name)
     * <p>
     * Searches for the appropriate game by name an returns it - otherwise,
     * returns null.
     *
     * @param name
     * @return g
     * @author Alexander Kyron
     * 13/02/2020
     */
    public GameData searchGames(String name) {
        //iterate games, return if found
        for (GameData g : games) {
            if (g.getTitle().equalsIgnoreCase(name)) {
                return g;
            }
        }
        //return null if not found
        return null;
    }

}
