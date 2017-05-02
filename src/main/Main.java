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
		//executeIris();
		executeMovie();
	}
	
	/**
	 * Execute knn for iris.arff
	 */
	public static void executeIris(){
		Iris irisTest = new Iris(5.1, 2.6, 1.1, 0.3);
		HashMap<Iris, Double> mapIris = new HashMap<Iris, Double>();
		HashMap<String, Integer> resultat = new HashMap<String, Integer>();
		
		String chaine = loadFile("iris.arff");
		ArrayList<Iris> listeIris = madeIrisObject(chaine);
		
		mapIris = genererCalculDistanceIris(irisTest, listeIris);
		mapIris = triAvecValeurIris(mapIris);
		genererResultatIris(mapIris, resultat);
		System.out.println(predictionIris(resultat));
	}
	
	/**
	 * Execute knn for movie.arff
	 */
	public static void executeMovie(){
		Movie movieTest = new Movie(null, null, 0, 0, 0, 0, null, 0, 0, null, null, null, 0, 0, null, 0, null, null, 0, null, null, null, 0, null, 0, 0, 0);
		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();
		HashMap<Double, Integer> resultat_imdb_score = new HashMap<Double, Integer>();
		HashMap<Double, Integer> resultat_ratio_rentabilite = new HashMap<Double, Integer>();
		
		String chaine = loadFile("movie.arff");
		ArrayList<Movie> listeMovie = madeMovieObject(chaine);
		
		mapMovie = genererCalculDistanceMovieIMDB(movieTest, listeMovie);
		mapMovie = triAvecValeurMovie(mapMovie);
		genererResultatIMDB(mapMovie, resultat_imdb_score);
		System.out.println(predictionIMDB(resultat_imdb_score));
		
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
	 * Construct all the movie values
	 * @param chaine
	 * @return
	 */
	public static ArrayList<Movie> madeMovieObject(String chaine){
		ArrayList<Movie> result = new ArrayList<Movie>();
		ArrayList<Object> contenuLigne = new ArrayList<Object>();
		try {
			InputStream ips = new ByteArrayInputStream(chaine.getBytes(StandardCharsets.UTF_8));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if(ligne.length()>0){
					//contenuLigne.add(ligne.substring(0, ligne.indexOf(',')));
					
					
					
					result.add(new Movie(ligne, ligne, 0, 0, 0, 0, ligne, 0, 0, ligne, ligne, ligne, 0, 0, ligne, 0, ligne, ligne, 0, ligne, ligne, ligne, 0, null, 0, 0, 0, 0, 0));
				}
				System.out.println("ligne : " + ligne);
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
	public static HashMap<Iris, Double> triAvecValeurIris( HashMap<Iris, Double> map ){
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

	public static HashMap<Movie, Double> triAvecValeurMovie( HashMap<Movie, Double> map ){
		List<Entry<Movie, Double>> list =
				new LinkedList<Map.Entry<Movie, Double>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<Movie, Double>>(){
			public int compare( Map.Entry<Movie, Double> o1, Map.Entry<Movie, Double> o2 ){
				return (o1.getValue()).compareTo( o2.getValue());
			}
		});
		HashMap<Movie, Double> map_apres = new LinkedHashMap<Movie, Double>();
		for(Map.Entry<Movie, Double> entry : list)
			map_apres.put( entry.getKey(), entry.getValue() );
		return map_apres;
	}
	
	public static HashMap<Iris, Double> genererCalculDistanceIris(Iris irisTest, ArrayList<Iris> listeIris){
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
	
	public static HashMap<Movie, Double> genererCalculDistanceMovieIMDB(Movie movieTest, ArrayList<Movie> listeMovie){
		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();

		for (int i = 0; i < listeMovie.size(); i++) {

			double distanceActor_1_facebook_likes = (movieTest.getActor_1_facebook_likes() - listeMovie
					.get(i).getActor_1_facebook_likes())
					* (movieTest.getActor_1_facebook_likes() - listeMovie.get(i)
							.getActor_1_facebook_likes());
			double distanceActor_2_facebook_likes = (movieTest.getActor_2_facebook_likes() - listeMovie
					.get(i).getActor_2_facebook_likes())
					* (movieTest.getActor_2_facebook_likes() - listeMovie.get(i)
							.getActor_2_facebook_likes());
			double distanceActor_3_facebook_likes = (movieTest.getActor_3_facebook_likes() - listeMovie
					.get(i).getActor_3_facebook_likes())
					* (movieTest.getActor_3_facebook_likes() - listeMovie.get(i)
							.getActor_3_facebook_likes());
			double distanceMovie_facebook_likes = (movieTest.getMovie_facebook_likes() - listeMovie
					.get(i).getMovie_facebook_likes())
					* (movieTest.getMovie_facebook_likes() - listeMovie.get(i)
							.getMovie_facebook_likes());
			double distanceBudget = (movieTest.getBudget() - listeMovie
					.get(i).getBudget())
					* (movieTest.getBudget() - listeMovie.get(i)
							.getBudget());
			
			double distanceCountry=1;
			if(movieTest.getCountry().equals("USA")){
				distanceCountry=0;
			}
			/*
			 * CF conclusion sur les acteurs de l'imdb_score
			 */
			double distance = Math
					.sqrt((distanceActor_1_facebook_likes + distanceActor_2_facebook_likes
							+ distanceActor_3_facebook_likes + distanceMovie_facebook_likes + 
							distanceBudget + distanceCountry));
			mapMovie.put(listeMovie.get(i), distance);

		}
		return mapMovie;
	}

	public static void genererResultatIris(HashMap<Iris, Double> mapIris, HashMap<String, Integer> resultat){
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
	
	public static void genererResultatIMDB(HashMap<Movie, Double> mapMovie, HashMap<Double, Integer> resultat){
		int cpt = 0;

		for (Entry<Movie, Double> entry : mapMovie.entrySet()) {

			if (cpt < 10) {
				Movie cle = entry.getKey();
				if (resultat.containsKey(cle.getImdb_score())){
					int repetition = resultat.get(cle.getImdb_score());
					resultat.put(cle.getImdb_score(), ++repetition);
				}else{
					resultat.put(cle.getImdb_score(), 1);
				}
				cpt++;
			}
		}
	}

	public static String predictionIris(HashMap<String, Integer> resultat){
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
	
	public static String predictionIMDB(HashMap<Double, Integer> resultat){
		int max = 0;
		Double prediction = 0.0;

		for (Entry<Double, Integer> entry2 : resultat.entrySet()) {
			Double cle2 = entry2.getKey();
			int res = entry2.getValue();

			if (res > max){
				max = res;
				prediction = cle2;
			}
		}
		return "La prédiction est que le score imdb sera : " + prediction;
	}  
}