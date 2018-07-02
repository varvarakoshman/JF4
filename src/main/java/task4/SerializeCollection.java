/*
Задание4
Дана коллекция фильмов, содержащая информацию об актерах,
снимавшихся в главных ролях (один актер мог сниматься и в нескольких фильмах).
Необходимо написать приложение, позволяющее при запуске восстанавливать коллекцию фильмов,
позволять ее модифицировать, а по завершении работы приложения – сохранять (в файл).
Для восстановления/сохранения коллекции использовать  сериализацию/десериализацию.
 */
package task4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SerializeCollection {

    public static ByteArrayOutputStream serializing( Collection collection) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(collection);
            oos.close();
            os.close();
            result = os;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public static void deserializing(ByteArrayOutputStream os) {
        try {
            byte[] bArray = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(bArray);
            ObjectInputStream ois = new ObjectInputStream(is);
            Collection collection = (Collection) ois.readObject();
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\user\\IdeaProjects\\epam JF\\JF4\\src\\main\\java\\task4\\result.txt"));
            out.write(collection.toString());
            out.close();
            is.close();
            ois.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("habensky");
        list1.add("serebrennikov");
        map.put("method", list1);
        Collection collection = new Collection(map);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("mcconaughey");
        collection.addFilm("true detective", list2);
        ByteArrayOutputStream os = serializing(collection);
        os.reset();
        collection.addActor("true detective", "farrell");
        collection.removeActor("method", "serebrennikov");
        collection.addFilm("mud", list2);
        os = serializing(collection);
        deserializing(os);
    }
}
