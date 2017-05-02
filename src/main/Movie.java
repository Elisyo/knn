package main;

public class Movie {
	String color;
	String director_name;
	int num_critic_for_reviews;
	int duration;
	int director_facebook_likes;
	int actor_3_facebook_likes;
	String actor_2_name;
	int actor_1_facebook_likes;
	double gross;
	String genres;
	String actor_1_name;
	String movie_title;
	int num_voted_users;
	int cast_total_facebook_likes;
	String actor_3_name;
	int facenumber_in_poster;
	String plot_keywords;
	String movie_imdb_link;
	int num_user_for_reviews;
	String language;
	String country;
	String content_rating;
	double budget;
	int title_year;
	int actor_2_facebook_likes;
	double imdb_score;
	double aspect_ratio;
	int movie_facebook_likes;
	double ratio_rentabilite;
	String statut_rentabilite;
	String ratio_imdb;

	Movie(String color, String director_name, int num_critic_for_reviews, int duration, int director_facebook_likes,
			int actor_3_facebook_likes, String actor_2_name, int actor_1_facebook_likes, double gross, String genres,
			String actor_1_name, String movie_title, int num_voted_users, int cast_total_facebook_likes,
			String actor_3_name, int facenumber_in_poster, String plot_keywords, String movie_imdb_link,
			int num_user_for_reviews, String language, String country, String content_rating, double budget,
			int title_year, int actor_2_facebook_likes, double imdb_score, double aspect_ratio,
			int movie_facebook_likes, double ratio_rentabilite) {
		this.color = color;
		this.director_name = director_name;
		this.num_critic_for_reviews = num_critic_for_reviews;
		this.duration = duration;
		this.director_facebook_likes = director_facebook_likes;
		this.actor_3_facebook_likes = actor_3_facebook_likes;
		this.actor_2_name = actor_2_name;
		this.actor_1_facebook_likes = actor_1_facebook_likes;
		this.gross = gross;
		this.genres = genres;
		this.actor_1_name = actor_1_name;
		this.movie_title = movie_title;
		this.num_voted_users = num_voted_users;
		this.cast_total_facebook_likes = cast_total_facebook_likes;
		this.actor_3_name = actor_3_name;
		this.facenumber_in_poster = facenumber_in_poster;
		this.plot_keywords = plot_keywords;
		this.movie_imdb_link = movie_imdb_link;
		this.num_user_for_reviews = num_user_for_reviews;
		this.language = language;
		this.country = country;
		this.content_rating = content_rating;
		this.budget = budget;
		this.title_year = title_year;
		this.actor_2_facebook_likes = actor_2_facebook_likes;
		this.imdb_score = imdb_score;
		this.aspect_ratio = aspect_ratio;
		this.movie_facebook_likes = movie_facebook_likes;
		this.ratio_rentabilite = ratio_rentabilite;
	}

	Movie(String color, String director_name, int num_critic_for_reviews, int duration, int director_facebook_likes,
			int actor_3_facebook_likes, String actor_2_name, int actor_1_facebook_likes, double gross, String genres,
			String actor_1_name, String movie_title, int num_voted_users, int cast_total_facebook_likes,
			String actor_3_name, int facenumber_in_poster, String plot_keywords, String movie_imdb_link,
			int num_user_for_reviews, String language, String country, String content_rating, double budget,
			int title_year, int actor_2_facebook_likes, double imdb_score, double aspect_ratio,
			int movie_facebook_likes, double ratio_rentabilite, String statut_rentabilite, String ratio_imdb) {
		this.color = color;
		this.director_name = director_name;
		this.num_critic_for_reviews = num_critic_for_reviews;
		this.duration = duration;
		this.director_facebook_likes = director_facebook_likes;
		this.actor_3_facebook_likes = actor_3_facebook_likes;
		this.actor_2_name = actor_2_name;
		this.actor_1_facebook_likes = actor_1_facebook_likes;
		this.gross = gross;
		this.genres = genres;
		this.actor_1_name = actor_1_name;
		this.movie_title = movie_title;
		this.num_voted_users = num_voted_users;
		this.cast_total_facebook_likes = cast_total_facebook_likes;
		this.actor_3_name = actor_3_name;
		this.facenumber_in_poster = facenumber_in_poster;
		this.plot_keywords = plot_keywords;
		this.movie_imdb_link = movie_imdb_link;
		this.num_user_for_reviews = num_user_for_reviews;
		this.language = language;
		this.country = country;
		this.content_rating = content_rating;
		this.budget = budget;
		this.title_year = title_year;
		this.actor_2_facebook_likes = actor_2_facebook_likes;
		this.aspect_ratio = aspect_ratio;
		this.movie_facebook_likes = movie_facebook_likes;
		this.imdb_score = imdb_score;
		this.ratio_rentabilite = ratio_rentabilite;
		this.statut_rentabilite = statut_rentabilite;
		this.ratio_imdb = ratio_imdb;
	}

	public Movie() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}

	public int getNum_critic_for_reviews() {
		return num_critic_for_reviews;
	}

	public void setNum_critic_for_reviews(int num_critic_for_reviews) {
		this.num_critic_for_reviews = num_critic_for_reviews;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDirector_facebook_likes() {
		return director_facebook_likes;
	}

	public void setDirector_facebook_likes(int director_facebook_likes) {
		this.director_facebook_likes = director_facebook_likes;
	}

	public int getActor_3_facebook_likes() {
		return actor_3_facebook_likes;
	}

	public void setActor_3_facebook_likes(int actor_3_facebook_likes) {
		this.actor_3_facebook_likes = actor_3_facebook_likes;
	}

	public String getActor_2_name() {
		return actor_2_name;
	}

	public void setActor_2_name(String actor_2_name) {
		this.actor_2_name = actor_2_name;
	}

	public int getActor_1_facebook_likes() {
		return actor_1_facebook_likes;
	}

	public void setActor_1_facebook_likes(int actor_1_facebook_likes) {
		this.actor_1_facebook_likes = actor_1_facebook_likes;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getActor_1_name() {
		return actor_1_name;
	}

	public void setActor_1_name(String actor_1_name) {
		this.actor_1_name = actor_1_name;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getNum_voted_users() {
		return num_voted_users;
	}

	public void setNum_voted_users(int num_voted_users) {
		this.num_voted_users = num_voted_users;
	}

	public int getCast_total_facebook_likes() {
		return cast_total_facebook_likes;
	}

	public void setCast_total_facebook_likes(int cast_total_facebook_likes) {
		this.cast_total_facebook_likes = cast_total_facebook_likes;
	}

	public String getActor_3_name() {
		return actor_3_name;
	}

	public void setActor_3_name(String actor_3_name) {
		this.actor_3_name = actor_3_name;
	}

	public int getFacenumber_in_poster() {
		return facenumber_in_poster;
	}

	public void setFacenumber_in_poster(int facenumber_in_poster) {
		this.facenumber_in_poster = facenumber_in_poster;
	}

	public String getPlot_keywords() {
		return plot_keywords;
	}

	public void setPlot_keywords(String plot_keywords) {
		this.plot_keywords = plot_keywords;
	}

	public String getMovie_imdb_link() {
		return movie_imdb_link;
	}

	public void setMovie_imdb_link(String movie_imdb_link) {
		this.movie_imdb_link = movie_imdb_link;
	}

	public int getNum_user_for_reviews() {
		return num_user_for_reviews;
	}

	public void setNum_user_for_reviews(int num_user_for_reviews) {
		this.num_user_for_reviews = num_user_for_reviews;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContent_rating() {
		return content_rating;
	}

	public void setContent_rating(String content_rating) {
		this.content_rating = content_rating;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public int getTitle_year() {
		return title_year;
	}

	public void setTitle_year(int title_year) {
		this.title_year = title_year;
	}

	public int getActor_2_facebook_likes() {
		return actor_2_facebook_likes;
	}

	public void setActor_2_facebook_likes(int actor_2_facebook_likes) {
		this.actor_2_facebook_likes = actor_2_facebook_likes;
	}

	public double getImdb_score() {
		return imdb_score;
	}

	public void setImdb_score(double imdb_score) {
		this.imdb_score = imdb_score;
	}

	public double getAspect_ratio() {
		return aspect_ratio;
	}

	public void setAspect_ratio(double aspect_ratio) {
		this.aspect_ratio = aspect_ratio;
	}

	public int getMovie_facebook_likes() {
		return movie_facebook_likes;
	}

	public void setMovie_facebook_likes(int movie_facebook_likes) {
		this.movie_facebook_likes = movie_facebook_likes;
	}

	public double getRatio_rentabilite() {
		return ratio_rentabilite;
	}

	public void setRatio_rentabilite(double ratio_rentabilite) {
		this.ratio_rentabilite = ratio_rentabilite;
	}

	public String getStatut_rentabilite() {
		return statut_rentabilite;
	}

	public void setStatut_rentabilite(String statut_rentabilite) {
		this.statut_rentabilite = statut_rentabilite;
	}

	public String getRatio_imdb() {
		return ratio_imdb;
	}

	public void setRatio_imdb(String ratio_imdb) {
		this.ratio_imdb = ratio_imdb;
	}
}
