package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.bean.CubeException;
import by.training.task6.dao.cuberepository.CubeRepository;
import by.training.task6.dao.cuberepository.CubeRepositoryImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CubeSpecificationByMaxVolumeTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() throws CubeException {
        return new Object[][]{
                {new CubeRepositoryImpl(new Cube(1.0,0.0,0.0,0.0),
                        new Cube(16.0,0.0,0.0,0.0),
                        new Cube(8.0,0.0,0.0,0.0),
                        new Cube(10.0,0.0,0.0,0.0)),
                        new Cube(1.0,0.0,0.0,0.0)}
        };
    }

    @Test(description = "test cube specification by max volume", dataProvider = "input_data")
    public void specifiedTest(CubeRepository cubeRepository, Cube cube){
        List<Cube> expected = new ArrayList<Cube>();
        expected.add(cube);
        List<Cube> actual = cubeRepository.quary(new CubeSpecificationByMaxVolume(2));
        assertEquals(actual,expected);
    }
}
