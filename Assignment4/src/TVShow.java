// -----------------------------------------------------
 // Assignment (4)
 // Question: (part 2)
 // Written by: (Mustafa Omran)
 // ----------------------------------------------------- 
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

public class TVShow implements Watchable, Cloneable {
	private String showID; 
	private String showName; 
	private double startTime; 
	private double endTime;
	
	
	/**
	 * get the show id 
	 * @return the show id in string
	 */
	public String getShowID() {
		return showID;
	}


	/**
	 * set the show id
	 * @param showID accept a strong show id
	 */
	public void setShowID(String showID) {
		this.showID = showID;
	}

	/**
	 * get the show name
	 * @return a string of the show name
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * set the show name
	 * @param showName paramter show name
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * get the start time of the show
	 * @return return the start time of the show
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * set the start time of the show 
	 * @param startTime start time of the show 
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	/**
	 * end time of the show 
	 * @return the end time of the show
	 */
	public double getEndTime() {
		return endTime;
	}


	/**
	 * set the end time of the show 
	 * @param endTime accept parameter end time of the show
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * copy Constructor that changes the id of the show
	 * @param s the tv show passed 
	 * @param value the new id of Tv show
	 */
	public TVShow (TVShow s, String value ) {
		this(value, s.getShowName(), s.getStartTime(), s.getEndTime());
	}
	

	/**
	 * A constructor that initialize all the attributes of the class
	 * @param showID the show id 
	 * @param showName the show name
	 * @param startTime the starting time of the show 
	 * @param endTime the end time of the show 
	 */
	public TVShow(String showID, String showName, double startTime, double endTime) {
		super();
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * clone method that copy the passed object and give it a new string ID using copy constructor
	 * @param value the value of the new id 
	 * @return the new copied thing 
	 */
	public TVShow clone (String value) { 
		return new TVShow(this, value); 
	}

	/**
	 * implements Watcable method and return a message based on time of the show
	 * @param s of a tv show object
	 */
	public String isOnSameTime(TVShow s) {		
		
		if (s.getStartTime() == startTime && s.getEndTime() == endTime) {
			return "Same time"; 
		}
		else if ((startTime < s.startTime && s.startTime < endTime) || (startTime > s.startTime && s.endTime > startTime)) {
	           return "Some overlap";
		}
		else {
			return "Different time"; 
		}

	} 
	
	/**
	 * toString method that return a string of all attributes with a message
	 */
	public String toString() { 
		return "The show ID is " + showID + ", Show name "+ showName + ", It start at: "+ startTime
				+", and ends at: " + endTime; 
	}
	
	/**
	 * Compare if  2 Tv shows are the same except for their id
	 * @param compare the TVshow passed 
	 * @return true if they are equal 
	 */
	public boolean equals(TVShow compare) {
		return showName.equalsIgnoreCase(compare.getShowName()) && startTime == compare.getStartTime()
				&& endTime == compare.getEndTime();
	}
	
}
