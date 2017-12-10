package com.forsrc.gwt.client.application.plantuml;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextArea;

public class PlantumlView extends ViewImpl implements PlantumlPresenter.MyView {
    interface Binder extends UiBinder<Widget, PlantumlView> {
    }

    @UiField
    MaterialLabel searchLabel;

    @Inject
    PlantumlView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

    @UiField
    MaterialTextArea uml;

    @UiField
    MaterialButton send;

    @UiField
    PlantumlMaterialImage plantuml;

    @UiHandler("send")
    public void send(ClickEvent clickEvent) {
        String uml = this.uml.getText();
        plantuml.setUrl(plantuml.getSrc(), uml);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        this.uml.setValue(
                  "@startuml\n"
                + "   A-> B : b()\n"
                + "   B-> A : a()\n"
                + "@enduml");
    }
}
