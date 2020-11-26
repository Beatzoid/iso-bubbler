package ui;

import core.Size;
import state.State;
import ui.clickable.UIClickable;

import java.awt.*;

public class UITabContainer extends VerticalContainer {

    private UIContainer tabContainer;
    private UIContainer contentContainer;
    private UITab activeTab;

    /**
     * The UITabContainer manages UI tabs in the map editor
     *
     * @param windowSize The window size
     */
    public UITabContainer(Size windowSize) {
        super(windowSize);
        tabContainer = new HorizontalContainer(windowSize);
        contentContainer = new VerticalContainer(windowSize);

        setMargin(new Spacing(0));

        tabContainer.setPadding(new Spacing(0));
        tabContainer.setMargin(new Spacing(0));

        contentContainer.setMargin(new Spacing(0));
        contentContainer.setBackgroundColor(Color.GRAY);

        addUIComponent(tabContainer);
        addUIComponent(contentContainer);
    }

    /**
     * Add a tab
     *
     * @param label The label for the tab
     * @param contents The contents of the tab
     */
    public void addTab(String label, UIContainer contents) {
        UITab tab = new UITab(this, label, contents);
        tabContainer.addUIComponent(tab);

        if (activeTab == null) {
            activateTab(tab);
        }
    }

    private void activateTab(UITab uiTab) {
        activeTab = uiTab;
        contentContainer.clear();
        contentContainer.addUIComponent(uiTab.getContents());
    }

    /**
     * Get the active tab
     */
    public UITab getActiveTab() {
        return activeTab;
    }

    static class UITab extends UIClickable {

        private UITabContainer parent;
        private UIContainer label;
        private UIContainer contents;

        public UITab(UITabContainer parent, String label, UIContainer contents) {
            this.parent = parent;
            this.contents = contents;

            contents.setMargin(new Spacing(0));
            contents.setPadding(new Spacing(0));

            this.label = new VerticalContainer(new Size(0, 0));
            this.label.addUIComponent(new UIText(label));
            this.label.setBackgroundColor(Color.DARK_GRAY);
        }

        @Override
        public void update(State state) {
            super.update(state);
            label.update(state);
            size = label.getSize();

            label.setBackgroundColor(Color.DARK_GRAY);

            if (hasFocus) {
                label.setBackgroundColor(Color.LIGHT_GRAY);
            }

            if (parent.getActiveTab().equals(this)) {
                label.setBackgroundColor(Color.GRAY);
            }
        }

        @Override
        protected void onFocus(State state) {}
        @Override
        public void onDrag(State state) {}

        @Override
        public void onClick(State state) {
            parent.activateTab(this);
        }

        @Override
        public Image getSprite() {
            return label.getSprite();
        }

        /**
         * Get the contents
         */
        public UIContainer getContents() {
            return contents;
        }
    }
}
