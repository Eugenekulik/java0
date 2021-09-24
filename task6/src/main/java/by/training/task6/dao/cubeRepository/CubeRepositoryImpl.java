package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubeSpecification.CubeSpecification;
import by.training.task6.dao.cubeSpecification.CubeSpecificationByMinVolume;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CubeRepositoryImpl implements CubeRepository {
    private final CubeStore cubeStore;

    public CubeRepositoryImpl(Cube... cubes) {
        cubeStore = new CubeStore();
        for (Cube c :cubes) {
            cubeStore.addCube(c);
        };
    }

    @Override
    public void addCube(Cube cube) {
        cubeStore.addCube(cube);
    }

    @Override
    public void removeCube(Cube cube) {
        cubeStore.removeCube(cube);
    }

    @Override
    public List quary(CubeSpecification cubeSpecification) {
        if(cubeSpecification == null) {
            return null;
        }
        return cubeStore.getAll()
                .filter(cubeSpecification::specified)
                .collect(Collectors.toList());
    }
}
