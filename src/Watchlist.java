// I havent touched anything in here in a while, so it may be placeholder
// My whole vision basically changed from when i did this
public class Watchlist {
    private MovieNode head;

    public Watchlist() {
        head = null;
    }

    public void addMovie(Movie movie) {
        MovieNode newNode = new MovieNode(movie);

        if (head == null) {
            head = newNode;
        } else {
            MovieNode current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }
    }
}