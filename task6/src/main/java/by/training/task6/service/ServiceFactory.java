package by.training.task6.service;

import by.training.task6.service.cubePlaneRatio.CubePlaneRatioXOY;
import by.training.task6.service.cubePlaneRatio.CubePlaneRatioXOZ;
import by.training.task6.service.cubePlaneRatio.CubePlaneRatioYOZ;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private CubeCreator cubeCreator;
    private CalculateCubeVolume calculateCubeVolume;
    private CalculateCubeArea calculateCubeArea;
    private CubeSideLocation cubeSideLocation;
    private CubePlaneRatioXOY cubePlaneRatioXOY;
    private CubePlaneRatioXOZ cubePlaneRatioXOZ;
    private CubePlaneRatioYOZ cubePlaneRatioYOZ;

    public static ServiceFactory getInstance(){
        return instance;
    }
    public CubeCreator getEllipseCreator(){
        if(cubeCreator == null){
            cubeCreator = new CubeCreator();
        }
        return cubeCreator;
    }
    public CalculateCubeVolume getCalculateCubeVolume(){
        if(calculateCubeVolume == null){
            calculateCubeVolume = new CalculateCubeVolume();
        }
        return calculateCubeVolume;
    }
    public CalculateCubeArea getCalculateCubeArea(){
        if(calculateCubeArea == null){
            calculateCubeArea = new CalculateCubeArea();
        }
        return calculateCubeArea;
    }
    public CubeSideLocation getCubeSideLocation(){
        if(cubeSideLocation == null) {
            cubeSideLocation = new CubeSideLocation();
        }
        return cubeSideLocation;
    }
    public CubePlaneRatioYOZ getCubePlaneRatioYOZ(){
        if(cubePlaneRatioYOZ == null){
            cubePlaneRatioYOZ = new CubePlaneRatioYOZ();
        }
        return cubePlaneRatioYOZ;
    }
    public CubePlaneRatioXOY getCubePlaneRatioXOY(){
        if(cubePlaneRatioXOY == null) {
            cubePlaneRatioXOY = new CubePlaneRatioXOY();
        }
        return cubePlaneRatioXOY;
    }
    public CubePlaneRatioXOZ getCubePlaneRatioXOZ(){
        if(cubePlaneRatioXOZ == null) {
            cubePlaneRatioXOZ = new CubePlaneRatioXOZ();
        }
        return cubePlaneRatioXOZ;
    }
}
