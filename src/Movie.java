public class Movie {
    private String title;
    private String genre;
    private int year;

    public Movie(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }

    public int year() {
        return year;
    }

    @Override 
    public String toString() {
        return title + " (" + year + ") - " + genre; //This can be added onto if we want. more info displayed
    }
}
