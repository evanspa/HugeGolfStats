<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- needed to put attributes added by the tiles-defs.xml into scope -->
<tiles:importAttribute scope="request" /> 

<html:html xhtml="true" lang="en">
	
	<!-- include head.jsp -->
	<tiles:get name="head" />
	
	<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
	<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->
	
	<!-- <body> tag START -->
	<body>
	
		<!-- Main Page Container -->
  		<div class="page-container">
  		
  			<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
			<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

			<!-- A. HEADER -->      
			<div class="header">
				<!-- include header.jsp -->
				<tiles:get name="header" />
			</div>
			
		    <!-- B. MAIN -->
		    <div class="main">
			
				<!-- B.1 MAIN CONTENT -->
      			<div class="main-content">
      			
      				<!-- Only output errors-tile if errors are presented -->
					<logic:messagesPresent>
						<tiles:get name="errors" />
					</logic:messagesPresent>
					
					<!-- ouput the main body content -->
					<br />
      				<tiles:get name="body-content" />
      			</div>              
    		</div>	
			
			<!-- include footer.jsp -->
			<tiles:get name="footer" />
		</div>
	</body>
</html:html>