package by.training.task2.controller;

import by.training.task2.controller.command.*;

/**
 * This class checks the validity of the arguments
 */
public class CurrentData {
    public CurrentData(){ }

    /**
     * This method check arguments
     * @param cmd what commaand is recieved
     * @param args what arguments are recieved
     * @return true if valid argument, else false
     */
    public boolean check(CommandType cmd, String[]args){
        switch(cmd) {
            case CALCEXPR:
                if(args.length!=4){
                    return false;
                }
                try{
                    double []a = new double[4];
                    for (int i=0;i<4;i++) {
                        a[i]=Double.parseDouble(args[i]);
                    }
                    return true;
                }
                catch (NumberFormatException e){
                    return false;
                }
            case CALCFUNC:
                if(args.length!=1){
                    return false;
                }
                try{
                    double x = Double.parseDouble(args[0]);
                    return true;
                }
                catch (NumberFormatException e){
                    return false;
                }
            case CALCFUNCSEGM:
            case CALCFUNCTABLE:
            case POSSUM:
                if(args.length!=3){
                    return false;
                }

                try{
                    double a=Double.parseDouble(args[0]);
                    double b=Double.parseDouble(args[1]);
                    double h=Double.parseDouble(args[2]);
                    return true;
                }
                catch (NumberFormatException e){
                    return false;
                }

            case CURRSTR:
                if(args.length!=1){
                    return false;
                }
                else {
                    return true;
                }
            case DEGREEACT:
                if(args.length!=3){
                    return false;
                }
                try{
                    double []a = new double[3];
                    for (int i=0;i<3;i++) {
                        a[i]=Double.parseDouble(args[i]);
                    }
                    return true;
                }
                catch (NumberFormatException e){
                    return false;
                }
            case MATHTASK:
            case PRODSEQ:
                return true;
            case REDISTR:
                if(args.length!=2){
                    return false;
                }
                try{
                    double x=Double.parseDouble(args[0]);
                    double y=Double.parseDouble(args[1]);
                    return true;
                }
                catch (NumberFormatException e){
                    return false;
                }
            default: return false;
        }
    }
}
