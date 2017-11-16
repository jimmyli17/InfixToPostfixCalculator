/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 12, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Lab4 - Linked Lists
Title: MyLinkedList.java
*/

public class MyLinkedList<E> implements SimpleLinkedList<E> {
  MyNode<E> head;
  //Constructor sets head and next to null
  public MyLinkedList() {
    head = null;
  }

  //Returns whether list is empty or not
  public boolean isEmpty(){
    if (head == null){
      return true;
    }
    return false;
  }

  //If the list doesn't already contain this item,
  //this method inserts new item at the front of the list
  //Expected Runtime: O(1)
  public void insertAtFrontOfList(E item){
    //if (contains(item) == false) {
      if (isEmpty()) {
        head = new MyNode<>();
        head.data = item;
        head.next = null;
      }
      else {
        MyNode<E> newlycreatednode = new MyNode<>();
        newlycreatednode.data = item;
        newlycreatednode.next = head;
        head = newlycreatednode;
      }
    //}
  }

  //If the list doesn't already contain this item,
  //this method inserts new item at the end of the list
  //Expected Runtime: O(n)
  public void insertAtEndOfList(E item){
    //if (contains(item) == false) {
      if (isEmpty()) {
        head = new MyNode();
        head.data = item;
        head.next = null;
      }
      else {
        MyNode<E> newlycreatednode = new MyNode();
        MyNode<E> counter = head;
        newlycreatednode.data = item;
        while (counter.next != null){
          counter = counter.next;
        }
        counter.next = newlycreatednode;
      }
    //}
  }

  //If the list contain a certain item,
  //this method deletes the item from the list
  //without returning anything
  public void delete(E item){
    if (head != null) {
      if (head.data.equals(item)){
        head = head.next;
      }
      MyNode<E> current = head;
      while (current.next != null){
        if (current.next.data.equals(item)){
          current.next = current.next.next;
        }
        else {
          current = current.next;
        }
      }
    }
  }

  //This method deletes the head from the list
  //& returns the data from the head that was deleted
  public E deleteHead(){
    if (head != null) {
        E data = head.data;
        head = head.next;
        return data;
    }
    return null;
  }

  //Returns whether or not the list contains a certain item
  public boolean contains(E item){
    if (head == null){
      return false;
    }
    else {
      MyNode<E> counter = head;
      boolean shouldContinue = true;
      while (shouldContinue){
        if (counter.next != null){
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

  //If a certain item is found in the list, it is returned
  //Otherwise, this method returns 'null'
  public E lookup(E item){
    if (head == null){
      return null;
    }
    else {
      MyNode<E> counter = head.next;
      boolean shouldContinue = true;
      while (shouldContinue){
        if (counter.next != null){
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

  //If a certain item is found in the list, it is returned
  //Otherwise, this method returns 'null'
  public E lookupHead(){
    if (head == null){
      return null;
    }
    else {
      return head.data;
    }
  }

  //Prints the complete linked list sequentially
  //Expected Runtime: O(n)
  public void printList(){
    System.out.print("My current linked list: ");
    if (head != null) {
      MyNode<E> counter = head;
      boolean shouldContinue = true;
      while (shouldContinue){
        if (counter.next != null) {
          System.out.print(counter.data + ", ");
          counter = counter.next;
          shouldContinue = true;
        }
        else {
          System.out.println(counter.data);
          shouldContinue = false;
        }
      }
    }
  }
}
