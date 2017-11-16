/*
Author: Jimmy Li, #29903320, NetID: jli119
Date: March 6, 2017
Course: CSC172, Professor Ted Pawlicki
Assignment: Project2 - Infix Calculator
Title: InfixCalculator.java
*/

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class InfixCalculator {
  public static void main (String[] args){
    InfixCalculator calculator = new InfixCalculator();
    if (args.length > 0){
      String inputFile = args[0];
      int numLines = 0;
      try {
        Scanner input = new Scanner(new File(inputFile));
        // Counts the number of lines in the file

        while (input.hasNextLine()) {
          numLines++;
          input.nextLine();
        }
      }

      catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + inputFile + "'");
      }

      String outputFile = args[1];
      String line = null;
      double answer = 0.0;
      DecimalFormat decimal = new DecimalFormat("0.00\n");
      DecimalFormat decimal2 = new DecimalFormat("0.00");

      try {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Keeps track of which line it is writing to in the output file, one it reaches the last line, it will refrain from adding a newline at the end
        int count = 0;
        while((line = bufferedReader.readLine()) != null) {
          count++;
          if (count < numLines ){
            if (((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop() instanceof Double)) {
              bufferedWriter.write(decimal.format((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop()));
            }
            else {
              bufferedWriter.write((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop().toString() + "\n");
            }
          }
          else {

            if (((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop() instanceof Double)) {
              bufferedWriter.write(decimal2.format((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop()));
            }
            else{
              bufferedWriter.write((calculator.evaluatePostfix(calculator.convertToPostfix(line))).pop().toString());
            }
          }
        }
        bufferedWriter.close();
        bufferedReader.close();
      }

      catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + inputFile + "'");
      }

      catch(IOException ex) {
        System.out.println("Error reading file '" + inputFile + "'");
      }
    }
  }

  public MyQueue convertToPostfix(String expression){
    MyQueue<Object> queue = new MyQueue<Object>();
    MyStack<Object> stack = new MyStack<Object>();
    // Removes all whitespaces from the expression
    expression = expression.replaceAll("\\s","");

    String token = "";
    Character character;
    boolean error = false;
    boolean isSin1 = false;
    boolean isSin2 = false;
    boolean isSin3 = false;

    // Declares a hashmap
    HashMap<Character, Integer> order_of_operations = new HashMap<Character, Integer>();

    // Adds characters with their order of operation into the hashmap
    order_of_operations.put('(', 10);
    order_of_operations.put(')', 10);
    order_of_operations.put('s', 9);
    order_of_operations.put('i', 9);
    order_of_operations.put('n', 9);
    order_of_operations.put('^', 8);
    order_of_operations.put('*', 7);
    order_of_operations.put('/', 7);
    order_of_operations.put('%', 7);
    order_of_operations.put('+', 6);
    order_of_operations.put('-', 6);
    order_of_operations.put('<', 5);
    order_of_operations.put('>', 5);
    order_of_operations.put('=', 4);
    order_of_operations.put('!', 3);
    order_of_operations.put('&', 2);
    order_of_operations.put('|', 1);

    for (int index = 0; index < expression.length(); index++){
      if (error == false){
        character = expression.charAt(index);

        // If this character is a digit or a decimal point
        if ((Character.isDigit(character)) || (character.equals('.'))){
          token = token + Character.toString(character);

          // If the expression ends with an operand
          if (index == expression.length()-1){
            Object operand = new Object();

            //Converts token to Double
            try {
              operand = new Double(Double.parseDouble(token));

            }
            catch (NumberFormatException nfe){
              try {
                operand = new Integer(Integer.parseInt(token));
              }
              catch (NumberFormatException nfe2){
              }
            }

            // Since this is a operand, it is enqueued
            queue.enqueue(operand);

          }

          // If the next character is not a digit nor a decimal point, it is an operand
          else if ((Character.isDigit(expression.charAt(index+1)) == false) && (expression.charAt(index+1) != '.')){
            Object operand = new Object();


            //Converts token to Double
            try {
              operand = new Double(Double.parseDouble(token));

            }
            catch (NumberFormatException nfe){

              try {
                operand = new Integer(Integer.parseInt(token));
              }
              catch (NumberFormatException nfe2){
              }

            }
            // Since this is a operand, it is enqueued
            queue.enqueue(operand);

          }
        }

        // If it this character is not part of an operand
        else {
          //If I should push the next token to the stack
          boolean doPush = true;

          if (character.equals('s') || character.equals('i') || character.equals('n')){
            if (character.equals('s')){
              isSin1 = true;
            }
            else if (isSin1 && character.equals('i')){
              isSin2 = true;
            }
            else if (isSin2 && character.equals('n')){
              isSin3 = true;
            }
            else {
              isSin1 = false;
              isSin2 = false;
              isSin3 = false;
            }
            if (isSin3){
              Object sin_operator = new Character('s');
              stack.push(sin_operator);
              isSin3 = false;
            }

          }

          // If this character is operator
          else if (token.equals(Character.toString(character))==false){
            Object original_operator;

            //else{
              original_operator = new Character(character);
              // Gets precedence based on the character
              if (order_of_operations.get((Character)original_operator) != null){
                int current_precedence = order_of_operations.get((Character)original_operator);

                if ((stack.isEmpty() == false) && (order_of_operations.get(stack.peek()) != null) ) {
                  int other_precedence = order_of_operations.get(stack.peek());

                  // If the token is a close-parenthesis [‘)’], pop all the stack elements and enqueue them one by one until an open-parenthesis [‘(‘] is found.
                  if (original_operator.equals(')')){
                    while ((stack.peek() != null )&& (stack.peek().equals('(') == false)){
                      queue.enqueue(stack.pop());
                    }
                    if ((stack.peek() != null )&& stack.peek().equals('(')){
                      stack.pop();
                      //If the token is an close-parenthesis, do not add it to the stack.
                      doPush = false;
                    }
                  }

                  else {
                    // While I do not reach neither an operator of lower precedence nor a right-associative operator of equal precedence nor parentheses,
                    while (!((other_precedence < current_precedence) || (((other_precedence == current_precedence) && (current_precedence == 8 || current_precedence == 3))) || (other_precedence == 10))){
                      // Pop every token on the stack and enqueue them one by one
                      queue.enqueue(stack.pop());
                      if (stack.isEmpty() == false){
                        other_precedence = order_of_operations.get(stack.peek());
                      }
                      else {
                        other_precedence = -1;
                      }
                    }
                  }
                }
              }
              else{
                //Character not recognized
                error = true;
              }
              // Push the original operator onto the stack if it is not a close-parenthesis
              if (doPush){
                stack.push(original_operator);
              }
            //}
          // Reset token
          token = "";
          }

        }
      }
      }

    // At the end of the input, pop every token that remains on the stack and add them to the queue one by one.
    while (stack.isEmpty() == false){
      queue.enqueue(stack.pop());
    }
    //queue.printQueue();
    return queue;
  }

  public MyStack evaluatePostfix(MyQueue queue){
    boolean error = false;
    MyStack<Object> stack = new MyStack<Object>();
    Object next = queue.peek();
    while (next != null && error == false){
      Object result = new Double(1.0);
      Object error_msg = new String();
      if (next instanceof Character){
        /* If it is an operator, pop the appropriate number of operands from the stack (e.g. 2 operands for multiplication,
           1 for logical NOT). Perform the operation on the popped operands, and push the resulting value onto the stack. */
        if (next.equals('!')){
          Object operand1 = stack.pop();
          if (operand1.equals(1.0) || operand1.equals(0.0)){
            if (operand1.equals(1.00)){
              result = 0.00;
            }
            else{
              result = 1.00;
            }
          }
          else {
            error_msg = "ERROR - Input Invalid: '" + operand1 + "' cannot be evaluated by logical '!'. Operand must be '1.0' or '0.0'.";
            error = true;
          }
        }
        else if (next.equals('s')){
          Double operand1 = new Double((Double)stack.pop());
          double op1 = operand1.doubleValue();
          result = Math.sin(op1);
        }
        else{
          Object temp = stack.pop();
          Object temp2 = stack.pop();
          if(temp instanceof Double && temp2 instanceof Double) {
            Double operand1 = (Double)temp;
            Double operand2 = (Double)temp2;
            if (next.equals('+')){
              result = operand1 + operand2;
            }
            else if (next.equals('-')){
              result = operand2 - operand1;
            }
            else if (next.equals('*')){
              result = operand1 * operand2;
            }
            else if (next.equals('/')){
              if (operand1 == 0.0)
              {
                error_msg = "ERROR - Input Invalid: Division by zero.";
                error = true;
              }
              else{
                result = operand2 / operand1;
              }
            }
            else if (next.equals('=')){
              if (operand2.compareTo(operand1)==0){
                result = 1.00;
              }
              else {
                result = 0.00;
              }
            }
            else if (next.equals('>')){
              if (operand2.compareTo(operand1)>0){
                result = 1.00;
              }
              else{
                result = 0.00;
              }
            }
            else if (next.equals('<')){
              if (operand2.compareTo(operand1)<0){
                result = 1.00;
              }
              else {
                result = 0.00;
              }
            }
            else if (next.equals('&')){
              if ((operand1 == 1.0 || operand1 == 0.0) && (operand2 == 1.0 || operand2 == 0.0)){
                if (operand1.equals(1.0) && operand2.equals(1.0)){
                  result = 1.00;
                }
                else{
                  result = 0.00;
                }
              }
              else {
                error_msg = "ERROR - Input Invalid: '" + operand1 + "' and '" + operand2 + "' cannot be evaluated by logical '&'. Operands must be '1.0' or '0.0'.";
                error = true;
              }
            }
            else if (next.equals('|')){
              if ((operand1 == 1.0 || operand1 == 0.0) && (operand2 == 1.0 || operand2 == 0.0)){
                if (operand1.equals(1.0) || operand2.equals(1.0)){
                  result = 1.00;
                }
                else{
                  result = 0.00;
                }
              }
              else {
                error_msg = "ERROR - Input Invalid: '" + operand1 + "' and '" + operand2 + "' cannot be evaluated by logical '|'. Operands must be '1.0' or '0.0'.";
                error = true;
              }
            }
            else if (next.equals('^')){
              result = Math.pow((double)operand1, (double)operand2);
            }
            else if (next.equals('%')){
              result = operand2 % operand1;
            }
            else{
              error_msg = ("ERROR - Input Invalid: '" + next + "' is not recognized. Please try again.");
              error = true;
            }

          }
          else {
            error_msg = ("ERROR - Input Invalid: '" + next + "' is not defined in this context and/or is not an operator handled by this program.");
            error = true;
          }
        }
        if (error_msg.equals("")== false){
          stack.push(error_msg);
        }
        else{
          stack.push(result);
        }

        queue.dequeue();
        next = queue.peek();
      }
      else if (next instanceof Double){
        // If it is an operand, push it onto the stack.
        stack.push(queue.dequeue());
        next = queue.peek();
      }

    }
    return stack;
  }
}
