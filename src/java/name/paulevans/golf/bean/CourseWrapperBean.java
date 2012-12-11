package name.paulevans.golf.bean;

import java.util.Set;

import name.paulevans.golf.dao.CourseDAO;

/**
 * Wraps a Course object and provides additional functionality.
 * @author Paul
 *
 */
public class CourseWrapperBean {
	
	// wrapped course object...
	private CourseDAO course;
	
	private Set<TeeWrapperBean> tees;
	
	/**
	 * public constructor
	 * @param aCourse
	 */
	public CourseWrapperBean(CourseDAO aCourse) {
		course = aCourse;
	}

	/**
	 * Getter
	 * @return
	 */
	public CourseDAO getCourse() {
		return course;
	}

	/**
	 * Setter
	 * @param aCourse
	 */
	public void setCourse(CourseDAO aCourse) {
		this.course = aCourse;
	}

	/**
	 * Getter
	 * @return
	 */
	public Set<TeeWrapperBean> getTees() {
		return tees;
	}

	/**
	 * Setter
	 * @param tees
	 */
	public void setTees(Set<TeeWrapperBean> tees) {
		this.tees = tees;
	}

}
