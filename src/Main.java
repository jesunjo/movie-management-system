

public class Main {
    public static void main(String[] args) {
        Movie movie1 = new Movie("Interstellar", "Sci-Fi", 2014);
        Movie movie2 = new Movie("Backrooms", "Horror", 2026);
// I'm not keeping main like this, this is me largely testing a singly linked list pointing
        MovieNode node1 = new MovieNode(movie1);
        MovieNode node2 = new MovieNode(movie2);

        node1.setNext(node2); 

        System.out.println(node1.getMovie());
        System.out.println(node1.getNext().getMovie());
    }
}
