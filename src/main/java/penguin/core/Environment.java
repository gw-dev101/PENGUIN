package penguin.core;

public class Environment{
    private final int width;
    private final int height;

    public Environment(int width, int height){
        this.width = width;
        this.height = height;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
}
public TerrainType getTerrainType(int x, int y){
    /**
     * Returns the terrain type at the given coordinates.
     */
    //TODO make this more complex
    if(x < 0 || x >= width || y < 0 || y >= height){
        throw new IllegalArgumentException("Invalid coordinates");
    }
    if(y < height / 2){
        return TerrainType.ICE;
    } else {
        return TerrainType.WATER;
    }
    }
}