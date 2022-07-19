package banroyale;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UnbanListener extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("unban")) {

            if (event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {

                int count = 0;

                for (Guild.Ban b : event.getGuild().retrieveBanList().complete()) {

                    event.getGuild().unban(b.getUser()).queue();

                    count = count + 1;

                }

                event.reply("✅ | **BEEEP!** Du hast alle Nutzer (" + count + ") erfolgreich für die nächste Runde entbannt!").setEphemeral(true).queue();

            }

        }

    }
}
