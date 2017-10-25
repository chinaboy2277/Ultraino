/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acousticfield3d.simulation;

import acousticfield3d.math.Quaternion;
import acousticfield3d.math.Transform;
import acousticfield3d.math.M;
import acousticfield3d.utils.GenericListModel;
import acousticfield3d.math.Vector3f;
import acousticfield3d.scene.Entity;
import acousticfield3d.scene.MeshEntity;
import acousticfield3d.scene.Scene;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Asier
 */
public class Simulation {
    Vector3f boundaryMin = new Vector3f();
    Vector3f boundaryMax = new Vector3f();
      
    float mediumSpeed, mediumDensity;
    float particleSpeed, particleDensity;
    float minSize;
    
    public float power = 0.0f;
    public ArrayList<Transducer> transducers = new ArrayList<>();
    public ArrayList<MeshEntity> controlPoints = new ArrayList<>();
    public GenericListModel<Animation> animations = new GenericListModel<>();
    public ArrayList<MeshEntity> maskObjects = new ArrayList<>();
    public ArrayList<MeshEntity> slices = new ArrayList<MeshEntity>();
    
    public float[] holoMemory;
    
    public Simulation() {
       minSize = 0.001f;
       
       mediumSpeed = 346;
       mediumDensity = 1.2f;
       particleSpeed = 2600;
       particleDensity = 25;
    }
    
    public static float getSoundSpeedInAir(float temperature){
        return 331.4f + 0.6f * temperature;
    }
    
    public void addTransToAnimationsKeys(Transducer t) {
        for(Animation a : animations.getElements()){
            a.addTransducer(t);
        }
    }
    
    public float getWavelenght(){
        return getMediumSpeed() / getFrequency();
    }
    
    public float getFrequency(){
        if (! transducers.isEmpty()){
            return transducers.get(0).getFrequency();
        }else{
            return 0;
        }
    }
    public float getWavelength(){
        return getMediumSpeed() / getFrequency();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and setters">

    public float getMediumSpeed() {
        return mediumSpeed;
    }

    public void setMediumSpeed(float mediumSpeed) {
        this.mediumSpeed = mediumSpeed;
    }

    public float getMediumDensity() {
        return mediumDensity;
    }

    public void setMediumDensity(float mediumDensity) {
        this.mediumDensity = mediumDensity;
    }

    public float getParticleSpeed() {
        return particleSpeed;
    }

    public void setParticleSpeed(float particleSpeed) {
        this.particleSpeed = particleSpeed;
    }

    public float getParticleDensity() {
        return particleDensity;
    }

    public void setParticleDensity(float particleDensity) {
        this.particleDensity = particleDensity;
    }
   
    
    public float getMinSize() {
        return minSize;
    }

    
    public void setMinSize(float minSize) {
        this.minSize = minSize;
    }

    public ArrayList<MeshEntity> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(ArrayList<MeshEntity> controlPoints) {
        this.controlPoints = controlPoints;
    }

    public Vector3f getBoundaryMin() {
        return boundaryMin;
    }

    public void setBoundaryMin(Vector3f boundaryMin) {
        this.boundaryMin = boundaryMin;
    }

    public Vector3f getBoundaryMax() {
        return boundaryMax;
    }

    public void setBoundaryMax(Vector3f boundaryMax) {
        this.boundaryMax = boundaryMax;
    }

    
    
    public ArrayList<Transducer> getTransducers() {
        return transducers;
    }
    
    public float getPower() {
        return power;
    }
    
    public void setPower(float power) {
        this.power = power;
    }
    
    public void setTransducers(ArrayList<Transducer> transducers) {
        this.transducers = transducers;
    }
   
    
    public GenericListModel<Animation> getAnimations() {
        return animations;
    }
    
    public void setAnimations(GenericListModel<Animation> animations) {
        this.animations = animations;
    }

    public float[] getHoloMemory() {
        return holoMemory;
    }

    public void setHoloMemory(float[] holoMemory) {
        this.holoMemory = holoMemory;
    }

    public ArrayList<MeshEntity> getMaskObjects() {
        return maskObjects;
    }

    public void setMaskObjects(ArrayList<MeshEntity> maskObjects) {
        this.maskObjects = maskObjects;
    }

    public ArrayList<MeshEntity> getSlices() {
        return slices;
    }

    public void setSlices(ArrayList<MeshEntity> slices) {
        this.slices = slices;
    }
    
    
    //</editor-fold>
    
    public float maxDistanceBoundary(){
        Vector3f distances = boundaryMax.subtract(boundaryMin);
        return distances.maxComponent(); 
    }
    
    public float minDistanceBoundary(){
        Vector3f distances = boundaryMax.subtract(boundaryMin);
        return distances.minComponent(); 
    }
    
    public void updateSimulationBoundaries(){
        Scene.calcBoundaries(transducers, boundaryMin, boundaryMax);
        
        minSize = Scene.smallestObject( transducers );
        
        boundaryMin.addLocal( - minSize / 2.0f);
        boundaryMax.addLocal(   minSize / 2.0f );
        
        Vector3f distances = boundaryMax.subtract(boundaryMin);
        float maxDistance = distances.maxComponent();
        
        boundaryMin.x -= (maxDistance - distances.x)/2.0f;
        //boundaryMin.y -= (maxDistance - distances.y)/2.0f;
        boundaryMin.y -= minSize * 5.f;
        boundaryMin.z -= (maxDistance - distances.z)/2.0f;
        
        boundaryMax.x += (maxDistance - distances.x)/2.0f;
        boundaryMax.y += (maxDistance - distances.y)/2.0f;
        boundaryMax.z += (maxDistance - distances.z)/2.0f;
    }
    
    public Vector3f getSimulationCenter(){
        return new Vector3f(boundaryMax).addLocal(boundaryMin).divideLocal(2.0f);
    }

    public Vector3f getSimulationSize() {
        return new Vector3f(boundaryMax).subtractLocal(boundaryMin);
    }
    
    public void orderTransducersAsSelection(ArrayList<Entity> selection) {
        //get all the transducers from selection
        ArrayList<Transducer> transSel = new ArrayList<>();
        for(Entity e : selection){
            if (e instanceof Transducer){
                transSel.add( (Transducer) e);
            }
        }
        
        //pass all the transducers to hashmap
        List<Transducer> trans = getTransducers();
        HashMap<Transducer, Transducer> mapTrans = new HashMap<>();
        for(Transducer t : trans) { mapTrans.put(t,t); }
        
        //clear transducers
        trans.clear();
        
        //add from allTrans while removing them
        for(Transducer t : transSel){
            trans.add(t);
            mapTrans.remove(t);
        }
        
        //add the rest
        for(Transducer t : mapTrans.keySet()){
            trans.add(t);
        }
        
        labelNumberTransducers();
    }
    
    public void labelNumberTransducers(){
        int lastIndex = -1;
        for(Transducer t : transducers){
            if (t.getOrderNumber() <= lastIndex){
                t.setOrderNumber(lastIndex+1);
            }
            lastIndex = t.getOrderNumber();
        }
    }
    
    public void sortTransducers(){
        Collections.sort(transducers, new Comparator<Transducer>() {
            @Override
            public int compare(Transducer o1, Transducer o2) {
                return Integer.compare( o1.getOrderNumber(), o2.getOrderNumber());
            }
        });
    }
    
    public void sortAnimations(){
        Collections.sort(animations.getElements(), new Comparator<Animation>() {
            @Override
            public int compare(Animation o1, Animation o2) {
                return Integer.compare(o1.getNumber(), o2.getNumber());
            }
        });
        for(Animation a : animations.getElements()){
            a.sortKeyFrames();
        }
    }
   
     public void copyToCube(MeshEntity cube){
        final Transform ct = cube.getTransform();
        ct.setTranslation( getSimulationCenter());
        ct.getRotation().set( Quaternion.IDENTITY );
        ct.setScale( getSimulationSize() );
    }
    
    public void copyFromCube(MeshEntity cube){
        Vector3f sCenter = cube.getTransform().getTranslation();
        Vector3f sSizeHalf = cube.getTransform().getScale().divide(2.0f);
        
        setBoundaryMax( sCenter.add(sSizeHalf) );
        setBoundaryMin( sCenter.subtract(sSizeHalf) );
    }
    
    public double[] getTransPhasesAsArray() {
        int nTrans = transducers.size();
        /*
        if (isReflection()){
            nTrans /= 2;
        }
                */
        double[] phases = new double[nTrans];
        for(int i = 0; i < nTrans; ++i){
            phases[i] = transducers.get(i).getPhase() * M.PI;
        }
        return phases;
    }
    
}
