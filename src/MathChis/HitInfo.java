package MathChis;

import ObjectChis.RenderableObject;

public class HitInfo {
    public Vector3 position;
    public Vector3 normal;
    public double depth;
    public Vector3 uvCoords;
    public Ray inRay;
    public RenderableObject hitObject;

    public HitInfo(Vector3 position, Vector3 normal, double depth) {
        this.position = position;
        this.normal = normal;
        this.depth = depth;
        this.uvCoords = new Vector3();
    }

    public HitInfo(Vector3 position, Vector3 normal, double depth, Vector3 uvCoords, Ray inRay, RenderableObject hitObject) {
        this.position = position;
        this.normal = normal;
        this.depth = depth;
        this.uvCoords = uvCoords;
        this.inRay = inRay;
        this.hitObject = hitObject;
    }
}
