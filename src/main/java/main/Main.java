package main;

import banroyale.AutoRole;
import banroyale.BanListener;
import banroyale.StartEndListener;
import banroyale.UnbanListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static JDABuilder builder;
    public static JDA shardMan;
    public static String token = "TOKEN";
    public static String guild = "guildID";

    public static void main(String[] args) throws LoginException, IOException, InterruptedException {

        new Main();

    }

    public Main() throws LoginException, IOException, InterruptedException {

        builder = JDABuilder.createDefault(token);

        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.watching("dem Bannhammer zu!"));

        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES);

        builder.addEventListeners(new BanListener());
        builder.addEventListeners(new UnbanListener());
        builder.addEventListeners(new AutoRole());
        builder.addEventListeners(new StartEndListener());

        shardMan = builder.build();

        shardMan.awaitReady().getGuildById(guild).upsertCommand("ban", "\uD83D\uDCDB〣Banne einen Nutzer von diesem Server!").addOption(OptionType.USER, "nutzer", "\uD83D\uDC65〣Wähle den Nutzer aus, welche du bannen möchtest!", true).queue();
        shardMan.awaitReady().getGuildById(guild).upsertCommand("unban", "✅〣Entbanne alle Nutzer für die nächste Runde!").queue();
        shardMan.awaitReady().getGuildById(guild).upsertCommand("start", "▶〣Starte die Runde Ban Royale!").queue();
        shardMan.awaitReady().getGuildById(guild).upsertCommand("end", "⏸〣Beende die Runde Ban Royale!").queue();

        //shardMan.awaitReady().updateCommands().queue();

        System.out.println("Der Bot ist nun online!");

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        if(reader.readLine().equals("stop")) {

            reader.close();

            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

            builder.setActivity(Activity.playing("Offline"));

            shardMan.shutdown();

            System.out.println("[Splayfer] Bot changed Status: Stopped");

            System.exit(0);

        } else if (reader.readLine().equals("checkfolder")) {

            //check data folders

            System.out.println("Alle Systeme laufen optimal!");
        }

    }

}
