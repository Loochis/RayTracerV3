import ColorChis.HDRColor;
import MathChis.TransformMatrix;
import MathChis.Vector3;
import MatterChis.Material;
import MatterChis.PBRMat;
import MatterChis.SolidDiffuse;
import ObjectChis.Scene;
import ObjectChis.Sphere;
import RendChis.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        Main main = new Main();
        main.BeginRender();
    }

    public void BeginRender() {
        BufferedImage bi = new BufferedImage(Consts.IMG_WIDTH, Consts.IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();

        TransformMatrix cameraTr = new TransformMatrix(new Vector3(), new Vector3(), new Vector3());
        cameraTr.scale = new Vector3(5,1,30);
        float aspectRatio = (float)Consts.IMG_HEIGHT / (float)Consts.IMG_WIDTH;
        cameraTr.scale.y = cameraTr.scale.x*aspectRatio;

        Material tiledMat = new PBRMat("TexTests/Tiles/", "Debug_Tiles");

        Scene scene = new Scene();
        scene.sceneObjs = new ArrayList<>();
        scene.sceneObjs.add(new Sphere(new Vector3(0,0,-5), 1, tiledMat));
        scene.sceneObjs.add(new Sphere(new Vector3(1,0,-6), 1.4d, tiledMat));
        scene.sceneObjs.add(new Sphere(new Vector3(-4,2,-3), 2d, tiledMat));
        Random rand = new Random(1);
        for (int i = 0; i < 20; i++) {
            Material nMat = new SolidDiffuse(new HDRColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat())));
            Vector3 newPos = new Vector3(rand.nextDouble()*cameraTr.scale.x*2d-cameraTr.scale.x, rand.nextDouble()*cameraTr.scale.y*2d-cameraTr.scale.y, rand.nextDouble()*-10 - 1);
            scene.sceneObjs.add(new Sphere(newPos, rand.nextDouble()*2, nMat));
        }

        Renderer rend = new RendLit(Consts.IMG_WIDTH, Consts.IMG_HEIGHT, cameraTr, scene);
        rend.Render(g);

        File theDir = new File("IMG");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        File file = new File("IMG/testIMG.png");
        try {
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            System.err.println("FILEIO Error: sorry B0ss, you're SOL");
        }
    }
}