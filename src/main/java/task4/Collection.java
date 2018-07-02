package task4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@ToString
@NoArgsConstructor
public class Collection implements Serializable {
    @Getter
    private HashMap<String, ArrayList<String>> map = new HashMap<>();

    public void addActor(String film, String actor) {
        if (map.containsKey(film)) {
            ArrayList<String> list = map.get(film);
            list.add(actor);
            map.put(film, list);
        }
    }

    public void addFilm(String film, ArrayList<String> actors) {
        map.put(film, actors);
    }

    public void removeActor(String film, String actor) {
        if (map.containsKey(film)) {
            ArrayList<String> list = map.get(film);
            list.remove(actor);
            map.put(film, list);
        }
    }

    public void removeFilm(String film) {
        map.remove(film);
    }

    public ByteArrayOutputStream serializing(Collection collection) {
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

    public void deserializing(ByteArrayOutputStream os) {
        try {
            byte[] bArray = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(bArray);
            ObjectInputStream ois = new ObjectInputStream(is);
            Collection collection = (Collection) ois.readObject();
            BufferedWriter out = new BufferedWriter(new FileWriter("Files\\task4\\result.txt"));
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
}
