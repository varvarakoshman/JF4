package task4;

import java.io.*;

public class SerializingUtilClass {
    public static ByteArrayOutputStream serializing(Collection collection) {
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
