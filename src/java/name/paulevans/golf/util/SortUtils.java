package name.paulevans.golf.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Utility class for sorting
 * @author pevans
 *
 */
public class SortUtils implements Comparator {
	
	// session attribute name for the sort direction map...
	private static final String SORT_DIRECTION_MAP = "SortDirectionMap";
	
	private String sortField;
	private boolean sortDirection;
	
	/**
	 * Private constructor
	 * @param aSortField
	 * @param aSortDirection
	 */
	private SortUtils(String aSortField, boolean aSortDirection) {
		sortField = aSortField;
		sortDirection = aSortDirection;
	}	

	/**
	 * Sorts the objects - uses the Jakarta-Commons BeanUtils library to easily
	 * extract the column values from the objects.  The value
	 * of aSortColumn is a nested property-reference used by PropertyUtils to 
	 * get the corresponding value.
	 * @param aObjects The objects to sort
	 * @param aSortField
	 * @param aRequest
	 */
	@SuppressWarnings( { "unchecked" })
	public static void sort(Object aObjects[], 
			final String aSortField, HttpServletRequest aRequest) {
		
		final Boolean sortDirection;
		
		sortDirection = getSortDirection(aRequest, aSortField);
		Arrays.sort(aObjects, new SortUtils(aSortField, sortDirection));
	}
	
	/**
	 * Returns the sort direction
	 * @param aRequest
	 * @param aColumn
	 * @return
	 */
	private static Boolean getSortDirection(HttpServletRequest aRequest, 
			String aColumn) {
		
		Boolean sortDirection;
		Map<String,Boolean> sortDirectionMap;
		
		sortDirectionMap = getSortDirectionMap(aRequest);
		sortDirection = sortDirectionMap.get(aColumn);
		if (sortDirection == null) {
			sortDirection = Boolean.TRUE;
		}
		sortDirectionMap.put(aColumn, !sortDirection);
		return sortDirection;
	}
	
	/**
	 * Returns the sort-direction map from the session
	 * @param aRequest
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	private static Map<String,Boolean> getSortDirectionMap(
			HttpServletRequest aRequest) {
		
		Map<String,Boolean> sortDirectionMap;
		HttpSession session;
		
		session = aRequest.getSession();
		sortDirectionMap = (Map<String,Boolean>)session.getAttribute(
				SORT_DIRECTION_MAP);
		if (sortDirectionMap == null) {
			sortDirectionMap = new HashMap<String,Boolean>();
			session.setAttribute(SORT_DIRECTION_MAP, sortDirectionMap);
		}
		return sortDirectionMap;
	}

	/**
	 * Needed to implement comparator; handles nulls safely.  Leverages the 
	 * Jakarta-Commons BeanUtils library to easily extract the column values 
	 * from the objects
	 */
	@SuppressWarnings( { "unchecked" })
	public int compare(Object aObj1, Object aObj2) {
		Comparable o1Column, o2Column;
		try {
			o1Column = (Comparable)PropertyUtils.getProperty(aObj1, sortField);
			o2Column = (Comparable)PropertyUtils.getProperty(aObj2, sortField);
			if (o1Column == null) {
				if (o2Column == null) {
					return 0;
				} else {
					return sortDirection ? 1 : -1;
				}
			} else {
				if (o2Column == null) {
					return sortDirection ? -1 : 1;
				} else {
					return sortDirection ? o1Column.compareTo(o2Column) :
						o2Column.compareTo(o1Column);
				}
			}
		} catch (Exception any) {
			throw new RuntimeException(any);
		}
	}
	
	/**
	 * Needed to implement Comparator
	 */
	public boolean equals(Object aObject) {
		return super.equals(aObject);
	}
}
