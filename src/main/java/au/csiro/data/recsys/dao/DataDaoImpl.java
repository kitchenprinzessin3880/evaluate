package au.csiro.data.recsys.dao;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import au.csiro.data.recsys.model.*;

public class DataDaoImpl implements DataDao {

	static final Logger log = Logger.getLogger(DataDaoImpl.class);

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	@Transactional
	public ResponseEntity<ResponseObject> getRelatedDatasets(String id) {
		Session session = null;
		List<RelatedDataset> rows = null;
		ResponseEntity<? extends Object> response = null;
		ResponseObject respObj = new ResponseObject();;
		try {
			session = HibernateConnector.getInstance().getSession();
			
			Query queryDataset = session.createQuery("from Dataset where fedoraId = :target ");
			queryDataset.setParameter("target", id);
			Dataset dataSetObj = (Dataset) queryDataset.list().get(0);
			String targetId = dataSetObj.getFedoraId();
			String targetTitle = dataSetObj.getTitle();

			rows = (List<RelatedDataset>) session
					.createQuery(
							"select r.relatedId, r.fedoraId,r.title from RelatedDataset r where r.dataset.fedoraId = :id")
					.setParameter("id", id).list();

			if (rows.isEmpty()) {
				return null;
			} else {
				Collections.shuffle(rows);
				respObj.prepareResponse(new Dataset(targetId,targetTitle),rows,HttpStatus.OK);
				return new ResponseEntity<ResponseObject>(respObj,respObj.getHttpStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public ResponseEntity<? extends Object> insertEvaluation(String evaluator,String target, HashMap<Long, String> eval,String comment) {
		Session session = null;
		session = HibernateConnector.getInstance().getSession();
		Transaction tx = null;
		Dataset dataSetObj = null;
		try {

			Query queryDataset = session.createQuery("from Dataset where fedoraId = :target ");
			queryDataset.setParameter("target", target);
			dataSetObj = (Dataset) queryDataset.list().get(0);

			tx = session.beginTransaction();
			
			Iterator<Map.Entry<Long, String>> entries = eval.entrySet().iterator();
			while (entries.hasNext()) {
				Results res = new Results();
			    Map.Entry<Long, String> entry = entries.next();
			    Long relId = entry.getKey();
			    String rankValue = entry.getValue();
			    RelatedDataset relatedDataObj = (RelatedDataset) session.get(RelatedDataset.class, relId);
			    
			    res.setEvaluator(evaluator);
				res.setRankvalue(rankValue);
				res.setEvaluationDate(new Date());
				res.setRelatedDataset(relatedDataObj);
				res.setDataset(dataSetObj);
				res.setComments(comment);
				session.saveOrUpdate(res);
				session.flush();
				session.clear();
				log.debug("Inserting evaluation :" + dataSetObj.getFedoraId() + " " + evaluator + " " + rankValue + " " + comment);
			    
			}
			tx.commit();
			return new ResponseEntity<String>("Evaluation results have been saved!", new HttpHeaders(),
					HttpStatus.CREATED);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			log.debug(e.getMessage());
			return new ResponseEntity<String>("Data insertion exception", new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		finally {
			session.close();
		}
	}
	

	private static String getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return sdf.format(today.getTime());
		
		/*
		
		for (int r = 0; r < rootNode.size(); r++) {
			Results res = new Results();
			JsonNode arrNode = rootNode.get(r);
			String evaluator = arrNode.get(0).asText();
			String rankValue = arrNode.get(1).asText();
			long relatedData = arrNode.get(3).asInt();
			RelatedDataset relatedDataObj = (RelatedDataset) session.get(RelatedDataset.class, relatedData);
			String comment = arrNode.get(4).asText();
			res.setEvaluator(evaluator);
			res.setRankvalue(rankValue);
			res.setEvaluationDate(new Date());
			res.setRelatedDataset(relatedDataObj);
			res.setComments(comment);
			res.setDataset(dataSetObj);
			session.saveOrUpdate(res);
			if (r % 3 == 0) {
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
			log.debug("Inserting evaluation :" + dataSetObj.getFedoraId() + " " + evaluator + " " + rankValue + " "
					+ relatedData);
		}*/

	}

}
