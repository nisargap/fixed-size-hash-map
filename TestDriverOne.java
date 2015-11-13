/*****************************************************
** Document: TestDriver.java
** Author: Nisarga Patel
** Date: 10/13/2015
** License: MIT
** Description: Driver for challenge problem on KPCB
** application.
**
******************************************************/

public class TestDriverOne {
    
    public static void main(String[] args){
        
        // create a FixedHashMap of size 3 to store Integers
        
        FixedHashMap<String> names = new FixedHashMap<>(5);
        
        
        // testing setting
        System.out.println("**********TESTING SETTING**********");
        names.set("Maryland", "Annapolis");
        names.set("New Jersey", "Trenton");
        names.set("Hawaii", "Honolulu");
        names.set("India", "New Delhi");
        names.set("USA", "Washington D.C.");
        System.out.println("Result: ");
        System.out.println(names);
        
        // newline for nice formatting
        System.out.println();
        
        // testing getting
        System.out.println("**********TESTING GETTING**********");
        System.out.println(names.get("Maryland") + " is the capital of Maryland ");
        System.out.println(names.get("New Jersey") + " is the capital of New Jersey ");
        
        System.out.println();
        System.out.println("**********TESTING DELETING**********");
        
        System.out.println("Deleting Maryland -> Anapolis");
        names.delete("Maryland");
        
        System.out.println("Deleting Hawaii -> Honolulu");
        names.delete("Hawaii");
        
        System.out.println("Result: ");
        System.out.println(names);
        
    } 
}