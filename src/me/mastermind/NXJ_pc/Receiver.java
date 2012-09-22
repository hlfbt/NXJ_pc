package me.mastermind.NXJ_pc;

import java.io.InputStream;
import lejos.pc.comm.NXTCommInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Receiver implements Runnable {    
    @Override
    public void run() {
        int data;
        NXTCommInputStream nxtIn = Connector.nxtIn;
        while(true) {
            try {
                data = nxtIn.read();
                System.out.println(data);
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
}
