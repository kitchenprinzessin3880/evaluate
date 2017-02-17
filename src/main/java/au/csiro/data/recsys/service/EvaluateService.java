package au.csiro.data.recsys.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import au.csiro.data.recsys.model.*;



public interface EvaluateService {
	
	ResponseEntity<ResponseObject> getRelatedDatasets(String fedoraId);
	public ResponseEntity<? extends Object> saveEvaluation(String evalutor, String target, HashMap<Long, String> hmap, String comment);
}
