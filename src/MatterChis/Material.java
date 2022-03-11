package MatterChis;

import MathChis.HitInfo;
import MathChis.Ray;

import java.awt.*;

public abstract class Material {
    public abstract Color GetMatColor(HitInfo info);
    public abstract Ray GetRayDir(HitInfo info);
}
