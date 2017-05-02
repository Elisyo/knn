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
		executeMovie();
	}
	
	/**
	 * Execute knn for movie.arff
	 */
	public static void executeMovie() {

		Movie movieTest = new Movie("Color", "Tim Burton", 451, 108, 13000, 11000, "Alan Rickman", 40000, 334185206,
				"Adventure|Family|Fantasy", "Johnny Depp", "Alice in Wonderland", 306336, 79957, "Anne Hathaway", 0,
				"alice in wonderland|mistaking reality for dream|queen|shrinking|shrinking potion",
				"http://www.imdb.com/title/tt1014759/?ref_=fn_tt_tt_13", 736, "English", "USA", "PG", 200000000, 2010,
				25000, 6.5, 1.85, 24000, 1.67092603);

		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();
		HashMap<String, Integer> resultat_imdb_score = new HashMap<String, Integer>();
		HashMap<String, Integer> resultat_ratio_rentabilite = new HashMap<String, Integer>();

		String chaine = loadFile("movie.arff");
		ArrayList<Movie> listeMovie = madeMovieObject(chaine);
		System.out.println(listeMovie.size());
		mapMovie = genererCalculDistanceMovie(movieTest, listeMovie);
		mapMovie = triAvecValeurMovie(mapMovie);
		resultat_imdb_score = genererResultatIMDB(mapMovie, resultat_imdb_score);
		resultat_ratio_rentabilite = genererResultatRR(mapMovie, resultat_ratio_rentabilite);
		System.out.println(predictionIMDB(resultat_imdb_score));
		System.out.println(predictionRR(resultat_ratio_rentabilite));

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
			InputStream ips = new ByteArrayInputStream(chaine.getBytes(StandardCharsets.UTF_8));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (ligne.length() > 0) {
					contenuLigne.clear();
					for (int i = 0; i < 30; i++) {
						if (ligne.substring(0, ligne.indexOf(',')).contains("?")
								|| ligne.substring(0, ligne.indexOf(',')).equals("")) {
							contenuLigne.add("?");
						} else {
							contenuLigne.add(ligne.substring(0, ligne.indexOf(',')));
						}
						ligne = ligne.substring(ligne.indexOf(',') + 1);
					}
					contenuLigne.add(ligne);
					
					try {
						Movie movie = new Movie();
						movie.setActor_1_facebook_likes(Integer.parseInt(contenuLigne.get(7)));
						movie.setActor_2_facebook_likes(Integer.parseInt(contenuLigne.get(24)));
						movie.setActor_3_facebook_likes(Integer.parseInt(contenuLigne.get(5)));
						movie.setMovie_facebook_likes(Integer.parseInt(contenuLigne.get(27)));
						movie.setBudget(Integer.parseInt(contenuLigne.get(22)));
						movie.setCountry(contenuLigne.get(20));	
						movie.setRatio_imdb(contenuLigne.get(30));
						movie.setStatut_rentabilite(contenuLigne.get(29));
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



	public static HashMap<Movie, Double> triAvecValeurMovie(HashMap<Movie, Double> map) {
		List<Entry<Movie, Double>> list = new LinkedList<Map.Entry<Movie, Double>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Movie, Double>>() {
			public int compare(Map.Entry<Movie, Double> o1, Map.Entry<Movie, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<Movie, Double> map_apres = new LinkedHashMap<Movie, Double>();
		for (Map.Entry<Movie, Double> entry : list)
			map_apres.put(entry.getKey(), entry.getValue());
		return map_apres;
	}


	public static HashMap<Movie, Double> genererCalculDistanceMovie(Movie movieTest, ArrayList<Movie> listeMovie) {
		HashMap<Movie, Double> mapMovie = new HashMap<Movie, Double>();

		for (int i = 0; i < listeMovie.size(); i++) {
			double distanceActor_1_facebook_likes = (movieTest.getActor_1_facebook_likes()
					- listeMovie.get(i).getActor_1_facebook_likes())
					* (movieTest.getActor_1_facebook_likes() - listeMovie.get(i).getActor_1_facebook_likes());
			double distanceActor_2_facebook_likes = (movieTest.getActor_2_facebook_likes()
					- listeMovie.get(i).getActor_2_facebook_likes())
					* (movieTest.getActor_2_facebook_likes() - listeMovie.get(i).getActor_2_facebook_likes());
			double distanceActor_3_facebook_likes = (movieTest.getActor_3_facebook_likes()
					- listeMovie.get(i).getActor_3_facebook_likes())
					* (movieTest.getActor_3_facebook_likes() - listeMovie.get(i).getActor_3_facebook_likes());
			double distanceMovie_facebook_likes = (movieTest.getMovie_facebook_likes()
					- listeMovie.get(i).getMovie_facebook_likes())
					* (movieTest.getMovie_facebook_likes() - listeMovie.get(i).getMovie_facebook_likes());
			double distanceBudget = (movieTest.getBudget() - listeMovie.get(i).getBudget())
					* (movieTest.getBudget() - listeMovie.get(i).getBudget());

			double distanceCountry = 1;
			if (movieTest.getCountry().equals("USA")) {
				distanceCountry = 0;
			}
			/*
			 * CF conclusion sur les acteurs de l'imdb_score
			 */
			double distance = Math.sqrt(
					(distanceActor_1_facebook_likes + distanceActor_2_facebook_likes + distanceActor_3_facebook_likes
							+ distanceMovie_facebook_likes + distanceBudget + distanceCountry));
			// System.out.println(distance);
			mapMovie.put(listeMovie.get(i), distance);
		}
		return mapMovie;
	}


	public static HashMap<String, Integer> genererResultatIMDB(HashMap<Movie, Double> mapMovie,
			HashMap<String, Integer> resultat_imdb_score) {
		int cpt = 0;

		for (Entry<Movie, Double> entry : mapMovie.entrySet()) {
			if (cpt < 100) {
				Movie cle = entry.getKey();
				if (mapMovie.containsKey(cle.getRatio_imdb())) {
					int repetition = resultat_imdb_score.get(cle.getRatio_imdb());
					resultat_imdb_score.put(cle.getRatio_imdb(), ++repetition);
				} else {
					resultat_imdb_score.put(cle.getRatio_imdb(), 1);
				}
				cpt++;
			}
		}
		return resultat_imdb_score;
	}
	
	public static HashMap<String, Integer> genererResultatRR(HashMap<Movie, Double> mapMovie,
			HashMap<String, Integer> resultat_ratio_rentabilite) {
		int cpt = 0;

		for (Entry<Movie, Double> entry : mapMovie.entrySet()) {
			if (cpt < 100) {
				Movie cle = entry.getKey();
				if (mapMovie.containsKey(cle.getStatut_rentabilite())) {
					int repetition = resultat_ratio_rentabilite.get(cle.getStatut_rentabilite());
					resultat_ratio_rentabilite.put(cle.getStatut_rentabilite(), ++repetition);
				} else {
					resultat_ratio_rentabilite.put(cle.getStatut_rentabilite(), 1);
				}
				cpt++;
			}
		}
		return resultat_ratio_rentabilite;
	}

	public static String predictionIris(HashMap<String, Integer> resultat) {
		int max = 0;
		String prediction = "";

		for (Entry<String, Integer> entry2 : resultat.entrySet()) {
			String cle2 = entry2.getKey();
			int res = entry2.getValue();

			if (res > max) {
				max = res;
				prediction = cle2;
			}
		}
		return "La prédiction est que l'iris sera de type: " + prediction;
	}

	public static String predictionIMDB(HashMap<String, Integer> resultat_imdb_score) {
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
		return "La prédiction est que le score imdb sera : " + prediction;
	}
	
	public static String predictionRR(HashMap<String, Integer> resultat_ratio_rentabilite) {
		int max = 0;
		String prediction = "";

		for (Entry<String, Integer> entry2 : resultat_ratio_rentabilite.entrySet()) {
			String cle2 = entry2.getKey();
			int res = entry2.getValue();
			if (res > max) {
				max = res;
				prediction = cle2;
			}
		}
		return "La prédiction est que le ratio de rentabilite sera : " + prediction;
	}
}