/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acousticfield3d.protocols;

import acousticfield3d.simulation.Simulation;
import acousticfield3d.simulation.Transducer;
import java.util.List;
import java.net.Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author am14010
 */

public class ZynqFPGA extends ArduinoNano{
    String ip;
    PrintWriter out;
    
    public ZynqFPGA(String ip) {
        this.ip = ip;
        Socket socket = null;
        try {
            socket = new Socket(ip, 1234);
            OutputStream outstream = socket.getOutputStream(); 
            PrintWriter out = new PrintWriter(outstream);
            this.out = out;
        } catch (Exception e) {
            System.err.print(e);
        }
    }
    
    @Override
    public int getDivs() {
        return 16;
    }

    @Override
    public int getTransducers() {
        return 19*19; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSpeed() {
        return 115200;
    }
    
    @Override
    public void sendPattern(final List<Transducer> transducers, float power) {
      
        final int size = getTransducers();
        StringBuilder sb = new StringBuilder();
        
        //send data
        for(int i = 0; i < size; ++i){
            double phase = (transducers.get(i).getPhase() * 0.5) + 8;
            phase = phase - (int)phase;
            phase = phase * 255;
            sb.append((int)phase + ";");
        }
        
        sb.append("\n");

        System.out.println(sb);
        
        Socket socket = null;
        try {
            String toSend = sb.toString();

            out.print(toSend );
            out.flush();
        } catch (Exception e) {
            System.err.print(e);
        }
        
        
            
        switchBuffers();
    }
}
