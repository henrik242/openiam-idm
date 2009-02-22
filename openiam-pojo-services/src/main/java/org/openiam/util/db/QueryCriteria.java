package org.openiam.util.db;


/**
 * Defines query criteria that can be used to control search operations.
 * @author Suneet Shah
 *
 */
public class QueryCriteria {

	String propertyName;
	Object value;
	String operationName;
	Object[] valueAry;
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Applies an "equal" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void eq(String propertyName, Object value) {
		operationName = OperationConstant.Equals;
		this.propertyName = propertyName;
		this.value = value;
	}
	/**
	 * Applies a "not equal" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void ne(String propertyName, Object value) {
		operationName = OperationConstant.NotEquals;
		this.propertyName = propertyName;
		this.value = value;

	}
	/**
	 * Applies a "like" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */	
	public void like(String propertyName, Object value) {
		operationName = OperationConstant.Like;
		this.propertyName = propertyName;
		this.value = value;
	}

	/**
	 * Applies a "greater than" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void gt(String propertyName, Object value) {
		operationName = OperationConstant.GreaterThan;
		this.propertyName = propertyName;
		this.value = value;
	}

	
	/**
	 * Applies a "less than" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void lt(String propertyName, Object value) {
		operationName = OperationConstant.LessThan;
		this.propertyName = propertyName;
		this.value = value;
	}

	/**
	 * Applies a "less than equal" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void le(String propertyName, Object value) {
		operationName = OperationConstant.LessThanEquals;
		this.propertyName = propertyName;
		this.value = value;
	}

	/**
	 * Applies a "greater than equal" operation for specified property.
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public void ge(String propertyName, Object value) {
		operationName = OperationConstant.GreaterThanEquals;
		this.propertyName = propertyName;
		this.value = value;
	}
	
	/**
	 * Applies a "in" operation for specified property.  The valid choices for the operation are 
	 * determined by the contents of the value array.
	 * @param propertyName
	 * @param values
	 */
	public void  in(String propertyName, Object[] values) {
		operationName = OperationConstant.In;
		this.propertyName = propertyName;
		this.valueAry = values;
	}

	public void isNull(String propertyName) {
		operationName = OperationConstant.IsNull;
		this.propertyName = propertyName;
	}
	
	public void isNotNull(String propertyName) {
		operationName = OperationConstant.IsNotNull;
		this.propertyName = propertyName;

	}
	public Object[] getValueAry() {
		return valueAry;
	}
	public void setValueAry(Object[] valueAry) {
		this.valueAry = valueAry;
	}
	
}
