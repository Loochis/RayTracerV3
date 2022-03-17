package RendChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import ObjectChis.RenderableObject;
import ObjectChis.Scene;

import java.awt.*;

public class DebugRendUVView extends Renderer {

    public DebugRendUVView(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public HDRColor GetPixelColor(double x, double y) {
        Ray ray = GetTransformedRay(x, y);
        HitInfo hitInfo = GetClosestHit(ray);
        if (hitInfo != null) {
            int evenOdd = (int)Math.floor(hitInfo.uvCoords.x * 10) + (int)Math.floor(hitInfo.uvCoords.y * 10);
            if (evenOdd % 2 == 0) {
                return new HDRColor(208, 208, 208);
            } else {
                return new HDRColor((float)(hitInfo.uvCoords.x * 1) % 1, (float)(hitInfo.uvCoords.y * 1) % 1, 0f);
            }
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
