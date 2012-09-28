package me.mastermind.NXJ_pc;

import lejos.pc.comm.NXTComm;

/**
 *
 * @author Alexander
 */
public class RemoteController {
    
    private static Connector nxt = new Connector();
    private static Receiver receiver = new Receiver();
    private static Thread receiverThread;
    
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
        if (nxt.getNXTInfo()[0].protocol == 2) {
            receiverThread = new Thread(receiver);
            receiverThread.start();
        }
        return true;
    }
    
    public static boolean write(int data) {
        boolean success = nxt.writeData(data);
        return success;
    }
    
    public static void kill() {
        nxt.killNXT();
    }
}