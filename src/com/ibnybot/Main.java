package com.ibnybot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.ibnybot.CodingAssistant; // Add this import

public class Main {
    public static void main(String[] args) {

        while (true) {
            
                System.out.println("Bot is still running...");


        try {
            
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
           botsApi.registerBot(new CodingAssistant("AIzaSyDjojLCjskhWGMD0YyQ0Vl8gB117fFOJdc"));
      //       botsApi.registerBot(new JavaAssistantBot("AIzaSyDjojLCjskhWGMD0YyQ0Vl8gB117fFOJdc"));
            System.out.println("Bot started successfully!");

        } catch (Exception e) {
             System.out.println("Bot failed to start. Retrying in 30 seconds...");
            Thread.sleep(30000);

            System.out.println("Bot is Dead!");
            e.printStackTrace();
        }
    }


    }
};

