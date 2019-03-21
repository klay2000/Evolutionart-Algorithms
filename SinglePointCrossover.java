import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

public class SinglePointCrossover extends EAFunction{

    final boolean isWeighted = true;

    int numberOfParents;

    public SinglePointCrossover(int numberOfParents){
        this.numberOfParents = numberOfParents;
    }

    @Override
    public Solution[] execute(Solution[] population) {

        Solution[] parents = new Solution[numberOfParents];

        for(int i = 0; i < parents.length; i++) { // pick best population members as parents
            int bestIndex = 0;
            parents[i] = population[0];
            for (int n = 0; n < population.length; n++) {
                if(population[n].getSelectionWeight() > parents[i].getSelectionWeight()){
                    parents[i] = population[n];
                    bestIndex = n;
                }
            }
            population[bestIndex].setSelectionWeight(0);
        }

        Solution[] children = new Solution[((numberOfParents*numberOfParents)-numberOfParents)/2]; // (x^2-x)/2

        int childIndex = 0;

        for(int i = 0; i < children.length; i++){
            children[i] = new Solution(population[0].getGenome().length, Solution.initGenes.zeros);
        }

        for(int i = 0; i < numberOfParents; i++){ // run crossover on parents to generate children
            for(int n = numberOfParents-i-1; n > 0; n--){

                int crossSite = (int)Math.round(Math.random()*population[n].getGenome().length);

                boolean[] newGenes = parents[n].getGenome().clone();

                for(int g = crossSite; g < parents[i].getGenome().length; g++){
                    newGenes[g] = parents[i].getGenome()[g];
                }

                children[childIndex].setGenome(newGenes);
                childIndex++;

            }
        }

        for(int i = 0; i < population.length; i++){ // alternate children through final population

            population[i] = children[i%children.length];

        }

        return population;
    }

}
