package fitness;

import generic.EAFunction;
import generic.Solution;

public class PolynomialMatchFitness extends EAFunction {

    private int max;
    private int min;
    private double[] values; // this contains all values, make sure genome size is 8 * this (or 8*max-min*10)
    private int numberOfRoots;

    public PolynomialMatchFitness(int numberOfRoots, int min, int max){
        this.max = max;
        this.min = min;
        this.numberOfRoots = numberOfRoots;
        int[] roots = new int[numberOfRoots];

        values = new double[(max-min)*10];

        for(int i = 0; i < roots.length; i++){
            roots[i] = ((int)Math.round((255)*Math.random()));
//            System.out.println(roots[i]);
        }


        for(int i = 0; i < values.length; i++){
            double value = 1;

            for (int root : roots) {
                value *= ((i * 0.1) - root);
            }

//            System.out.println(value);

            values[i] = value;
        }

//        System.out.println();

    }

    @Override
    public Solution[] execute(Solution[] population) {

        for(int i = 0; i < population.length; i++){
            boolean [] genes = population[i].getGenome();
            int[] roots = new int[numberOfRoots];

            double fitness = 0;

            for(int n = 0; n < numberOfRoots*8; n+=8){ // decode binary to decimal
                int root = 0;
                for(int r = 0; r < 8; r++){
                    int bool = 0;
                    if(genes[n+r]) bool++;
                    root += Math.pow(2, r)*bool;
//                    System.out.print(" "+genes[n+r] + " " + root);
                }

                roots[n/8] = root;

//                for(boolean b : genes) System.out.print(b);
//                System.out.println();

//                System.out.println(" " + root);
            }
//            System.out.println();

            for(int n = 0; n < values.length; n++) { // calculate fitness
                double value = 1;

                for(int r = 0; r < numberOfRoots; r++){
                    value *= ((n*0.1)-roots[r]);
                }

//                System.out.println(value);

                fitness += (1.0/(Math.abs(value - values[n])+1.0));
            }

//            System.out.println();

            population[i].setFitness(fitness);
        }

        return population;
    }
}
