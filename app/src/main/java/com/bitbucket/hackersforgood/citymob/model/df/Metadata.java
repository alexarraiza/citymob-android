package com.bitbucket.hackersforgood.citymob.model.df;

/**
 * Created by aarraiza on 04/08/2015.
 */
public class Metadata {

	private String[] schema;
	private Integer count;
	private Integer next;

	public Metadata() {
	}

	public Metadata(String[] schema, Integer count, Integer next) {
		this.schema = schema;
		this.count = count;
		this.next = next;
	}

	public String[] getSchema() {
		return schema;
	}

	public void setSchema(String[] schema) {
		this.schema = schema;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}
}
