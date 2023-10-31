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
