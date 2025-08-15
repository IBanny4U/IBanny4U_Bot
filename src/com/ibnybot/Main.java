package com.ibnybot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.ibnybot.CodingAssistant; // Add this import

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
           botsApi.registerBot(new CodingAssistant("AIzaSyDjojLCjskhWGMD0YyQ0Vl8gB117fFOJdc"));
      //       botsApi.registerBot(new JavaAssistantBot("AIzaSyDjojLCjskhWGMD0YyQ0Vl8gB117fFOJdc"));
            System.out.println("Bot started successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
};

