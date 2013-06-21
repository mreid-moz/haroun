import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class Files {

   public static byte[] readFileAsBytes(String filename) {
      return readFileAsString(filename).getBytes();
   }

   public static String readFileAsString(String filename) {
      String result = null;
      try {
         BufferedReader reader = new BufferedReader(new FileReader(filename));
         String line = null;
         StringBuilder stringBuilder = new StringBuilder();
         String ls = System.getProperty("line.separator");

         while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
         }

         result = stringBuilder.toString();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }
}
