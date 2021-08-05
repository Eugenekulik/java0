package by.training.task2.command;

import by.training.task2.service.*;

public class Receiver {
    private String []args;
    private String result;
    public Receiver(String []args){
        this.args=args;
    }
    public void action(CommandType cmd) {
        switch (cmd) {

            case CALCEXPR:
                result =String.valueOf(new CalculateExpression().execute(args));
                break;
            case CALCFUNC:
                result= "result: " + String.valueOf(new CalculateFunction().execute(args));
                break;
            case CALCFUNCSEGM:
                break;
            case CALCFUNCTABLE:
                break;
            case CURRSTR:
                break;
            case DEGREEACT:
                double[] r1 = new DegreeAction().execute(args);
                result = "result: " +r1[0]+"; "+r1[1]+"; "+r1[2]+";";
                break;
            case MATHTASK:
                break;
            case POSSUM:
                result=new PositiveSum().execute(args);
                break;
            case PRODSEQ:
                double[] r2= new ProductSequence().execute(args);
                result = "result: " +r2[0]+"; "+r2[1]+";";
                break;
            case REDISTR:
                break;
        }
    }

    public String getResult() {
        return result;
    }
}
