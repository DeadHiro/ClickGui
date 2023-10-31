package HiroCode.gui;

import HiroCode.gui.buttons.*;
import HiroCode.Module.Category;
import HiroCode.Module.Module;
import HiroCode.Module.ModuleManager;
import HiroCode.Module.settings.Type;
import HiroCode.Module.settings.Value;
import HiroCode.Module.settings.ValueManager;
import HiroCode.event.EventManager;
import HiroCode.event.EventTarget;
import HiroCode.event.events.UpdateEvent;
import HiroCode.Modules.Render.CGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static HiroCode.gui.buttons.ColorAttributes.*;


public class ClickGui extends GuiScreen {



    public String clickedTextValue;
    public Module clickedTextModule;


    public ClickGui() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc,mc.displayWidth,mc.displayHeight);
    
        int y = 12;
        for (Category cat : Category.values()) {
            ButtonManager.addToCategoryList(cat,15,y);
            y+=20;
        }
        for (Module mod : ModuleManager.getModuleList()) {
        	if(!mod.viewGui) {
            ButtonManager.addToModuleList(mod);
        	}
            for (Value vals : ValueManager.getValuesFromModule(mod)) {
                ButtonManager.addToValueList(vals);
            }
        }
        ButtonManager.getModuleButtons().sort(Comparator.comparingDouble(m -> mc.fontRendererObj.getStringWidth(m.getModule().getName())));
        EventManager.register(this);
    }

    @EventTarget
   public void update(UpdateEvent event) {

   }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.drawDefaultBackground();
        new ColorAttributes();
        ScaledResolution sr = new ScaledResolution(mc,mc.displayWidth,mc.displayHeight);
        for (CategoryButton cat : ButtonManager.getCategoryList()) {
            if (movingCategory && cat.getHoveredButton() == cat) {
                cat.setX(mouseX - cat.getButtonWidth() / 2);
                cat.setY(mouseY - 3);
            }
            if (cat.isModuleTabOpened()) {
                int moduleY = cat.getY() + 20 + cat.getScroll();
                for (ModuleButton mod : ButtonManager.getModuleButtons()) {
                    if (mod.getModule().getCategory() == cat.getCategory()) {
                        mod.setMouseX(mouseX);
                        mod.setMouseY(mouseY);
                        mod.render(cat.getX() - 15, moduleY - 1, cat.getX() + cat.getButtonWidth() + 15, moduleY + 11, cat.getY() + 20, mouseX, mouseY);
                        moduleY += 11;
                        if (mod.isSettingTabOpened()) {
                            for (ValueButton val : ButtonManager.getValueButtons()) {
                            	   if (val.getValue().getVisibility() == null || val.getValue().getVisibility().isVisible()) {
                                if (val.getValue().getModule() == mod.getModule()) {
                                    val.render(cat.getX()-15,moduleY-1,cat.getButtonWidth(),11,cat.getY()+20,mouseX,mouseY);
                                    if (val.getValue().getType().equals(Type.NUMBER)) {
                                        moduleY+=13;
                                    }
                                    moduleY+=11;
                                }
                            	   }
                            }
                        }
                    }
                }

                int descY = cat.getY() + 20 + cat.getScroll();
                for (ModuleButton mod : ButtonManager.getModuleButtons()) {
                    if (mod.getModule().getCategory() == cat.getCategory()) {
                        mod.setMouseX(mouseX);
                        mod.setMouseY(mouseY);
                        descY += 11;
                        if (mod.isSettingTabOpened()) {
                            for (Value val : ValueManager.getValuesFromModule(mod.getModule())) {
                                if (val.getType() == Type.NUMBER) {
                                    descY += 13;
                                }
                                if (isHovered(cat.getX() - 15, descY, cat.getButtonWidth() + 30, 8, mouseX, mouseY)) {
                                    if (descY >= cat.getY() + 20) {
                                        drawFixedRect(mouseX + 5, mouseY - 1, mouseX + mc.fontRendererObj.getStringWidth(val.getDescription()) + 8, mouseY + 11, DISABLED_COLOR);
                                        mc.fontRendererObj.drawString(val.getDescription(), mouseX + 6, mouseY, ENABLED_COLOR.getRGB());
                                    }
                                }
                                descY += 11;
                            }
                            }
                    }
                }
                cat.setScrollable(descY > sr.getScaledHeight() || cat.getY() - descY <= 0);
                if (isHovered(cat.getX() - 15, cat.getY() - 1, cat.getButtonWidth() + 15, cat.getButtonHeight() + descY, mouseX, mouseY)) {
                    if (cat.isScrollable()) {
                        cat.setScroll(cat.getScroll() + Mouse.getDWheel() / 20);
                        int value = descY - cat.getY() - 20 + cat.getScroll();
                        int val2 = descY - value;
                        if (val2 < cat.getY() + 20) {
                            cat.setScroll(0);
                        }
                    }
                }
            } else {
                cat.setScroll(0);
            }
            cat.render();
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        movingCategory = false;
        for (CategoryButton cat : ButtonManager.getCategoryList()) {
            cat.setHoveredButton(null);
        }
        CGui.save();
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        for (CategoryButton cat : ButtonManager.getCategoryList()) {
            if (isHovered(cat.getX()-15,cat.getY()-1,cat.getButtonWidth()+30,cat.getButtonHeight(),mouseX,mouseY)) {
                if (mouseButton == 1) {
                    cat.setModuleTabOpened(!cat.isModuleTabOpened());
                }
            }
            if (cat.isModuleTabOpened()) {
                int moduleY = cat.getY()+20+cat.getScroll();
                for (ModuleButton mod : ButtonManager.getModuleButtons()) {
                    if (mod.getModule().getCategory() == cat.getCategory()) {
                        if (isModuleHovered(cat.getX() - 14, moduleY - 2, cat.getX() + cat.getButtonWidth() + 13, moduleY + 8, mouseX, mouseY)) {
                            if (moduleY + 13 >= cat.getY() + 20) {
                                if (mouseButton == 0) {
                                    mod.getModule().toggle();
                                } else if (mouseButton == 1) {
                                    mod.setSettingTabOpened(!mod.isSettingTabOpened());
                                }
                            }
                        }
                        moduleY += 11;
                        if (mod.isSettingTabOpened()) {
                            for (ValueButton val : ButtonManager.getValueButtons()) {
                                if (val.getValue().getModule() == mod.getModule()) {
                                    if (val.getValue().getType() == Type.NUMBER) {
                                        moduleY += 13;
                                    }
                                	   if (val.getValue().getVisibility() == null || val.getValue().getVisibility().isVisible()) {
                                    if (val.getValue().getType() == Type.BOOLEAN && (moduleY + 13 >= cat.getY() + 20)) {
                                        if (isHovered(cat.getX() - 15, moduleY, cat.getButtonWidth() + 30, 10, mouseX, mouseY)) {
                                            val.getValue().setBool(!val.getValue().isBool());
                                        }
                                    }
                                	   }
                                   
                                    if (val.getValue().getType() == Type.MODE && (moduleY + 13 >= cat.getY() + 20)) {
                                        if (isHovered(cat.getX() - 15, moduleY, cat.getButtonWidth() + 30, 10, mouseX, mouseY)) {
                                            ArrayList<String> modes = new ArrayList<>(Arrays.asList(val.getValue().getModes()));
                                            int current = modes.indexOf(val.getValue().getMode());
                                            val.setModeY(15);
                                            if (mouseButton == 0) {
                                                current += 1;
                                                if (current >= modes.size()) {
                                                    current = 0;
                                                }
                                                val.getValue().setMode(modes.get(current));
                                            } else if (mouseButton == 1) {
                                                current -= 1;
                                                if (current < 0) {
                                                    current = modes.size() - 1;
                                                }
                                                val.getValue().setMode(modes.get(current));
                                            }
                                        }
                                    }
                                    if (val.getValue().getType() == Type.TEXT && (moduleY + 13 >= cat.getY() + 20)) {
                                        if (isHovered(cat.getX() - 15, moduleY, cat.getButtonWidth() + 30, 10, mouseX, mouseY)) {
                                            if (val.getTextField() == null) {
                                                val.setTextField(new GuiTextField(1, mc.fontRendererObj, cat.getX() - 15, moduleY - 2, cat.getX() + cat.getButtonWidth() + 15, moduleY + 10));
                                            }
                                            val.getTextField().setFocused(true);
                                            val.getTextField().setText("");
                                            clickedTextValue = val.getValue().getName();
                                            clickedTextModule = val.getValue().getModule();
                                        }
                                    }
                                    moduleY += 11;
                                }
                            }
                        }
                    }
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }



    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        GuiTextField text = null;
        if (clickedTextModule != null && clickedTextValue != null) {
            if (ButtonManager.getValue(clickedTextValue, clickedTextModule).getTextField() != null) {
                text = ButtonManager.getValue(clickedTextValue, clickedTextModule).getTextField();
            }
        }

        if (text != null) {
            if (text.isFocused()) {
                text.textboxKeyTyped(typedChar, keyCode);
                if (keyCode == Keyboard.KEY_RETURN) {
                    text.setFocused(false);
                }
            }
        }
        super.keyTyped(typedChar, keyCode);
    }

    private boolean movingCategory;

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

        for (CategoryButton cat : ButtonManager.getCategoryList()) {
            if (!movingCategory && clickedMouseButton == 0 && cat.getHoveredButton() == null && isHovered(cat.getX() - 14, cat.getY() - 1, cat.getButtonWidth() + 30, cat.getButtonHeight(), mouseX, mouseY)) {
                cat.setHoveredButton(cat);
                movingCategory = true;
            }
            if (movingCategory && cat.getHoveredButton() == cat) {
                cat.setX(mouseX - cat.getButtonWidth() / 2);
                cat.setY(mouseY-3);
            }
            if (cat.isModuleTabOpened()) {
                int moduleY = cat.getY() + 20 + cat.getScroll();
                for (ModuleButton mod : ButtonManager.getModuleButtons()) {
                    if (mod.getModule().getCategory() == cat.getCategory()) {
                        moduleY += 11;
                        if (mod.isSettingTabOpened()) {
                            for (ValueButton val : ButtonManager.getValueButtons()) {
                                if (val.getValue().getModule() == mod.getModule()) {
                                    if (val.getValue().getType() == Type.NUMBER && (moduleY + 13 >= cat.getY() + 20)) {
                                        if (isHovered(cat.getX() - 14, moduleY + 11, cat.getButtonWidth() + 30, 11, mouseX, mouseY)) {
                                            double min = val.getValue().getMin();
                                            double max = val.getValue().getMax();
                                            double inc = val.getValue().getIncrement();
                                            double valAbs = mouseX - (cat.getX() - 14 + 2 + 1.0D);
                                            double perc = valAbs / ((cat.getX() + cat.getButtonWidth() + 15) - (cat.getX() - 14) - 5.0D);
                                            perc = Math.min(Math.max(0.0D, perc), 1.0D);
                                            double valRel = (max - min) * perc;
                                            double val1 = min + valRel;
                                            val1 = Math.round(val1 * (1.0D / inc)) / (1.0D / inc);
                                            val.getValue().setNumber(val1);
                                        }
                                        moduleY += 13;
                                    }
                                    moduleY += 11;
                                }
                            }
                        }
                    }
                }
            }
        }
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }


    private boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    private boolean isModuleHovered(int x,int y,int width,int height,int mouseX,int mouseY) {
        return mouseX >= x && mouseX <= width && mouseY >= y && mouseY <= height;
    }
    public static void drawFixedRect(double left, double top, double right, double bottom, Color color)
    {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
        Gui.drawRect(left,top,right,bottom,color.getRGB());
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
}
