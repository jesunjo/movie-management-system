public class MovieNode {
    private Movie movie;
    private int priority;
    private MovieNode next;
// Experimenting with implementing the linked list
//Still very much a WIP

    public MovieNode(Movie movie) {
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
        return "[Priority " + priority + "] " + movie;
    }
    
}
