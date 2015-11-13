/*****************************************************
** Document: TestDriver.java
** Author: Nisarga Patel
** Date: 10/13/2015
** License: MIT
** Description: Driver for challenge problem on KPCB
** application.
**
******************************************************/

public class TestDriverThree {
    
    public static void main(String[] args){
        
        FixedHashMap<Integer> integers = new FixedHashMap<>(3);
        FixedHashMap<Boolean> bools = new FixedHashMap<>(3);
        FixedHashMap<Double> doubles = new FixedHashMap<>(3);
        FixedHashMap<String> strings = new FixedHashMap<>(3);
        FixedHashMap<Float> floats = new FixedHashMap<>(3);
        FixedHashMap<Character> chars = new FixedHashMap<>(3);
        
        
        integers.set("stars", 999999999);
        bools.set("nisarga", true);
        doubles.set("Maryland Tax", 0.06);
        strings.set("First Name:", "Nisarga");
        
        floats.set("Pi value", 3.14f);
        
        chars.set("Initial", 'N');
        
        System.out.println("*********** TESTING INTEGERS **************");
        System.out.println(integers);
        
        System.out.println("*********** TESTING BOOLEANS **************");
        System.out.println(bools);
        
        System.out.println("*********** TESTING DOUBLES **************");
        System.out.println(doubles);
        
        System.out.println("*********** TESTING STRINGS **************");
        System.out.println(strings);
        
        System.out.println("*********** TESTING FLOATS **************");
        System.out.println(floats);
        
        System.out.println("*********** TESTING CHARACTERS **************");
        System.out.println(chars);
        
    } 
}