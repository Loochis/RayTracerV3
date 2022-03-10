package MathChis;

public class HitInfo {
    public Vector3 position;
    public Vector3 normal;
    public double depth;

    public HitInfo(Vector3 position, Vector3 normal, double depth) {
        this.position = position;
        this.normal = normal;
        this.depth = depth;
    }
}
