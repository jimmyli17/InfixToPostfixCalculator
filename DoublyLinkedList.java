/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 12, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Lab5 - Doubly Linked Lists
Title: DoublyLinkedList.java
*/

//Citations: Mostly taken from the lab instructions.

public interface DoublyLinkedList<AnyType> {
  public void insertAtFrontOfList(AnyType x);
  public void insertAtEndOfList(AnyType x);
  public void delete(AnyType x);
  public boolean contains(AnyType x);
  public AnyType lookup(AnyType x);
  public boolean isEmpty();
  public void printList();
  public void printListRev();
}
