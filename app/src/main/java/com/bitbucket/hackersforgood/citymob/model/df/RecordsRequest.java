package com.bitbucket.hackersforgood.citymob.model.df;

import java.util.List;

/**
 * Created by alexarraiza on 21/4/16.
 */
public class RecordsRequest<T> {

	private List<T> resource;

	public RecordsRequest() {
	}

	public RecordsRequest(List<T> resource) {
		this.resource = resource;
	}

	public List<T> getResource() {
		return resource;
	}

	public void setResource(List<T> resource) {
		this.resource = resource;
	}
}
