public class MovieNode {
    private Movie movie;
    private int priority;    // lower number means higher priority
    private MovieNode next;
// Node for Watchlist and ViewedList linked lists.
// Holds a Movie, integer priority, and pointer to next node.

    public MovieNode(Movie movie, int priority) {
        this.movie = movie;
        this.priority = priority;
        this.next = null;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public int getPriority() {
        return priority;
    }

    public MovieNode getNext() {
        return next;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setNext(MovieNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[Priority: " + priority + "] " + movie;
    }
    
}
