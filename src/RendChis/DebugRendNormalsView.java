package RendChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import MathChis.Vector3;
import ObjectChis.Entity;
import ObjectChis.Scene;

import java.awt.*;

public class DebugRendNormalsView extends Renderer {

    public DebugRendNormalsView(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public Color GetPixelColor(double x, double y) {
        Vector3 rayOrig = GetTransformedRayOrigin(x, y);
        HitInfo hitInfo = GetClosestHit(new Ray(rayOrig, new Vector3(0,0,1)));
        if (hitInfo != null) {

            return new Color((float) Math.max(hitInfo.normal.x, 0), (float) Math.max(hitInfo.normal.y, 0), (float) Math.max(-hitInfo.normal.z, 0));
        }
        return new Color(0,0,0);
    }

    private HitInfo GetClosestHit(Ray ray) {
        HitInfo closestInfo = null;
        for (Entity e : scene.sceneObjs) {
            HitInfo tempInfo = e.HitTest(ray);
            if (tempInfo == null)
                continue;
            if (closestInfo == null || closestInfo.depth > tempInfo.depth)
                closestInfo = tempInfo;
        }
        return closestInfo;
    }
}
