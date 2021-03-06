package ObjectChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import MathChis.Vector3;
import MatterChis.Material;

public class Sphere extends RenderableObject {

    public Sphere(Vector3 pos, double radius, Material mat) {
        super(new TransformMatrix(pos, new Vector3(), new Vector3(radius)), mat);
    }

    @Override
    public HitInfo HitTest(Ray ray) {
        double t = Vector3.Dot(Vector3.Sub(super.trMatrix.position, ray.origin), ray.dir); // Move position to 0,0 relative to ray. Get dot
        Vector3 p = Vector3.Add(Vector3.Mul(ray.dir, t), ray.origin); // Find point on perpendicular plane, translate back to world space
        double y = Vector3.Length2(Vector3.Sub(super.trMatrix.position, p)); // Distance from the center of the sphere
        if (y > Math.pow(super.trMatrix.scale.x, 2)) // If the distance is greater than the radius, return no intersections
            return null;

        float x = (float) Math.sqrt(Math.pow(super.trMatrix.scale.x, 2) - y); // Distance from plane intersection to spheres surface

        double finalDepth = t - x;
        if (finalDepth <= 0)
            finalDepth = t + x;
        if (finalDepth <= 0)
            return null;

        // Get hit point and hit normal
        Vector3 finalP = Vector3.Add(Vector3.Mul(ray.dir, finalDepth), ray.origin);
        Vector3 norm = Vector3.Normalize(Vector3.Sub(finalP, super.trMatrix.position));

        // Calculate UV coords
        double u = Math.atan2(norm.x, norm.z) / (Math.PI) + 0.5d;
        double v = norm.y * 0.5d + 0.5d;

        // Generate a hit-object
        return new HitInfo(finalP, norm, t - x, new Vector3(u, v, 0), ray, this);
    }
}
