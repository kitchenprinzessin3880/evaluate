package au.csiro.data.recsys.model;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseObject {

	private List<RelatedDataset> relatedDataset;
	private String message;
	private HttpStatus httpStatus;
	private Dataset dataset;

	public void prepareResponse(Dataset dataset, List<RelatedDataset> result, HttpStatus status, String message) {
		this.relatedDataset = result;
		this.httpStatus = status;
		this.message = message;
		this.dataset = dataset;
	}
	
	public void prepareResponse(Dataset dataset, List<RelatedDataset> result, HttpStatus status) {
		this.relatedDataset = result;
		this.httpStatus = status;
		this.dataset = dataset;
	}


	public HttpStatus getHttpStatus() {// gettername is data here
		return httpStatus;
	}
	
	public List<RelatedDataset> getRelatedDataset() {// gettername is data here
		return relatedDataset;
	}

	public void setRelatedDataset(List<RelatedDataset> relateddataset) {// settername is data here
		this.relatedDataset = relateddataset;
	}
	
	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}


	@Override
	public String toString() {
		return "ResponseObject [result=" + relatedDataset + ",  httpStatus=" + httpStatus + ", message=" + message + "]";
	}
}