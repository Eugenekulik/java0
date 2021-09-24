package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.bean.CubeException;
import by.training.task6.dao.cubeRepository.CubeRepository;
import by.training.task6.dao.cubeRepository.CubeRepositoryImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.testng.AssertJUnit.assertEquals;

public class CubeSpecificationByVolumeTest {
  @DataProvider(name = "input_data")
    public Object[][] createData() throws CubeException {
        return new Object[][]{
                {new CubeRepositoryImpl(new Cube(1.0,0.0,0.0,0.0),
                        new Cube(16.0,0.0,0.0,0.0),
                        new Cube(8.0,0.0,0.0,0.0),
                        new Cube(10.0,0.0,0.0,0.0)),
                        new Cube(8.0,0.0,0.0,0.0)}
        };
    }

    @Test(description = "testcubespecificationbyminmaxvolume", dataProvider = "input_data")
    public void specifiedTest(CubeRepository cubeRepository, Cube cube){
        List<Cube> expected = new ArrayList<Cube>();
        expected.add(cube);
        List<Cube> actual = cubeRepository.quary(new CubeSpecificationByMinMaxVolume(500,550));
        assertEquals(actual,expected);
    }
}