package org.doblander.javareflectiontutorial;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


interface HelloMeth {

    public void hello();
}

class CA
        implements HelloMeth {

    public void hello() {
        System.out.println("hello CA");
    }
}

class CB
        implements HelloMeth {

    public void hello() {
        System.out.println("hello CB");
    }
}

class CC extends CA {

    @Override
    public void hello() {
        System.out.println("hello CC");
    }
}

class CD extends CB {

    public void hallo() {
        System.out.println("hallo CD");
    }
}

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        // for getting user input
        String buf = "";
        BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                            new DataInputStream(System.in)));

        System.out.println("Hello World!");

        try {
            Class c = Class.forName("org.doblander.javareflectiontutorial.App");
            System.out.println("test" + " " + c);

            c = Class.forName("org.doblander.javareflectiontutorial.TestItHard");
            System.out.println("test2" + " " + c);

            c = Class.forName("org.doblander.javareflectiontutorial.CC");
            System.out.println("test3" + " " + c);

            while (true) {
                try {
                    System.out.print("Klassenname oder ende eingeben: ");
                    buf = in.readLine();
                    if (buf.equals("ende")) {
                        break;
                    }
                    
                    //c = Class.forName(buf);
                    c = Class.forName("org.doblander.javareflectiontutorial." + buf);               
                            
                    Object o = c.newInstance();
                    ((HelloMeth) o).hello();
                    
                } catch (IOException e) {
                    System.out.println(e.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("Klasse nicht gefunden");
                } catch (ClassCastException e) {
                    System.out.println(e.toString());
                } catch (InstantiationException e) {
                    System.out.println(e.toString());
                } catch (IllegalAccessException e) {
                    System.out.println(e.toString());
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
