package HiroCode.gui.buttons;

import HiroCode.Module.Category;
import HiroCode.Module.Module;
import HiroCode.Module.settings.Value;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {
    private static List<CategoryButton> categoryButtonList = new ArrayList<>();
    private static List<ModuleButton> moduleButtons = new ArrayList<>();
    private static List<ValueButton> valueButtons = new ArrayList<>();

    public static List<CategoryButton> getCategoryList() {
        return categoryButtonList;
    }

    public static void addToCategoryList(Category category, int x, int y) {
        categoryButtonList.add(new CategoryButton(category, x, y));
    }

    public static CategoryButton getCategoryButton(String name) {
        CategoryButton yoo = null;
        for (CategoryButton cat : getCategoryList()) {
            if (cat.getCategory().name().equals(name)) {
                yoo = cat;
            }
        }
        return yoo;
    }
    public static ValueButton getValue(String name,Module module) {
        ValueButton value = null;
        for (ValueButton valButton : getValueButtons()) {
            if (valButton.getValue().getName().equals(name) && valButton.getValue().getModule() == module) {
                value = valButton;
            }
        }
        return value;
    }
    public static void addToModuleList(Module module) {
        moduleButtons.add(new ModuleButton(module));
    }
    public static void addToValueList(Value value) {
        valueButtons.add(new ValueButton(value));
    }
    public static List<ModuleButton> getModuleButtons() {
        return moduleButtons;
    }

    public static List<ValueButton> getValueButtons() {
        return valueButtons;
    }
}
