package RendChis;

import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import ObjectChis.RenderableObject;
import ObjectChis.Scene;

import java.awt.*;

public class RendFlat extends Renderer {

    public RendFlat(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public Color GetPixelColor(double x, double y) {
        Ray ray = GetTransformedRay(x, y);
        try {
            return RecursiveTrace(ray);
        } catch (Exception e) {
            return Consts.ERROR_COL;
        }
    }

    private Color RecursiveTrace(Ray rayIn) {
        HitInfo hitInfo = GetClosestHit(rayIn);
        if (hitInfo == null)
            return Consts.SKY_COL;

        Color hitCol = hitInfo.hitObject.mat.GetMatColor(hitInfo);
        Ray reflectedRay = hitInfo.hitObject.mat.GetRayDir(hitInfo);
        if (reflectedRay == null) {
            return hitCol;
        } else {
            return Consts.ERROR_COL;
            // TODO: IMPLEMENT COLOR MATH
        }
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
