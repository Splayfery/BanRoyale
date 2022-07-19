package banroyale;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class StartEndListener extends ListenerAdapter {


    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {

            TextChannel channel = event.getGuild().getTextChannelById("981117243318493184");
            Role everyone = event.getGuild().getRoleById("981114938238062622");

            switch (event.getName()) {

                case "start":

                    event.reply("✅ | Der Countdown zu Start des Events beginnt nun!").setEphemeral(true).queue();

                    channel.sendMessage("**3**").queue();

                    Timer t1 = new Timer();
                    t1.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            channel.sendMessage("**2**").queue();

                            t1.cancel();

                        }
                    }, 1000);

                    Timer t2 = new Timer();
                    t2.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            channel.sendMessage("**1**").queue();

                            t2.cancel();

                        }
                    }, 2000);

                    Timer t3 = new Timer();
                    t3.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            Message m = channel.sendMessage("**<:ban:981119027013378128> LETS GO " + everyone.getAsMention() + "! DIE RUNDE BEGINNT! Nutzt `/ban` um eure Gegner zu bannen, bevor sie es tun!** <a:dance:981905382723178556> ").complete();

                            m.addReaction("ban:981119027013378128").queue();
                            m.addReaction("a:dance:981905382723178556").queue();
                            m.addReaction("\uD83C\uDF89").queue();

                            channel.getPermissionContainer().upsertPermissionOverride(everyone).grant(Permission.USE_APPLICATION_COMMANDS).queue();

                            t3.cancel();

                        }
                    }, 3000);

                    break;

                case "end":

                    channel.getPermissionContainer().upsertPermissionOverride(everyone).deny(Permission.USE_APPLICATION_COMMANDS).queue();

                    event.reply("✅ | Das Event wurde erfolgreich pausiert!").setEphemeral(false).queue();

                    break;

            }

        }

    }
}
