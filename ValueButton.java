package HiroCode.gui.buttons;

import HiroCode.gui.ClickGui;
import HiroCode.utils.Math.MathUtils;
import HiroCode.Module.ModuleManager;
import HiroCode.Module.settings.Value;
import HiroCode.Modules.Render.CGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.text.DecimalFormat;

import static HiroCode.gui.buttons.ColorAttributes.*;

public class ValueButton {
    private Value value;

    private int mouseX,mouseY;
    private double numberAnim,boolAnim,modeAnim,modeY;
    private GuiTextField textField;

    public ValueButton(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void render(int x,int y,int width,int height,int parentY,int mouseX,int mouseY) {
        if (y+7 >= parentY) {
            if (y+14 >= parentY) {
                ClickGui.drawFixedRect(x, y, x+width+30, y+height, CATEGORY_COLOR);
            }
            switch (getValue().getType()) {
                case TEXT: {
                	Minecraft.getMinecraft().fontRendererObj.drawString(getValue().getName() + " : " + getValue().getText(), x+2, y+2, ENABLED_COLOR.getRGB());
                    if (getTextField() != null) {
                        if (getTextField().isFocused()) {
                            getValue().setText(getTextField().getText());
                        }
                    }
                    break;
                }
                case BOOLEAN: {
                    if (isHovered(x, y+1, width+30, 10, mouseX, mouseY)) {
                 		     ClickGui.drawFixedRect(x, y-1, x+width+30, y+height+1, HOVERED_BUTTON_COLOR);
                    }
                    ClickGui.drawFixedRect(x+2, y+1, x+10, y+9, DISABLED_COLOR);
                    if (getValue().isBool()) {
                        if (ENABLED_COLOR.getAlpha() > getBoolAnim()) {
                            setBoolAnim(getBoolAnim() + 2.5);
                        }
                    } else {
                        if (getBoolAnim() > 0) {
                            setBoolAnim(getBoolAnim() - 2.5);
                        }
                    }
                    if (getBoolAnim() > 255) {
                        setBoolAnim(255);
                    }
                    if (getBoolAnim() < 0) {
                        setBoolAnim(0);
                    }
                    CGui cGui = (CGui) ModuleManager.getModule(CGui.class);
                    if (cGui.anim.isBool()) {
                        ClickGui.drawFixedRect(x+4, y+3, x+8, y+7, new Color(ENABLED_COLOR.getRed(), ENABLED_COLOR.getGreen(), ENABLED_COLOR.getBlue(), (int) getBoolAnim()));
                    } else {
                        if (getValue().isBool()) {
                            ClickGui.drawFixedRect(x+4, y+4, x+8, y+7, ENABLED_COLOR);
                        }
                    }
                    Minecraft.getMinecraft().fontRendererObj.drawString(getValue().getName(), x+12, y+2, DISABLED_COLOR.getRGB());
                    break;
                }
                case NUMBER: {
                    CGui cGui = (CGui) ModuleManager.getModule(CGui.class);
                    ClickGui.drawFixedRect(x, y, x+width+30, y+height*2+2, CATEGORY_COLOR);
                    Minecraft.getMinecraft().fontRendererObj.drawString(getValue().getName(), x+1, y+1, DISABLED_COLOR.getRGB());
                    Minecraft.getMinecraft().fontRendererObj.drawString(String.valueOf(getValue().getNumber()), x + 29 + width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(String.valueOf(getValue().getNumber())), y+1, DISABLED_COLOR.getRGB());
                    ClickGui.drawFixedRect(x+1, y+12, x+width+29, y + height*2, HOVERED_BUTTON_COLOR);
                    double value = ((cGui.anim.isBool() ? getNumberAnim() : getValue().getNumber()) - getValue().getMin()) / (getValue().getMax() - getValue().getMin());
                    ClickGui.drawFixedRect(x+2, (y+height+2), (double) (x+2) + value * (double) (width+26), (y+height*2-1), ColorAttributes.ENABLED_COLOR);
                    if (cGui.anim.isBool()) {
                        if (getNumberAnim() < getValue().getMin()) {
                            setNumberAnim(getValue().getMin());
                        }
                        if (getNumberAnim() > getValue().getMax()) {
                            setNumberAnim(getValue().getMax());
                        }
                        if (getValue().getNumber() < getNumberAnim()) {
                            setNumberAnim(getNumberAnim() - getValue().getIncrement() / 4);
                        } else if (getValue().getNumber() > getNumberAnim()) {
                            setNumberAnim(getNumberAnim() + getValue().getIncrement() / 4);
                        }
                        setNumberAnim(MathUtils.fixFormat(getNumberAnim(),6));
                    }
                    break;
                }
                case MODE: {
                    String mode = getValue().getName();
                    if (isHovered(x, y+1, width+30, height-1, mouseX, mouseY)) {
                        ClickGui.drawFixedRect(x, y-1, x+width+30, y+height, HOVERED_BUTTON_COLOR);
                    }
                    if (getModeY() > getModeAnim()) {
                        setModeAnim(getModeAnim() + 0.5);
                    } else if (getModeAnim() > getModeY()) {
                        setModeAnim(getModeAnim() - 0.5);
                    } else {
                        setModeY(0);
                    }
                    Minecraft.getMinecraft().fontRendererObj.drawString(mode + " : ", x+1, y+2, DISABLED_COLOR.getRGB());
                    CGui cGui = (CGui) ModuleManager.getModule(CGui.class);

                    Minecraft.getMinecraft().fontRendererObj.drawString(getValue().getMode(), x + 1 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(mode + " : "), (int) (y+2 + (cGui.anim.isBool() ? (float) getModeAnim() : 0)), DISABLED_COLOR.getRGB());
                    break;
                }
            }
        }
    }

    private boolean isHovered(int x,int y,int width,int height,int mouseX,int mouseY) {
        return mouseX >= x && mouseX <= width+x && mouseY >= y && mouseY <= height+y;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public double getNumberAnim() {
        return numberAnim;
    }

    public void setNumberAnim(double numberAnim) {
        this.numberAnim = numberAnim;
    }

    public double getBoolAnim() {
        return boolAnim;
    }

    public void setBoolAnim(double boolAnim) {
        this.boolAnim = boolAnim;
    }

    public double getModeAnim() {
        return modeAnim;
    }

    public void setModeAnim(double modeAnim) {
        this.modeAnim = modeAnim;
    }

    public double getModeY() {
        return modeY;
    }

    public void setModeY(double modeY) {
        this.modeY = modeY;
    }

    public GuiTextField getTextField() {
        return textField;
    }

    public void setTextField(GuiTextField textField) {
        this.textField = textField;
    }
}
