/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acousticfield3d.gui.panels;

import acousticfield3d.algorithms.SimplePhaseAlgorithms;
import acousticfield3d.gui.MainForm;
import acousticfield3d.math.Vector3f;
import acousticfield3d.scene.Entity;
import acousticfield3d.scene.MeshEntity;
import acousticfield3d.simulation.AnimKeyFrame;
import acousticfield3d.simulation.Animation;
import acousticfield3d.simulation.Transducer;
import acousticfield3d.utils.Parse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

import acousticfield3d.gui.panels.ControlHapticObject;

import acousticfield3d.math.Ray;
import acousticfield3d.utils.Color;

/**
 *
 * @author Asier
 */
public class MovePanel extends javax.swing.JPanel {
    final MainForm mf;
    final Controller controller;
  
    Vector3f startPosition;
    float time;
    Vector3f snapBeadPosition = new Vector3f();
    
    SampleListener listener = new SampleListener();
    
    final float TILT = 0.0f; //Leap motion x-axis tilt angle in degree
    final float ZOFFSET = 0.0f; //Z distance from center of the leap to center of the array in mm
    
    Vector tiltReferenceY = new Vector(0f, (float)Math.cos(Math.toRadians(TILT)), -(float)Math.sin(Math.toRadians(TILT)));
    Vector tiltReferenceZ = new Vector(0f, (float)Math.sin(Math.toRadians(TILT)), (float)Math.cos(Math.toRadians(TILT)));
    
    class SampleListener extends Listener {
        public void onInit(Controller controller) {
            System.out.println("Initialized");
        }

        public void onConnect(Controller controller) {
            System.out.println("Connected");
            controller.enableGesture(Gesture.Type.TYPE_SWIPE);
            controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
            controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
            controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        }

        public void onDisconnect(Controller controller) {
            //Note: not dispatched when running in a debugger.
            System.out.println("Disconnected");
        }

        public void onExit(Controller controller) {
            System.out.println("Exited");
        }

        public void onFrame(Controller controller) {
            // Get the most recent frame and report some basic information
            Frame frame = controller.frame();
            /*System.out.println("Frame id: " + frame.id()
                             + ", timestamp: " + frame.timestamp()
                             + ", hands: " + frame.hands().count()
                             + ", fingers: " + frame.fingers().count()
                             + ", tools: " + frame.tools().count()
                             + ", gestures " + frame.gestures().count());*/

            //Get hands
            Pointable index = frame.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
            Vector i1 = index.tipPosition();
            //System.out.println("Index Finger absolut: " + i1);
            
            //Vector tiltReference = new Vector(0f, 1f, 1f);
            float x = i1.dot(Vector.xAxis());
            float y = i1.dot(tiltReferenceY);
            float z = i1.dot(tiltReferenceZ);
            
            //System.out.println("Index Finger tilted: (" + x + ", " + y + ", " + z + ")");
            setBeadPosition(x / 1000.0f, y / 1000.0f, (z - ZOFFSET) / 1000.0f);
            
            float c = checkCollision(x / 1000.0f, y / 1000.0f, (z - ZOFFSET) / 1000.0f);
            System.out.println("Collision distance: (" + c + ")");

            
            /*if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
                System.out.println();
            }*/
        }
    }
    
    public float checkCollision(float x, float y, float z) {
        //mf.clearSelection();
        float distance = 1337.0f;
        for(MeshEntity me : mf.scene.getEntities()){
            if ( (me.getTag() & Entity.TAG_OBJ) != 0){
                //me.selected = true;
                //mf.selection.add(me);
                
                float newDistance = me.rayToBox(new Ray(new Vector3f(x, y, z), new Vector3f(x, y, z), false));
                if (newDistance < distance && newDistance != -1.0f) {
                    distance = newDistance;
                    me.setAlpha(0.5f);
                }
                else {
                    me.setAlpha(1);
                }
            }
        }
        return distance;
    }

    
    public MovePanel(MainForm mf, Controller controller) {
        this.mf = mf;
        this.controller = controller;
        time = 0.0f;
        startPosition = new Vector3f();
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

        lateralTrapGroup = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        beadNSpinner = new javax.swing.JSpinner();
        autoCalcCheck = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        speedText = new javax.swing.JTextField();
        leftButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        backwardsButton = new javax.swing.JButton();
        autoSendCheck = new javax.swing.JCheckBox();
        resetButton = new javax.swing.JButton();
        snapButton = new javax.swing.JButton();
        autoAddCheck = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        periodsLateralText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        periodsF1Text = new javax.swing.JTextField();
        offsetF1Text = new javax.swing.JTextField();
        periodsF2Text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        offsetF2Text = new javax.swing.JTextField();
        multiTrapCheck = new javax.swing.JCheckBox();
        neutralButton = new javax.swing.JButton();
        lateralVortexCheck = new javax.swing.JRadioButton();
        lateralTwinCheck = new javax.swing.JRadioButton();
        lateralQuadCheck = new javax.swing.JRadioButton();
        lateralHoloCheck = new javax.swing.JRadioButton();
        f1HoloCheck = new javax.swing.JCheckBox();
        leapEnableCheck = new javax.swing.JCheckBox();

        jLabel2.setText("N:");

        beadNSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        autoCalcCheck.setSelected(true);
        autoCalcCheck.setText("calc");

        jLabel3.setText("Length:");

        speedText.setText("0.001");

        leftButton.setText("L");
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });

        upButton.setText("U");
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        rightButton.setText("R");
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });

        downButton.setText("D");
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        forwardButton.setText("F");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        backwardsButton.setText("B");
        backwardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardsButtonActionPerformed(evt);
            }
        });

        autoSendCheck.setSelected(true);
        autoSendCheck.setText("send");

        resetButton.setText("R");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        snapButton.setText("S");
        snapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapButtonActionPerformed(evt);
            }
        });

        autoAddCheck.setText("genAnim");

        jLabel1.setText("tLateral");

        periodsLateralText.setText("40");

        jLabel4.setText("F1");

        periodsF1Text.setText("20");

        offsetF1Text.setText("0.01");

        periodsF2Text.setText("20");

        jLabel5.setText("F2");

        offsetF2Text.setText("-0.01");

        multiTrapCheck.setText("use multitrap");

        neutralButton.setText("0");
        neutralButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neutralButtonActionPerformed(evt);
            }
        });

        lateralTrapGroup.add(lateralVortexCheck);
        lateralVortexCheck.setSelected(true);
        lateralVortexCheck.setText("PVortex");

        lateralTrapGroup.add(lateralTwinCheck);
        lateralTwinCheck.setText("Twin");

        lateralTrapGroup.add(lateralQuadCheck);
        lateralQuadCheck.setText("Quad");

        lateralTrapGroup.add(lateralHoloCheck);
        lateralHoloCheck.setText("Holo");

        f1HoloCheck.setText("Holo");

        leapEnableCheck.setText("leap");
        leapEnableCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leapEnableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(neutralButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(2, 2, 2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(downButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(47, 47, 47)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(forwardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4))
                                    .addComponent(backwardsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(snapButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(autoAddCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedText))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(periodsF1Text)
                            .addComponent(periodsF2Text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(offsetF1Text)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(f1HoloCheck))
                            .addComponent(offsetF2Text)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(periodsLateralText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lateralVortexCheck))
                            .addComponent(multiTrapCheck)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lateralTwinCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lateralQuadCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lateralHoloCheck))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(beadNSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(autoCalcCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(autoSendCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leapEnableCheck)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(beadNSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoCalcCheck)
                    .addComponent(autoSendCheck)
                    .addComponent(leapEnableCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(autoAddCheck)
                    .addComponent(jLabel3)
                    .addComponent(speedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftButton)
                    .addComponent(upButton)
                    .addComponent(rightButton)
                    .addComponent(forwardButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downButton)
                    .addComponent(backwardsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton)
                    .addComponent(snapButton)
                    .addComponent(neutralButton))
                .addGap(11, 11, 11)
                .addComponent(multiTrapCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(periodsLateralText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lateralVortexCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lateralTwinCheck)
                    .addComponent(lateralQuadCheck)
                    .addComponent(lateralHoloCheck))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(periodsF1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offsetF1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f1HoloCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(periodsF2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offsetF2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void doAutoCalcAndSend(boolean updateGraphicsAfter, boolean skipMultiTrap){
        final boolean autoCalc = autoCalcCheck.isSelected();
        final boolean autoAdd = autoAddCheck.isSelected();
        final boolean autoSend = autoSendCheck.isSelected();
        
        final boolean useMultiTrap = multiTrapCheck.isSelected() && !skipMultiTrap;
        Animation animation = null;
        
        if (autoCalc) {
            Entity e = getBeadEntity();
            if (e != null){
                final Vector3f pos = e.getTransform().getTranslation();
                if ( useMultiTrap ){
                    animation = calculateMultiTrap( pos );
                }else{
                    mf.trapsPanel.applyOnTarget(pos);
                }
            } 
        }
        
        if( autoAdd ){
            // add the key frame
            mf.animPanel.pressAddKeyFrame();
            
            //add the bead to the new keyframe
            final Entity bead = getBeadEntity();
            if(bead != null){
                final Vector3f pos = bead.getTransform().getTranslation();
                final Animation anim = mf.animPanel.getCurrentAnimation();
                if (anim != null){
                    final int lastFrame = anim.getKeyFrames().getSize() - 1;
                    MeshEntity newBead = mf.cpPanel.createControlPoint(pos.x, pos.y, pos.z, lastFrame, 0, true);
                    mf.animPanel.getCurrentAnimation().getControlPoints().add( newBead );
                    newBead.getTransform().set( bead.getTransform() );
                }
            }
        }
        
        if (autoSend) {
            if ( useMultiTrap ){
                mf.transControlPanel.sendAnim( animation.getKeyFrames().getElements() );
            }else{
                mf.transControlPanel.sendPattern();
            }
        }
    }
    
    public Entity getBeadEntity(){
        final int n = (Integer)beadNSpinner.getValue();
        
        //check bead number n in selection
        final ArrayList<Entity> sel = mf.selection;
        if (n < 0 || n >= sel.size()){
            return null;
        }        
        return sel.get( n );
    }
    
    public void applyVector(float x, float y, float z, boolean skipMultitrap){
        Entity e = getBeadEntity();
        if (e == null){ return;}
        
        final Vector3f t = new Vector3f(x, y, z);
        t.multLocal( getSpeed() );
        
        final ArrayList<Entity> sel = mf.getSelection();
        for (Entity ent : sel) {
            ent.getTransform().getTranslation().addLocal(t);
        }
        
        mf.transformToGUI( e.getTransform() );
            
        doAutoCalcAndSend(true, skipMultitrap);
        
        mf.needUpdate();
    }
    
    public void setBeadPosition(float x, float y, float z){
        Entity e = getBeadEntity();
        if (e == null){ return;}
        
        final Vector3f t = new Vector3f(x, y, z);
        //t.multLocal( getSpeed() );
        
        final ArrayList<Entity> sel = mf.getSelection();
        for (Entity ent : sel) {
            ent.getTransform().getTranslation().set(t);
        }
        
        mf.transformToGUI( e.getTransform() );
            
        doAutoCalcAndSend(true, false);
        
        mf.needUpdate();
    }

    
   
    
    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        applyVector(-1,0,0, false);
    }//GEN-LAST:event_leftButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
         applyVector(0,1,0, false);
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        applyVector(0,-1,0, false);
    }//GEN-LAST:event_downButtonActionPerformed

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightButtonActionPerformed
        applyVector(1,0,0, false);
    }//GEN-LAST:event_rightButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        applyVector(0,0,-1, false);
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void backwardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardsButtonActionPerformed
        applyVector(0,0,1, false);
    }//GEN-LAST:event_backwardsButtonActionPerformed

 
    public float getSpeed(){
        return Parse.toFloat( speedText.getText() );    
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        Entity e = getBeadEntity();
        if (e == null){ return;}
        e.getTransform().getTranslation().set( snapBeadPosition );
        
        applyVector(0,0,0, true);
        
    }//GEN-LAST:event_resetButtonActionPerformed

    public void selectFirstBead() {
        Entity e = mf.scene.getFirstWithTag( Entity.TAG_BEAD | Entity.TAG_CONTROL_POINT );
        if (e == null){ return;}
        mf.clearSelection();
        mf.getSelection().add( e );
        e.selected = true;
    }
    
    private void snapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapButtonActionPerformed
        snapBeadPosition();
    }//GEN-LAST:event_snapButtonActionPerformed

    private void neutralButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neutralButtonActionPerformed
        applyVector(0, 0, 0, false);
    }//GEN-LAST:event_neutralButtonActionPerformed

    private void leapEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leapEnableActionPerformed
        // Have the sample listener receive events from the controller
        if (leapEnableCheck.isSelected())
            controller.addListener(listener);
        else
            controller.removeListener(listener);
    }//GEN-LAST:event_leapEnableActionPerformed


    public void snapBeadPosition(){
        Entity e = getBeadEntity();
        if (e == null){ return;}
        snapBeadPosition.set( e.getTransform().getTranslation() );
    }
    
    public void snapFirstBead(){
        Entity e = mf.scene.getFirstWithTag( Entity.TAG_BEAD | Entity.TAG_CONTROL_POINT );
        if (e == null){ return;}
        snapBeadPosition.set( e.getTransform().getTranslation() );
    }
    
    private Animation calculateMultiTrap(Vector3f pos) {
        final List<Transducer> transducers = mf.simulation.getTransducers();
        final Animation a = Animation.createEmpty(4, transducers);
        final float mSpeed = mf.simulation.getMediumSpeed();
        final List<AnimKeyFrame> frames = a.getKeyFrames().getElements();
        
        int index = 0;
        //lateral trap
        final int periodsLateral = (int)Parse.toFloat(periodsLateralText.getText() );
        
        if (lateralVortexCheck.isSelected()){
            //vortex clockwise
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            SimplePhaseAlgorithms.addVortexSignature(transducers, 1);
            frames.get(index).setDuration( periodsLateral  );
            frames.get(index).snap( mf.simulation );
            index++;
            
            //vortex counterclockwise           
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            SimplePhaseAlgorithms.addVortexSignature(transducers, -1);
            frames.get(index).setDuration( periodsLateral );
            frames.get(index).snap( mf.simulation );
            index++;
        }else if(lateralTwinCheck.isSelected()){
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            SimplePhaseAlgorithms.addTwinSignature(transducers, 90);
            frames.get(index).setDuration( periodsLateral  );
            frames.get(index).snap( mf.simulation );
            index++;
        }else if(lateralQuadCheck.isSelected()){
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            SimplePhaseAlgorithms.addTwinSignature(transducers, 90);
            frames.get(index).setDuration( periodsLateral  );
            frames.get(index).snap( mf.simulation );
            index++;
            
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            SimplePhaseAlgorithms.addTwinSignature(transducers, 0);
            frames.get(index).setDuration( periodsLateral  );
            frames.get(index).snap( mf.simulation );
            index++;
        }else if(lateralHoloCheck.isSelected()){
            SimplePhaseAlgorithms.focus(transducers, pos, mSpeed);
            mf.holoPatternsForm.addMemorizedHoloPattern();
            frames.get(index).setDuration( periodsLateral  );
            frames.get(index).snap( mf.simulation );
            index++;
        }
        
        
        
        //F1
        final int periodF1 = (int)Parse.toFloat( periodsF1Text.getText() );
        if(periodF1 > 0){
            SimplePhaseAlgorithms.focus(transducers, pos.add(0, Parse.toFloat(offsetF1Text.getText()), 0), mSpeed);
            if (f1HoloCheck.isSelected()){
                mf.holoPatternsForm.addMemorizedHoloPattern();
            }
            frames.get(index).setDuration( periodF1  );
            frames.get(index).snap( mf.simulation );
            index++;
        }
        
        //F2
        final int periodF2 = (int)Parse.toFloat( periodsF2Text.getText() );
        if(periodF2 > 0){
            SimplePhaseAlgorithms.focus(transducers, pos.add(0, Parse.toFloat(offsetF2Text.getText()), 0), mSpeed);
            frames.get(index).setDuration( periodF2  );
            frames.get(index).snap( mf.simulation );
            index++;
        }
        
        return a;
    }

    public JTextField gettDownText() {
        return periodsF2Text;
    }

    public JTextField gettUpText() {
        return periodsF1Text;
    }

    public JTextField getTimeLateralText() {
        return periodsLateralText;
    }

    public JButton getNeutralButton() {
        return neutralButton;
    }

    public JTextField getpDownText() {
        return offsetF2Text;
    }

    public JTextField getpUpText() {
        return offsetF1Text;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoAddCheck;
    private javax.swing.JCheckBox autoCalcCheck;
    private javax.swing.JCheckBox autoSendCheck;
    private javax.swing.JButton backwardsButton;
    private javax.swing.JSpinner beadNSpinner;
    private javax.swing.JButton downButton;
    private javax.swing.JCheckBox f1HoloCheck;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton lateralHoloCheck;
    private javax.swing.JRadioButton lateralQuadCheck;
    private javax.swing.ButtonGroup lateralTrapGroup;
    private javax.swing.JRadioButton lateralTwinCheck;
    private javax.swing.JRadioButton lateralVortexCheck;
    private javax.swing.JCheckBox leapEnableCheck;
    private javax.swing.JButton leftButton;
    private javax.swing.JCheckBox multiTrapCheck;
    private javax.swing.JButton neutralButton;
    private javax.swing.JTextField offsetF1Text;
    private javax.swing.JTextField offsetF2Text;
    private javax.swing.JTextField periodsF1Text;
    private javax.swing.JTextField periodsF2Text;
    private javax.swing.JTextField periodsLateralText;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JButton snapButton;
    private javax.swing.JTextField speedText;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    

}
