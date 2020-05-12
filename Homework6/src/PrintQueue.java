import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Homework 6 PrintQueue
 * 
 * Implement the class below as specified in the
 * homework 6 document.
 * 
 * @author sh4aj
 *
 */

// Don't forget to include the appropriate import statements
/**
 * a thread-safe, FIFO list of Strings for a printer
 */
public class PrintQueue {

    private LinkedList<String> toPrint; // the printer's list of documents
    private Lock documentChangeLock = new ReentrantLock(); // a ReentrantLock lock
    private Condition hasPrintTask = documentChangeLock.newCondition(); // a condition object
    private AtomicBoolean isOn; // atomic boolean variable describing if the
                          // queue is on (accepting jobs)

    // Handle locking and conditions around the
    // enqueueing and dequeuing of documents
    // in this PrintQueue's document list (toPrint)
    // Note: See the BetterBestBank example in Bank.zip
    // or in zip folder 'Thread Example 6 - Bank Deadlock'
    // on Collab.
    // Bank.zip: http://tinyurl.com/cs2110bank

    /**
     * Constructor
     */
    public PrintQueue() {
        toPrint = new LinkedList<String>(); // create the list of documents
        isOn = new AtomicBoolean(true); // turn on the print queue
    }

    /**
     * enqueue Locks the PrintQueue and enqueues the string
     * 
     */
    public void enqueue(String s) {
        documentChangeLock.lock();
        toPrint.add(s); // add to the list of documents
        hasPrintTask.signalAll();
        documentChangeLock.unlock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * dequeue
     * Locks the PrintQueue and dequeues to obtain the first string
     * @return the first {@code String} in the PrintQueue
     */
    public String dequeue() {
        documentChangeLock.lock();
        try {
            while (this.isEmpty()) {
                hasPrintTask.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            documentChangeLock.unlock();
        }

        return toPrint.remove(); // return the first document
    }

    /**
     * 
     * @return whether the current PrintQueue is empty
     */
    public boolean isEmpty(){
        documentChangeLock.lock();
        boolean result = toPrint.isEmpty();
        documentChangeLock.unlock();
        return result;
    }

    /**
     * sets {@code isOn} to false to indicate that the PrintQueue is no longer accepting new jobs 
     */
    public void turnOff(){
        isOn.set(false);
        System.exit(0);
    }

    public synchronized boolean isOn(){
        return isOn.get();
    }
}
