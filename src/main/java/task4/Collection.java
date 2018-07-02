package task4;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
@ToString
public class Collection implements Serializable {
    private HashMap<String, ArrayList<String>> map;

    public void addActor(String film, String actor) {
        ArrayList<String> list = this.map.get(film);
        if (list != null) {
            list.add(actor);
            this.map.put(film, list);
        }
    }

    public void addFilm(String film, ArrayList<String> actors) {
        this.map.put(film, actors);
    }

    public void removeActor(String film, String actor) {
        ArrayList<String> list = this.map.get(film);
        if (list != null) {
            list.remove(actor);
            this.map.put(film, list);
        }
    }

    public void removeFilm(String film) {
        this.map.remove(film);
    }
}
