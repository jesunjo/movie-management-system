public boolean removeMovie(String title) {
    if (head == null) return false;
    if (head.getMovie().getTitle().equalsIgnoreCase(title)) {
        head = head.getNext();
        return true;
    }
    MovieNode current = head;
    while (current.getNext() != null) {
        if (current.getNext().getMovie().getTitle().equalsIgnoreCase(title)) {
            current.setNext(current.getNext().getNext());
            return true;
        }
        current = current.getNext();
    }
    return false;
}

public void sortByPriority() {
    // Convert to list, sort, rebuild — or implement merge sort on linked list
    // For simplicity, extract to ArrayList, sort, rebuild
}
