package PoolGame.Config;
import PoolGame.Items.Pocket;
import org.json.simple.JSONObject;

/** A config class that will contain the pocket configuration */
//EricaChange added new class
public class PocketConfig implements Configurable{
    private PositionConfig position;
    private double radius;

    /**
     * Initialise the instance with the provided JSONObject
     * @param obj A JSONObject containing the pocket configuration
     */
    public PocketConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise the instance with the provided value
     * @param position The position config instance of the pocket
     * @param radius The radius of the pocket
     */
    public PocketConfig(PositionConfig position, double radius) {
        this.init(position, radius);
    }

    private void init(PositionConfig position, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius of pockets must be greater than 0");
        }
        this.position = position;
        this.radius = radius;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        PositionConfig position = new PositionConfig(json.get("position"));
        double radius = (double)json.get("radius");
        this.init(position, radius);
        return null;
    }

    /**
     * Get the position config instance as defined in the config.
     * @return The position config instance
     */
    public PositionConfig getPositionConfig() {
        return this.position;
    }

    /**
     * Get the radius of the pocket as defined in the config.
     * @return The radius of the pocket
     */
    public double getRadius() {
        return this.radius;
    }
}
