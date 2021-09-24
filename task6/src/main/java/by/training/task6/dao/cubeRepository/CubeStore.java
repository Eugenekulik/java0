package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;

import java.util.*;
import java.util.stream.Stream;

public class CubeStore {
    private static int id = 1;
    private final List<Cube> cubes = new ArrayList<>();

    public void addCube(Cube cube) {
        cube.setId(id);
        cubes.add(cube);
        id++;
    }

    public void removeCube(Cube cube) {
        cubes.remove(cube);
    }

    public Stream<Cube> getAll() {
        return cubes.stream();
    }
}
