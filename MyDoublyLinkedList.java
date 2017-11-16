/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 12, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Lab5 - Doubly Linked Lists
Title: MyDoublyLinkedList.java
*/

public class MyDoublyLinkedList<T> implements DoublyLinkedList<T>{
  MyDoubleNode<T> head = new MyDoubleNode<T>();
  MyDoubleNode<T> tail = new MyDoubleNode<T>();

  //Constructor sets head.next to tail and tail.prev to head to indicate that the list is empty
  public MyDoublyLinkedList() {
    head.data = null;
    head.next = tail;
    tail.data = null;
    tail.prev = head;
  }

  //Returns whether list is empty or not
  public boolean isEmpty(){
    if ((head.next == tail) && (tail.prev == head)){
      return true;
    }
    return false;
  }

  //If the list doesn't already contain this item,
  //this method inserts new item at the front of the list
  public void insertAtFrontOfList(T item){
    //if (contains(item) == false){
      MyDoubleNode<T> temp = new MyDoubleNode();
      if (isEmpty()){
        temp.data = item;
        head.next = temp;
        temp.prev = head;
        tail.prev = temp;
        temp.next = tail;
      }
      else {
        temp.data = item;
        temp.next = head.next;
        temp.prev = head;
        head.next = temp;
      }
    //}
  }

  //If the list doesn't already contain this item,
  //this method inserts new item at the end of the list
  public void insertAtEndOfList(T item){
    //if (contains(item) == false) {
      MyDoubleNode<T> temp = new MyDoubleNode();
      MyDoubleNode<T> temp2 = new MyDoubleNode();
      if (isEmpty()){
        temp.data = item;
        head.next = temp;
        temp.prev = head;
        temp.next = tail;
        tail.prev = temp;
      }
      else {
        temp.data = item;
        temp2 = tail.prev;
        temp.prev = temp2;
        temp2.next = temp;
        temp.next = tail;
        tail.prev = temp;

      }
    //}
  }

  //Prints the complete linked list sequentially in forward order
  //Expected Runtime: O(n)
  public void printList(){
    System.out.println("My current doubly linked list:");
    if (head.next != tail || tail.prev != head){
      MyDoubleNode<T> counter = head;
      while (counter.next != tail){
        counter = counter.next;
        if (counter.next != tail){
          System.out.print(counter.data + ", ");
        }
        else {
          System.out.print(counter.data + "\n");
        }


      }
    }
  }

  //Prints the complete linked list sequentially in backward order
  //Expected Runtime: O(n)
  public void printListRev(){
    System.out.println("My current doubly linked list in reverse:");
    if (head.next != tail || tail.prev != head){
      MyDoubleNode<T> counter = tail;
      while (counter.prev != head){
        counter = counter.prev;
        if (counter.prev != head){
          System.out.print(counter.data + ", ");
        }
        else {
          System.out.print(counter.data + "\n");
        }
      }
    }
  }

  //If a certain item is found in the list, it is returned
  //Otherwise, this method returns 'null'
  public T lookup(T item){
    if (head.next == tail && tail.prev == head){
      return null;
    }
    else {
      MyDoubleNode<T> counter = head.next;
      boolean shouldContinue = true;
      while (shouldContinue){
        if (counter.next != tail){
          if (counter.data.equals(item)){
            return counter.data;
          }
          counter = counter.next;
          shouldContinue = true;
        }
        else{
          if (counter.data.equals(item)){
            return counter.data;
          }
          shouldContinue = false;
        }
      }
      return null;
    }
  }

  //Returns whether or not the list contains a certain item
  public boolean contains(T item){
    if (head.next == tail && tail.prev == head){
      return false;
    }
    else {
      MyDoubleNode<T> counter = head.next;
      boolean shouldContinue = true;
      while (shouldContinue){
        if (counter.next != tail){
          if (counter.data.equals(item)){
            return true;
          }
          counter = counter.next;
          shouldContinue = true;
        }
        else{
          if (counter.data.equals(item)){
            return true;
          }
          shouldContinue = false;
        }
      }
      return false;
    }
  }

  //If the list contain a certain item,
  //this method deletes the item from the list
  public void delete (T item){
    if (head.next != tail || tail.prev != head){
      if (head.next.data.equals(item)){
        head.next = head.next.next;
      }
      MyDoubleNode<T> current = head;
      while (current.next != tail){
        if (current.next.data.equals(item)){
          current.next = current.next.next;
        }
        else {
          current = current.next;
        }
      }
      if (tail.prev.data.equals(item)){
        tail.prev = tail.prev.prev;
      }
      MyDoubleNode<T> current2 = tail;
      while (current2.prev != head){
        if (current2.prev.data.equals(item)){
          current2.prev = current2.prev.prev;
        }
        else {
          current2 = current2.prev;
        }
      }
    }
  }
  //This method deletes the head from the list
  //& returns the data from the head that was deleted
  public T deleteHead(){
    if (head.next != tail || tail.prev != head) {
        T data = head.next.data;
        head = head.next;
        return data;
    }
    return null;
  }
  //If a certain item is found in the list, it is returned
  //Otherwise, this method returns 'null'
  public T lookupHead(){
    if (head.next == tail && tail.prev == head){
      return null;
    }
    else {
      return head.next.data;
    }
  }

}
