package RendChis;

import java.awt.*;

public class DebugRendRGGradient extends Renderer{

    public DebugRendRGGradient(int pixWidth, int pixHeight) {
        super(pixWidth, pixHeight, null, null);
    }

    @Override
    public Color GetPixelColor(double x, double y) {
        return new Color((float)x/(float)pixWidth, (float)y/(float)pixHeight, 0f, 1f);
    }
}
