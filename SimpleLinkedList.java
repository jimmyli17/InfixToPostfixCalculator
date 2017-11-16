/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: February 12, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Lab4 - Linked Lists
Title: SimpleLinkedList.java
*/

//Citations: Mostly taken from the lab instructions.

public interface SimpleLinkedList<AnyType> {
  public void insertAtFrontOfList(AnyType x);
  public void insertAtEndOfList(AnyType x);
  public void delete(AnyType x);
  public boolean contains(AnyType x);
  public AnyType lookup(AnyType x);
  public boolean isEmpty();
  public void printList();
}
