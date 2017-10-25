/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acousticfield3d.protocols;

import acousticfield3d.simulation.AnimKeyFrame;
import acousticfield3d.simulation.TransState;
import acousticfield3d.simulation.Transducer;
import acousticfield3d.utils.uartComm.SerialComms;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author am14010
 */
public class DeviceConnection implements SerialComms.Listener{
    protected SerialComms serial;
    
    public void connect(int port){
        disconnect();
        serial = new SerialComms(port, getSpeed(), this);
    }
    
    public int getSpeed(){
        return 115200;
    }
    
    public int getDivs(){
        return 20;
    }
    
    public void disconnect(){
        if (serial != null) {
            try {
                serial.disconnect();
            } catch (Exception e) {
            }
            serial = null;
        }
    }
	
    
    public void setFrequency(float frequency){}
    public void switchBuffers(){}
    
    public void sendDurations(final int[] durations){}
    
    public void sendPattern(final List<Transducer> transducers, float power){}
    
    public void sendAnim(final List<AnimKeyFrame> keyFrames){}
  
    public byte[] calcSignals01(final int nTrans, final List<Transducer> transducers){
        return calcSignals01(nTrans, transducers, getDivs());
    }
    
    public static byte[] calcSignals01(final int nTrans, final List<Transducer> transducers, final int divs){
        final int transPerByte = 8;
        final int divsHalf = divs / 2;
        final int nBytes = nTrans * divs / transPerByte;
        final int bytesPerDiv = nTrans / transPerByte;
        
        final byte[] data = new byte[nBytes];
        
        for(Transducer t : transducers){
            final int n = t.getDriverPinNumber();
            if (t.getAmplitude() > 0.0f){
                if (n >= 0 && n < nTrans){ //is it within range
                    final int phase = t.getDiscPhase(divs);
                    int targetByte = n / transPerByte;

                    final int value = 1 << (n - targetByte * transPerByte);
                   
                    for (int i = 0; i < divsHalf; ++i) {
                        final int d = (i + phase) % divs;
                        data[ targetByte + d*bytesPerDiv ] |= value;
                    }
                    
                }
                
            }
        }
        
        return data;
    }
    
    public static byte[] calcSignals01AnimFrame(final int nTrans, final Collection<TransState> transducers, final int divs){
        final int transPerByte = 8;
        final int divsHalf = divs / 2;
        final int nBytes = nTrans * divs / transPerByte;
        final int bytesPerDiv = nTrans / transPerByte;
        
        final byte[] data = new byte[nBytes];
        
        for(TransState t : transducers){
            final int n = t.getTransducer().getDriverPinNumber();
            if (t.getAmplitude() > 0.0f){
                if (n >= 0 && n < nTrans){ //is it within range
                    final int phase = Transducer.calcDiscPhase(t.getPhase(), divs);
                    int targetByte = n / transPerByte;

                    final int value = 1 << (n - targetByte * transPerByte);
                   
                    for (int i = 0; i < divsHalf; ++i) {
                        final int d = (i + phase) % divs;
                        data[ targetByte + d*bytesPerDiv ] |= value;
                    }
                    
                }
                
            }
        }
        
        return data;
    }

    @Override
    public void rxMsg(byte[] data, int len) {
  
    }
    
}
