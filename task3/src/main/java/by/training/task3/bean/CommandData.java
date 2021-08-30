package by.training.task3.bean;

import by.training.task3.controller.command.CommandType;
import by.training.task3.controller.command.SortVariation;

public class CommandData {
    public CommandData() { };
    private MyArray array;
    private MyMatrix matrix1;
    private MyMatrix matrix2;
    private MyMatrix matrixResult;
    private SortVariation variation;
    private CommandType commandType;
    public MyMatrix getMatrixResult() {
        return matrixResult;
    }
    public void setMatrixResult(MyMatrix matrixResult) {
        this.matrixResult = matrixResult;
    }
    public MyMatrix getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(MyMatrix matrix2) {
        this.matrix2 = matrix2;
    }


    public MyMatrix getMatrix1() {
        return matrix1;
    }
    public void setMatrix1(MyMatrix matrix1) {
        this.matrix1 = matrix1;
    }
    public CommandType getCommandType() {
        return commandType;
    }
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }
    public MyArray getArray() {
        return array;
    }
    public void setArray(MyArray array) {
        this.array = array;
    }
    public SortVariation getVariation() {
        return variation;
    }
    public void setVariation(SortVariation variation) {
        this.variation = variation;
    }
    public void clear(){
        array = null;
        variation = null;
        commandType = null;
        matrix1 = null;
    }
}

