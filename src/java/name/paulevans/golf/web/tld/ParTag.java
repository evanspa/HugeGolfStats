package name.paulevans.golf.web.tld;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag definition of the par tag
 * @author Paul
 *
 */
public class ParTag extends TagSupport {
	
	private String name;

	/**
	 * Setter
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Output the <select><option>...
	 */
	public int doStartTag() {
		JspWriter out;
		try {
			out = pageContext.getOut();
			out.println("<select name=\"" + name + "\">");
			out.println("<option value=\"3\">3</option>");
			out.println("<option value=\"4\">4</option>");
			out.println("<option value=\"5\">5</option>");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return SKIP_BODY;
	}

	/**
	 * Output the </select> 
	 */
	public int doEndTag() {
		JspWriter out;
		try {
			out = pageContext.getOut();
			out.println("</select>");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return SKIP_BODY;
	}
	
}
