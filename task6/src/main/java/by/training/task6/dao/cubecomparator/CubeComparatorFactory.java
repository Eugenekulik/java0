package by.training.task6.dao.cubecomparator;

public class CubeComparatorFactory {
    private static final CubeComparatorFactory INSTANCE = new CubeComparatorFactory();
    private CubeComparatorByVolume  cubeComparatorByVolume;
    private CubeComparatorByArea cubeComparatorByArea;
    private CubeComparatorById cubeComparatorById;

    public static CubeComparatorFactory getInstance(){
        return INSTANCE;
    }
    public CubeComparatorByVolume getCubeComparatorByVolume(){
        if(cubeComparatorByVolume == null){
            cubeComparatorByVolume = new CubeComparatorByVolume();
        }
        return cubeComparatorByVolume;
    }

    public CubeComparatorByArea getCubeComparatorByArea() {
        if(cubeComparatorByArea == null){
            cubeComparatorByArea = new CubeComparatorByArea();
        }
        return cubeComparatorByArea;
    }

    public CubeComparatorById getCubeComparatorById() {
        if(cubeComparatorById == null){
            cubeComparatorById = new CubeComparatorById();
        }
        return cubeComparatorById;
    }
}
