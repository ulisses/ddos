package pack;

import java.io.*;
import java.net.*;

public class JavaGetUrl extends Thread {
    @Override
   public void run () {
      URL u;
      InputStream is = null;
      DataInputStream dis;
      String s;

      try {
         u = new URL("http://www.moodys.com/");
         is = u.openStream();         // throws an IOException
         //dis = new DataInputStream(new BufferedInputStream(is));
         /*
         while ((s = dis.readLine()) != null) {
            System.out.println(s);
         }
         */
      } catch (IOException ioe) {
      } finally {
      try {
           is.close();
      } catch (IOException ioe) { }
      }
   }
}
