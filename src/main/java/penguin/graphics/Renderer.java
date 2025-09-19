package penguin.graphics;
import penguin.core.Environment;
import penguin.core.TerrainType;
import java.awt.*;
import javax.swing.*;

public class Renderer extends JPanel {
    private final Environment environment;

    public Renderer(Environment environment) {
        this.environment = environment;
        setPreferredSize(new Dimension(environment.getWidth(), environment.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < environment.getWidth(); x++) {
            for (int y = 0; y < environment.getHeight(); y++) {
                TerrainType terrainType = environment.getTerrainType(x, y);
                if (terrainType == TerrainType.ICE) {
                    g.setColor(Color.CYAN);
                } else if (terrainType == TerrainType.WATER) {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(x, y, 1, 1);
            }
        }
    }
}