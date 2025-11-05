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
                return new Environment(width, height);
            case SQUARE:
                // Generate square environment
                return new Environment(width, height);
            case TRIANGLE:
                // Generate triangular environment
                return new Environment(width, height);
            default:
                throw new IllegalArgumentException("Unsupported shape");
        }
    }
    @Override
    public void populate(Environment environment){
        // Populate environment with entities based on shape
        // This is a placeholder implementation
    }
    @Override
    public TerrainType getTerrainType(int x, int y){
        // Determine terrain type based on shape and coordinates
        // This is a placeholder implementation
        return TerrainType.ICE;
    }
}
