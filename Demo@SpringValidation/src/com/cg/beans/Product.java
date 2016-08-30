package com.cg.beans;

import java.util.HashSet;
import java.util.Set;

public class Product {

	private String name;
	private String category;
	private String version;
	private LicenseType licenseType;
	private Set<Platform> supportedPlatforms;

	public Product() {
		supportedPlatforms = new HashSet<Platform>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set<Platform> getSupportedPlatforms() {
		return supportedPlatforms;
	}

	public void setSupportedPlatforms(Set<Platform> supportedPlatforms) {
		this.supportedPlatforms = supportedPlatforms;
	}

	public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}
}