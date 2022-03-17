package MatterChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;
import MathChis.Vector3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PBRMat extends Material{

    // Path to textures
    public String texPath = "TexTests/Tiles";
    public String name;

    // Argument names in this order: colour, normals, roughness, smoothness, metallic, ambient occlusion, height map
    private final String[] ARG_NAMES = {"COL", "NORM", "ROUGH", "SMOOTH", "METAL", "AO", "DISP"};
    private BufferedImage[] argImgs;

    public PBRMat(String texPath, String name) {
        this.texPath = texPath;
        this.name = name;
        ReloadTextures();
    }

    private void ReloadTextures() {
        System.out.println("Loading "+name+" PBR Textures:");
        argImgs = new BufferedImage[ARG_NAMES.length];
        for (int i = 0; i < ARG_NAMES.length; i++) {
            try {
                argImgs[i] = ImageIO.read(new File(texPath+ARG_NAMES[i]+".jpg"));
                System.out.println("- "+String.format("%6s", ARG_NAMES[i]) + " ☑");
            } catch(Exception e) {
                System.out.println("- "+String.format("%6s", ARG_NAMES[i]) + " ☐");
            }
        }
    }

    @Override
    public HDRColor GetMatColor(HitInfo info) {
        int newX = Math.floorMod((int)(info.uvCoords.x*argImgs[0].getWidth()), argImgs[0].getWidth());
        int newY = Math.floorMod((int)(info.uvCoords.y*argImgs[0].getHeight()), argImgs[0].getHeight());
        return new HDRColor(bufIMGtoColor(argImgs[0].getRGB(newX, newY)));
    }

    @Override
    public Ray GetRayDir(HitInfo info) {
        return new Ray(info.position, Vector3.Reflect(info.inRay.dir, info.normal));
    }

    // TODO: Eventually put this in the Color script
    private Color bufIMGtoColor(int in) {
        int red =   (in & 0x00ff0000) >> 16;
        int green = (in & 0x0000ff00) >> 8;
        int blue =   in & 0x000000ff;
        return new Color(red, green, blue);
    }
}
