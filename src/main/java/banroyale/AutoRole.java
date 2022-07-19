package banroyale;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AutoRole extends ListenerAdapter {

    public void onGuildMemberJoin (GuildMemberJoinEvent event) {

        Role role = event.getGuild().getRoleById("981117442766036992");

        if (!(event.getUser().isBot())) {

            event.getGuild().addRoleToMember(event.getMember(), role).queue();

        }

    }

}
