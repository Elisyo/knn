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

	private static int minActor1_facebook_likes = 0;
	private static int maxActor1_facebook_likes = 0;
	private static int minActor2_facebook_likes = 0;
	private static int maxActor2_facebook_likes = 0;
	private static int minActor3_facebook_likes = 0;
	private static int maxActor3_facebook_likes = 0;
	private static int minBudget = 0;
	private static int maxBudget = 0;
	private static int minDuration = 0;
	private static int maxDuration = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeMovie();
	}

	/**
	 * Execute knn for movie.arff
	 */
	public static void executeMovie() {

		Movie movieTest = new Movie(
				"Color",
				"Tim Burton",
				451,
				108,
				13000,
				11000,
				"Alan Rickman",
				40000,
				334185206,
				"Adventure|Family|Fantasy",
				"Johnny Depp",
				"Alice in Wonderland",
				306336,
				79957,
				"Anne Hathaway",
				0,
				"alice in wonderland|mistaking reality for dream|queen|shrinking|shrinking potion",
				"http://www.imdb.com/title/tt1014759/?ref_=fn_tt_tt_13", 736,
				"English", "USA", "PG", 200000000, 2010, 25000, 6.5, 1.85,
				24000, 1.67092603);

		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();
		HashMap<String, Integer> resultat_imdb_score = new HashMap<String, Integer>();
		HashMap<String, Integer> resultat_ratio_rentabilite = new HashMap<String, Integer>();

		String chaine = loadFile("movie.arff");
		ArrayList<Movie> listeMovie = madeMovieObject(chaine);
		//System.out.println(listeMovie.size());
		mapMovie = genererCalculDistanceMovie(movieTest, listeMovie);
		mapMovie = triAvecValeurMovie(mapMovie);
		resultat_imdb_score = genererResultatIMDB(mapMovie, resultat_imdb_score);
		resultat_ratio_rentabilite = genererResultatRR(mapMovie,resultat_ratio_rentabilite);
		System.out.println("Pour notre 'MovieTest', ses prédictions sont :");
		System.out.println("- Son score imdb sera : " + predictionIMDB(resultat_imdb_score));
		System.out.println("- Son ratio de rentabilite sera : "+ predictionRR(resultat_ratio_rentabilite));
		measures(listeMovie);
		/*
		System.out.println("min et max");
		System.out.println(minActor1_facebook_likes + ", " + maxActor1_facebook_likes);
		System.out.println(minActor2_facebook_likes + ", " + maxActor2_facebook_likes);
		System.out.println(minActor3_facebook_likes + ", " + maxActor3_facebook_likes);
		System.out.println(minBudget + ", " + maxBudget);
		System.out.println(minDuration + ", " + maxDuration);*/
	}

	/**
	 * Convert the input file as an Object
	 * 
	 * @param fichier
	 */
	public static String loadFile(String fichier) {
		String chaine = "";
		boolean debut = false;
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (ligne.equals("@data")) {
					debut = true;
				}
				if (debut) {
					ligne = ligne.replaceAll(".*@data", "");
					ligne = ligne.replaceAll("%", "");
					ligne = ligne.replaceAll(" ' ", "");
					ligne = ligne.replaceAll(" '", "");
					ligne = ligne.replaceAll("' ", "");
					ligne = ligne.replaceAll("'", "");
					if (ligne.length() > 0) {
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
	 * Construct all the movie values
	 * 
	 * @param chaine
	 * @return
	 */
	public static ArrayList<Movie> madeMovieObject(String chaine) {
		ArrayList<Movie> result = new ArrayList<Movie>();
		ArrayList<String> contenuLigne = new ArrayList<String>();
		try {
			InputStream ips = new ByteArrayInputStream(
					chaine.getBytes(StandardCharsets.UTF_8));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (ligne.length() > 0) {
					contenuLigne.clear();
					for (int i = 0; i < 30; i++) {
						if (ligne.substring(0, ligne.indexOf(','))
								.contains("?")
								|| ligne.substring(0, ligne.indexOf(','))
										.equals("")) {
							contenuLigne.add("?");
						} else {
							contenuLigne.add(ligne.substring(0,
									ligne.indexOf(',')));
						}
						ligne = ligne.substring(ligne.indexOf(',') + 1);
					}
					contenuLigne.add(ligne);

					try {
						Movie movie = new Movie();
						movie.setActor_1_facebook_likes(Integer.parseInt(contenuLigne.get(7)));
						if(minActor1_facebook_likes==0 && maxActor1_facebook_likes==0){
							minActor1_facebook_likes=Integer.parseInt(contenuLigne.get(7));
							maxActor1_facebook_likes=Integer.parseInt(contenuLigne.get(7));
						}else if(Integer.parseInt(contenuLigne.get(7))<minActor1_facebook_likes){
							minActor1_facebook_likes=Integer.parseInt(contenuLigne.get(7));
						}else if(Integer.parseInt(contenuLigne.get(7))>maxActor1_facebook_likes){
							maxActor1_facebook_likes=Integer.parseInt(contenuLigne.get(7));
						}
						
						movie.setActor_2_facebook_likes(Integer.parseInt(contenuLigne.get(24)));
						if(minActor2_facebook_likes==0 && maxActor2_facebook_likes==0){
							minActor2_facebook_likes=Integer.parseInt(contenuLigne.get(24));
							maxActor2_facebook_likes=Integer.parseInt(contenuLigne.get(24));
						}else if(Integer.parseInt(contenuLigne.get(24))<minActor2_facebook_likes){
							minActor2_facebook_likes=Integer.parseInt(contenuLigne.get(24));
						}else if(Integer.parseInt(contenuLigne.get(24))>maxActor2_facebook_likes){
							maxActor2_facebook_likes=Integer.parseInt(contenuLigne.get(24));
						}
						
						movie.setActor_3_facebook_likes(Integer.parseInt(contenuLigne.get(5)));
						if(minActor3_facebook_likes==0 && maxActor3_facebook_likes==0){
							minActor3_facebook_likes=Integer.parseInt(contenuLigne.get(5));
							maxActor3_facebook_likes=Integer.parseInt(contenuLigne.get(5));
						}else if(Integer.parseInt(contenuLigne.get(5))<minActor3_facebook_likes){
							minActor3_facebook_likes=Integer.parseInt(contenuLigne.get(5));
						}else if(Integer.parseInt(contenuLigne.get(5))>maxActor3_facebook_likes){
							maxActor3_facebook_likes=Integer.parseInt(contenuLigne.get(5));
						}
						
						movie.setBudget(Integer.parseInt(contenuLigne.get(22)));
						if(minBudget==0 && maxBudget==0){
							minBudget=Integer.parseInt(contenuLigne.get(22));
							maxBudget=Integer.parseInt(contenuLigne.get(22));
						}else if(Integer.parseInt(contenuLigne.get(22))<minBudget){
							minBudget=Integer.parseInt(contenuLigne.get(22));
						}else if(Integer.parseInt(contenuLigne.get(22))>maxBudget){
							maxBudget=Integer.parseInt(contenuLigne.get(22));
						}
						
						movie.setRatio_imdb(contenuLigne.get(30));
						movie.setStatut_rentabilite(contenuLigne.get(29));
						
						movie.setDuration(Integer.parseInt(contenuLigne.get(3)));
						if(minDuration==0 && maxDuration==0){
							minDuration=Integer.parseInt(contenuLigne.get(3));
							maxDuration=Integer.parseInt(contenuLigne.get(3));
						}else if(Integer.parseInt(contenuLigne.get(3))<minDuration){
							minDuration=Integer.parseInt(contenuLigne.get(3));
						}else if(Integer.parseInt(contenuLigne.get(3))>maxDuration){
							maxDuration=Integer.parseInt(contenuLigne.get(3));
						}
						
						result.add(movie);
					} catch (Exception e) {
					}
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public static HashMap<Movie, Double> triAvecValeurMovie(
			HashMap<Movie, Double> map) {
		List<Entry<Movie, Double>> list = new LinkedList<Map.Entry<Movie, Double>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Movie, Double>>() {
			public int compare(Map.Entry<Movie, Double> o1,
					Map.Entry<Movie, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<Movie, Double> map_apres = new LinkedHashMap<Movie, Double>();
		for (Map.Entry<Movie, Double> entry : list)
			map_apres.put(entry.getKey(), entry.getValue());
		return map_apres;
	}

	public static HashMap<Movie, Double> genererCalculDistanceMovie(
			Movie movieTest, ArrayList<Movie> listeMovie) {
		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();

		for (int i = 0; i < listeMovie.size(); i++) {
			double distanceActor_1_facebook_likes = 
					((movieTest.getActor_1_facebook_likes() - listeMovie.get(i).getActor_1_facebook_likes())/(maxActor1_facebook_likes - minActor1_facebook_likes))*
					((movieTest.getActor_1_facebook_likes() - listeMovie.get(i).getActor_1_facebook_likes())/(maxActor1_facebook_likes - minActor1_facebook_likes));
			
			double distanceActor_2_facebook_likes = 
					((movieTest.getActor_2_facebook_likes() - listeMovie.get(i).getActor_2_facebook_likes())/(maxActor2_facebook_likes - minActor2_facebook_likes))*
					((movieTest.getActor_2_facebook_likes() - listeMovie.get(i).getActor_2_facebook_likes())/(maxActor2_facebook_likes - minActor2_facebook_likes));
			
			double distanceActor_3_facebook_likes = 
					((movieTest.getActor_3_facebook_likes() - listeMovie.get(i).getActor_3_facebook_likes())/(maxActor3_facebook_likes - minActor3_facebook_likes))*
					((movieTest.getActor_3_facebook_likes() - listeMovie.get(i).getActor_3_facebook_likes())/(maxActor3_facebook_likes - minActor3_facebook_likes));
			
			double distanceBudget = 
					((movieTest.getBudget() - listeMovie.get(i).getBudget())/(maxBudget - minBudget))*
					((movieTest.getBudget() - listeMovie.get(i).getBudget())/(maxBudget - minBudget));
			
			double distanceDuration = 
					((movieTest.getDuration() - listeMovie.get(i).getDuration())/(maxDuration - minDuration))*
					((movieTest.getDuration() - listeMovie.get(i).getDuration())/(maxDuration - minDuration));

			/*
			 * CF conclusion sur les acteurs de l'imdb_score
			 */
			double distance = Math
					.sqrt((distanceActor_1_facebook_likes
							+ distanceActor_2_facebook_likes
							+ distanceActor_3_facebook_likes + distanceBudget + distanceDuration));
			mapMovie.put(listeMovie.get(i), distance);
		}
		return mapMovie;
	}

	public static HashMap<String, Integer> genererResultatIMDB(
			HashMap<Movie, Double> mapMovie,
			HashMap<String, Integer> resultat_imdb_score) {
		int cpt = 0;
		int repetition = 0;
		for (Entry<Movie, Double> entry : mapMovie.entrySet()) {
			if (cpt < 10) {
				Movie cle = entry.getKey();
				if(cle.getRatio_imdb()!=null){
					if(resultat_imdb_score.get(cle.getRatio_imdb())==null){
						resultat_imdb_score.put(cle.getRatio_imdb(), 1);
					}else{
						repetition = resultat_imdb_score.get(cle.getRatio_imdb());
						resultat_imdb_score.put(cle.getRatio_imdb(), ++repetition);
					}
				}
				cpt++;
			}
		}
		return resultat_imdb_score;
	}

	public static HashMap<String, Integer> genererResultatRR(
			HashMap<Movie, Double> mapMovie,
			HashMap<String, Integer> resultat_ratio_rentabilite) {
		int cpt = 0;
		int repetition = 0;
		for (Entry<Movie, Double> entry : mapMovie.entrySet()) {
			if (cpt < 10) {
				Movie cle = entry.getKey();
				if(cle.getStatut_rentabilite()!=null){
					if(resultat_ratio_rentabilite.get(cle.getStatut_rentabilite())==null){
						resultat_ratio_rentabilite.put(cle.getStatut_rentabilite(), 1);
					}else{
						repetition = resultat_ratio_rentabilite.get(cle.getStatut_rentabilite());
						resultat_ratio_rentabilite.put(cle.getStatut_rentabilite(),++repetition);
					}
				}
				cpt++;
			}
		}
		return resultat_ratio_rentabilite;
	}

	public static String predictionIMDB(
			HashMap<String, Integer> resultat_imdb_score) {
		int max = 0;
		String prediction = "";

		for (Entry<String, Integer> entry2 : resultat_imdb_score.entrySet()) {
			String cle2 = entry2.getKey();
			int res = entry2.getValue();
			if (res > max) {
				max = res;
				prediction = cle2;
			}
		}
		//System.out.println("La prédiction est que le score imdb sera : " + prediction);
		return prediction;
	}

	public static String predictionRR(
			HashMap<String, Integer> resultat_ratio_rentabilite) {
		int max = 0;
		String prediction = "";

		for (Entry<String, Integer> entry2 : resultat_ratio_rentabilite
				.entrySet()) {
			String cle2 = entry2.getKey();
			int res = entry2.getValue();
			if (res > max) {
				max = res;
				prediction = cle2;
			}
		}
		//System.out.println("La prédiction est que le ratio de rentabilite sera : "+ prediction);
		return prediction;
	}
	
	public static void measures(ArrayList<Movie> listeMovie){
		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();
		HashMap<String, Integer> resultat_imdb_score = new HashMap<String, Integer>();
		String prediction_imdb_score ="";
		HashMap<String, Integer> resultat_ratio_rentabilite = new HashMap<String, Integer>();
		String prediction_statut_RR ="";

		int tpRate_imdb=0;
		int fpRate_imdb = 0;
		double tauxTP_imdb = 0.0;
		double tauxFP_imdb = 0.0;
		int tauxEP_imdb = 0;
		int tauxEF_imdb = 0;
		double tauxErreur_imdb = 0.0;
		double precision_imdb = 0.0;
		
		int tpRate_rr=0;
		int fpRate_rr = 0;
		double tauxTP_rr = 0.0;
		double tauxFP_rr = 0.0;
		int tauxEP_rr = 0;
		int tauxEF_rr = 0;
		double tauxErreur_rr = 0.0;
		double precision_rr = 0.0;
		
		for(int i=0;i<listeMovie.size();i++){
			mapMovie = genererCalculDistanceMovie(listeMovie.get(i), listeMovie);
			mapMovie = triAvecValeurMovie(mapMovie);
			resultat_imdb_score = genererResultatIMDB(mapMovie, resultat_imdb_score);
			prediction_imdb_score=predictionIMDB(resultat_imdb_score);
			
			resultat_ratio_rentabilite = genererResultatRR(mapMovie,resultat_ratio_rentabilite);
			prediction_statut_RR=predictionRR(resultat_ratio_rentabilite);
			
			if((!prediction_imdb_score.equals("mauvais") && listeMovie.get(i).equals("mauvais")) || 
					(!prediction_imdb_score.equals("passable") && listeMovie.get(i).equals("passable")) ||
					(!prediction_imdb_score.equals("moyen") && listeMovie.get(i).equals("moyen")) ||
					(!prediction_imdb_score.equals("divertissant") && listeMovie.get(i).equals("divertissant")) ||
					(!prediction_imdb_score.equals("a_voir") && listeMovie.get(i).equals("a_voir"))){
				fpRate_imdb=fpRate_imdb+1;
			}/*
			if(predictionIMDB(resultat_imdb_score).equals()){
				
			}*/

			if(prediction_imdb_score.equals(listeMovie.get(i).getRatio_imdb())){
			//if(resultat_imdb_score.containsKey(listeMovie.get(i).getRatio_imdb())){
				tauxEP_imdb=tauxEP_imdb+1;
				tpRate_imdb=tpRate_imdb+1;
			}else{
				tauxEF_imdb=tauxEF_imdb+1;
			}

			// prevu comme _______ mais ne sont pas
			if((!prediction_statut_RR.equals("pas_rentable") && listeMovie.get(i).equals("pas_rentable")) || 
					(!prediction_statut_RR.equals("peu_rentable") && listeMovie.get(i).equals("peu_rentable")) ||
					(!prediction_statut_RR.equals("rentable") && listeMovie.get(i).equals("rentable")) ||
					(!prediction_statut_RR.equals("bien_rentable") && listeMovie.get(i).equals("bien_rentable")) ||
					(!prediction_statut_RR.equals("tres_rentable") && listeMovie.get(i).equals("tres_rentable"))){
				fpRate_rr=fpRate_rr+1;
			}
			
			if(prediction_statut_RR.equals(listeMovie.get(i).getStatut_rentabilite())){
			//if(resultat_ratio_rentabilite.containsKey(listeMovie.get(i).getStatut_rentabilite())){
				tauxEP_rr=tauxEP_rr+1;
				tpRate_rr=tpRate_rr+1;
			}else{
				tauxEF_rr=tauxEF_rr+1;
			}
		}
		//System.out.println(tauxEP +" : "+tauxEF);
		System.out.println("-------Mesures : Score IMDB-------");
		// taux à 93,94% car on a 5 classes
		tauxErreur_imdb = ((double)tauxEF_imdb/((double)tauxEP_imdb+(double)tauxEF_imdb))*100;
		System.out.printf("Taux d'erreur : %.2f",tauxErreur_imdb);System.out.println("% ("+tauxEF_imdb+"/"+listeMovie.size()+")");
		tauxTP_imdb = ((double)tpRate_imdb/(double)listeMovie.size())*100;
		System.out.printf("Taux de vrais positifs : %.2f" , tauxTP_imdb);System.out.println("% ("+tpRate_imdb+"/"+listeMovie.size()+")");
		tauxFP_imdb = (double)fpRate_imdb/(double)listeMovie.size();
		System.out.println((double)fpRate_imdb+" : "+(double)listeMovie.size());
		System.out.println("Taux de faux positifs : " + tauxFP_imdb);
		precision_imdb = (double)tpRate_imdb/((double)tpRate_imdb+(double)fpRate_imdb);
		System.out.println("Precision : "+ precision_imdb);
		
		System.out.println("-------Mesures : Ratio rentabilite-------");
		// taux à 79,33% car on a 5 classes
		tauxErreur_rr = ((double)tauxEF_rr/((double)tauxEP_rr+(double)tauxEF_rr))*100;
		System.out.printf("Taux d'erreur : %.2f",tauxErreur_rr);System.out.println("% ("+tauxEF_rr+"/"+listeMovie.size()+")");
		tauxTP_rr = ((double)tpRate_rr/(double)listeMovie.size())*100;
		System.out.printf("Taux de vrais positifs : %.2f" , tauxTP_rr);System.out.println("% ("+tpRate_rr+"/"+listeMovie.size()+")");
		tauxFP_rr = (double)fpRate_rr/(double)listeMovie.size();
		System.out.println((double)fpRate_rr+" : "+(double)listeMovie.size());
		System.out.println("Taux de faux positifs : " + tauxFP_rr);
		precision_rr = (double)tpRate_rr/((double)tpRate_rr+(double)fpRate_rr);
		System.out.println("Precision : "+ precision_rr + " ("+tpRate_rr+"/("+tpRate_rr+"+"+fpRate_rr+"))");
	}
}