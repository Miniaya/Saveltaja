package saveltaja.domain;

/**
 * Random number generator
 */
public class RandomGenerator {
    
    private int lastRandom;
    
    public RandomGenerator() {
        lastRandom = (int) System.nanoTime();
    }
    
    /**
     * Returns one pseudorandom number from range 0...max - 1 
     * 
     * @param max upper limit for generated number
     * 
     * @return pseudorandom number
     */
    public int getRandom(int max) {
        lastRandom = (28082000 * lastRandom + (int) System.nanoTime()) % max;
        return lastRandom > 0 ? lastRandom : -lastRandom;
    }
}
