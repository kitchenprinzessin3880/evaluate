package au.csiro.data.recsys.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import au.csiro.data.recsys.model.RelatedDataset;
import au.csiro.data.recsys.model.ResponseObject;
import au.csiro.data.recsys.model.Results;


public interface DataDao {
	
	public ResponseEntity<ResponseObject> getRelatedDatasets(String id) throws Exception;
	/*public ResponseEntity<? extends Object> registerSubName(String user, String pwd, String prefix)throws Exception;
	public boolean addEntity(Registrant registrant) throws Exception;
	public Registrant getEntityById(int id) throws Exception;
	public List<Registrant> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
	public List<String> getPrefixesByUsername(String usr) throws Exception;
	public ResponseEntity<? extends Object> insertOrUpdateSamples(List<Sample> sampleList, String usr, String eventType) throws Exception;
	public List<Prefix> getSubnamespaces() throws Exception;*/

	public ResponseEntity<? extends Object> insertEvaluation(String evaluator,String target, HashMap<Long, String> eval,String comment);
}
