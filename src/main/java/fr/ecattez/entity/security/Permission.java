package fr.ecattez.entity.security;

import com.j256.ormlite.field.DatabaseField;

public class Permission {
	
	@DatabaseField(columnName = "pno", id = true)
	private int id;
	@DatabaseField(columnName = "module_name")
	private String moduleName;
	@DatabaseField(columnName = "resource_name")
	private String resourceName;
	@DatabaseField(columnName = "recursive_path")
	private boolean recursivePath;
	@DatabaseField(columnName = "http_method")
	private String httpMethod;
	
	public Permission() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the recursivePath
	 */
	public boolean isRecursivePath() {
		return recursivePath;
	}

	/**
	 * @param recursivePath the recursivePath to set
	 */
	public void setRecursivePath(boolean recursivePath) {
		this.recursivePath = recursivePath;
	}

	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

}
