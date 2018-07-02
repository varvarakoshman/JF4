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
            Scanner scan_code = new Scanner(codeFile);
            BufferedReader wordsFile = new BufferedReader(new FileReader("Files\\task1\\keywords.txt"));
            Scanner scan_words = new Scanner(wordsFile);
            ArrayList<String> keywords_chars = new ArrayList();
            ArrayList<String> code_chars = new ArrayList<>();
            Pattern p = Pattern.compile("^[a-zA-Z]+$");
            String read;
            while (scan_code.hasNext()) {
                read = scan_code.next();
                if (p.matcher(read).matches()) {
                    code_chars.add(read);
                }
            }
            while (scan_words.hasNext()) {
                keywords_chars.add(scan_words.next());
            }
            codeFile.close();
            wordsFile.close();
            int count = 0;//число ключевых слов в коде
            BufferedWriter out = new BufferedWriter(new FileWriter("Files\\task2\\result.txt"));
            for(String word: code_chars){
                if (keywords_chars.contains(word)){
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
