package ObjectChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import sun.corba.EncapsInputStreamFactory;

public abstract class Entity {
    public TransformMatrix trMatrix;

    public Entity(TransformMatrix trMatrix) {
        this.trMatrix = trMatrix;
    }

    public abstract HitInfo HitTest(Ray ray);
}
