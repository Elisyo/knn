package main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Iris irisTest = new Iris(5.1, 2.6, 1.1, 0.3);
		HashMap<Iris, Double> mapIris = new HashMap<Iris, Double>();
		HashMap<String, Integer> resultat = new HashMap<String, Integer>();
		
		String chaine = loadFile("iris.arff");
		ArrayList<Iris> listeIris = madeIrisObject(chaine);
		
		mapIris = genererCalculDistance(irisTest, listeIris);
		mapIris = triAvecValeur(mapIris);
		genererResultat(mapIris, resultat);
		System.out.println(prediction(resultat));
		
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
		ArrayList<Iris> result = new ArrayList<Iris>();
		try {
			InputStream ips = new ByteArrayInputStream(chaine.getBytes(StandardCharsets.UTF_8));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if(ligne.length()>0){
					result.add(new Iris(Double.parseDouble(ligne.substring(0,3)),
							Double.parseDouble(ligne.substring(4,7)),
							Double.parseDouble(ligne.substring(8,11)),
							Double.parseDouble(ligne.substring(12,15)),
							ligne.substring(16)));
				}
				//System.out.println(ligne);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static HashMap<Iris, Double> triAvecValeur( HashMap<Iris, Double> map ){
		List<Entry<Iris, Double>> list =
				new LinkedList<Map.Entry<Iris, Double>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<Iris, Double>>(){
			public int compare( Map.Entry<Iris, Double> o1, Map.Entry<Iris, Double> o2 ){
				return (o1.getValue()).compareTo( o2.getValue());
			}
		});
		HashMap<Iris, Double> map_apres = new LinkedHashMap<Iris, Double>();
		for(Map.Entry<Iris, Double> entry : list)
			map_apres.put( entry.getKey(), entry.getValue() );
		return map_apres;
	}

	public static HashMap<Iris, Double> genererCalculDistance(Iris irisTest, ArrayList<Iris> listeIris){
		HashMap<Iris, Double> mapIris = new HashMap<Iris, Double>();

		for (int i = 0; i < listeIris.size(); i++) {

			double distanceSepallength = (irisTest.getSepallength() - listeIris
					.get(i).getSepallength())
					* (irisTest.getSepallength() - listeIris.get(i)
							.getSepallength());
			double distanceSepalwidth = (irisTest.getSepalwidth() - listeIris
					.get(i).getSepalwidth())
					* (irisTest.getSepalwidth() - listeIris.get(i)
							.getSepalwidth());
			double distancePetallength = (irisTest.getPetallength() - listeIris
					.get(i).getPetallength())
					* (irisTest.getPetallength() - listeIris.get(i)
							.getPetallength());
			double distancePetalwidth = (irisTest.getSepalwidth() - listeIris
					.get(i).getSepalwidth())
					* (irisTest.getSepalwidth() - listeIris.get(i)
							.getSepalwidth());
			double distance = Math
					.sqrt((distanceSepallength + distanceSepalwidth
							+ distancePetallength + distancePetalwidth));
			mapIris.put(listeIris.get(i), distance);

		}
		return mapIris;
	}

	public static void genererResultat(HashMap<Iris, Double> mapIris, HashMap<String, Integer> resultat){
		int cpt = 0;

		for (Entry<Iris, Double> entry : mapIris.entrySet()) {

			if (cpt < 10) {
				Iris cle = entry.getKey();
				if (resultat.containsKey(cle.getGenre())){
					int repetition = resultat.get(cle.getGenre());
					resultat.put(cle.getGenre(), ++repetition);
				}else{
					resultat.put(cle.getGenre(), 1);
				}
				cpt++;
			}
		}
	}

	public static String prediction(HashMap<String, Integer> resultat){
		int max = 0;
		String prediction ="";

		for (Entry<String, Integer> entry2 : resultat.entrySet()) {
			String cle2 = entry2.getKey();
			int res = entry2.getValue();

			if (res > max){
				max = res;
				prediction = cle2;
			}
		}
		return "La prédiction est que l'iris sera de type: " + prediction;

	}   
}