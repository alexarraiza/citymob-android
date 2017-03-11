package com.bitbucket.hackersforgood.citymob.model.df;

/**
 * Created by aarraiza on 04/08/2015.w
 */
public class RecordsResponseTyped<T> {

	private T[] resource;
	private Metadata meta;

	public RecordsResponseTyped() {
	}

	public RecordsResponseTyped(T[] resource, Metadata meta) {
		this.resource = resource;
		this.meta = meta;
	}

	public T[] getResource() {
		return resource;
	}

	public void setResource(T[] resource) {
		this.resource = resource;
	}

	public Metadata getMeta() {
		return meta;
	}

	public void setMeta(Metadata meta) {
		this.meta = meta;
	}
}
