package commands;

import core.Bot;
import data.RoleData;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class RolesCommands implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        System.out.println("Command issued");
        DiscordApi api = event.getApi();
        if(event.getMessage().getContent().equalsIgnoreCase("?roles")) {
            System.out.println("?roles command running");
            String roleList = "";
            for(RoleData roleName : Bot.getInstance().getRoles()) {
                roleList += roleName.getName();
                roleList += "\n";
            }
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Roles")
                    .addField("Roles:", roleList == "" ? "No roles in database!" : roleList);
            event.getChannel().sendMessage(embed);
        }
        else if(event.getMessage().getContent().startsWith("?roles give:")) {
            String[] commandParts = event.getMessage().getContent().split(":");
            String desiredRoleName = commandParts[1];
            try {
                api.getRoleById(Bot.getInstance().searchRoles(desiredRoleName).getId()).get().addUser(event.getMessageAuthor().asUser().get());
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Gave role: " + desiredRoleName);
                event.getChannel().sendMessage(embed);
            } catch (Exception e) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Role " + desiredRoleName + " does not exist, or is not in the bot.");
                event.getChannel().sendMessage(embed);
            }


        }
        else if(event.getMessage().getContent().startsWith("?roles remove:")) {
            String[] commandParts = event.getMessage().getContent().split(":");
            String unDesiredRoleName = commandParts[1];
            try {
                api.getRoleById(Bot.getInstance().searchRoles(unDesiredRoleName).getId()).get().removeUser(event.getMessageAuthor().asUser().get());
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Removed role: " + unDesiredRoleName);
                event.getChannel().sendMessage(embed);
            } catch (Exception e) {
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Role " + unDesiredRoleName + " does not exist, or is not in the bot.");
                event.getChannel().sendMessage(embed);
            }
        }

    }
}
