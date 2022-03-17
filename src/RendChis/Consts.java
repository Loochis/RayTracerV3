package RendChis;

import ColorChis.HDRColor;

import java.awt.*;

public class Consts {
    public static final int IMG_WIDTH = 1920;
    public static final int IMG_HEIGHT = 1080;

    public static final int PIX_SAMPLES = 10;
    public static final int RAY_BOUNCES = 3;

    public static final HDRColor SKY_COL = new HDRColor(new Color(50, 50, 50));

    public static final HDRColor ERROR_COL = new HDRColor(new Color(255, 67, 254));

    public static final double EPSILON = 0.01d;
}
