package HiroCode.gui.buttons;

import HiroCode.gui.ClickGui;
import HiroCode.utils.Client.ClientUtils;
import HiroCode.utils.Color.ColorUtility;
import HiroCode.utils.Render.Particles;
import HiroCode.utils.Render.RenderUtils;
import HiroCode.MyMind;
import HiroCode.Module.Module;
import HiroCode.Module.ModuleManager;
import HiroCode.Modules.Render.CGui;
import HiroCode.Modules.System.Speak;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

import static HiroCode.gui.buttons.ColorAttributes.*;
import HiroCode.gui.ClickGui;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    private boolean settingTabOpened;
    private Module module;
    private final List<Particles> particles = new ArrayList<>();
    private int mouseX,mouseY;
    private int enableAnim;
   public  CGui ka = (CGui) ModuleManager.getModule(CGui.class);
    public ModuleButton(Module module) {
        this.module = module;
    }

    public boolean isSettingTabOpened() {
        return settingTabOpened;
    }

    public void setSettingTabOpened(boolean settingTabOpened) {
        this.settingTabOpened = settingTabOpened;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void render(int x,int y,int width,int height,int parentY,int mouseX,int mouseY) {
        if (y+7 >= parentY) {
        	
           RenderUtils.drawRound(x, y , 96, 15 - 1,2, CATEGORY_COLOR.getRGB());
            ClickGui.drawFixedRect(x + 1, y - 1, width - 1, height - 3, BUTTON_COLOR);
            if (getModule().isEnabled()) {
                if (enableAnim > 0) {
                    enableAnim--;
                }
            } else {
                if (enableAnim < 45) {
                    enableAnim++;
                }
            }

            int neededX = (int) ((width + 2 + x) / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(getModule().getName()) / 2);
            if (isHovered(x + 2, y, width - 1, height - 3, mouseX, mouseY)) {
                ClickGui.drawFixedRect(x + 1, y - 1, width - 1, height - 3, HOVERED_BUTTON_COLOR);
            }
         
            if(ka.rain.isBool()) {
            if(module.isEnabled()) {
            	getRainbow2(getModule().getName(), neededX, y+1);
            }else {
            Minecraft.getMinecraft().fontRendererObj.drawString(getModule().getName(), neededX, y+1, DISABLED_COLOR.getRGB() -2);
        }
            } else {
            	 Minecraft.getMinecraft().fontRendererObj.drawString(getModule().getName(), neededX, y+1, module.isEnabled() ? CATEGORY_COLOR.getRGB() : DISABLED_COLOR.getRGB() -2);
            }
        }
        for (final Particles s : particles) {
            if (s.opacity > 4)
                s.render2D();
        }
    for (final Particles s : particles) {
        s.updatePosition();

        if (s.opacity < 1)
            particles.remove(s);
    }
    final ArrayList<Particles> removeList = new ArrayList<>();
    for (final Particles s : particles) {
        if (s.opacity <= 1) {
            removeList.add(s);
        }
    }

    for (final Particles s : removeList) {
        particles.remove(s);
    }
    	   if ( enableAnim > 0 && getModule().isEnabled() && Minecraft.getMinecraft().currentScreen instanceof ClickGui ) {
               for (int i = 0; i <= 2; i++) {
                   final Particles s = new Particles();
                   s.init(x + 1.0f,y+7  , (Math.random() * 2.3 )  , (Math.random() *-0.2 ) ,
                           Math.random() * 4, new Color(getRainbow(3,3)));
                   particles.add(s);
               }
       	   }
    }

    private boolean isHovered(int x,int y,int width,int height,int mouseX,int mouseY) {
        return mouseX >= x && mouseX <= width && mouseY >= y && mouseY <= height;
    }
	  public static int getRainbow(int speed, int offset) {
		    float hue = (float)((System.currentTimeMillis() + offset) % speed);
		    hue /= speed;
		    return Color.getHSBColor(hue, 0.5F, 1.0F).getRGB();
		  }
	  public void getRainbow2(String s, double d, double e) {
		    int xx = (int)d;
		    for (int i = 0; i < s.length(); i++) {
		      String sdd = (new StringBuilder(String.valueOf(s.charAt(i)))).toString();
		      Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(sdd, xx, (float)e, getRainbow(1500, -15 * i * 3));
		      xx += Minecraft.getMinecraft().fontRendererObj.getStringWidth(sdd);
		    } 
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

}
