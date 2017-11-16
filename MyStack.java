public class MyStack<E> implements Stack<E> {
  private MyLinkedList<E> linkedList;
  public MyStack() {
    linkedList = new MyLinkedList();
  }

  public boolean isEmpty() {
    if (linkedList.isEmpty()) {
      return true;
    }
    return false;
  }

  public void push(E item) {
    linkedList.insertAtFrontOfList(item);
  }

  public E pop() {
    if (isEmpty()){
      return null;
    }
    else {
      return linkedList.deleteHead();
    }
  }

  public E peek(){
    return linkedList.lookupHead();
  }

  public void printStack(){
    linkedList.printList();
  }
}
