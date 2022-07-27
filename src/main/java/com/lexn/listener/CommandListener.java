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

import java.io.IOException;
import java.net.http.HttpClient;

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
                        Process p = new ProcessBuilder("cmd.exe").start();
                        p.waitFor();
                    } catch (IOException | InterruptedException e) {
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
