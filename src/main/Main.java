package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
 
public class Main {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String chaine = loadFile("iris.arff");
        ArrayList<Iris> samples = madeIrisObject(chaine);
        knn(samples);
    }
    
    /**
     * Convert the input file as an Object
     * @param fichier
     */
    public static String loadFile(String fichier){
    	String chaine = "";
        boolean debut = false;
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.equals("@DATA")){
                    debut = true;
                }
                if(debut){
                    ligne = ligne.replaceAll(".*@DATA", "");
                    ligne = ligne.replaceAll("%", "");
                    if (ligne.length() > 0){
                        chaine += ligne + "\n";                    
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chaine;
    }
    
    /**
     * Construct all the iris values
     * @param chaine
     * @return
     */
    public static ArrayList<Iris> madeIrisObject(String chaine){
    	return null;
    }
    
    /**
     * Algo of KNN
     * @param chaine
     */
    public static void knn(ArrayList<Iris> samples){
    	for(Iris s : samples){
    		System.out.println(s);
    	}
    }
 
}