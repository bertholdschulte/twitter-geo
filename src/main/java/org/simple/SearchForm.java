package org.simple;

import org.hibernate.validator.constraints.Length;

public class SearchForm {

	private String searchTerm;

	@Length(min=1,message="search.form.validation.missing.searchterm")
	public String getSearchTerm() {
		return this.searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

}