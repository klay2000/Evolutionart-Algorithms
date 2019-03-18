public class TestFitness extends EAFunction {

    @Override
    public Solution[] execute(Solution[] population) {

        for(int i = 0; i < population.length; i++){
            boolean [] genes = population[i].getGenome();

            int fitness = 0;

            for(boolean b : genes){
                if(b)fitness++;
            }

            population[i].setFitness(fitness);
        }

        return population;
    }
}
