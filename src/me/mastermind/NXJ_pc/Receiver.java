package me.mastermind.NXJ_pc;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Receiver implements Runnable {
    public static InputStream nxtIn;
    @Override
    public void run() {
        int data;
        this.nxtIn = Connector.nxtIn;
        while(true) {
            try {
                data = nxtIn.read();
                if (data == 255) {
                    System.out.println("Shutdown by NXT");
                    System.exit(0);
                }
            } catch (Exception ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
//                System.exit(0); // workaround because it doesn't get the data packet for some reason...
            }
        }
    }
    public void stopThread(Thread thread) {
        thread.interrupt();
    }
}