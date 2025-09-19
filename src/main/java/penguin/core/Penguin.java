package penguin.core;


public class Penguin {
    // Position
    public double x, y;
    // Velocity
    public double vx, vy;
    // Acceleration
    public double ax, ay;
    // Orientation
    public double angle;

    // Boid parameters
    public double maxSpeed = 2.0;
    public double maxForce = 0.05;
    public double perceptionRadius = 50.0;

    public Penguin(double x, double y) {
        this.x = x;
        this.y = y;
        this.vx = Math.random() * 2 - 1;
        this.vy = Math.random() * 2 - 1;
        this.ax = 0;
        this.ay = 0;
        this.angle = 0;
    }

    public void update() {
        vx += ax;
        vy += ay;
        double speed = Math.sqrt(vx * vx + vy * vy);
        if (speed > maxSpeed) {
            vx = (vx / speed) * maxSpeed;
            vy = (vy / speed) * maxSpeed;
        }
        x += vx;
        y += vy;
        angle = Math.atan2(vy, vx);
        ax = 0;
        ay = 0;
    }

    public void applyForce(double fx, double fy) {
        ax += fx;
        ay += fy;
    }

    public void flock(Penguin[] penguins) {
        double[] alignment = align(penguins);
        double[] cohesion = cohere(penguins);
        double[] separation = separate(penguins);

        applyForce(alignment[0], alignment[1]);
        applyForce(cohesion[0], cohesion[1]);
        applyForce(separation[0], separation[1]);
    }

    private double[] align(Penguin[] penguins) {
        double steeringX = 0;
        double steeringY = 0;
        int total = 0;
        for (Penguin other : penguins) {
            double d = distance(other);
            if (other != this && d < perceptionRadius) {
                steeringX += other.vx;
                steeringY += other.vy;
                total++;
            }
        }
        if (total > 0) {
            steeringX /= total;
            steeringY /= total;
            double mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
            if (mag > 0) {
                steeringX = (steeringX / mag) * maxSpeed - vx;
                steeringY = (steeringY / mag) * maxSpeed - vy;
                mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
                if (mag > maxForce) {
                    steeringX = (steeringX / mag) * maxForce;
                    steeringY = (steeringY / mag) * maxForce;
                }
            }
        }
        return new double[]{steeringX, steeringY};
    }

    private double[] cohere(Penguin[] penguins) {
        double centerX = 0;
        double centerY = 0;
        int total = 0;
        for (Penguin other : penguins) {
            double d = distance(other);
            if (other != this && d < perceptionRadius) {
                centerX += other.x;
                centerY += other.y;
                total++;
            }
        }
        double steeringX = 0, steeringY = 0;
        if (total > 0) {
            centerX /= total;
            centerY /= total;
            steeringX = centerX - x;
            steeringY = centerY - y;
            double mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
            if (mag > 0) {
                steeringX = (steeringX / mag) * maxSpeed - vx;
                steeringY = (steeringY / mag) * maxSpeed - vy;
                mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
                if (mag > maxForce) {
                    steeringX = (steeringX / mag) * maxForce;
                    steeringY = (steeringY / mag) * maxForce;
                }
            }
        }
        return new double[]{steeringX, steeringY};
    }

    private double[] separate(Penguin[] penguins) {
        double steeringX = 0;
        double steeringY = 0;
        int total = 0;
        for (Penguin other : penguins) {
            double d = distance(other);
            if (other != this && d < perceptionRadius / 2) { // shorter range
                steeringX += (x - other.x) / d; // weighted by distance
                steeringY += (y - other.y) / d;
                total++;
            }
        }
        if (total > 0) {
            steeringX /= total;
            steeringY /= total;
            double mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
            if (mag > 0) {
                steeringX = (steeringX / mag) * maxSpeed - vx;
                steeringY = (steeringY / mag) * maxSpeed - vy;
                mag = Math.sqrt(steeringX * steeringX + steeringY * steeringY);
                if (mag > maxForce) {
                    steeringX = (steeringX / mag) * maxForce;
                    steeringY = (steeringY / mag) * maxForce;
                }
            }
        }
        return new double[]{steeringX, steeringY};
    }

    double distance(Penguin other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }
}
