
package penguin.core;

import java.util.Random;

public abstract class EnvironmentGenerator{
    protected long seed;
    protected Random random;
    public EnvironmentGenerator(){
        this.seed = System.currentTimeMillis();
        this.random = new Random(seed);
    }
    public long getSeed(){
        return seed;
    }
    public void setSeed(long seed){
        this.seed = seed;
        this.random = new Random(seed);
    
    }
    /** Generates an environment with the given width and height.
     */
    public abstract Environment generate(int width, int height);
    /**
     * populate/modify an existing environment.
     */
    public abstract void populate(Environment environment);

    /** Deterministically get the terrain at x,y
     * should be consistent with generate/populate
     */
    public abstract TerrainType getTerrainType(int x, int y);

}
