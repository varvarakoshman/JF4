package task4;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Collection collection = new Collection();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("habensky");
        list1.add("serebryakov");
        collection.addFilm("method", list1);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("mcconaughey");
        collection.addFilm("true detective", list2);

        @Cleanup ByteArrayOutputStream os = SerializingUtilClass.serializing(collection);
        collection.addActor("true detective", "farrell");
        collection.removeActor("method", "serebryakov");
        collection.addFilm("mud", list2);
        os.reset();
        os = SerializingUtilClass.serializing(collection);
        SerializingUtilClass.deserializing(os);
    }
}
