import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public String fileName;

    public Dictionary(String fileName) throws IOException {
        this.fileName = fileName;
        if (check(fileName) == -1) throw new NumberFormatException("This dictionary is prohibited");
    }

    public HashMap<String, String> readDictionary() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        //слова и перевод на русский в файле разделены пробелом
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String s = reader.readLine();
            map.put(s.substring(0, s.charAt(32)), s.substring(s.charAt(32) + 1));
        }
        return map;
    }

    public void delete(String key) throws IOException {
        HashMap<String, String> map = readDictionary();
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getKey().equals(key)) map.remove(pair.getKey());
        }
        rewrite(map);
    }

    public void search(String key) throws IOException {
        HashMap<String, String> map = readDictionary();
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getKey().equals(key)) {
                System.out.println("Ключ: " + pair.getKey());
                System.out.println("Значение: " + pair.getValue());
            }
        }
    }

    public void add(String key, String value) throws IOException {
        HashMap<String, String> map = readDictionary();
        if (((key.length() == 4 && key.matches("[a-zA-Z]")) && check(fileName) == 4) ||
            (key.length() == 5 && key.matches("[0-9]")) && check(fileName) == 5)
        map.put(key, value);
        rewrite(map);
    }

    public void rewrite(HashMap<String, String> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        for (Map.Entry<String, String> pair : map.entrySet()){
            writer.write(pair.getKey() +  " " + pair.getValue() + "\n");
        }
    }

    public void watchDictionary() throws IOException {
        HashMap<String, String> map = readDictionary();
        for (Map.Entry<String, String> pair : map.entrySet()){
            System.out.println(pair.getKey() +  " " + pair.getValue());
        }
    }

    public int check(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String s = reader.readLine();
        String key = s.substring(0, s.charAt(32));
        if (key.length() == 4 && key.matches("[a-zA-Z]")) return 4;
        else if (key.length() == 5 && key.matches("[0-9]")) return 5;
        return -1;
    }
}
