package saveltaja.domain;

public class RandomGenerator {
    private int lastRandom;
    
    public RandomGenerator() {
        lastRandom = (int) System.nanoTime();
    }
    
    public int getRandom(int max) {
        lastRandom = (28082000 * lastRandom + (int) System.nanoTime()) % max;
        return lastRandom > 0 ? lastRandom : -lastRandom;
    }
}
