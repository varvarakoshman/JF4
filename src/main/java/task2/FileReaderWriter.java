/*
Задание 2
Прочитайте файл, содержащий код на языке Java. Определите, какие ключевые слова языка Java это код содержит.
Выведите эти слова и их количество в другой файл. Используйте только символьные потоки ввода-вывода.
 */
package task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileReaderWriter {
    public static void main(String[] args) {
        try {
            BufferedReader codeFile = new BufferedReader(new FileReader("Files\\task1\\code.txt"));
            Scanner scanCode = new Scanner(codeFile);
            BufferedReader wordsFile = new BufferedReader(new FileReader("Files\\task1\\keywords.txt"));
            Scanner scanWords = new Scanner(wordsFile);
            ArrayList<String> keywordsChars = new ArrayList();
            ArrayList<String> codeChars = new ArrayList<>();
            Pattern p = Pattern.compile("^[a-zA-Z]+$");
            String read;
            while (scanCode.hasNext()) {
                read = scanCode.next();
                if (p.matcher(read).matches()) {
                    codeChars.add(read);
                }
            }
            while (scanWords.hasNext()) {
                keywordsChars.add(scanWords.next());
            }
            codeFile.close();
            wordsFile.close();
            int count = 0;//число ключевых слов в коде
            BufferedWriter out = new BufferedWriter(new FileWriter("Files\\task2\\result.txt"));
            for(String word: codeChars){
                if (keywordsChars.contains(word)){
                    count++;
                    out.write(word);
                    out.write("\n");
                }
            }
            out.write(Integer.toString(count));
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
