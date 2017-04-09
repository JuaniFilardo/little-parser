/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim_tp2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author filardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File("datacount.txt");
        
        ArrayList<String> al = new ArrayList<>();
        String prefix = "data-count=\"";
        String suffix = "\"";
        
        al = getValuesAfterString(file,prefix,suffix);
        
        System.out.println(al.toString());
        System.out.println("Number of values: " + al.size());
    }

    /**
     *
     * @param file The file to be read
     * @param prefix The string after which the method starts reading
     * @param suffix The string until the method will read
     *
     * @return An ArrayList with all the strings that exist between prefixes and
     * suffixes
     *
     * Be aware: this only works for the first prefix/suffix set per line. If
     * you want to make sure to get all the values, then make sure there are no
     * more that one per line. Also, this won't work if the prefix and suffix
     * are on different lines. Sorry :(
     */
    private static ArrayList<String> getValuesAfterString(File file, String prefix, String suffix) {

        /*
        Acá estaría usando un prefix "data-count="" (sic)
        y un suffix """ (sic) que de antemano sé que existen y que están en
        la misma línea
         */
        ArrayList<String> al = new ArrayList<>();

        try (Scanner sca = new Scanner(file)) {

            String line;

            while (sca.hasNext()) {
                // Get the whole line
                line = sca.nextLine();
                // Look for the prefix and save its index
                int prefixIndex = line.indexOf(prefix);

                // If there's a match
                if (prefixIndex >= 0) {

                    // Important: here I drop the beginning of the line
                    // (INCLUDING the prefix)
                    // and I'm left with the rest
                    String cutLine = line.substring(prefixIndex + prefix.length());

                    // Beware: That means that prefixIndex no longer
                    // represents the index for prefix. To make things clearer,
                    // and avoid mistakes, let's just set it to zero, ok?
                    prefixIndex = 0;

                    // Look for the suffix
                    int suffixIndex = cutLine.indexOf(suffix);
                    
                    // If there's a match
                    if (suffixIndex >= 0) {

                        try { // let's catch some errors

                            // Let's get the value we were looking for 
                            // This is why I set prefix to zero, remember?
                            String value = cutLine.substring(prefixIndex, suffixIndex);

                            // And we can finally set the value into the ArrayList     
                            al.add(value);

                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return al;
    }

}
