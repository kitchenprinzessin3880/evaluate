package au.csiro.data.recsys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import au.csiro.data.recsys.dao.DataDao;
import au.csiro.data.recsys.model.RelatedDataset;
import au.csiro.data.recsys.model.ResponseObject;
import au.csiro.data.recsys.model.Results;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService{
	
	@Autowired
	DataDao dataDao;

	public ResponseEntity<ResponseObject> getRelatedDatasets(String fedoraId) {
		ResponseEntity<ResponseObject> results = null;
		try {
			results = dataDao.getRelatedDatasets(fedoraId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public ResponseEntity<? extends Object> saveEvaluation(String evaluator, String target, HashMap<Long, String> eval,String comment) {
		ResponseEntity<? extends Object> code = null;
		try {
			code = dataDao.insertEvaluation(evaluator, target,eval, comment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	

}
