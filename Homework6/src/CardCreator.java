import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Homework 6 Card Creator
 * 
 * This class defines the thread task that will
 * "come up with" and submit greeting card ideas
 * to the print queue.  We have added the code
 * necessary to read from the file, but it's up to
 * you to handle turning off the printer (keeping
 * track of how many threads are open) and adding
 * the read-in line from the inspiration file to
 * the queue.
 * 
 * @author sh4aj
 *
 */
public class CardCreator implements Runnable {

	/** thread-safe counter for number of threads running CardCreator */
	static AtomicInteger threadCount = new AtomicInteger(0);
	
	/**
	 * Print queue to add new card ideas
	 */
	private PrintQueue printQueue;
	
	/**
	 * Inspiration file name
	 */
	private String filename;
	
	public CardCreator(PrintQueue d, String filename) {
		printQueue = d;
		this.filename = filename;
	}

	/**
	 * Run method that is the main method for the thread
	 */
	@Override
	public void run() {
		Scanner s = null;
		threadCount.incrementAndGet();
		try {
			s = new Scanner(new FileReader(filename));
			while (s.hasNextLine()) {
				String nextLine = s.nextLine();
				printQueue.enqueue(nextLine);
			}
		} catch (IOException e) {
			System.out.println("Could not read file");
		} finally {
			if (s != null){
				s.close();
			}
			
			// turn off the program
			if(threadCount.decrementAndGet() == 0){
				printQueue.turnOff();
			}
		}
	}

}
