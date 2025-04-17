package cp213;

import java.io.PrintStream;

/**
 * Movie class definition.
 *
 * @author your name, id, email here
 * @version 2024-09-01
 */
public class Movie implements Comparable<Movie> {

	// Constants
	/**
	 * The first year movies were produced.
	 */
	public static final int FIRST_YEAR = 1888;
	/**
	 * The names of movie genres.
	 */
	public static final String[] GENRES = { "science fiction", "fantasy", "drama", "romance", "comedy", "zombie",
			"action", "historical", "horror", "war", "mystery" };
	/**
	 * The maximum allowed Movie rating.
	 */
	public static final double MAX_RATING = 10.0;
	/**
	 * The minimum allowed Movie rating.
	 */
	public static final double MIN_RATING = 0.0;

	/**
	 * Creates a string of movie genres in the format:
	 *
	 * <pre>
	 0: science fiction
	 1: fantasy
	 2: drama
	...
	10: mystery
	 * </pre>
	 *
	 * @return A formatted numbered string of Movie genres.
	 */
	public static String genresMenu() {

		// your code here
		String string = "";
		for (int i = 0; i < GENRES.length; i++) {
			if (i <= 9) {
				string += ' ' + Integer.toString(i) + ": " + GENRES[i] + "\n";
			} else {
				string += Integer.toString(i) + ": " + GENRES[i] + "\n";
			}
		}

		return string;
	}

	// Attributes
	private String director = "";
	private int genre = -1;
	private double rating = 0;
	private String title = "";
	private int year = 0;
	private String string;

	/**
	 * Instantiates a Movie object.
	 *
	 * @param title    Movie title.
	 * @param year     Year of release.
	 * @param director Name of director.
	 * @param rating   Rating of 1 - 10 from IMDB.
	 * @param genre    Number representing Movie genre.
	 */
	public Movie(final String title, final int year, final String director, final double rating, final int genre) {

		// your code here

		this.title = title;
		this.year = year;
		this.director = director;
		this.rating = rating;
		this.genre = genre;

		return;
	}

	/**
	 * Movies are compared by title, then by year if the titles match. Must ignore
	 * case. Ex: Zulu, 1964 precedes Zulu, 2013. Returns:
	 * <ul>
	 * <li>0 if this equals target</li>
	 * <li>&lt; 0 if this precedes target</li>
	 * <li>&gt; 0 if this follows target</li>
	 * </ul>
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Movie target) {

		// your code here
		int total = 0;
		if (this.title.compareTo(target.title) != 0) {
			total = this.title.compareTo(target.title);
		} else {
			int num = this.year - target.year;
			if (num > 0) {
				total = 1;
			} else if (num < 0) {
				total = -1;
			} else {
				total = 0;
			}
		}

		return total;
	}

	/**
	 * Converts a genre integer to a string.
	 *
	 * @return The string version of the genre number.
	 */
	public String genreToName() {

		// your code here
		String gen = GENRES[this.genre];

		return gen;
	}

	/**
	 * Director getter.
	 *
	 * @return The director.
	 */
	public String getDirector() {

		// your code here

		return director;
	}

	/**
	 * Genre getter.
	 *
	 * @return The genre number.
	 */
	public int getGenre() {

		// your code here

		return genre;
	}

	/**
	 * Rating getter.
	 *
	 * @return The rating.
	 */
	public double getRating() {

		// your code here

		return rating;
	}

	/**
	 * Title getter.
	 *
	 * @return The title.
	 */
	public String getTitle() {

		// your code here

		return title;
	}

	/**
	 * Year getter.
	 *
	 * @return The year.
	 */
	public int getYear() {

		// your code here

		return year;
	}

	/**
	 * Creates a formatted string of Movie key data in the format "title, year". Ex:
	 * "Zulu, 1964".
	 *
	 * @return A Movie key as a string.
	 */
	public String key() {
		return String.format("%s, %d", this.title, this.year);
	}

	/**
	 * Rating setter.
	 *
	 * @param rating The new rating.
	 */
	public void setRating(final double rating) {

		// your code here
		this.rating = rating;

	}

	/**
	 * Returns a string in the format:
	 *
	 * <pre>
	Title:    title
	Year:     year
	Director: director
	Rating:   rating
	Genre:    genre
	 * </pre>
	 *
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString() Creates a formatted string of movie data.
	 */
	@Override
	public String toString() {

		// your code here
		String string = "";
		string += "Title: " + this.title + "\n";
		string += "Year: " + this.year + "\n";
		string += "Director: " + this.director + "\n";
		string += "Rating: " + this.rating + "\n";
		string += "Genre: " + GENRES[this.genre] + "\n";

		return string;
	}

	/**
	 * Writes a single line of movie data to an open PrintStream in the format:
	 * title|year|director|rating|genre
	 *
	 * @param ps Output PrintStream.
	 */
	public void write(final PrintStream ps) {

		// your code here
		ps.println(this.title + '|' + this.year + '|' + this.director + '|' + this.rating + '|' + this.genre);

		return;
	}

}
