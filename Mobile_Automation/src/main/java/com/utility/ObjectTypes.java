package com.utility;

public enum ObjectTypes {
	LABEL("Label"),
	TITLE("Title"),
    BUTTON("Button"),
    POPUP_BUTTON("Popup Button"),
    TABULAR("Tabular"),
    LINK("Link"),
    TOGGLE("Toggle"),
    TEXT("Text"),
    STATUS("Status"),
    TAB("Tab"),
    ICON("Icon"),
    IMAGE("Image"),
    PROGRESS_BAR("Progress Bar"),
    LOGO("Logo"),
    MENU_OPTION("Menu Option"),
    DROPDOWN("Dropdown"),
    DATE_PICKER("Date Picker"),
    DESCRIPTION("Description"),
    PUSHUP("Pushup"),
    PUSH_NOTIFICATION("Push Notification"),
    TILE("Tile"),
    TEXT_FIELD_VALUE("Text Field Value"),
    TEXT_BOX("Text Box"),
    CHECKBOX("Checkbox"),
    USERNAME("Username"),
    RADIO_BUTTON("Radio Button"),
    NAVIGATION_LINK("Navigation Link"),
    SEARCH_BAR("Search Bar"),
    SUB_TITLE("Sub Title"),
    POPUP_TEXT("Popup Text"),
    POPUP_TEXT_FIELD("Popup Text Field"),
    POPUP_TITLE("Popup Title"),
    PAGE_TITLE("Page Title"),
    CHAT_GROUP_TITLE("Chat Group Title"),
    POPUP_MENU_OPTION("Popup Menu Option"),
    CARD_TITLE("Card Title"),
	DASHBOARD_CARD("Dashboard Card"),
	VALIDATION_ERROR_MESSAGE("Validation Error Message");
	
	private final String value;
	
	ObjectTypes(String value) {
        this.value = value;
    }
	
	public String getValue() {
        return value;
    }
	
}
