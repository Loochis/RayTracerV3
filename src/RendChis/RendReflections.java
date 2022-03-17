package RendChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.TransformMatrix;
import ObjectChis.RenderableObject;
import ObjectChis.Scene;

import java.awt.*;

public class RendReflections extends Renderer {

    public RendReflections(int pixWidth, int pixHeight, TransformMatrix camTransformMatrix, Scene scene) {
        super(pixWidth, pixHeight, camTransformMatrix, scene);
    }

    @Override
    public HDRColor GetPixelColor(double x, double y) {
        Ray ray = GetTransformedRay(x, y);
        return RecursiveTrace(ray, 0);
    }

    private HDRColor RecursiveTrace(Ray rayIn, int depth) {
        if (depth >= Consts.RAY_BOUNCES)
            return Consts.SKY_COL;
        HitInfo hitInfo = GetClosestHit(rayIn);
        if (hitInfo == null)
            return Consts.SKY_COL;

        HDRColor hitCol = hitInfo.hitObject.mat.GetMatColor(hitInfo);
        Ray reflectedRay = hitInfo.hitObject.mat.GetRayDir(hitInfo);
        if (reflectedRay == null) {
            return hitCol;
        } else {
            return HDRColor.Add(hitCol, HDRColor.Mul(RecursiveTrace(reflectedRay, depth+1), 0.8d));
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
