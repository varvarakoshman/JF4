package task4;

import lombok.Cleanup;

import java.io.*;

public class SerializingUtilClass {
    public static ByteArrayOutputStream serializing(Collection collection) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
            @Cleanup ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(collection);
            result = os;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void deserializing(ByteArrayOutputStream os) {
        try {
            byte[] bArray = os.toByteArray();
            @Cleanup ByteArrayInputStream is = new ByteArrayInputStream(bArray);
            @Cleanup ObjectInputStream ois = new ObjectInputStream(is);
            Collection collection = (Collection) ois.readObject();
            @Cleanup BufferedWriter out = new BufferedWriter(new FileWriter("Files\\task4\\result.txt"));
            out.write(collection.toString());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
