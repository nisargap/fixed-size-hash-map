/*****************************************************
** Document: FixedHashMap.java
** Author: Nisarga Patel
** Date: 10/13/2015
** License: MIT
** Description: Fixed Size HashMap implementation based
** on problem given in the KPCB Fellows application.
**
******************************************************/

// Templated class FixedHashMap
public class FixedHashMap<T> {

    
    /************************************
    ** Enum needed here to keep track of 
    ** bucket state.
    *************************************/
    private enum state {
        
        OCCUPIED,
        WAS_OCCUPIED,
        FREE
    }
    
    // Keeps track of the fixed size the user passes in
    // This is the max size
    private int size;
    
    // Stores the number of elements in the hash table
    private int currentSize;
    
    // m_size is the actual size of the hash table
    private int m_size;
    
    // m_power is the power of 2 that m_size is
    private int m_power;
    
    // Debug flag to turn on debug print statements
    private boolean debug;
    
    // Stores the keys for the FixedHashMap
    private String[] keys;
    
    // Stores the items, utilizing weak typing here
    private Object[] items;
    
    // Stores the states for the FixedHashMap
    private state[] states;
    
    
    
    /*************************************************
    ** Preconditions: Valid integer size
    ** Postconditions: Creates a fixedHashMap object
    **************************************************/
    public FixedHashMap(int paramSize){
        
        // Sets the size private variable of the hashMap
        size = paramSize;
        
        // Set the debug flag to true to turn on print statements
        debug = false;
        
        
        // The hash table is optimized if it is of M size 2^p
        // This stores the power of two by taking Log Base 2 of the size
        // The ceiling function is used because for example, if the user wants to store 3 things
        // The M size would be 4 and m_power would be 2
        m_power = (int)Math.ceil(Math.log(size)/Math.log(2));
        
        // Setting M size
        m_size = (int)Math.pow(2.0, m_power);
        
        
        keys = new String[m_size];
        
        states = new state[m_size];
        
        // Initialize the items array
        items = new Object[m_size];
       
        // Setting current size to 0
        currentSize = 0;
    }
    
    // Getter for m_size
    public int getM(){
        
        return m_size;
    }
    
    // Getter for keys
    public String[] getKeys(){
        
        return keys;
        
    }
    
    // Getter for currentSize
    public int getCurrentSize(){
        
        return currentSize;
        
    }
    
    // Getter for m_power
    public int getPower(){
        
        return m_power;
        
    }
    
    /*************************************************
    ** Preconditions: Valid string key
    ** Postconditions: Returns index if key exists, else -1
    **************************************************/
    public int keyExists(String key){
        
        for(int i = 0; i < m_size; i++){
            
            if(states[i] == state.OCCUPIED && keys[i].equals(key)){
                
                return i;
                
            }
            
        }
        
        // returning -1 because there can never be a -1 index
        return -1;
        
    }
    
    /*************************************************
    ** Preconditions: Valid hash, index, and key
    ** Postconditions: Finds open bucket for value and returns that index
    **************************************************/
    public int quadraticHashing(int hash, int index, String key){
        
        
        int hashTwo;
        while(states[index] == state.OCCUPIED){
            
            
            if(keys[index].equals(key)){
                
                return index;
            }
            
            // constant one and constant two to be used in quadratic hashing function
            final double c1 = 0.5;
            final double c2 = 0.5;
            hashTwo = (int)Math.floor((hash + index*c1 + index*index*c2) % m_size);
            
            index = indexFor(hashTwo, m_size);
            
            // This is needed in the edge case where there is a 'hole'
            // For example if a key is added on collision, then the original place it
            // hashed to is removed upon a set on the key it will add a duplicate
            // For that reason a WAS_OCCUPIED flag is needed
            if(states[index] == state.WAS_OCCUPIED){
                
                continue;
            }
            
            
        }
        
        return index;
    }
    public boolean insert(String key, Object value, int index){
        
        keys[index] = key;
        items[index] = value;
        states[index] = state.OCCUPIED;
        
        return true;
        
    }
    
    /***************************************************
    ** Preconditions: Valid string key and value
    ** Postconditions: Adds that value to our hashmap
    ****************************************************/
    public boolean set(String key, T value){
        
        // Check if the desired size is reached
        if(currentSize == size){
            
            return false;
            
        }
        
        // Check if the key exists
        int check = keyExists(key);
        if(check != -1){
            
            // Set the value if it does
            items[check] = value;
            return true;
            
        }
        
        // Grab the hashCode() using Java's builtin hashCode()
        int hash = key.hashCode();
        
        // Get the index for that hashCode() based on m_size
        int index = indexFor(hash, m_size);
        
        if(debug == true){
            
            System.out.println("KEY IS: " + key);

            System.out.println("Current KEY THERE: " + keys[index]);
            
        }
        
        
        if(states[index] == state.FREE || states[index] == state.WAS_OCCUPIED){
            
            insert(key, value, index);
            
            if(debug == true){
                System.out.println("The index for: " + value + " is " + index);
                System.out.println("Creates new");
            }
            
            
        }
        else {
            
            if(debug == true){
                System.out.println("Collision!");
                System.out.println("Using quadratic hashing");
                
                System.out.println("Value is set!!!");
            }
            
            index = quadraticHashing(hash, index, key);
            
            
            insert(key, value, index);
            
        }
      
        // Increments the currentSize
        ++currentSize;
        
        return true;
    }
    
    /*******************************************************
    ** Preconditions: Key exists in array
    ** Postconditions: Returns the value associated to that key
    *********************************************************/
    public Object get(String key){
        
        
        int hash = key.hashCode();
        
        int index = indexFor(hash, m_size);
        
        if(keys[index] == null){
            
            return null;
            
        }
        else if (keys[index].equals(key)){
            
            return items[index];
            
        }
        else {
            
            if(debug == true){
                System.out.println("Collision!");
                System.out.println("Using quadratic hashing");
                
                System.out.println("Value is get!");
            }
            
            index = quadraticHashing(hash, index, key);
            
            
            return items[index];
            
        }
        
    }
    
    /***************************************************
    ** Preconditions: Key exists in array
    ** Postconditions: Deletes that key from the array
    ****************************************************/
    public Object delete(String key){
        
        int hash = key.hashCode();
        
        int index = indexFor(hash, m_size);
        
        if(states[index] == state.FREE || states[index] == state.WAS_OCCUPIED){
            
            return null;
            
        }
        else if (keys[index].equals(key)){
            
            Object oldValue = items[index];
            
            items[index] = null;
            keys[index] = null;
            states[index] = state.WAS_OCCUPIED;
            --currentSize;
            return oldValue;
            
        }
        else {
            
            if(debug == true){
                System.out.println("Collision!");
                System.out.println("Using quadratic hashing");
                
                System.out.println("Value is get!");
            }
            
            index = quadraticHashing(hash, index, key);
            
            
            Object oldValue = items[index];
            
            items[index] = null;
            keys[index] = null;
            states[index] = state.WAS_OCCUPIED;
            --currentSize;
            return oldValue;
            
        }
    }
    
    /***************************************************
    ** Preconditions: currentSize and size are defined
    ** Postconditions: Returns load factor
    ****************************************************/
    public float load(){
        
        // the bottom value must be cast as float because Java
        // is statically typed on integer division
        return currentSize/(float)m_size;
        
    }
    
    
    // HELPER FUNCTIONS

    /*****************************************************
    ** Preconditions: valid hash and length of array
    ** Postconditions: returns an index for a hash value
    ******************************************************/
    private int indexFor(int hashCode, int length){
        
        // bitwise and operation with the length - 1 to give a valid index value
        // indicies start at 0 hence the length - 1
        return hashCode & (length-1);
    }
    
    // Print function for nice output
    public String toString(){
        
        String result = "";
        for(int i = 0; i < m_size; i++){
            
            if(keys[i] != null){
                result += keys[i] + " -> " + items[i] + "\n";
            }
            
        }
        result += "Current Size: " + currentSize + "\n";
        
        result += "Max Size: " + size + "\n";
        
        result += "Table Size (M): " + m_size + "\n";
        
        result += "Load Factor (N/M): " + load();
        
        return result;
        
    }
}