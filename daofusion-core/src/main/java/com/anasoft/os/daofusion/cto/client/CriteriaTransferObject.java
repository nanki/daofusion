package com.anasoft.os.daofusion.cto.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.anasoft.os.daofusion.criteria.PersistentEntityCriteria;
import com.anasoft.os.daofusion.cto.server.CriteriaTransferObjectConverter;

/**
 * Generic persistent entity criteria transfer object
 * used by the {@link CriteriaTransferObjectConverter}.
 * 
 * <p>
 * 
 * Criteria transfer object pattern allows client-side
 * components to construct <em>serializable</em> versions
 * of {@link PersistentEntityCriteria} instances, which can
 * then be passed through the chosen communication mechanism
 * to the remote (server-side) component. This way, the user
 * can create persistent entity criteria on the client side
 * and use {@link CriteriaTransferObjectConverter} to transform
 * them into corresponding {@link PersistentEntityCriteria}
 * instances seamlessly on the server.
 * 
 * <p>
 * 
 * This class essentially acts as map-based container for
 * {@link FilterAndSortCriteria} instances, defining basic
 * client-side persistent entity criteria for target entity
 * properties.
 * 
 * @see FilterAndSortCriteria
 * @see CriteriaTransferObjectConverter
 * @see PersistentEntityCriteria
 * 
 * @author vojtech.szocs
 */
public class CriteriaTransferObject implements Serializable {

    private static final long serialVersionUID = 8442027307110021159L;
    
    private Integer firstResult;
    private Integer maxResults;
	
    private final Map<String, FilterAndSortCriteria> criteriaMap = new HashMap<String, FilterAndSortCriteria>();
    
	/**
	 * @return Index of the starting element or <tt>null</tt>
	 * representing no constraints on this paging parameter.
	 */
	public Integer getFirstResult() {
		return firstResult;
	}
	
	/**
	 * @param firstResult Index of the starting element or
	 * <tt>null</tt> representing no constraints on this
	 * paging parameter.
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	
	/**
	 * @return Maximum number of elements to return or
	 * <tt>null</tt> representing no constraints on this
	 * paging parameter.
	 */
	public Integer getMaxResults() {
		return maxResults;
	}
	
	/**
	 * @param maxResults Maximum number of elements to return
	 * or <tt>null</tt> representing no constraints on this
	 * paging parameter.
	 */
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	/**
	 * Adds the given {@link FilterAndSortCriteria} instance
	 * to this transfer object.
	 * 
	 * <p>
	 * 
	 * Note that the <tt>propertyId</tt> of the given
	 * {@link FilterAndSortCriteria} instance must be unique
	 * within the transfer object.
	 * 
	 * @param criteria {@link FilterAndSortCriteria} instance
	 * to add.
	 */
	public void add(FilterAndSortCriteria criteria) {
	    criteriaMap.put(criteria.getPropertyId(), criteria);
	}
	
	/**
	 * Returns a {@link FilterAndSortCriteria} instance
	 * with the given <tt>propertyId</tt>.
	 * 
	 * <p>
	 * 
	 * When not found, the method creates and adds
	 * the {@link FilterAndSortCriteria} instance
	 * to the transfer object automatically.
	 * 
	 * @param propertyId Symbolic persistent entity property
     * identifier.
	 * @return {@link FilterAndSortCriteria} instance with
	 * the given <tt>propertyId</tt>.
	 */
	public FilterAndSortCriteria get(String propertyId) {
	    if (!criteriaMap.containsKey(propertyId)) {
	        add(new FilterAndSortCriteria(propertyId));
	    }
	    
	    return criteriaMap.get(propertyId);
	}
	
	/**
	 * Returns a set of symbolic persistent entity property
     * identifiers (<tt>propertyId</tt> values) for
     * {@link FilterAndSortCriteria} instances contained
     * within this transfer object.
	 * 
	 * @return Set of symbolic persistent entity property
     * identifiers (<tt>propertyId</tt> values).
	 */
	public Set<String> getPropertyIdSet() {
	    return criteriaMap.keySet();
	}
	
}