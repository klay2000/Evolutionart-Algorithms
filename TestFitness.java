public class TestFitness extends EAFunction {

    @Override
    public Solution[] execute(Solution[] population) {

        for(Solution i : population){
            boolean [] genes = i.getGenome();

            int fitness = 0;

            for(boolean b : genes){
                if(b)fitness++;
            }

            i.setFitness(fitness);
        }

        return population;
    }
}
