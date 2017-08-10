/*****************************************************************************************************
 ** Program Name            - iWMSPhaseListener.java
 ** Program Description     - This class implements PagePhaseListner. 
 **                           Classes can extends this class to implement any custom logic to be execute before page load. 
 ** Date written            - 
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.handlers;

import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.controller.faces.context.FacesPageLifecycleContext;
import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

import oracle.binding.BindingContainer;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class iFWMSPhaseListener implements PagePhaseListener {
    private BindingContainer bc = null;

    public void beforePhase(PagePhaseEvent event) {
        FacesPageLifecycleContext ctx =
            (FacesPageLifecycleContext)event.getLifecycleContext();
        if (event.getPhaseId() == Lifecycle.PREPARE_MODEL_ID) {
            bc = ctx.getBindingContainer();
            onPageLoad();
            bc = null;
        }
        if (event.getPhaseId() == Lifecycle.PREPARE_RENDER_ID) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            //check internal request parameter
            Map requestMap = fctx.getExternalContext().getRequestMap();
            Object showPrintableBehavior =
                requestMap.get("oracle.adfinternal.view.faces.el.PrintablePage");
            if (showPrintableBehavior != null) {
                if (Boolean.TRUE == showPrintableBehavior) {
                    ExtendedRenderKitService erks = null;
                    erks =
                        Service.getRenderKitService(fctx,
                                                    ExtendedRenderKitService.class);
                    //invoke JavaScript from the server
                    erks.addScript(fctx, "window.print();window.close();");
                }
            }
        }
    }

    public void afterPhase(PagePhaseEvent event) {
    }

    // SubClasses can override that method
    public void onPageLoad() {
        //On Load logic
    }
}
