public class MyQueue<E> implements Queue<E>{
  private MyDoublyLinkedList<E> dll;

  public MyQueue() {
    dll = new MyDoublyLinkedList();
  }

  public boolean isEmpty(){
    if (dll.isEmpty()) {
      return true;
    }
    return false;
  }
  public void enqueue(E x){
    dll.insertAtEndOfList(x);
  }
  public E dequeue(){
    if (isEmpty()){
      return null;
    }
    else {
      return dll.deleteHead();
    }
  }
  public E peek(){
    return dll.lookupHead();
  }
  public void printQueue(){
    dll.printList();
  }
}
