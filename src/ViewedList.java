import java.util.ArrayList;

public class ViewedList {
    private ViewedMovieNode head;
    private int size;

    public ViewedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Adds movie with rating
    public boolean addMovie(Movie movie, float rating) {
        if (movie == null) {
            return false;
        }

        if (contains(movie.getTitle())) {
            return false;
        }

        ViewedMovieNode newNode = new ViewedMovieNode(movie, rating); 

        if (head == null || rating > head.getRating()) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return true;
        }
        ViewedMovieNode current = head;

        while (current.getNext() != null && current.getNext().getRating() >= rating) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
        return true;
    }
    // rating with 1 sig fig
    public boolean addMovie(Movie movie) {
        return addMovie(movie, 0.0f);
    }

    public boolean contains(String title) {
        if (title == null) {
            return false;
        }
        ViewedMovieNode current = head;

        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title.trim())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean removeMovie(String title) {
        if (title == null || head == null) {
            return false;
        }

        String target = title.trim();

        // Removing the head
        if (head.getMovie().getTitle().equalsIgnoreCase(target)) {
            head = head.getNext();
            size--;
            return true;
        }
        ViewedMovieNode current = head;

        while (current.getNext() != null) {
            if (current.getNext().getMovie().getTitle().equalsIgnoreCase(target)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean changeRating(String title, float newRating) {
        Movie movie = findMovie(title);

        if (movie == null) {
            return false;
        }

        removeMovie(title);
        return addMovie(movie, newRating);
    }

    public Movie findMovie(String title) {
        if (title == null) {
            return null;
        }
        ViewedMovieNode current = head;

        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title.trim())) {
                return current.getMovie();
            }
            current = current.getNext();
        }
        return null;
    }

    public ArrayList<Movie> toArrayList() {
        ArrayList<Movie> movies = new ArrayList<>();
        ViewedMovieNode current = head;

        while (current != null) {
            movies.add(current.getMovie());
            current = current.getNext();
        }
        return movies;
    }

    public ArrayList<Float> getRatings() {
        ArrayList<Float> ratings = new ArrayList<>();
        ViewedMovieNode current = head;

        while (current != null) {
            ratings.add(current.getRating());
            current = current.getNext();
        }
        return ratings;
    }
}