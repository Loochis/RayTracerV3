package MatterChis;

import ColorChis.HDRColor;
import MathChis.HitInfo;
import MathChis.Ray;

import java.awt.*;

public abstract class Material {
    public abstract HDRColor GetMatColor(HitInfo info);
    public abstract Ray GetRayDir(HitInfo info);
}
