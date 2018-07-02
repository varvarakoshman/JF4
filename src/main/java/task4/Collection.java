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

    public ArrayList<String> getActors(String film){
        return map.get(film);
    }
}
