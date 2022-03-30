package RendChis;

import ColorChis.HDRColor;
import MathChis.Ray;
import MathChis.TransformMatrix;
import MathChis.Vector3;
import ObjectChis.Scene;

import java.awt.*;
import java.util.Random;

public abstract class Renderer {
    protected int pixWidth, pixHeight;
    private TransformMatrix camTransformMatrix;
    protected Scene scene;

    private Random random;

    public Renderer(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        this.pixWidth = pixWidth;
        this.pixHeight = pixHeight;
        this.camTransformMatrix =   camTransformMatrix;
        this.scene = scene;
        random = new Random(0);
    }

    public abstract HDRColor GetPixelColor(double x, double y);

    public void Render(Graphics g) {
        for (int x = 0; x < pixWidth; x++) {
            for (int y = 0; y < pixHeight; y++) {
                HDRColor pixCol = new HDRColor(0,0,0);
                for (int s = 0; s < Consts.PIX_SAMPLES; s++) {
                    double xOff = 0;
                    double yOff = 0;
                    try {
                        pixCol = HDRColor.Add(pixCol, GetPixelColor(x+xOff, y+yOff).Clamp01());
                    } catch (Exception e) {
                        pixCol = HDRColor.Add(pixCol, Consts.ERROR_COL.Clamp01());
                    }
                }
                g.setColor(HDRColor.Mul(pixCol, 1d/Consts.PIX_SAMPLES).FlattenClamped());
                g.drawRect(x,pixHeight-y,0,0);
            }
        }
    }

    protected Ray GetTransformedRay(double x, double y) {
        double xPercent = ((double)x/(double)pixWidth) * 2d - 1d;
        double yPercent = ((double)y/(double)pixHeight) * 2d - 1d;
        xPercent *= camTransformMatrix.scale.x;
        yPercent *= camTransformMatrix.scale.y;
        Vector3 outPos = new Vector3(xPercent, yPercent, 0);
        outPos = Vector3.Add(outPos, camTransformMatrix.position);

        Vector3 frustumPos = Vector3.Add(camTransformMatrix.position, new Vector3(0,0, camTransformMatrix.scale.z));
        Vector3 rayDir = Vector3.Normalize(Vector3.Sub(outPos, frustumPos));
        return new Ray(outPos, rayDir);
    }
}
