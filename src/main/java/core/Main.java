package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    //Make sure that it is easy to get non-static values from singleton core.Main without constantly passing around an instance
    static Main instance;
    static Main getInstance() {
        if(instance == null)
            instance = new Main();
        return instance;
    }
    Bot botBackend;
    //Discord API token
    private String token;

    public static void main(String[] args) {
        //escape static
        Main m = Main.getInstance();
        m.start();
    }

    /***
     * void start()
     * In a new instance of main, initialize the bot program.
     * @author Alexander Kyron
     * @date 13/02/2020
     */
    public void start() {
        try {
            String tk = Files.readString(Paths.get("token.txt"), StandardCharsets.US_ASCII);
            setToken(tk);
            botBackend = Bot.getInstance();
            botBackend.login();
            System.out.println("Logged in attempted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getToken()
     * Getter for the Token
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * setToken
     * Token setter
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
