// -----------------------------------------------------
 // Assignment (4)
 // Question: (part I)
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
 *         Interface that has method isOnSameTime that need implementation
 */
public interface Watchable {
	
	/**
	 * compare if a TVShow is on the same time frame or overlaps with another tv show 
	 * @param s TVShow
	 * @return a string with the results 
	 */
	public String isOnSameTime(TVShow s); 

}
