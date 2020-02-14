package commands;

import core.Bot;
import data.GameData;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

    public class HelpCommand implements MessageCreateListener {
        @Override
        public void onMessageCreate(MessageCreateEvent event) {
            System.out.println("Command issued");
            DiscordApi api = event.getApi();
            //?games command - lists the games stored
            if(event.getMessage().getContent().equalsIgnoreCase("?help")) {

                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Commands:", "?roles \n ?games\n?roll");
                event.getChannel().sendMessage(embed);
            } else if (event.getMessage().getContent().equalsIgnoreCase("?help roll")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Roll Command:", "?roll")
                        .addField("Functionality: ", "\"Rolls\" for a random game and associated mode/map.")
                        .addField("Arguments:", "-p - Pings the associated role for the game. \ngame:name - replace name with a game title. Only selects modes and maps from the specified game.");
                event.getChannel().sendMessage(embed);
            } else if (event.getMessage().getContent().equalsIgnoreCase("?help games")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Games Command:", "?games")
                        .addField("Functionality: ", "Lists the games currently stored by the bot.");
                        //.addField("Arguments: ", "add - adds a game based on the supplied input (MUST be in correct format). Must be admin. \nremove:name - replace name with a game title. Removes a game from the list. Must be admin.");
                event.getChannel().sendMessage(embed);
            }else if (event.getMessage().getContent().equalsIgnoreCase("?help roles")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Roles Command:", "?roles")
                        .addField("Functionality: ", "Lists the roles stored and available, as well as allowing adding and removing user from roles.")
                        .addField("Arguments: ", "add:name - replace name with role name. Gives you the specified role.\nremove:name - replace name with role name. Removes specified role from user.");
                event.getChannel().sendMessage(embed);
            }
    }

}
