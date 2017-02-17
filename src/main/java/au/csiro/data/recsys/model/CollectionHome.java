package au.csiro.data.recsys.model;

// Generated 09/02/2017 5:17:21 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Collection.
 * @see au.csiro.data.recsys.model.Collection
 * @author Hibernate Tools
 */
@Stateless
public class CollectionHome {

	private static final Log log = LogFactory.getLog(CollectionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Collection transientInstance) {
		log.debug("persisting Collection instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Collection persistentInstance) {
		log.debug("removing Collection instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Collection merge(Collection detachedInstance) {
		log.debug("merging Collection instance");
		try {
			Collection result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Collection findById(long id) {
		log.debug("getting Collection instance with id: " + id);
		try {
			Collection instance = entityManager.find(Collection.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
