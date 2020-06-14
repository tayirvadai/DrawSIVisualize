package com.truist.integration.io.model;

public class ContentDescriptor {

	private String providerVersion;
	
	private Double providerFormatVersion;
	
	private String provider;

	public String getProviderVersion() {
		return providerVersion;
	}

	public void setProviderVersion(String providerVersion) {
		this.providerVersion = providerVersion;
	}

	public Double getProviderFormatVersion() {
		return providerFormatVersion;
	}

	public void setProviderFormatVersion(Double providerFormatVersion) {
		this.providerFormatVersion = providerFormatVersion;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
	
}
