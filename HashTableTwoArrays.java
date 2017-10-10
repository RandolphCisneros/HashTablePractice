//This is a hashTable using two 2D arrays, one to store keys and the other to store values. 
//The purpose of the 2D array is to handle collisions without linear probing.
//Note that I can't use generics in this situation because it will produce a run-time error in
//java. We can however pass Objects as the type to substitute this behavior.

public class HashTableTwoArrays {
	private  String [][]keys;
	private  String [][]values;
	
	//The constructor takes the size of the arrays as input. I use a square of the size for
	//storage but this isn't necessary.
	HashTableTwoArrays(int size) {
		keys = new String[size * size][size];
		values = new String[size * size][size];
	}
	
	//First gets position as hashCode of the key. Then it checks if the first item in position's
	//subarray is null. If it is not null, go to the next position in position's subarray.
	//Value gets placed in the same position in its own subarray.
	//I recently changed to check if the key is already in, and if it is it replaces value.
	public  void put(String key, String value){
		int position = hashCode(key);
		for(int i = 0; i < keys[position].length; i++){
			if(keys[position][i] == null || keys[position][i] == key){
				keys[position][i] = key;
				values[position][i] = value;
				return;
			}
		}
		System.out.println("Failed to put " + key + " with hashCode " + position);
	}
	
	//This is a simple hashCode function. In this case I convert the String to 
	//a lowercase charArray and return the remainder of the first character's
	//numeric value divided by the size of the outer array.
	public  int hashCode (String key) {
		char[] codeString = key.toLowerCase().toCharArray();
		return Character.getNumericValue(codeString[0]) % keys.length;
	}
	
	//Takes the key as the argument, finds the position using the hash function,
	//checks if the key is in that array, and if it is it returns the value using
	//that position.
	public  String get(String key){
		int position = hashCode(key);
		for(int i = 0; i < keys[position].length; i++){
			if(keys[position][i] != null && keys[position][i] == key)
				return values[position][i];
		}
		return "Not in map";
	}
			
		
	public static void main(String[] args) {
		HashTableTwoArrays test =new HashTableTwoArrays(10);
		test.put("Cat", "Dog");
		test.put("House", "Car");
		test.put("Hand", "Foot");
		System.out.println(test.get("Hand"));
		System.out.println(test.get("Cat"));
		System.out.println(test.get("House"));
		System.out.println("Success");
		
	}
}
//Insertion and access are O(1) with a good hash function and a good distribution
//of data.
