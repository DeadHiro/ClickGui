package HiroCode.gui.buttons;


import HiroCode.Module.Module;
import HiroCode.Module.ModuleManager;
import HiroCode.Module.settings.ValueManager;
import HiroCode.Modules.Render.CGui;


import java.awt.*;
import java.io.IOException;

public class ColorAttributes {
    public static int categoryRed = 240,disabledRed = 75,hoveredRed = 125,buttonRed = 175;
    public static int categoryGreen = 240,disabledGreen = 75,hoveredGreen = 125,buttonGreen = 175;
    public static int categoryBlue = 240,disabledBlue = 75,hoveredBlue = 125,buttonBlue = 175;

    private static Module m = ModuleManager.getModule(CGui.class);
    public static Color ENABLED_COLOR = new Color(ValueManager.getValueByName("GuiRed",m).getInt(), ValueManager.getValueByName("GuiGreen",m).getInt(), ValueManager.getValueByName("GuiBlue",m).getInt());
    public static Color DISABLED_COLOR = new Color(disabledRed, disabledGreen, disabledBlue);
    public static Color HOVERED_BUTTON_COLOR = new Color(hoveredRed, hoveredGreen, hoveredBlue,125);
    public static Color BUTTON_COLOR = new Color(buttonRed, buttonGreen, buttonBlue);
    public static Color CATEGORY_COLOR = new Color(categoryRed, categoryGreen, categoryBlue);




    public ColorAttributes() {
        ENABLED_COLOR = new Color(ValueManager.getValueByName("GuiRed",m).getInt(), ValueManager.getValueByName("GuiGreen",m).getInt(), ValueManager.getValueByName("GuiBlue",m).getInt());
        DISABLED_COLOR = new Color(disabledRed, disabledGreen, disabledBlue);
        HOVERED_BUTTON_COLOR = new Color(hoveredRed, hoveredGreen, hoveredBlue,125);
        BUTTON_COLOR = new Color(buttonRed, buttonGreen, buttonBlue,150);
        CATEGORY_COLOR = new Color(categoryRed, categoryGreen, categoryBlue);
    }
}
