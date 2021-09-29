package by.training.task6.dao.cuberepository;

import by.training.task6.bean.Cube;

import java.util.*;

public class CubeStore {
    private int id = 1;
    private final List<Cube> cubes = new ArrayList<>();

    public void addCube(Cube cube) {
        cube.setId(id);
        cubes.add(cube);
        id++;
    }

    public void removeCube(Cube cube) {
        cubes.remove(cube);
    }

    public List<Cube> getAll() {
        return new ArrayList<>(cubes);
    }
}
