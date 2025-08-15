package com.gemini;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.http.*;
import com.google.gson.*;

public class GeminiClient {
    private final String apiKey;
    public GeminiClient(String apiKey) {
        this.apiKey = apiKey;
    }

public String getReply(String prompt) {
    HttpURLConnection connection = null;

    try {
        // Step 1: Setup connection
        URL url = new URL("https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=" + apiKey );
        connection = (HttpURLConnection) url.openConnection();

        // Step 2: Set headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // ✅ Step 3: Build correct JSON payload
        JsonObject part = new JsonObject();
        part.addProperty("text", prompt);

        JsonArray parts = new JsonArray();
        parts.add(part);

        JsonObject content = new JsonObject();
        content.addProperty("role", "user"); // ✅ Required by Gemini
        content.add("parts", parts);

        JsonArray contents = new JsonArray();
        contents.add(content);

        JsonObject input = new JsonObject();
        input.add("contents", contents);

        // Step 4: Send request
        try (OutputStream os = connection.getOutputStream()) {
            os.write(input.toString().getBytes());
        }

        // Step 5: Read response
        try (InputStream is = connection.getInputStream();
             InputStreamReader reader = new InputStreamReader(is)) {
            JsonObject responseJson = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray candidates = responseJson.getAsJsonArray("candidates");
            JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
            JsonObject contentObj = firstCandidate.getAsJsonObject("content");
            JsonArray replyParts = contentObj.getAsJsonArray("parts");
            return replyParts.get(0).getAsJsonObject().get("text").getAsString();
        }

    } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());

        try (InputStream errorStream = connection.getErrorStream();
             InputStreamReader reader = new InputStreamReader(errorStream)) {
            JsonObject errorJson = JsonParser.parseReader(reader).getAsJsonObject();
            System.err.println("Gemini error response: " + errorJson);
        } catch (Exception ex) {
            System.err.println("Failed to read error stream: " + ex.getMessage());
        }

        return "Error connecting to Gemini.";
    }
}
}