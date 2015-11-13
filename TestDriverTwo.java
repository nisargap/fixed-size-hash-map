/*****************************************************
** Document: TestDriver.java
** Author: Nisarga Patel
** Date: 10/13/2015
** License: MIT
** Description: Driver for challenge problem on KPCB
** application.
**
******************************************************/

public class TestDriverTwo {
    
    public static void main(String[] args){
        
        // create a FixedHashMap of size 3 to store Integers
        
        FixedHashMap<Integer> names = new FixedHashMap<>(11);
        
        
        // testing setting
        System.out.println("**********TESTING SETTING**********");
        names.set("Delaware", 1);
        names.set("Maryland", 7);
        names.set("Pennsylvania", 2);
        names.set("Georgia", 4);
        names.set("Virginia", 10);
        names.set("New Jersey", 3);
        names.set("Hawaii", 50);
        names.set("North Dakota", 39);
        names.set("South Dakota", 40);
        names.set("New Hampshire", 9);
        names.set("New York", 11);
        System.out.println("Result: ");
        System.out.println(names);
        
        // newline for nice formatting
        System.out.println();
        
        // testing getting
        System.out.println("**********TESTING GETTING**********");
        System.out.println("State number " + names.get("Maryland") + " to be admitted to the union was Maryland. ");
        System.out.println("State number " + names.get("Hawaii") + " to be admitted to the union was Hawaii. ");
        
        System.out.println();
        System.out.println("**********TESTING DELETING**********");
        
        System.out.println("Deleting North Dakota -> 39");
        names.delete("North Dakota");
        
        System.out.println("Deleting Hawaii -> 50");
        names.delete("Hawaii");
        
        System.out.println("Result: ");
        System.out.println(names);
        
    } 
}