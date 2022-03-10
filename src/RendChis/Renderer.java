package RendChis;

import MathChis.Ray;
import MathChis.TransformMatrix;
import MathChis.Vector3;
import ObjectChis.Scene;

import java.awt.*;

public abstract class Renderer {
    protected int pixWidth, pixHeight;
    private TransformMatrix camTransformMatrix;
    protected Scene scene;

    public Renderer(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        this.pixWidth = pixWidth;
        this.pixHeight = pixHeight;
        this.camTransformMatrix =   camTransformMatrix;
        this.scene = scene;
    }

    public abstract Color GetPixelColor(double x, double y);

    public void Render(Graphics g) {
        for (int x = 0; x < pixWidth; x++) {
            for (int y = 0; y < pixHeight; y++) {
                //Ray ray = new Ray()
                //try {
                    g.setColor(GetPixelColor(x, y));
                //} catch (Exception e) {
                    //g.setColor(Consts.ERROR_COL);
                //}
                g.drawRect(x,pixHeight-y,0,0);
            }
        }
    }

    protected Vector3 GetTransformedRayOrigin(double x, double y) {
        double xPercent = ((double)x/(double)pixWidth) * 2d - 1d;
        double yPercent = ((double)y/(double)pixHeight) * 2d - 1d;
        xPercent *= camTransformMatrix.scale.x;
        yPercent *= camTransformMatrix.scale.y;
        Vector3 outPos = new Vector3(xPercent, yPercent, 0);
        outPos = Vector3.Add(outPos, camTransformMatrix.position);
        return outPos;
    }
}
