package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Main {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String chaine = loadFile("iris.arff");
        knn(chaine);
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
     * Algo of KNN
     * @param chaine
     */
    public static void knn(String chaine){
    	System.out.println(chaine);
    }
 
}