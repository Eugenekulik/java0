package by.training.task6.dao.cuberepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubespecification.CubeSpecification;
import by.training.task6.dao.cubespecification.find.CubeFindSpecification;
import by.training.task6.dao.cubespecification.sort.CubeSortSpecification;
import java.util.List;
import java.util.stream.Collectors;

public class CubeRepositoryImpl implements CubeRepository {
    private final CubeStore cubeStore;

    public CubeRepositoryImpl(Cube... cubes) {
        cubeStore = new CubeStore();
        for (Cube c :cubes) {
            cubeStore.addCube(c);
        }
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
        if(cubeSpecification instanceof CubeFindSpecification) {
            return cubeStore.getAll().stream()
                    .filter(((CubeFindSpecification) cubeSpecification)::specified)
                    .collect(Collectors.toList());
        }
        if(cubeSpecification instanceof CubeSortSpecification){
            return cubeStore.getAll().stream()
                    .sorted(((CubeSortSpecification) cubeSpecification)::specified)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
