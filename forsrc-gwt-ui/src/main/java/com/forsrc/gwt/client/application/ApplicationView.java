package com.forsrc.gwt.client.application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventUiHandlers;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.base.SearchObject;
import gwt.material.design.client.events.SearchFinishEvent;
import gwt.material.design.client.events.SideNavClosedEvent;
import gwt.material.design.client.events.SideNavOpenedEvent;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialSearch;
import gwt.material.design.client.ui.MaterialSideNavCard;
import gwt.material.design.client.ui.MaterialToast;


public class ApplicationView extends ViewWithUiHandlers<MyEventUiHandlers>
        implements ApplicationPresenter.MyView {

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    public static final String TYPE_SEARECH = "ApplicationView/Search";
    public static final String TYPE_SIDEBAR = "ApplicationView/SideNav";

    @UiField
    SimplePanel mainContentPanel;

    @UiField
    MaterialNavBar navBar;

    @UiField
    MaterialSideNavCard sideNav;

    @UiField
    MaterialNavBar navBarSearch;

    @UiField
    MaterialSearch txtSearch;

    @Inject
    public ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MAIN, mainContentPanel);
    }
 
    @Override
    protected void onAttach() {
        super.onAttach();
        init();
    }
 
    private void init() {
        navBarSearch.setVisible(false);

//        txtSearch.addOpenHandler(openEvent -> {
//            navBar.setVisible(false);
//            sideNav.hide();
//            navBarSearch.setVisible(true);
//            MaterialToast.fireToast("Open Event was fired");
//        });
//
//        // Add Close Handler
//        txtSearch.addCloseHandler(event -> {
//            navBar.setVisible(true);
//            navBarSearch.setVisible(false);
//            MaterialToast.fireToast("Close Event was fired");
//        });

        List<SearchObject> objects = new ArrayList<>();
        SearchObject login = new SearchObject();
        login.setKeyword("login");
        login.setLink("#login");
        objects.add(login);

        txtSearch.setListSearches(objects);

//        txtSearch.addSearchFinishHandler(event -> {
//            // Get the selected search object
//            SearchObject so = txtSearch.getSelectedObject();
//            MaterialToast.fireToast("Search Finish Event was fired -> " + so.getKeyword());
//        });
    }

    @Override
    public void showLoading(boolean visibile) {
        MaterialLoader.progress(visibile);
    }

    @Override
    public void setPageTitle(String title, String description, String link, String specification) {
        Window.alert("setPageTitle() -> " + title);
    }

    @UiHandler("txtSearch")
    public void onSearchOpen(OpenEvent<String> e) {
        navBar.setVisible(false);
        sideNav.hide();
        navBarSearch.setVisible(true);
        MaterialToast.fireToast("onSearchOpen()");
    }

    @UiHandler("txtSearch")
    public void onSearchClose(CloseEvent<String> e) {
        navBar.setVisible(true);
        navBarSearch.setVisible(false);
        MaterialToast.fireToast("onSearchClose()");
    }

    @UiHandler("txtSearch")
    public void onSearchFinish(SearchFinishEvent e) {
        SearchObject so = txtSearch.getSelectedObject();
        
        getUiHandlers().onMyEventUiHandlers(new MyEventData(TYPE_SEARECH, so.getKeyword()));
        MaterialToast.fireToast("onSearchFinish() -> getKeyword: " + so.getKeyword());
    }

    @UiHandler("txtSearch")
    public void onSearchChange(ChangeEvent e) {
        getUiHandlers().onMyEventUiHandlers(new MyEventData(TYPE_SEARECH, txtSearch.getValue()));
        MaterialToast.fireToast("onSearchFinish() -> getKeyword: " + txtSearch.getValue());
    }


    @UiHandler("btnSearch")
    public void onSearch(ClickEvent e){
        //Window.alert("onSearch");
        txtSearch.open();
    }

    @UiHandler("sideNav")
    public void onSideNavClosed(SideNavClosedEvent e){
        //MaterialToast.fireToast("onSideNavClosed");
        getUiHandlers().onMyEventUiHandlers(new MyEventData(TYPE_SIDEBAR, sideNav.getWidth()));
    }

    @UiHandler("sideNav")
    public void onSideNavOpened(SideNavOpenedEvent e){
        //MaterialToast.fireToast("onSideNavClosed");
        getUiHandlers().onMyEventUiHandlers(new MyEventData(TYPE_SIDEBAR, 0));
    }
}
