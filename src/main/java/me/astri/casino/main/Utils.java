package me.astri.casino.main;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utils {
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean areNumbers(String input) {
        if(input.startsWith("-")) input = input.replaceFirst("-","");
        for(char c : input.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static ArrayList<List<String>> readCSV(String key) {
        String path = Config.get(key.toUpperCase());
        ArrayList<List<String>> records = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(line.startsWith("#")) continue;
                line = line.replaceAll("([ ]*,[ ]*)",",");
                String[] values = line.split(",");
                records.add(Arrays.stream(values).toList());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=========");
        return records;
    }

    public static void addReactions(Message message, String ... reactions) {
        Arrays.stream(reactions).forEach(reaction -> message.addReaction(reaction).queue());
    }

    public static boolean hasBotReacted(MessageReactionAddEvent e) {
        AtomicBoolean bool = new AtomicBoolean(false);
        e.getReaction().retrieveUsers().complete().forEach(user -> {
            if(user.equals(e.getJDA().getSelfUser()))
                bool.set(true);
        });
        return bool.get();
    }
}
