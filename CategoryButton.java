package HiroCode.gui.buttons;


import HiroCode.gui.ClickGui;
import HiroCode.utils.Render.RenderUtils;
import HiroCode.Module.Category;
import net.minecraft.client.Minecraft;

import java.awt.*;

import static HiroCode.gui.buttons.ColorAttributes.*;

public class CategoryButton {
    private int x,y;
    private Category category;

    private boolean moduleTabOpened;
    private boolean scrollable;
    private int scroll;
    private CategoryButton hoveredButton;


    public CategoryButton(Category category,int x,int y) {
        this.category = category;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getWidth() {
        return (int) Minecraft.getMinecraft().fontRendererObj.getStringWidth(getCategory().name());
    }
    public int getButtonWidth() {
        return (int) (Minecraft.getMinecraft().fontRendererObj.getStringWidth("Movement")+20);
    }
    public int getButtonHeight() {
        return 19;
    }

    public void render() {
        RenderUtils.drawRound(x-15,y,getButtonWidth()+30,19,2,CATEGORY_COLOR.getRGB());
        ClickGui.drawFixedRect(x-15,y-1,x+getButtonWidth()+15,y+1,isModuleTabOpened() ? ENABLED_COLOR : DISABLED_COLOR);
        float neededX = (x-14);
        Minecraft.getMinecraft().fontRendererObj.drawString(getCategory().name(),(int) neededX,y+8,Color.black.getRGB());
    }

    public boolean isModuleTabOpened() {
        return moduleTabOpened;
    }

    public void setModuleTabOpened(boolean moduleTabOpened) {
        this.moduleTabOpened = moduleTabOpened;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public int getScroll() {
        return scroll;
    }

    public void setScroll(int scroll) {
        this.scroll = scroll;
    }

    public CategoryButton getHoveredButton() {
        return hoveredButton;
    }

    public void setHoveredButton(CategoryButton hoveredButton) {
        this.hoveredButton = hoveredButton;
    }
}
