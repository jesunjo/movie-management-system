public class MovieNode {
    private Movie movie;
    private MovieNode next;
// Experimenting with implementing the linked list
//Still very much a WIP

    public MovieNode(Movie movie) {
        this.movie = movie;
        this.next = null;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public MovieNode getNext() {
        return next;
    }

    public void setNext(MovieNode next) {
        this.next = next;
    }
}
