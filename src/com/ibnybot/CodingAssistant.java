package com.ibnybot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.gemini.GeminiClient;

public class CodingAssistant extends TelegramLongPollingBot {

        private final GeminiClient gemini;

    public CodingAssistant(String geminiApiKey) {
        this.gemini = new GeminiClient(geminiApiKey);
    }

/* 
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText("Hey! " + text);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
*/
    @Override
    public String getBotUsername() {
        return "IBanny4u_bot"; // Do NOT include '@'
    }

    @Override
    public String getBotToken() {
        return "7607659902:AAEMPAsX2zBPep455OFSCAeaKjDuFpIZdw8"; // From BotFather
    }

     @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            String reply = gemini.getReply(userMessage);
            SendMessage response = new SendMessage(update.getMessage().getChatId().toString(), reply);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
