/*
Задание 1
Прочитайте файл, содержащий код на языке Java.
Определите, какие ключевые слова языка Java это код содержит.
Выведите эти слова и их количество в другой файл.
Используйте только байтовые потоки ввода-вывода.
 */
package task1;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByteStreams {
    public static void main(String[] args) {
        try {
            BufferedInputStream codeFile = new BufferedInputStream(new FileInputStream("Files\\task1\\code.txt"));
            BufferedInputStream wordsFile = new BufferedInputStream(new FileInputStream("Files\\task1\\keywords.txt"));
            ArrayList<Character> keywordsChars = new ArrayList();
            ArrayList<Character> codeChars = new ArrayList<>();
            int b;
            while ((b = wordsFile.read()) != -1) {
                keywordsChars.add((char) b);
            }
            while ((b = codeFile.read()) != -1) {
                codeChars.add((char) b);
            }
            codeFile.close();
            wordsFile.close();
            int count = 0;//число ключевых слов в коде
            ArrayList<String> codeKeywords = getArray(codeChars);
            ArrayList<String> keywords = getArray(keywordsChars);
            BufferedOutputStream out= new BufferedOutputStream(new FileOutputStream("Files\\task1\\result.txt"));
            for (String el : codeKeywords) {
                if (keywords.contains(el)) {
                    out.write(el.getBytes());
                    out.write("\n".getBytes());
                    count++;
                }
            }
            DataOutputStream out2 = new DataOutputStream(out);
            out2.writeUTF(Integer.toString(count));
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<String> getArray(ArrayList<Character> charArray) {
        Pattern p = Pattern.compile("[a-zA-Z]");//обработав код, оставит только буквы
        Matcher m;
        int curr = 0;
        ArrayList<Integer> indexes = new ArrayList<>();//лист индексов "небукв"
        indexes.add(0);
        for (Character el : charArray) {
            m = p.matcher(el.toString());
            if (!m.matches()) {
                indexes.add(curr);
            }
            curr++;
        }
        ArrayList<String> keywords = new ArrayList<>();//если промежуток между "небуквами" больше 1, то это слово, его можно смерджить и добавить в лист слов из кода
        keywords.add((charArray.subList(indexes.get(0), indexes.get(1)).toString()));
        for (int i = 2; i < indexes.size(); i++) {
            if (indexes.get(i) - indexes.get(i - 1) > 1) {
                keywords.add((charArray.subList(indexes.get(i - 1) + 1, indexes.get(i)).toString()));
            }
        }
        return keywords;
    }
}