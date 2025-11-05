import penguin.core.EnvironmentGenerator;
import penguin.core.Environment;
import penguin.core.TerrainType;
import penguin.core.Shape;
/*
 * Use a shape enum to generate an environment based on shapes.
 * Circle for example would generate a round environment with random entities.
 * Square would generate a square environment with random entities.
 * Triangle would generate a triangular environment with random entities.
 */

public final class ShapeEnvGen extends EnvironmentGenerator {
    private final Shape shape;
    public ShapeEnvGen(Shape shape){
        super();
        this.shape = shape;
    }
    @Override
    public Environment generate(int width, int height){
        // Generate environment based on shape
        switch(shape){
            case CIRCLE:
                // Generate circular environment
                return new Environment(width, height).populate(this);
            case SQUARE:
                // Generate square environment
                return new Environment(width, height).populate(this);
            case TRIANGLE:
                // Generate triangular environment
                return new Environment(width, height).populate(this);
            default:
                throw new IllegalArgumentException("Unsupported shape");
        }
    }
    @Override
    public void populate(Environment environment){
        for(int x = 0; x < environment.getWidth(); x++){
            for(int y = 0; y < environment.getHeight(); y++){
                if(Shape.isPointInsideShape(shape, Math.min(environment.getWidth(), environment.getHeight()), x - environment.getWidth()/2, y - environment.getHeight()/2)){
                    // Populate with terrain ice inside WATER outside
                    environment.setTerrainType(x, y, TerrainType.ICE);
                } else {
                    environment.setTerrainType(x, y, TerrainType.WATER);
                }
            }
        }
    }
    @Override
    public TerrainType getTerrainType(int x, int y){
        // Deterministically get terrain type based on shape
        if(Shape.isPointInsideShape(shape, 100, x - 50, y - 50)){
            return TerrainType.ICE;
        } else {
            return TerrainType.WATER;
    }
}
}