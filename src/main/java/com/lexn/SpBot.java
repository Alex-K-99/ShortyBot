package com.lexn;

import com.lexn.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class SpBot {

    public static JDA jda;

    public SpBot(String token) {
        try {
            JDABuilder builder = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                    .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER);


            builder.setActivity(Activity.playing("mit Steuergeldern"));
            builder.setStatus(OnlineStatus.ONLINE);

            builder.addEventListeners(new CommandListener());


            jda = builder.build();
        } catch (LoginException e){
            e.printStackTrace();
            System.out.println("Login Failed.");
        }
    }

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        String token = env.get("ShortyBotToken");
        new SpBot(token);

        shutdown();
    }

    static public void shutdown(){
        new Thread(() -> {

            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try{
                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if(jda != null){
                            System.out.println("Shutting down Bot...");
                            jda.getPresence().setStatus(OnlineStatus.OFFLINE);
                            jda.shutdown();
                            System.out.println("Shutdown complete.");
                            return;
                        } else {
                            System.out.println("Bot seems to be offline already.");
                        }
                    } else {
                        System.out.println("Unknown Command.");
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }


        }).start();
    }
}
