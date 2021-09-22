package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CubeStore {
    private static int id = 1;
    private Map<Integer,Cube> cubes = new HashMap<>();
    public void addCube(Cube cube){
        cube.setId(id);
        cubes.put(id,cube);
        id++;
    }
    public void removeCube(int id) {
        if (cubes.containsKey(id)) {
            cubes.remove(id);
        }
    }
    public Collection<Cube> getAll(){
        return cubes.values();
    }
}
