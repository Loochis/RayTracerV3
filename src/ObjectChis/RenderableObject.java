package ObjectChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import MatterChis.Material;

public abstract class RenderableObject {
    public TransformMatrix trMatrix;
    public Material mat;

    public RenderableObject(TransformMatrix trMatrix, Material mat) {
        this.trMatrix = trMatrix;
        this.mat = mat;
    }

    public abstract HitInfo HitTest(Ray ray);
}
