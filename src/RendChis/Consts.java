package RendChis;

import ColorChis.HDRColor;

import java.awt.*;

public class Consts {
    public static final int IMG_WIDTH = 1920;
    public static final int IMG_HEIGHT = 1080;

    public static final int PIX_SAMPLES = 2;
    public static final int RAY_BOUNCES = 3;
    public static final int DIFFUSE_SAMPLES = 5;

    public static final HDRColor SKY_COL = new HDRColor(new Color(255, 255, 255));

    public static final HDRColor ERROR_COL = new HDRColor(new Color(255, 67, 254));

    public static final double EPSILON = 0.001d;
}