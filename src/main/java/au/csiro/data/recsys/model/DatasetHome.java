package au.csiro.data.recsys.model;

// Generated 09/02/2017 5:17:21 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Dataset.
 * @see au.csiro.data.recsys.model.Dataset
 * @author Hibernate Tools
 */
@Stateless
public class DatasetHome {

	private static final Log log = LogFactory.getLog(DatasetHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Dataset transientInstance) {
		log.debug("persisting Dataset instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Dataset persistentInstance) {
		log.debug("removing Dataset instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Dataset merge(Dataset detachedInstance) {
		log.debug("merging Dataset instance");
		try {
			Dataset result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dataset findById(long id) {
		log.debug("getting Dataset instance with id: " + id);
		try {
			Dataset instance = entityManager.find(Dataset.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
