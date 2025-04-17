package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author your name, id, email here
 * @version 2024-09-01
 */
public class MovieUtilities {

	/**
	 * Counts the number of movies in each genre given in Movie.GENRES. An empty
	 * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
	 *
	 * @param movies List of movies.
	 * @return Number of genres across all Movies. One entry for each genre in the
	 *         Movie.GENRES array.
	 */
	public static int[] genreCounts(final ArrayList<Movie> movies) {

		// your code here
		int[] count = new int[Movie.GENRES.length];
		for (Movie i : movies) {
			int gen = i.getGenre();
			count[gen] += 1;
		}

		return count;
	}

	/**
	 * Creates a Movie object by requesting data from a user. Uses the format:
	 *
	 * <pre>
	Title:
	Year:
	Director:
	Rating:
	Genres:
	0: science fiction
	1: fantasy
	...
	10: mystery
	
	Enter a genre number:
	 * </pre>
	 *
	 * @param keyboard A keyboard (System.in) Scanner.
	 * @return A Movie object.
	 */
	public static Movie getMovie(final Scanner keyboard) {

		// your code here
		// title is good
		System.out.print("Title:");
		String title = keyboard.nextLine();
		// year is fine
		System.out.print("year: ");
		int year = keyboard.nextInt();

		while (year < Movie.FIRST_YEAR) {
			System.out.println("Enter the right year.");
			year = keyboard.nextInt();
		}
		// Director is fine
		keyboard.nextLine();
		System.out.print("Director: ");
		String director = keyboard.nextLine();

		System.out.print("Rating: ");
		double rating = keyboard.nextDouble();
		while (rating < Movie.MIN_RATING || rating > Movie.MAX_RATING) {
			System.out.println("Enter the proper rating");
			System.out.println("Rating: ");
			rating = keyboard.nextDouble();
		}

		System.out.println("Genres: ");
		for (int i = 0; i < Movie.GENRES.length; i++) {
			if (i <= 9) {
				System.out.println(" " + i + ": " + Movie.GENRES[i]);
			} else {
				System.out.println(i + ": " + Movie.GENRES[i]);
			}
		}
		System.out.println("");
		System.out.print("Enter a genre number: ");
		int genre = keyboard.nextInt();
		while (genre > Movie.GENRES.length || genre < 0) {
			System.out.println("Enter the proper genre");
			System.out.println("Enter a genre number: ");
			genre = keyboard.nextInt();
		}

		return new Movie(title, year, director, rating, genre);
	}

	/**
	 * Creates a list of Movies whose genre is equal to the genre parameter.
	 *
	 * @param movies List of movies.
	 * @param genre  Genre to compare against.
	 * @return List of movies of genre.
	 */
	public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

		// your code here
		final ArrayList<Movie> values = new ArrayList<>();
		for (Movie i : movies) {
			if (i.getGenre() == genre) {
				values.add(i);
			}
		}

		return values;
	}

	/**
	 * Creates a list of Movies whose ratings are equal to or higher than rating.
	 *
	 * @param movies List of movies.
	 * @param rating Rating to compare against.
	 * @return List of movies of rating equal to or higher.
	 */
	public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

		// your code here
		final ArrayList<Movie> values = new ArrayList<>();
		for (Movie i : movies) {
			if (i.getRating() > rating) {
				values.add(i);
			}
		}
		return values;
	}

	/**
	 * Creates a list of Movies from a particular year.
	 *
	 * @param movies List of movies.
	 * @param year   Year to compare against.
	 * @return List of movies of year.
	 */
	public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

		// your code here
		final ArrayList<Movie> values = new ArrayList<>();
		for (Movie i : movies) {
			if (i.getYear() == year) {
				values.add(i);
			}
		}

		return values;
	}

	/**
	 * Asks a user to select a genre from a list of genres displayed by calling
	 * Movie.genresMenu() and returns an integer genre code. The genre must be a
	 * valid index to an item in Movie.GENRES.
	 *
	 * @param keyboard A keyboard (System.in) Scanner.
	 * @return An integer genre code.
	 */
	public static int readGenre(final Scanner keyboard) {

		// your code here
		System.out.println(Movie.genresMenu());

		// System.out.println("Enter a genre code from 0 - 10");
		System.out.print("Enter a genre number: ");

		while (!keyboard.hasNextInt()) {
			System.out.println("Enter an int from 0 - " + Movie.GENRES.length);
			keyboard.next();
		}

		int genre = keyboard.nextInt();
		while (genre < 0 || genre > Movie.GENRES.length) {
			System.out.println("Enter an int from 0 - " + Movie.GENRES.length);

			while (!keyboard.hasNextInt()) {
				System.out.println("Enter an Int from 0 - " + Movie.GENRES.length);
				keyboard.next();
			}
			genre = keyboard.nextInt();
		}

		return genre;
	}

	/**
	 * Creates and returns a Movie object from a line of formatted string data.
	 *
	 * @param line A vertical bar-delimited line of movie data in the format
	 *             title|year|director|rating|genre
	 * @return The data from line as a Movie object.
	 */
	public static Movie readMovie(final String line) {

		// your code here
		String[] values = line.split("\\|");

		String title = values[0];
		int year = Integer.parseInt(values[1]);
		String director = values[2];
		double rating = Double.parseDouble(values[3]);
		int genre = Integer.parseInt(values[4]);

		Movie movie = new Movie(title, year, director, rating, genre);
		return movie;
	}

	/**
	 * Reads a list of Movies from a file.
	 *
	 * @param fileIn A Scanner of a Movie data file in the format
	 *               title|year|director|rating|genre
	 * @return A list of Movie objects.
	 */
	public static ArrayList<Movie> readMovies(final Scanner fileIn) {

		// your code here
		final ArrayList<Movie> values = new ArrayList<>();
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			values.add(readMovie(line));
		}

		return values;
	}

	/**
	 * Writes the contents of a list of Movie to a PrintStream.
	 *
	 * @param movies A list of Movie objects.
	 * @param ps     Output PrintStream.
	 */
	public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

		// your code here
		for (Movie i : movies) {
			ps.println(i.getTitle() + '|' + i.getYear() + '|' + i.getDirector() + '|' + i.getRating() + '|'
					+ i.getGenre());
		}

		return;
	}

}
