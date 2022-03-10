package MathChis;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TransformMatrix {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public TransformMatrix(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Vector3 GetTransformedPoint(Vector3 in) {
        Vector3 trP = in.CopyOf();
        // Scale Vector
        trP.x *= scale.x;
        trP.y *= scale.y;
        trP.z *= scale.z;
        // Translate Vector
        trP = Vector3.Add(trP, position);
        // Return translated vector
        return trP;
    }
}
