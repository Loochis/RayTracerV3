package ColorChis;

import MathChis.Vector3;

import java.awt.*;

public class HDRColor extends Vector3 {
    public HDRColor(double r, double g, double b) {
        super(r, g, b);
    }

    public HDRColor(Color c) {
        super(c.getRed()/255f, c.getGreen()/255f, c.getBlue()/255f);
    }

    public HDRColor(Vector3 v) {
        super(v.x, v.y, v.z);
    }

    public Color FlattenClamped() {
        float r = (float) Math.max(Math.min(x, 1f), 0f);
        float g = (float) Math.max(Math.min(y, 1f), 0f);
        float b = (float) Math.max(Math.min(z, 1f), 0f);
        return new Color(r, g, b);
    }

    public static HDRColor Add(HDRColor h1, HDRColor h2) {
        return new HDRColor(Vector3.Add(h1, h2));
    }

    public static HDRColor Mul(HDRColor h, double d) {
        return new HDRColor(Vector3.Mul(h, d));
    }

    public static HDRColor Clamp0(HDRColor h) {
        return new HDRColor(Math.max(h.x,0), Math.max(h.y,0), Math.max(h.z,0));
    }

    public static HDRColor Clamp01(HDRColor h) {
        return new HDRColor(Math.min(Math.max(h.x,0), 1), Math.min(Math.max(h.y,0), 1), Math.min(Math.max(h.z,0), 1));
    }

    public HDRColor Clamp0() {
        return Clamp0(this);
    }
    public HDRColor Clamp01() {
        return Clamp01(this);
    }
}
