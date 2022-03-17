package MathChis;

import RendChis.Consts;

public class Ray {

    public Vector3 origin, dir;

    public Ray(Vector3 origin, Vector3 dir) {
        this.origin = origin;
        this.dir = dir;
    }

    public void TranslateForward() {
        this.origin = Vector3.Add(this.origin, Vector3.Mul(this.dir, Consts.EPSILON));
    }
}
