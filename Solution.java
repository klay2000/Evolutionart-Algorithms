public class Solution {

    public enum initGenes{
        zeros,
        ones,
        random
    }

    private boolean[] genome;

    public Solution(int genomeSize, initGenes init){

        genome = new boolean[genomeSize];

        switch (init){

            case ones:

                for(boolean i : genome)
                    i = true;
                break;

            case zeros:
                    for(boolean i : genome)
                        i = false;
                 break;

            case random:
                    for(boolean i : genome)
                        i = Math.round(Math.random()) == 1;
                break;

        }

    }

    public boolean[] getGenome(){
        return genome;
    }

    public void setGenome( boolean[] newGenome){
        genome = newGenome;
    }

}
