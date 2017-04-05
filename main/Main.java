import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Main {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String chaine="";
        String fichier ="iris.arff";
       
        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                //System.out.println(ligne);
                if (ligne.length() != 0){
                    char start = ligne.charAt(0);
 
                    if ((int)start != 37 || (int) start != 64){
                        chaine+=ligne+"\n";
                    }
                }
               
            }
            System.out.println(chaine);
            br.close();
        }      
        catch (Exception e){
            System.out.println(e.toString());
        }
   
    }
 
}