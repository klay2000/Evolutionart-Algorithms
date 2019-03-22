import java.util.Arrays;

public class TournamentWeightedSelection extends EAFunction{

    final boolean isWeighted = true;

    private int matches;

    public TournamentWeightedSelection(int matches){
        this.matches = matches;
    }

    @Override
    public Solution[] execute(Solution[] population) {

        Solution [] temp = population;

        for(int n = 0; n < matches; n++){

            if(temp.length%2 == 1){
                temp = Arrays.copyOf(temp, temp.length+1);
                temp[temp.length-1] = temp[0];
            }


            Solution[] temp2 = new Solution[temp.length/2];
            for(int i = 0; i < temp2.length; i++) temp2[i] = new Solution(temp[0].getGenome().length, Solution.initGenes.zeros);

            for(int i = 0; i < temp.length; i+=2){
                if(temp[i].getFitness() > temp[i+1].getFitness()) temp2[i/2] = temp[i];
                else temp2[i/2] = temp[i+1];
            }
            temp=temp2;

        }

        for(int i = 0; i < population.length; i++){
            population[i] = temp[i%temp.length];
            population[i].setSelectionWeight(temp[i%temp.length].getFitness());
        }

        return population;
    }
}
