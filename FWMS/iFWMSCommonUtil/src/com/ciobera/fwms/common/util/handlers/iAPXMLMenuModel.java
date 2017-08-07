package com.ciobera.fwms.common.util.handlers;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.faces.context.FacesContext;

import org.apache.myfaces.trinidad.menu.MenuNode;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.XMLMenuModel;


public class iAPXMLMenuModel extends XMLMenuModel implements Serializable {

    @Override
    /**
   * Return focus row key based on mapped viewId when
   * getFocusRowKeyMapping is found, otherwise call super to return standard behavior.
   */
    public Object getFocusRowKey() {
        // first check whether default behavior results in focus of some menu item
        Object focusRowKey = super.getFocusRowKey();
        if (focusRowKey != null) {
            return focusRowKey;
        }
        Map<String, List<Object>> focusPathMap = getViewIdFocusPathMap();
        // check whether there is a menu mapping for the current viewId
        Map viewIdMapping = getViewIdMapping();
        String viewId =
            FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewIdMapping != null && viewIdMapping.get(viewId) != null) {
            String mappedViewId = (String)viewIdMapping.get(viewId);
            List<Object> focusPath = focusPathMap.get(mappedViewId);
            return focusPath != null ? focusPath.get(0) : null;
        }
        return null;
    }

    /**
     * Return the view id mapping, should be defined as managed bean in current
     * pageFlowScope using name "MenuViewIdMapping".
     * @return
     */
    public Map getViewIdMapping() {
        ExpressionFactory ef =
            FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        return (Map)ef.createValueExpression(context,
                                             "#{pageFlowScope.MenuViewIdMapping}",
                                             Object.class).getValue(context);
    }

    private List<MenuNode> menuList;

    public void setMenuList(List<MenuNode> menuList) {
        this.menuList = menuList;
    }

    public List<MenuNode> getMenuList() {
        ChildPropertyTreeModel ch =
            (ChildPropertyTreeModel)this.getWrappedData();

        if (ch != null) {
            menuList = (List<MenuNode>)(ch.getWrappedData());
        }
        return menuList;
    }
}

