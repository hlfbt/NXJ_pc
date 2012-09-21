/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mastermind.NXJ_pc;

import lejos.pc.comm.NXTComm;

/**
 *
 * @author Alexander
 */
public class RemoteController {

    /**
     * @param args the command line arguments
     */
    
    private static Connector nxt = new Connector();
    
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
        return true;
    }
    
    public static boolean write(int data) {
        return nxt.writeData(data);
    }
    
    public static void kill() {
        nxt.killNXT();
    }
}