package com.lexn.listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

public class CommandListener extends ListenerAdapter {
    public String prefix = "&";
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if(event.getAuthor().isBot()){
            return;
        }
        Message messageObject = event.getMessage();
        String message = messageObject.getContentRaw();



        //nur aus server chats
        if(event.isFromType(ChannelType.TEXT)){
            TextChannel channel = event.getChannel().asTextChannel();
            if(message.startsWith(prefix)){
                String[] args = message.split(" ");
                if(args[0].equalsIgnoreCase(prefix+"boot")){
                    messageObject.reply("Booting Server. (meme)").queue();
                    try {
                        System.out.println("Trying to boot server...");
                        /*Process p = new ProcessBuilder("cmd.exe").start();
                        p.waitFor();*/
                        Runtime rt = Runtime.getRuntime();
                        Process p = rt.exec("cmd.exe /c start cmd /k cd C:\\Users\\MinecraftServer\\Desktop\\mcserver /k java -Xms20G -Xmx20G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=40 -XX:G1MaxNewSizePercent=50 -XX:G1HeapRegionSize=16M -XX:G1ReservePercent=15 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=20 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs/ -Daikars.new.flags=true -jar paper.jar --nogui");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(args[0].equalsIgnoreCase(prefix+"test")){
                    messageObject.reply("Ah yes. Test.").queue();
                }
            }
        }
    }
}
