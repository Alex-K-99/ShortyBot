package com.lexn;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpBot {

    public static JDA jda;

    public SpBot() {
        try {
            JDABuilder builder = JDABuilder.createDefault("MTAwMTA4ODEzMDE1MTA4ODE1OA.GPmQ-m.SxZwb52n0jM93Tyi-o0UBYMWn-FcB_tKAWauuo");


            builder.setActivity(Activity.playing("mit Steuergeldern"));
            builder.setStatus(OnlineStatus.ONLINE);



            this.jda = builder.build();
        } catch (LoginException e){
            e.printStackTrace();
            System.out.println("Login Failed.");
        }
    }

    public static void main(String[] args) {
        new SpBot();

        shutdown();

    }

    static public void shutdown(){
        new Thread(() -> {

            String line = "";
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
