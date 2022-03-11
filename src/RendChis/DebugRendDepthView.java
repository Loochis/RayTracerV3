package RendChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import ObjectChis.RenderableObject;
import ObjectChis.Scene;

import java.awt.*;

public class DebugRendDepthView extends Renderer {

    public DebugRendDepthView(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public Color GetPixelColor(double x, double y) {
        Ray ray = GetTransformedRay(x, y);
        HitInfo hitInfo = GetClosestHit(ray);
        if (hitInfo != null) {

            return new Color(1f/(float)hitInfo.depth, 1f/(float) hitInfo.depth, 1f/(float) hitInfo.depth);
        }
        return new Color(0,0,0);
    }

    private HitInfo GetClosestHit(Ray ray) {
        HitInfo closestInfo = null;
        for (RenderableObject e : scene.sceneObjs) {
            HitInfo tempInfo = e.HitTest(ray);
            if (tempInfo == null)
                continue;
            if (closestInfo == null || closestInfo.depth > tempInfo.depth)
                closestInfo = tempInfo;
        }
        return closestInfo;
    }
}
