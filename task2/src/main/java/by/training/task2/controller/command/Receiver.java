package by.training.task2.controller.command;

import by.training.task2.service.*;

public class Receiver {
    private String []args;
    private String result;
    public Receiver(String []args){
        this.args=args;
    }
    public void action (CommandType cmd)throws ServiceException {
        switch (cmd) {

            case CALCEXPR:
                result =String.valueOf(new CalculateExpression().execute(args));
                break;
            case CALCFUNC:
                result= "result: " + String.valueOf(new CalculateFunction().execute(args));
                break;
            case CALCFUNCSEGM:
                double []r1 = new CalculateFunctionSegment().execute(args);
                result ="result: " + r1.toString();
                break;
            case CALCFUNCTABLE:
                CalculateFunctionTable c= new CalculateFunctionTable();
                double []r2=c.execute(args);
                String s1="";
                for(int i=0;i<r2.length;i+=2){
                    s1+="\t\t"+r2[i] + "\t\t" + r2[i+1] + "\n\t";
                }
                result ="result:\tx\t\tf(x)\n\t" + s1 ;
                break;
            case CURRSTR:
                result=String.valueOf(new CurrentString().execute(args[0]));
                break;
            case DEGREEACT:
                double[] r3 = new DegreeAction().execute(args);
                result = "result: " +r3[0]+"; "+r3[1]+"; "+r3[2]+";";
                break;
            case MATHTASK:
                int[] r=new MathTask().execute();
                result= "result: " + r[0] + "; "+ r[1];
                break;
            case POSSUM:
                result=new PositiveSum().execute(args);
                break;
            case PRODSEQ:
                double[] r4= new Redistribution().execute(args);
                result = "result: " +r4[0]+"; "+r4[1]+";";
                break;
            case REDISTR:
                break;
        }
    }

    public String getResult() {
        return result;
    }
}
