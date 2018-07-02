/*
Задание 3
Дан файл, содержащий буквы текст на кириллице. Кодировка файла utf-8.
Прочитайте информацию из файла и перепишите ее в файл в кодировкой utf-16.
*/
package task3;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Scanner;

public class Rewriting {
    public static void rewrite(BufferedReader in, BufferedWriter out) {
        try {
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                out.write(scanner.next());
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\user\\IdeaProjects\\epam JF\\JF4\\src\\main\\java\\task3\\letters.txt"), "UTF8"));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\user\\IdeaProjects\\epam JF\\JF4\\src\\main\\java\\task3\\letters_copy.txt"), "UTF16"));
        rewrite(in,out);
    }
}
