package commands;

import core.Bot;
import data.GameData;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CmdListGames implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        System.out.println("Command issued");
        DiscordApi api = event.getApi();
        //?games command - lists the games stored
        if(event.getMessage().getContent().equalsIgnoreCase("?games")) {
          System.out.println("?games command running");
            String gameList = "";
            for(GameData i: Bot.getInstance().getGames()) {
                gameList += i.getTitle();
                gameList += "\n";
            }
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Games List")
                    .addField("Games:", gameList == "" ? "No games in database!" : gameList);
            event.getChannel().sendMessage(embed);
        }
    }
}``
