package me.astri.casino.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Utils {
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
                if(line.startsWith("#")) continue;
                line = line.replaceAll("([ ]*,[ ]*)",",");
                String[] values = line.split(",");
                records.add(Arrays.stream(values).toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
