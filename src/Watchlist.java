import java.util.ArrayList;

public class Watchlist {
    private MovieNode head;
    private int size;

    public Watchlist() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /*Inserts movie in priority order.
     *Lower priority number means earlier in the list (watch/watched sooner).
     *Duplicates (i.e. same title, case-insensitive) are rejected.
     */
    public boolean addMovie(Movie movie, int priority) {
        if (movie == null) {
            return false;
        }

        // Reject duplicates
        if (contains(movie.getTitle())) {
            return false;
        }

        MovieNode newNode = new MovieNode(movie, priority);

        // Case 1: empty list, or a new node goes at the head
        if (head == null || priority < head.getPriority()) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return true;
        }

        // Case 2: walk along until we find the right insertion point
        MovieNode current = head;
        while (current.getNext() != null 
                && current.getNext().getPriority() <= priority) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
        return true;
    }

    /* Overload: adds to the end (lowest priority) if no priority given. */
    public boolean addMovie(Movie movie) {
        int nextPriority = (head == null) ? 1 : findMaxPriority() + 1;
        return addMovie(movie, nextPriority);
    }

    private int findMaxPriority() {
        int max = 0;
        MovieNode current = head;
        while (current != null) {
            if (current.getPriority() > max) {
                max = current.getPriority();
            }
            current = current.getNext();
        }
        return max;
    }

    public boolean contains(String title) {
        if (title == null) {
            return false;
        }
        MovieNode current = head;
        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title.trim())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /* Removes a movie by it's title. Returns 'true' if removed. */
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

        // Walk to find the node before the target
        MovieNode current = head;
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

    /* Changes the priority of an existing movie and re-sorts. Returns true if the movie was found and updated. */
    public boolean changePriority(String title, int newPriority) {
        Movie movie = findMovie(title);
        if (movie == null) {
            return false;
        }
        removeMovie(title);
        return addMovie(movie, newPriority);
    }

    /* Finds and returns a Movie by title, or null if not found. */
    public Movie findMovie(String title) {
        if (title == null) {
            return null;
        }
        MovieNode current = head;
        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title.trim())) {
                return current.getMovie();
            }
            current = current.getNext();
        }
        return null;
    }

    /* Prints the watchlist in its current (priority-sorted) order. */
    public void display() {
        if (head == null) {
            System.out.println("Your watchlist is empty.");
            return;
        }

        System.out.println("\n| Your Watchlist |");
        MovieNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current);
            current = current.getNext();
            index++;
        }
    }

    public void merge(Watchlist other) {
        MovieNode current = other.head;
        while (current != null) {
            addMovie(current.getMovie(), current.getPriority());
            current = current.getNext();
        }
    }

    /* Returns all movies as an ArrayList, in current order. */
    public ArrayList<Movie> toArrayList() {
        ArrayList<Movie> list = new ArrayList<>();
        MovieNode current = head;
        while (current != null) {
            list.add(current.getMovie());
            current = current.getNext();
        }
        return list;
    }

    /* Returns priorities in the same order as toArrayList(). */
    public ArrayList<Integer> getPriorities() {
        ArrayList<Integer> list = new ArrayList<>();
        MovieNode current = head;
        while (current != null) {
            list.add(current.getPriority());
            current = current.getNext();
        }
        return list;
    }

    /* Clears entire watchlist. */
    public void clear() {
        head = null;
        size = 0;
    }
    
}
