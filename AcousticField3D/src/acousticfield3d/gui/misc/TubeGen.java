/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acousticfield3d.gui.misc;

import acousticfield3d.gui.MainForm;
import acousticfield3d.math.M;
import acousticfield3d.math.Matrix4f;
import acousticfield3d.math.Vector3f;
import acousticfield3d.scene.Entity;
import acousticfield3d.simulation.Transducer;
import acousticfield3d.utils.BufferedImageView;
import acousticfield3d.utils.FileUtils;
import acousticfield3d.utils.Parse;
import acousticfield3d.utils.TextFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author am14010
 */
public class TubeGen extends javax.swing.JFrame {
    final MainForm mf;
    
    public TubeGen(MainForm mf) {
        this.mf = mf;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heighRender = new javax.swing.JCheckBox();
        plusLambdaButton = new javax.swing.JButton();
        decLambdaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        discPhasesText = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        uniformPhasesButton = new javax.swing.JButton();
        genTextButton = new javax.swing.JButton();
        zLayersText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        xyPixelsText = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rowsText = new javax.swing.JTextField();
        finsCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TubeGen");

        heighRender.setText("Height rendering");
        heighRender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heighRenderActionPerformed(evt);
            }
        });

        plusLambdaButton.setText("+Lambda");
        plusLambdaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLambdaButtonActionPerformed(evt);
            }
        });

        decLambdaButton.setText("-Lambda");
        decLambdaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decLambdaButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Disc Phases:");

        discPhasesText.setText("8");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jButton4.setText("Export Image");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        uniformPhasesButton.setText("Uniform Phases");
        uniformPhasesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniformPhasesButtonActionPerformed(evt);
            }
        });

        genTextButton.setText("GenText");
        genTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genTextButtonActionPerformed(evt);
            }
        });

        zLayersText.setText("45");

        jLabel2.setText("ZLayers");

        jLabel3.setText("XYpixels");

        xyPixelsText.setText("512");

        jButton5.setText("Export Layers");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Rows");

        rowsText.setText("8");

        finsCheckbox.setText("fins");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(finsCheckbox))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discPhasesText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uniformPhasesButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zLayersText, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xyPixelsText))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(genTextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rowsText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(heighRender)
                                .addGap(18, 18, 18)
                                .addComponent(plusLambdaButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(decLambdaButton))
                            .addComponent(jButton5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heighRender)
                    .addComponent(plusLambdaButton)
                    .addComponent(decLambdaButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(discPhasesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton)
                    .addComponent(uniformPhasesButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rowsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(genTextButton)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zLayersText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(xyPixelsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(finsCheckbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void heighRenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heighRenderActionPerformed
        final boolean ug = heighRender.isSelected();
        for(Transducer t :  mf.simulation.transducers){
            if (ug){
                t.snapHeight();
            }else{
                t.restoreHeight();
            }
            t.useHeightRendering = ug;
        }
        mf.needUpdate();
    }//GEN-LAST:event_heighRenderActionPerformed

    private void addPhaseToSelection(float inc){
        for(Entity e : mf.selection){
            if (e instanceof Transducer){
                Transducer t = (Transducer)e;
                t.setPhase( inc + t.getPhase() );
            }
        }
    }
    private void plusLambdaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLambdaButtonActionPerformed
        addPhaseToSelection( 2.0f );
        mf.needUpdate();
    }//GEN-LAST:event_plusLambdaButtonActionPerformed

    private void decLambdaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decLambdaButtonActionPerformed
        addPhaseToSelection( - 2.0f );
        mf.needUpdate();
    }//GEN-LAST:event_decLambdaButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        final int n = Parse.toInt( discPhasesText.getText() );
        float disc = 2.0f / n;
        
        for(Transducer t : mf.simulation.getTransducers()){
            final float phase = M.discretize(t.getPhase(), disc);
            t.setPhase( phase );
        }
        
        mf.needUpdate();
    }//GEN-LAST:event_okButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //capture the screen
        BufferedImage bi = mf.captureScreen();
        final int w = bi.getWidth();
        final int h = bi.getHeight();
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setColor( Color.RED );
        
        //iterate over the transducers rendering their height
        Matrix4f projection = mf.scene.getCamera().getProjection();
        
        Matrix4f view = new Matrix4f();
        mf.scene.getCamera().getTransform().copyTo(view);
        view.invertLocal();
    
        Matrix4f model = new Matrix4f();
        Matrix4f viewModel = new Matrix4f();
        Matrix4f projectionViewModel = new Matrix4f();
        
        final float mSpeed = mf.simulation.getMediumSpeed();
        
        final HashMap<Float, Integer> bom = new HashMap<>();
        
        for(Transducer t : mf.simulation.getTransducers()){
            t.getTransform().copyTo(model);
            view.mult(model, viewModel);
            projection.mult(viewModel, projectionViewModel);
            
            Vector3f pos = new Vector3f();
            projectionViewModel.multiplyPoint(Vector3f.ZERO, pos);
            pos.multLocal(1,-1,1).addLocal(1.0f).divideLocal(2.0f);
            
            final int x = (int)(pos.x * w );
            final int y = (int)(pos.y * h );
            g2.setColor( Color.BLACK );
            g2.fillOval(x, y, 4, 4);
            g2.setColor( Color.WHITE );
            g2.fillOval(x, y, 2, 2);
            
            final float waveLength = mSpeed / t.getFrequency();
            final float length = waveLength * t.getPhase() / 2.0f * 1000;
            g2.drawString( mf.formats.dc2(length) , x, y);
            
            if (bom.containsKey( length )){
                bom.put(length, bom.get(length) + 1);
            }else{
                bom.put(length, 1);
            }
        }
        
        
        //generate bom
        StringBuilder sb = new StringBuilder();
        for(Float length : bom.keySet()){
            sb.append("length " + length + " amount " + bom.get(length) + "\n");
        }
        
        //show image
        g2.dispose();
        BufferedImageView biw = new BufferedImageView("Lenghts", bi);
        biw.setLocationRelativeTo(mf);
        biw.setVisible(true);
        
        //show bom
        TextFrame.showText("BOM lengths", sb.toString(), this);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void uniformPhasesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniformPhasesButtonActionPerformed

        for(Transducer t : mf.simulation.getTransducers()){
            float phase = t.getPhase();
            phase = phase % 2.0f;
            t.setPhase( phase );
        }
        
        mf.needUpdate();
    }//GEN-LAST:event_uniformPhasesButtonActionPerformed

    private void genTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genTextButtonActionPerformed
        StringBuilder sb = new StringBuilder();
        final int perRow = Parse.toInt( rowsText.getText() );
        
        int counter = 0;
        for(Entity e : mf.selection){
            if (e instanceof Transducer){
                Transducer t = (Transducer)e;
                final float phase = t.getPhase() * 180f;
                sb.append( mf.formats.dc2( phase ));
                counter++;
                if (counter >= perRow){
                    sb.append("\n");
                    counter = 0;
                }else{
                    sb.append(", ");
                }
                
            }
        }
        
        TextFrame.showText("Phases", sb.toString(), this);
    }//GEN-LAST:event_genTextButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        final String dir = FileUtils.selectDirectory(this, "select", null);
        if (dir != null){
            final boolean fins = finsCheckbox.isSelected();
            final int nLayers = Parse.toInt( zLayersText.getText() );
            final int xyPixels = Parse.toInt( xyPixelsText.getText() );
            final Transducer[][] trans = getTransducersInArray( Parse.toInt( rowsText.getText() ) );
            
            final int wi = trans.length;
            final int hi = trans[0].length;
            final int pbx = xyPixels / wi;
            final int pby = xyPixels / hi;
            
            final float lambda = mf.simulation.getWavelength();
            
            for(int i = 0; i < nLayers+1; ++i){
                final float prevHeight = lambda * (nLayers-i+1) / nLayers;
                final float currentHeight = lambda * (nLayers-i) / nLayers;
                
                BufferedImage bi = new BufferedImage(xyPixels, xyPixels, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D)bi.getGraphics();
                
                g2.setColor( Color.WHITE);
                g2.fillRect(0, 0, xyPixels, xyPixels);
                
                for(int ix = 0; ix < wi; ++ix){
                    for(int iy = 0; iy < hi; ++iy){
                        final Transducer t = trans[ix][iy];
                        final float length = t.getPhase() / 2.0f * lambda;
                        
                        if (length >= currentHeight){
                            g2.setColor( Color.BLACK);
                            g2.fillRect(ix*pbx, iy*pby, pbx, pby);
                            if (length < prevHeight){ //first layer
                                g2.setColor( Color.RED); //emitter
                                g2.fillOval(ix*pbx+2, iy*pby+2, pbx-4, pby-4);
                            }else{
                                g2.setColor( Color.WHITE); //hole
                                g2.fillOval(ix*pbx+2, iy*pby+2, pbx-4, pby-4);
                                if (fins){
                                    g2.setColor( Color.BLACK);
                                    final int thickness = 3;
                                    final int t2 = (thickness-1)/2;
                                    
                                    g2.fillRect(ix*pbx, iy*pby+pby/2-t2, pbx, thickness);
                                    g2.fillRect(ix*pbx+pbx/2-t2, iy*pby, thickness, pby);
                                  
                                }
                            }
                            
                            
                        }
                        
                    }
                }
                
                bi.flush();
                try {
                    ImageIO.write(bi, "png", new File(dir, i + ".png"));
                } catch (IOException ex) {
                    Logger.getLogger(TubeGen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public Transducer[][] getTransducersInArray(final int rows){
        // get transducers
        final ArrayList<Transducer> trans = new ArrayList<>( mf.simulation.getTransducers());
        
        // sort by order
        trans.sort( new Comparator<Transducer>() {
            @Override
            public int compare(Transducer o1, Transducer o2) {
                return Integer.compare(o1.getOrderNumber(), o2.getOrderNumber());
            }
        });
        
        //create the matrix
        final int W = Parse.toInt( rowsText.getText() );
        final int H = trans.size() / W;
        final Transducer[][] m = new Transducer[W][H];
        
        int x = 0, y = 0;
        for (Transducer t : trans) {
            m[x][y] = t;
            
            ++y;
            if (y == H) {
                y = 0;
                ++x;
            }
            
        }
        
        return m;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton decLambdaButton;
    private javax.swing.JTextField discPhasesText;
    private javax.swing.JCheckBox finsCheckbox;
    private javax.swing.JButton genTextButton;
    private javax.swing.JCheckBox heighRender;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton okButton;
    private javax.swing.JButton plusLambdaButton;
    private javax.swing.JTextField rowsText;
    private javax.swing.JButton uniformPhasesButton;
    private javax.swing.JTextField xyPixelsText;
    private javax.swing.JTextField zLayersText;
    // End of variables declaration//GEN-END:variables
}
