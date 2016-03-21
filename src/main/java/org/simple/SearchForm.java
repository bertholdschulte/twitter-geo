package org.simple;

import org.hibernate.validator.constraints.Length;

public class SearchForm {

	private String searchTerm;
	private String consumerKey;
	private String consumerSecret;

	@Length(min = 1, message = "search.form.validation.missing.searchterm")
	public String getSearchTerm() {
		return this.searchTerm;
	}
	
	@Length(min = 1, message = "search.form.validation.missing.consumerKey")
	public String getConsumerKey() {
		return consumerKey;
	}
	@Length(min = 1, message = "search.form.validation.missing.consumerSecret")
	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

}