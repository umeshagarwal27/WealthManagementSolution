/*****************************************************************************************************
 ** Program Name            - HomeBean.java
 ** Program Description     - This class contains the logic for Home Page.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.ui.beans.common;

import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.ui.pattern.dynamicShell.TabContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.apache.myfaces.trinidad.model.TreeModel;


public class HomeBean implements Serializable {
    @SuppressWarnings("compatibility:6865142187096768312")
    private static final long serialVersionUID = 1L;
    private String tabTitle;
    private String tabTaskFlowId;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(HomeBean.class);

    /**
     *  Default Constructor
     */
    public HomeBean() {
        super();
    }

    public void onScreenNameClick(ActionEvent actionEvent) {
        if (tabTitle != null && !"".equals(tabTitle) && tabTaskFlowId != null && !"".equals(tabTaskFlowId))
            _launchActivity(tabTitle, tabTaskFlowId, false);
    }

    private void _launchActivity(String title, String taskflowId, boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    /**
     * Depending on the tree node selection show the corresponding attributes
     * @param selectionEvent
     */
    public void select_bean(SelectionEvent selectionEvent) {
        ADFUtil.invokeMethodExpression("#{bindings.FWMSMainMenu.treeModel.makeCurrent}",
                                       new Class[] { SelectionEvent.class }, new Object[] { selectionEvent });
        RichTree tree = (RichTree) selectionEvent.getSource(); // get the tree component from the event
        TreeModel model = (TreeModel) tree.getValue();
        //get selected nodes
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeySet.iterator();
        //Validating for single select only. Need to check for multiselect
        while (rksIterator.hasNext()) {
            List key = (List) rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel) tree.getValue();
            treeBinding = (JUCtrlHierBinding) collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row row = nodeBinding.getRow();
            row.getAttribute("WmmDesc");
            row.getAttribute("TaskFlowId");
        }
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTaskFlowId(String tabTaskFlowId) {
        this.tabTaskFlowId = tabTaskFlowId;
    }

    public String getTabTaskFlowId() {
        return tabTaskFlowId;
    }
}
