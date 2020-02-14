package commands;

import core.Bot;
import data.GameData;
import data.Mode;
import data.RoleData;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Random;

public class RollGames implements MessageCreateListener {
 @Override
    public void onMessageCreate(MessageCreateEvent event) {
        System.out.println("Command issued");
        //Get the DiscordApi instance for the message. Usually unused.
        DiscordApi api = event.getApi();
        //Roll command with no args - picks a random game and associated map and mode from the hierarchy.
        if(event.getMessage().getContent().equalsIgnoreCase("?roll")) {
            System.out.println("?roles command running");
            //Pick a random game from the bot and retrieve the tile
            if(Bot.getInstance().getGames().size() > 0) {
                Random r = new Random();
                int randInt = r.nextInt(Bot.getInstance().getGames().size());
                GameData game = Bot.getInstance().getGames().get(randInt);
                String gameTitle = game.getTitle();
                //Pick a random mode from that game and retrieve the name
                randInt = r.nextInt(game.getModes().size());
                Mode mode = game.getModes().get(randInt);
                String modeName = mode.getTitle();
                //Pick a random map string from that mode
                randInt = r.nextInt(mode.getMaps().size());
                String map = mode.getMaps().get(randInt);
                //Create a rich embed message including all relevant information and send it to the appropriate channel
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Game Roller")
                        .addField("Game:", gameTitle)
                        .addField("Mode: ", modeName)
                        .addField("Map: ", map);
                event.getChannel().sendMessage(embed);
            } else {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Game Roller")
                        .addField("Error", "There are no games set up.");
                event.getChannel().sendMessage(embed);
            }
        }
        //roll command with game args - picks a random map and mode from a game, specified by title
        else if(event.getMessage().getContent().startsWith("?roll game:") || event.getMessage().getContent().startsWith("?roll -p game:")) {
            String[] commandParts = event.getMessage().getContent().split(":");
            String desiredGameName = commandParts[1];
            Random r = new Random();
            int randInt;
            //Search and retrieve game by title. This should only return one possible game.
            GameData game = Bot.getInstance().searchGames(desiredGameName);
            if(game != null) {
            String gameTitle = game.getTitle();
            //Pick a random mode from that game and retrieve the name
            randInt = r.nextInt(game.getModes().size());
            Mode mode = game.getModes().get(randInt);
            String modeName = mode.getTitle();
            //Pick a random map string from that mode
            randInt = r.nextInt(mode.getMaps().size());
            String map = mode.getMaps().get(randInt);
            //Create a rich embed message including all relevant information and send it to the appropriate channel
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Game Roller")
                    .addField("Game:", gameTitle)
                    .addField("Mode: ", modeName)
                    .addField("Map: ", map);
            if(event.getMessage().getContent().contains("-p")) {
                embed.addField("Pings: ", "<@&" + Bot.getInstance().searchRoles(gameTitle).getId() +">");
            }
            event.getChannel().sendMessage(embed);
            } else {
              EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Game Roller")
                    .addField("Error", "Game not found!");
              event.getChannel().sendMessage(embed);
            }


        }
        
    }
}
