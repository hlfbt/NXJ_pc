package me.mastermind.NXJ_pc;

import java.util.logging.Level;
import java.util.logging.Logger;
import lejos.pc.comm.NXTComm;

/**
 *
 * @author Alexander
 */
public class RemoteController {
    
    private static Connector nxt = new Connector();
    private static Thread receiver;
    
    public static void main(String[] args) {
        GUI.main(args);
    }
    
    public static boolean initalize() {
        if (nxt.getNXTInfo().length == 0) {
            System.out.println("No NXT Found.");
            return false;
        }
        if (!nxt.tryConnection(0,NXTComm.PACKET)) {
            System.out.println("Could not connect to NXT.");
            return false;
        }
        receiver = new Thread(new Receiver());
        receiver.start();
        return true;
    }
    
    public static boolean write(int data) {
        try {
            receiver.wait();
        } catch (InterruptedException ex) {
            System.out.println("\n\n\nwait error\n\n\n");
            Logger.getLogger(RemoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean success = nxt.writeData(data);
        receiver.notify();
        return success;
    }
    
    public static void kill() {
        nxt.killNXT();
    }
}