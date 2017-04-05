package main;

public class Iris {
	
	double sepallength;
	double sepalwidth;
	double petallength;
	double petalwidth;
	String genre;
	
	Iris(double sepallength, double sepalwidth, double petallength, double petalwidth, String genre){
		this.sepallength=sepallength;
		this.sepalwidth=sepalwidth;
		this.petallength=petallength;
		this.petalwidth=petalwidth;
		this.genre=genre;
	}

	public double getSepallength() {
		return sepallength;
	}

	public void setSepallength(double sepallength) {
		this.sepallength = sepallength;
	}

	public double getSepalwidth() {
		return sepalwidth;
	}

	public void setSepalwidth(double sepalwidth) {
		this.sepalwidth = sepalwidth;
	}

	public double getPetallength() {
		return petallength;
	}

	public void setPetallength(double petallength) {
		this.petallength = petallength;
	}

	public double getPetalwidth() {
		return petalwidth;
	}

	public void setPetalwidth(double petalwidth) {
		this.petalwidth = petalwidth;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
