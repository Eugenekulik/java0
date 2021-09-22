package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubeSpecification.CubeSpecification;
import by.training.task6.dao.cubeSpecification.CubeSpecificationByMinVolume;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CubeRepositoryImpl implements CubeRepository {
    private CubeStore cubeStore;

    public  CubeRepositoryImpl(){
        cubeStore = new CubeStore();
    }
    @Override
    public void addCube(Cube cube) {
        cubeStore.addCube(cube);
    }

    @Override
    public Collection<Cube> getAll() {
        return cubeStore.getAll();
    }

    @Override
    public void removeCube(int id) {
        cubeStore.removeCube(id);
    }

    @Override
    public List quary(CubeSpecification cubeSpecification) {
        if(cubeSpecification instanceof CubeSpecificationByMinVolume){
            return getAll().stream()
                    .filter(cube -> {return cubeSpecification.specified(cube);})
                    .collect(Collectors.toList());
        }
        return null;
    }
}
