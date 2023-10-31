package HiroCode.Module.settings;

import HiroCode.Module.Module;
import net.minecraft.client.gui.GuiTextField;

public class Value {
    private String name,description,mode,text;
    private Module module;
    private Type type;
    private IOption visibility;
    private double number,increment,min,max;
    private boolean bool;
    private String[] modes;
    public Value(String name, Module module, boolean bool, String description ) {
        this.name = name;
        this.description = description;
        this.module = module;
        this.bool = bool;
        type = Type.BOOLEAN;
        ValueManager.addToList(this);
    }
    public Value(String name, Module module, boolean bool, String description , IOption visibility) {
        this.name = name;
        this.description = description;
        this.module = module;
        this.bool = bool;
		this.visibility = visibility;
        type = Type.BOOLEAN;
        ValueManager.addToList(this);
    }
    
    public Value(String name, Module module, String text, String description) {
        this.name = name;
        this.description = description;
        this.module = module;
        this.text = text;
        type = Type.TEXT;
        ValueManager.addToList(this);
    }
    public Value(String name, Module module,String mode, String[] modes, String description) {
        this.name = name;
        this.description = description;
        this.mode = mode;
        this.module = module;
        this.modes = modes;
        type = Type.MODE;
        ValueManager.addToList(this);
    }

    public Value(String name, Module module, double value, double  min, double max, double increment, String description) {
        this.name = name;
        this.module = module;
        this.number = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.description = description;
        type = Type.NUMBER;
        ValueManager.addToList(this);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public IOption getVisibility() {
        return visibility;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    
    public void setType(Type type) {
        this.type = type;
    }

    public double getNumber() {
        return number;
    }


    
    public int getInt() {
        return (int)number;
    }

    public float getFloat()
    {
        return (float) number;
    }
    public void setNumber(double value) {
        this.number = value;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String[] getModes() {
        return modes;
    }

    public void setModes(String[] modes) {
        this.modes = modes;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getIncrement() {
        return increment;
    }

    
    public String getText() {
        return text;
    }

    public void setText(String test) {
        this.text = test;
    }

}
