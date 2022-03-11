package MathChis;

public class Vector3{
    public double x, y, z;

    // CONSTRUCTORS ------------------------------ //

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(double d) {
        this.x = d;
        this.y = d;
        this.z = d;
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    // STATIC METHODS ---------------------------- //

    public static double Length2(Vector3 v) {
        return v.x*v.x +v.y*v.y + v.z*v.z;
    }

    public static double Length(Vector3 v) {
        return Math.sqrt(Length2(v));
    }

    public static double Dot(Vector3 v1, Vector3 v2) {
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }

    public static Vector3 Normalize(Vector3 v) {
        double l = Length(v);
        return new Vector3(v.x/l, v.y/l, v.z/l);
    }

    public static Vector3 Invert(Vector3 v) {
        return new Vector3(-v.x, -v.y, -v.z);
    }

    public static Vector3 Reflect(Vector3 in, Vector3 normal) {
        double coeff = 2*Vector3.Dot(in, normal);
        return Vector3.Sub(in, Vector3.Mul(normal, coeff));
    }

    // INSTANCE METHODS -------------------------- //

    public double Length2() {
        return Length2(this);
    }

    public double Length() {
        return Length(this);
    }

    public void Normalized() {
        double l = this.Length();
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public void Inverted() {
        Vector3 newV = Vector3.Invert(this);
        this.x = newV.x;
        this.y = newV.y;
        this.z = newV.z;
    }

    public Vector3 CopyOf() {
        return new Vector3(this.x, this.y, this.z);
    }

    // ARITHMETIC METHODS ------------------------ //

    public static Vector3 Add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector3 Sub(Vector3 v1, Vector3 v2) {
        return Vector3.Add(v1, Vector3.Invert(v2));
    }

    public static Vector3 FromTo(Vector3 from, Vector3 to) {
        return Vector3.Sub(to, from);
    }

    public static Vector3 Mul(Vector3 v, double d) {
        return new Vector3(v.x * d, v.y * d, v.z *d);
    }
}
