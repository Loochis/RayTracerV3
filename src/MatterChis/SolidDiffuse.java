package MatterChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.Vector3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SolidDiffuse extends Material{

    // Path to textures
    public HDRColor color;

    // Argument names in this order: colour, normals, roughness, smoothness, metallic, ambient occlusion, height map

    public SolidDiffuse(HDRColor color) {
        this.color = color;
    }

    @Override
    public HDRColor GetMatColor(HitInfo info) {
        return color;
    }

    @Override
    public Ray GetRayDir(HitInfo info) {
        return new Ray(info.position, Vector3.Reflect(info.inRay.dir, info.normal));
    }
}
