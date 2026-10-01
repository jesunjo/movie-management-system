public class ViewedMovieNode {
    private Movie movie;
    private float rating;    // lower number means higher priority
    private ViewedMovieNode next;
// Node for Watchlist and ViewedList linked lists.
// Holds a Movie, integer priority, and pointer to next node.

    public ViewedMovieNode(Movie movie, float rating) {
        this.movie = movie;
        this.rating = rating;
        this.next = null;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public float getRating() {
        return rating;
    }

    public ViewedMovieNode getNext() {
        return next;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setNext(ViewedMovieNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[" + rating + "] " + movie;
    }
    
}
