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
        //?roles command - list out the available roles
        if(event.getMessage().getContent().equalsIgnoreCase("?roles")) {
            System.out.println("?roles command running");
            //Get the roles and build a string out of them
            String roleList = "";
            for(RoleData roleName : Bot.getInstance().getRoles()) {
                roleList += roleName.getName();
                roleList += "\n";
            }
            //Build a rich embed out of the retrieved data and send it
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Roles")
                    .addField("Roles:", roleList == "" ? "No roles in database!" : roleList);
            event.getChannel().sendMessage(embed);
        }
        //?roles give: - gives a specified role by name
        else if(event.getMessage().getContent().startsWith("?roles give:")) {
            //split the command before and after the regex (":") to identify passed arguments
            String[] commandParts = event.getMessage().getContent().split(":");
            //Take the passed arg
            String desiredRoleName = commandParts[1];
            //try/catch block in case of nonexistent role. This is lazy, but it works and is unlikely to fail:tm:
            try {
                //Searches the role from the list of available roles, finds it by ID on Discord, and adds the message sender to it
                api.getRoleById(Bot.getInstance().searchRoles(desiredRoleName)
                        .getId()).get()
                        .addUser(event.getMessageAuthor().asUser().get());
                //Create an embed and send it
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Gave role: " + desiredRoleName);
                event.getChannel().sendMessage(embed);
            } catch (Exception e) {
                //Notify of failure to find role, and send it
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Role " + desiredRoleName + " does not exist, or is not in the bot.");
                event.getChannel().sendMessage(embed);
            }


        }
        //?roles remove: - removes a specified role by name
        else if(event.getMessage().getContent().startsWith("?roles remove:")) {
            //Get the arg for the command by splitting by ":" and storing
            String[] commandParts = event.getMessage().getContent().split(":");
            String unDesiredRoleName = commandParts[1];
            //try/catch incase of nonexistent role
            try {
                //Find the resultant searched role by ID and remove the user from it
                api.getRoleById(Bot.getInstance().searchRoles(unDesiredRoleName)
                        .getId()).get()
                        .removeUser(event.getMessageAuthor().asUser().get());
                //Create embed and send it
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Removed role: " + unDesiredRoleName);
                event.getChannel().sendMessage(embed);
            } catch (Exception e) {
                //Notify user of failure
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Roles")
                        .addField("Role", "Role " + unDesiredRoleName + " does not exist, or is not in the bot.");
                event.getChannel().sendMessage(embed);
            }
        }

    }
}
