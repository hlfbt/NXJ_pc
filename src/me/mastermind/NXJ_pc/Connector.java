package me.mastermind.NXJ_pc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommInputStream;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

/**
 *
 * @author Alexander
 */
public class Connector {
    
    private NXTComm nxtComm = null;
    private NXTInfo[] nxtInfo = null;
    private NXTConnector nxtCon = new NXTConnector();
    private OutputStream nxtOut = null;

    public static NXTCommInputStream nxtIn = null;
    
    public Connector() {
        try {
            nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
            nxtInfo = nxtComm.search(null);
            if (nxtInfo.length == 0) {
                nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
                nxtInfo = nxtComm.search(null);
            }
        } catch (NXTCommException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public NXTInfo[] getNXTInfo() {
        return nxtInfo;
    }
    
    public boolean tryConnection(int index, int mode) {
        if (nxtCon.connectTo(nxtInfo[index], mode)) {
            nxtOut = nxtCon.getOutputStream();
            nxtIn = (NXTCommInputStream) nxtCon.getInputStream();
            return true;
        } else {
            return false;
        }
    }
    
    public InputStream getNXTInputStream() {
        return nxtIn;
    }
    
    public boolean writeData(int data) {
        try {
            if (data >=255 || data < 0) {
                return false;
            }
            nxtOut.write(data);
            nxtOut.flush();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public void killNXT() {
        try {
            nxtOut.write(255);
            nxtOut.flush();
        } catch (IOException ex) {
            System.exit(1);
        }
    }
}