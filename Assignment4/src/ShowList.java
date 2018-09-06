// -----------------------------------------------------
// Assignment (4)
// Question: (part III & IV)
// Written by: (Mustafa Omran)
// ----------------------------------------------------- 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Mustafa Omran 26745954
 *         <p>
 *         Comp 249
 *         <p>
 *         Assignment # 4
 *         <p>
 *         Due Date 06 /08/ 2018
 *         <p>
 *        Implements Watcable interface, hold the data for TVShow  <p>
 *        Contains methdos to copy a tv show and decide wether two tv shows are equal 
 */

public class ShowList implements Cloneable{
	/**
	 * Inner class that hold object of the tv show and a pointer 
	 */
	private class ShowNode implements Cloneable{
		private TVShow object; 
		private ShowNode pointer;

		/**
		 * get the object of the tv show 
		 * @return object of the tv show 
		 */
		public TVShow getObject() {
			return object;
		}

		/**
		 * set the object of tv show 
		 * @param object object of tv show 
		 */
		public void setObject(TVShow object) {
			this.object = object;
		}

		/**
		 * get the pointer 
		 * @return pointer
		 */
		public ShowNode getPointer() {
			return pointer;
		}

		/**
		 * set the pointer 
		 * @param pointer point ShowNode
		 */
		public void setPointer(ShowNode pointer) {
			this.pointer = pointer;
		}

		/**
		 * defult constructor that set all attributes to null
		 */

		@SuppressWarnings("unused")
		public ShowNode() {
			super();
			object = null; 
			pointer = null; 
		}

		/**
		 * A constructor that takes and initalize all the attributes
		 * @param object TVSHow object
		 * @param pointer ShowNode pointer
		 */
		public ShowNode(TVShow object, ShowNode pointer) {
			super();
			this.object = object;
			this.pointer = pointer;
		} 


		/**
		 * copy constructor 
		 * @param s passed SHowNode to be copied
		 */
		public ShowNode(ShowNode s) {
			this( s.getObject().clone(s.getObject().getShowID()), s.getPointer().clone()); 
		}

		/**
		 * a clone method that uses copy constructor to copy the object
		 */
		public ShowNode clone() { 
			try {
				return (ShowNode)super.clone(); 
			}
			catch (CloneNotSupportedException e) {
				return null; 
			}
			//return new ShowNode(this); 
		}

		@Override
		public String toString() {
			return "ShowNode [object=" +  object + ", pointer=" + pointer + "]";
		}



	}

	private ShowNode head; 
	private int size;


	/**
	 * get the start point of the show node
	 * @return the start point of the node
	 */
	public ShowNode getHead() {
		return head;
	}

	/**
	 * set the start point of the node
	 * @param head set the showNode
	 */
	public void setHead(ShowNode head) {
		this.head = head;
	}

	/**
	 * get the size of the list
	 * @return the size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * set the size of the list
	 * @param size size of the list
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Default constructor that creates an empty list 
	 */
	public ShowList() {
		head = null; 
		size = 0; 
	} 

	/**
	 * Copy constructor to copy the passed object
	 * @param s passed object
	 */
	public ShowList(ShowList s) {
		head.object = s.getHead().object.clone(s.getHead().object.getShowID()) ; 
		ShowNode postion = s.head;  
		size =s.getSize(); 

		while (postion != null) {
			head.setPointer(postion.getPointer().clone());
			head = head.getPointer(); 
			postion = postion.getPointer(); 
		}
	}

	/**
	 * add a show to the start of the node 
	 * @param s TVShow object
	 */
	public void addToStart(TVShow s) {

		head = new ShowNode(s,head); 
		size++; 
	}

	/**
	 * add based on index
	 * @param s which is tv show 
	 * @param index integer where it will be deleted
	 * @throws NoSuchElementException in the case of invalid index
	 */
	public void insertAtIndex(TVShow s, int index) throws NoSuchElementException {
		if (index <0 || index > size) {
			System.out.println("(a valid index must have a value between" + 
					" 0 and " + (size-1)); 
			throw new NoSuchElementException("No such element, the progrma will terminate accordingly"); 
		}
		else {
			if (index ==0) {
				head = new ShowNode(s,head);
				size ++; 
			}
			else {
				ShowNode postion = head;
				int counter = 0; 
				while (counter < index ) {
					postion = postion.getPointer(); 
					counter++; 
				}

				if (postion == null) {
					postion = new ShowNode(s, null) ; 
				}

				else {
					ShowNode after = new ShowNode(postion); 
					postion = new ShowNode (s, after.getPointer()); 
					after = new ShowNode(after.getObject(), null); 
				}
				size++; 
			}
		}

	}

	/**
	 * delete based on index and throws exception if the index is not valid
	 * @param index integer where it will be deleted
	 * @throws NoSuchElementException in the case of invalid index
	 */
	public void deleteFromIndex(int index) throws NoSuchElementException{
		if (index > size) {
			throw new NoSuchElementException("Index is not valid"); 
		}

		ShowNode postion = head; 
		ShowNode beforePostion = head; 

		int counter = 0;
		while (true) {
			if (counter < index) {
				beforePostion =  postion; 
			}
			postion = postion.getPointer(); 
			counter++; 
			if (counter == index) { 
				break; 
			}
		}

		if (postion.getPointer() == null ) {
			postion = null; 
		}

		else {
			beforePostion.setPointer(postion.getPointer());
			postion=null; 
		}
		size--; 

	}

	/**
	 * delete the first object 
	 */
	public void deleteFromStart() {
		if (head != null) {
			head = head.getPointer(); 
		}
	}

	/**
	 * Replace at index 
	 * @param s TVShow
	 * @param index the index we want to replace
	 */
	public void replaceAtIndex(TVShow s, int index) { 

		if (index > size ) {
			return;
		}

		int counter = 0;
		ShowNode postion = head;
		while (counter < index) {
			postion = postion.getPointer();
			counter++; 
		}

		if (postion.getPointer() == null)
			return; 

		else {	
			postion.setObject(s);
		}	
	}

	/**
	 * Find show based on id 
	 * @param showID accept id of the show 
	 * @return the pointer to the id of the show
	 */
	public ShowNode find(String showID) { 

		ShowNode postion = head;
		int counter = 0; 

		while (postion != null) {
			if (postion.getObject().getShowID().equalsIgnoreCase(showID)) {
				System.out.println("We found something, it took: " + counter);
				return postion;
			}
			postion = postion.getPointer(); 
			counter++;                            
		}
		System.out.println("Nothing was found, it took: "+counter);
		return null; 
	}

	/**
	 * A method that return true if it has that show id otherwise false 
	 * @param showID a string of the show id 
	 * @return true if it contains it
	 */
	public boolean contains(String showID) {
		ShowNode postion = head;
		while (postion != null) {
			if (postion.getObject().getShowID().equalsIgnoreCase(showID))
				return true; 
			postion = postion.getPointer();
		}
		return false; 
	}

	/**
	 * return true if the two shows contain the same shows
	 * @param s A show list
	 * @return true if they are the same 
	 */
	public boolean equals(ShowList s) {
		ShowNode passed = s.getHead(); 
		ShowNode classUsed = head; 

		if (s.getSize() != size) { 
			return false; 
		}

		while (passed != null) {
			if (!passed.getObject().equals(classUsed.getObject())) {
				return false; 
			}
			passed = passed.getPointer(); 
			classUsed = classUsed.getPointer(); 
		}
		return true; 
	}

	/**
	 * clone method that invoke the super clone of object calss to return a true copy
	 */
	public Object clone() { 
		try 
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			System.out.println("The clone is not supported");
			return null; 
		}
	}

	/**
	 * @author Mustafa Omran 26745954
	 *         <p>
	 *         Comp 249
	 *         <p>
	 *         Assignment # 4
	 *         <p>
	 *         Due Date 06 /08/ 2018
	 *         <p>
	 *        Contains the main class where all the elements will be tested. 
	 *        <p> try catch and close the program in the case of an error  error  
	 */

	public static class ProcessWishlist {
		/**
		 * public main method 
		 * @param args String array 
		 */
		public static void main (String[] args)  {
			try 
			{

				Scanner keyb = new Scanner (System.in);
				System.out.println("\t\n************ Welcome to your TVGuide **********\n");
				ShowList empty1 = new ShowList(); 
				ShowList empty2= new ShowList(); 

				//try { 
				Scanner read = new Scanner(new FileInputStream("TVGuide.txt")); 
				String key = ""; 

				while (read.hasNext()) { 
					String temp = read.nextLine(); 
					if (temp.length() !=0) {
						key += temp;
					}
					if (temp.length() == 0 )  {

						splitadd(key,empty1); 

						key ="";

					}

				}

				splitadd(key,empty1); 

				read.close();

				Scanner readInterest = new Scanner (new FileInputStream("Interest.txt")); 
				ArrayList<ShowNode> watching = new ArrayList<>(); 
				ArrayList<ShowNode> wishtlist = new ArrayList<>(); 
				String readFrom= ""; 
				while (readInterest.hasNextLine()) {
					readFrom +=  readInterest.nextLine() + " "; 


				}
				String[] array = readFrom.split(" ");
				readInterest.close();
				for (int i =0, k=0 ; i <array.length; i++){
					if (array[i].equalsIgnoreCase("Wishlist")) {
						k++; 
					}
					if (empty1.find(array[i]) != null ) { 
						if (k ==0)
							watching.add(empty1.find(array[i]));
						else {
							wishtlist.add(empty1.find(array[i]));
						}
					}
				}

				while (true) {
					System.out.print("\nDo you want to compare another file name with your wishlist? " );
					String addtoWishlist = keyb.next();

					if (addtoWishlist.equalsIgnoreCase("yes")) {
						System.out.print("Enter the id so we can find it and compare: " );
						String ID = keyb.next(); 
						ShowNode temp = empty1.find(ID);
						if (temp!=null)
							wishtlist.add(temp);
					}
					else {
						break; 
					}
				}

				System.out.println("\nHere is the list of Show you can or cant watch according to your interest: \n");
				for (ShowNode k: wishtlist ) {

					if (k.getObject().isOnSameTime(watching.get(0).getObject()).equalsIgnoreCase("Different time")) {
						if (k.getObject().isOnSameTime(watching.get(1).getObject()).equalsIgnoreCase("Different time")) { 
							System.out.println("User can watch show "+k.getObject().getShowID() +" as user is not currently "
									+ "watching anything else during that time");
						}
						else { 
							System.out.println("User cant watch show "+k.getObject().getShowID() +" as user is begining"
									+ " another show at the same time");
						}
					}
					else { 
						System.out.println("User cant watch show "+k.getObject().getShowID() +" as user is currently "
								+ "watching something else during that time");

					}
				}

				while (true) {
					System.out.print("\nDo you want to compare another file name with your wishlist? " );
					String answer = keyb.next();

					if (answer.equalsIgnoreCase("yes")) {
						System.out.print("Enter the id so we can find it and compare: " );
						String ID = keyb.next(); 
						ShowNode temp = empty1.find(ID);
						if (temp!= null)
							determineWatch(temp,wishtlist);
					}
					else 
						break; 
				}


				while (true) { 
					System.out.print("Do you want to find file names? " );
					String compare = keyb.next();

					if (compare.equalsIgnoreCase("yes")) {
						System.out.print("\tEnter the id so we can find it and compare: " );
						String ID = keyb.next(); 
						ShowNode temp = empty1.find(ID);
						if (temp!= null)
							System.out.println(temp);
					}
					else 
						break; 
				}

				System.out.println("\nHere is empty1 string being printed : \n");
				empty1.print(); 

				empty2 = (ShowList) empty1.clone(); 
				System.out.println("\nHere is empty2 string being printed which is a deep copy of empty1 : \n");
				empty2.print(); 


				//				int delete = checkInt(); 
				empty2.deleteFromIndex(2);
				System.out.println("\nHere is empty2 string being printed after deleting from index 2: \n");
				empty2.print();

				empty2.deleteFromStart();
				System.out.println("\nHere is empty2 after deleting from start : \n");
				empty2.print(); 


				System.out.print("\nPlease insert a ShowId to see if it we have it in our list : ");
				String id = keyb.next(); 

				if (empty1.contains(id))
					System.out.println("\tYes we have it in our list" );

				else 
					System.out.println("\tWe couldn't find anything with the input");


				empty2.replaceAtIndex(empty1.getHead().getObject(), 5);

				System.out.println("\nHere is empty2 after replace at index 5 with the head of empty 1 : \n");
				empty2.print(); 

				System.out.println("\n Now we will compare if empty 1 and empty 2 are equals "); 
				if (empty1.equals(empty2) ) {
					System.out.println("\tThey are  equal to each other ");
				}
				else {
					System.out.println("\tThey are not equal to each other ");
				}

				keyb.close();

				System.out.println("\nNow we will produce a deep copy of a show node and a deep copy of a TVShow ");
				TVShow temp = empty1.getHead().getObject().clone("Mustafa"); 
				ShowNode temp2 = empty2.getHead().clone();
				System.out.println("\tThe TV show deep copy is " + temp);
				System.out.println("\tThe ShowNode deep copy is " + temp2);


				System.out.println("\nNow we will compare if 2 TVShow are the same"); 
				if (temp.equals(empty1.getHead().getObject())) {
					System.out.println("\t They are the same"); 
				}
				else 
					System.out.println("\t They are not the same same"); 

				System.out.println("\nNow we will print empty1 for easy compare \n\t");
				empty1.print();
			}
			catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
			catch (FileNotFoundException e) {
				System.out.println("File is not found the program will terminate");
			}


			catch (Exception e) {
				System.out.println(e.getCause());
			}

			finally 
			{
				System.out.println("\n\t*********************** Thank you for using our services ******************** ");

			}
		}
	}
	//
	//	/**
	//	 * check if the int entered is valid, and return the int 
	//	 * @return int 
	//	 */
	//	public static int checkInt() {
	//		Scanner keyb = new Scanner (System.in); 
	//		boolean temp = true; 
	//		int delete =0; 
	//		
	//		do {
	//			try
	//			{
	//	
	//				delete = 0; 
	//				System.out.print("\nPlease insert a number to be deleted from the index of empty2 : ");
	//				delete = keyb.nextInt(); 
	//				temp = false;  
	//			}
	//			catch (Exception  e) {
	//				System.err.println("Please enter a valid numerical value");
	//				 keyb = new Scanner (System.in); 
	//				
	//			}
	//			finally {
	//				keyb.close();
	//			}
	//		} while (temp); 
	//		
	//		return delete; 
	//		
	//	}


	/**
	 * Determine either we can watch the show or not 
	 * @param temp a show node 
	 * @param wishtlist an array of showNodes
	 */
	public static void determineWatch(ShowNode temp, ArrayList<ShowNode> wishtlist ) { 
		for (ShowNode k : wishtlist) {
			if (k.getObject().isOnSameTime(temp.getObject()).equalsIgnoreCase("Different time")){ 
				System.out.println("User can watch show "+k.getObject().getShowID() +" as user is not currently "
						+ "watching anything else during that time");
				System.out.println(k.getObject() +" " +temp.getObject());
			}
			else {
				System.out.println("User cant watch show "+k.getObject().getShowID() +" as user is currently "
						+ "watching something else during that time");
				System.out.println(k.getObject() +" " +temp.getObject()); 
			}
		}
	}	
	/**
	 * Add to the start of the empty list using the passed string and remove any duplicates
	 * @param key string  to be placed in TVShow containing everything in its constructor
	 * @param empty1 show list to place the TVShow in 
	 */
	public static void splitadd(String key, ShowList empty1) {
		String[] split = key.split(" ");

		String showID = split[0]; 
		String showName = split[1]; 

		double startTime = Double.parseDouble(split[2].substring(0,5));	
		double endTime = Double.parseDouble(split[3].substring(0));	

		TVShow addtoList = new TVShow(showID, showName, startTime, endTime);

		ShowNode postion = empty1.head; 
		while (postion!=null) {
			if (postion.getObject().equals(addtoList)) 
				return; 

			postion = postion.getPointer(); 

		}
		empty1.addToStart(addtoList); 
	}
	/**
	 * print the ShowNode with all its pointers
	 */
	public void print () { 
		ShowNode postion = head; 

		while (postion != null) {

			System.out.println(postion);
			postion = postion.getPointer(); 
		}

		System.out.println("");
	}
}


