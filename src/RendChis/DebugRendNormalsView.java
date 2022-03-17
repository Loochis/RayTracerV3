package RendChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import ObjectChis.RenderableObject;
import ObjectChis.Scene;

import java.awt.*;

public class DebugRendNormalsView extends Renderer {

    public DebugRendNormalsView(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public HDRColor GetPixelColor(double x, double y) {
        Ray ray = GetTransformedRay(x, y);
        HitInfo hitInfo = GetClosestHit(ray);
        if (hitInfo != null) {

            return new HDRColor((float) Math.max(hitInfo.normal.x, 0), (float) Math.max(hitInfo.normal.y, 0), (float) Math.max(hitInfo.normal.z, 0));
        }
        return new HDRColor(0,0,0);
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
