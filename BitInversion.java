public class BitInversion extends EAFunction {

    private double probability = 0;

    /*
    *Sets probability.
     */
    void setProbability(double probability){ this.probability = probability; }

    /*
    *Gets probability.
     */
    double getProbability(){ return probability; }

    public BitInversion(double probability){

        this.probability = probability;

    }

    @Override
    public Solution[] execute(Solution[] population) {

        for(Solution i : population){

            boolean[] genes = i.getGenome();

            for(int n = 0; n < genes.length; n++){
                if(Math.random() <= probability) genes[n] = !genes[n];
            }
        }

        return population;
    }
}
