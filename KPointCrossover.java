public class KPointCrossover extends EAFunction{

    final boolean isWeighted = true;

    private int numberOfParents;

    private int numberOfPoints;

    public KPointCrossover(int numberOfParents, int numberOfPoints)
    {
        this.numberOfParents = numberOfParents;
        this.numberOfPoints = numberOfPoints;
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
            for(int n = numberOfParents-i-1; n > 0; n--) {


                int[] points = new int[numberOfPoints]; // ensures there is no duplicate points in the array
                for (int p = 0; p < points.length; p++) points[p] = population[0].getGenome().length+1;
                for (int p = 0; p < points.length; p++) {
                    boolean done = false;
                    int newPoint = 0;
                    while (!done) {

                        newPoint = (int) Math.floor(Math.random() * population[0].getGenome().length);

                        done = true;
                        for( int iteratePoint : points) if(iteratePoint == newPoint) done = false;

                    }
                    points[p] = newPoint;
                }

                bubbleSort(points);

                boolean[] newGenes = parents[n].getGenome().clone();

                for (int p = 0; p < points.length; p++){
                    if(p%2 == 0) for(int g = points[p]; g < parents[i].getGenome().length; g++) { newGenes[g] = parents[i].getGenome()[g]; }
                    else for(int g = points[p]; g < parents[n].getGenome().length; g++) { newGenes[g] = parents[n].getGenome()[g]; }
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

    private void bubbleSort(int[] array) { // Just sorts an array.
        boolean done = false;

        while(!done) {
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                    }
                }
                else if (i == array.length - 1) {
                    if (array[i] < array[i - 1]) {
                        int temp = array[i];
                        array[i] = array[i - 1];
                        array[i - 1] = temp;
                    }
                } else {
                    if (array[i] < array[i - 1]) {
                        int temp = array[i];
                        array[i] = array[i - 1];
                        array[i - 1] = temp;
                    }

                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                    }
                }
            }

            int min = array[0];
            int max = array[array.length-1];

            done = true;

            for(int i : array){
                if(i > max || i < min) done = false;
            }
        }
    }

}
