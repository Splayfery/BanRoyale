package banroyale;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanListener extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {

        Role role = event.getGuild().getRoleById("981117730805678110");

        Role defaultRole = event.getGuild().getRoleById("981117442766036992");

        if (event.getName().equals("ban")) {

            Member target = event.getOptionsByName("nutzer").get(0).getAsMember();

            try {

                if (event.getUser().getMutualGuilds().contains(event.getGuild()) && target.getUser().getMutualGuilds().contains(event.getGuild())) {

                    if (!(target.getUser().isBot() || target.getRoles().contains(role))) {

                        if (!(target.getId().equals(event.getMember().getId()))) {

                            if (event.getGuild().getMembersWithRoles(defaultRole).size() != 1) {

                                target.ban(0, "Du wurdest von " + event.getUser().getAsTag() + " gebannt!").queue();

                                event.reply("<:ban:981119027013378128> **DAMN!** " + target.getAsMention() + " wurde mit dem Bannhammer zerstört!").setEphemeral(false).queue();

                            }

                        } else {

                            event.reply("<:cross:981129589537529906> **BRUH!** Du kannst dich doch nicht selbst bannen!").setEphemeral(false).queue();

                        }


                    } else {

                        event.reply("<:cross:981129589537529906> **BRUH!** Du kannst doch keinen König des Orbits bannen!").setEphemeral(false).queue();

                    }

                } else {

                    event.reply("<:cross:981129589537529906> **BRUH!** Das Mitglied wurde bereits vom Bannerhammer zerschnetzelt!").setEphemeral(false).queue();

                }

            } catch (Exception e) {

                event.reply("<:cross:981129589537529906> **BRUH!** Das Mitglied wurde bereits vom Bannerhammer zerschnetzelt!").setEphemeral(false).queue();

            }

        }

    }

}
