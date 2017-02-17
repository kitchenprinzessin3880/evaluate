package au.csiro.data.recsys.model;

// Generated 09/02/2017 5:17:21 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RelatedDataset.
 * @see au.csiro.data.recsys.model.RelatedDataset
 * @author Hibernate Tools
 */
@Stateless
public class RelatedDatasetHome {

	private static final Log log = LogFactory.getLog(RelatedDatasetHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RelatedDataset transientInstance) {
		log.debug("persisting RelatedDataset instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RelatedDataset persistentInstance) {
		log.debug("removing RelatedDataset instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RelatedDataset merge(RelatedDataset detachedInstance) {
		log.debug("merging RelatedDataset instance");
		try {
			RelatedDataset result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RelatedDataset findById(long id) {
		log.debug("getting RelatedDataset instance with id: " + id);
		try {
			RelatedDataset instance = entityManager.find(RelatedDataset.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
