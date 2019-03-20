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

        for(int i = 0; i < parents.length; i++) {
            int bestIndex = 0;
            for (int n = 0; n < population.length; n++) {
                if(population[n].getSelectionWeight() > parents[i].getSelectionWeight()){
                    parents[i] = population[n];
                    bestIndex = n;
                }
            }
            population[bestIndex].setSelectionWeight(0);
        }

        Solution[] children = new Solution[(numberOfParents*numberOfParents)];

        for(int i = 0; i < numberOfParents; i++){
            for(int n = 0; n < numberOfParents; n++){

                int crossSite = (int)Math.round(Math.random()*population[n].getGenome().length);

                boolean[] newGenes = parents[n].getGenome();

                for(int g = crossSite; g < parents[i].getGenome().length; g++){
                    newGenes[g] = parents[i].getGenome()[g];
                }

                children[i*n].setGenome(newGenes);

            }
        }

        for(int i = 0; i < population.length; i++){
            population[i] = children[population.length%children.length];
        }

        return population;
    }

}
