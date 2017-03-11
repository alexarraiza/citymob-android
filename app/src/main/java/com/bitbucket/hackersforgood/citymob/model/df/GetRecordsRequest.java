package com.bitbucket.hackersforgood.citymob.model.df;

public class GetRecordsRequest {

	private RecordRequest[] record;
	private Integer[] ids;
	private String filter;
	private String[] params;

	public GetRecordsRequest() {
	}

	public GetRecordsRequest(RecordRequest[] record, Integer[] ids, String filter, String[] params) {
		this.record = record;
		this.ids = ids;
		this.filter = filter;
		this.params = params;
	}

	public GetRecordsRequest(String filter) {
		this.filter = filter;
	}

	public GetRecordsRequest(String filter, String[] params) {
		this.filter = filter;
		this.params = params;
	}

	public RecordRequest[] getRecord() {
		return record;
	}

	public void setRecord(RecordRequest[] record) {
		this.record = record;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
}
