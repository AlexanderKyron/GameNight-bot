package core;

import commands.CmdListGames;
import commands.RolesCommands;
import data.GameData;
import data.RoleData;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.ExceptionLogger;

import java.util.ArrayList;

public class Bot {
    Main main = Main.getInstance();
    static Bot instance;
    private ArrayList<GameData> games = new ArrayList<GameData>();
    private ArrayList<RoleData> roles = new ArrayList<RoleData>();
    public static Bot getInstance() {
        if (instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    public void login() {
        roles.add(new RoleData("Xonotic","676591549253156885"));
        String token = main.getToken();
        new DiscordApiBuilder().setToken(token).login().thenAccept(api -> {
            // Add a listener which answers with "Pong!" if someone writes "!ping"
            api.addMessageCreateListener(new CmdListGames());
            api.addMessageCreateListener(new RolesCommands());
            // Print the invite url of your bot
            System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
        })
                // Log any exceptions that happened
                .exceptionally(ExceptionLogger.get());
    }

    public ArrayList<GameData> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameData> games) {
        this.games = games;
    }

    public ArrayList<RoleData> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<RoleData> roles) {
        this.roles = roles;
    }

    public RoleData searchRoles(String name) {
        for(RoleData r:roles) {
            if(r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }
}
