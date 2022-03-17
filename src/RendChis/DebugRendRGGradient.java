package RendChis;

import ColorChis.HDRColor;

import java.awt.*;

public class DebugRendRGGradient extends Renderer{

    public DebugRendRGGradient(int pixWidth, int pixHeight) {
        super(pixWidth, pixHeight, null, null);
    }

    @Override
    public HDRColor GetPixelColor(double x, double y) {
        return new HDRColor(x/(double)pixWidth, y/(double)pixHeight, 0d);
    }
}
