<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="gen.jaxb.name.paulevans.golf.Menubar" %>
<%@ page import="gen.jaxb.name.paulevans.golf.Menu" %>
<%@ page import="gen.jaxb.name.paulevans.golf.Menuitem" %>
<%@ page import="name.paulevans.golf.BeanFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<%
	NewUtil util;
	List menuItemList;
	Menubar menubar;
	Menu currentMenu;
	Menuitem currentMenuitem;
	String requiredRole;
	boolean renderMenu;

	util = (NewUtil) session.getAttribute(AttributeKeyConstants.UTIL);
	if (util == null) {
		util = BeanFactory.getUtilObject();
		util.initialize();
	}
	menuItemList = util.getMenuItems(menubar = util.getMenubar(request));
	pageContext.setAttribute("menuItems", menuItemList);
	pageContext.setAttribute("numMenuItems", menuItemList.size());
	pageContext.setAttribute("menubar", menubar);

%>


<!-- Output the primary (and only) menubar -->
<ul id="primary">

	<!-- loop over and output the menus -->
	<logic:iterate id="menu" name="menubar" property="menu">

		<%	currentMenu = (Menu)pageContext.getAttribute("menu");
			renderMenu = true;
			requiredRole = currentMenu.getRole();
			if (StringUtils.isNotBlank(requiredRole)) {
				if (!request.isUserInRole(requiredRole)) {
					renderMenu = false;
				}
			}
			if (renderMenu) { %>
				<li>
					<% if (currentMenu.isSelected() != null && currentMenu.isSelected().booleanValue()) { %>

						<!-- Output the menu -->
						<span><bean:message key="${menu.messageKey}"/></span>

						<%	menuItemList = currentMenu.getMenuitem();
							pageContext.setAttribute("menuItems", menuItemList);
							pageContext.setAttribute("numMenuItems", menuItemList.size()); %>

							<!-- loop over and ouptut the menuitems -->
							<ul id="secondary">
								<logic:iterate indexId="loop" id="menuItem" name="menuItems">
									<% currentMenuitem = (Menuitem) pageContext.getAttribute("menuItem"); %>
									<li>
										<a href="<%= BooleanUtils.isTrue(currentMenuitem.isSecure()) ? WebUtils.securePrefix(request) : WebConstants.CONTEXT_ROOT %>/${menuItem.action}"><bean:message
											key="${menuItem.messageKey}"/></a></li>
								</logic:iterate>
							</ul>
					<% } else {  %>
						<a href="<%= BooleanUtils.isTrue(currentMenu.isSecure()) ? WebUtils.securePrefix(request) : WebConstants.CONTEXT_ROOT %>/${menu.action}"><bean:message
							key="${menu.messageKey}"/></a>
					<% } %>
				</li>
		<%	} %>
	</logic:iterate>
</ul>