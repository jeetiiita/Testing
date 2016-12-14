// $codepro.audit.disable methodJavadoc
package com.clicktable.support.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.google.common.reflect.TypeToken;

import play.Logger;

public abstract class JPADao<T> implements GenericDao<T> {
    private static final Logger.ALogger log = Logger.of(JPADao.class);
    /**
     * Field type.
     */
    protected final Class<? super T> type;
    /**
     * Field token.
     */
    private final TypeToken<T> token;
    @PersistenceContext
    public EntityManager em;

    public JPADao() {
        token = new TypeToken<T>(getClass()) {
        };
        type = token.getRawType();
    }

    public Class getType() {
        return type;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public T create(T t) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public T find(Object id) {
        return (T) em.find(type, id);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public T update(T t) {
        return (T) em.merge(t);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void delete(Class type, Object id) {
        // em.remove(id);
        em.remove(em.contains(id) ? id : em.merge(id));
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public List<T> findAll(Class type) {
        return em.createQuery("Select t from " + type.getSimpleName() + " t")
                .getResultList();
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public List<T> findByFields(Class type, Map<String, Object> params) {
        TypedQuery<T> pquery = createQuery(type, params);
        return pquery.getResultList();
    }

    // @Override
    public List<T> createMultiple(List<T> list) {
        // do nothing
        return list;
    }

    @Transactional
    protected TypedQuery<T> createQuery(Class type, Map<String, Object> params) {

        log.debug("in create query");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);

        Root<T> root = cq.from(type);
        List<Predicate> predicates = new ArrayList<Predicate>();
        setPredicates(predicates, cb, root, params);

        cq.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<T> pquery = em.createQuery(cq);

        int pageSize = getPageSize(params);
        pquery.setFirstResult(getIndex(params, pageSize));
        pquery.setMaxResults(pageSize);

        return pquery;
    }

    protected void setPredicates(List<Predicate> predicates, CriteriaBuilder cb,
                                 Root<T> root, Map<String, Object> params) {
        predicates.addAll(getModelSpecificPredicates(cb, root, params, type));
        predicates.addAll(getPredicatesForDate(root, params, cb));
    }

    @Transactional
    protected int getIndex(Map<String, Object> params, int pageSize) {
        // check for page no
        int startIndex = 0;
        if (params.containsKey(Constants.PAGE_NO)) {
            String pageNoStr = (String) params.get(Constants.PAGE_NO);
            startIndex = UtilityMethods.getStartIndex(pageNoStr, pageSize);
        }
        return startIndex;
    }

    /**
     * predicates related to dates like created before,created after,created
     * on,updated on,updated after,updated before
     *
     * @param predicates
     * @param root
     * @param params
     * @param cb
     * @return
     */

    @Transactional
    protected List<Predicate> getPredicatesForDate(Root root,
                                                   Map<String, Object> params, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Entry<String, Object> entry : params.entrySet()) {
            //TODO refactor for duplicacy
            if (entry.getKey().endsWith(Constants.BEFORE)) {
                String fieldName = entry.getKey().substring(0,
                        entry.getKey().length() - Constants.BEFORE.length());
                Field field;
                if (null != (field = UtilityMethods.getClassField(fieldName,
                        type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.DATESTR, type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.TIMESTR, type))
                        && (field.getType().isAssignableFrom(Date.class))) {
                    if (entry.getValue() instanceof Date)
                        predicates.add(cb.lessThan(root.get(field.getName()),
                                (Date) entry.getValue()));
                }
            } else if (entry.getKey().endsWith(Constants.BEFORE_OR_ON)) {
                String fieldName = entry.getKey().substring(
                        0,
                        entry.getKey().length()
                                - Constants.BEFORE_OR_ON.length());
                Field field;
                if (null != (field = UtilityMethods.getClassField(fieldName,
                        type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.DATESTR, type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.TIMESTR, type))
                        && (field.getType().isAssignableFrom(Date.class))) {
                    if (entry.getValue() instanceof Date)
                        predicates.add(cb.lessThanOrEqualTo(
                                root.get(field.getName()),
                                (Date) entry.getValue()));
                }
            } else if (entry.getKey().endsWith(Constants.AFTER)) {
                String fieldName = entry.getKey().substring(0,
                        entry.getKey().length() - Constants.AFTER.length());
                Field field;
                if (null != (field = UtilityMethods.getClassField(fieldName,
                        type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.DATESTR, type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.TIMESTR, type))
                        && (field.getType().isAssignableFrom(Date.class))) {
                    if (entry.getValue() instanceof Date)
                        predicates.add(cb.greaterThan(
                                root.get(field.getName()),
                                (Date) entry.getValue()));
                }
            } else if (entry.getKey().endsWith(Constants.AFTER_OR_ON)) {
                String fieldName = entry.getKey().substring(
                        0,
                        entry.getKey().length()
                                - Constants.AFTER_OR_ON.length());
                Field field;
                if (null != (field = UtilityMethods.getClassField(fieldName,
                        type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.DATESTR, type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.TIMESTR, type))
                        && (field.getType().isAssignableFrom(Date.class))) {
                    if (entry.getValue() instanceof Date)
                        predicates.add(cb.greaterThanOrEqualTo(
                                root.get(field.getName()),
                                (Date) entry.getValue()));
                }
            } else if (entry.getKey().endsWith(Constants.ON)) {
                String fieldName = entry.getKey().substring(0,
                        entry.getKey().length() - Constants.ON.length());
                Field field;
                if (null != (field = UtilityMethods.getClassField(fieldName,
                        type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.DATESTR, type))
                        || null != (field = UtilityMethods.getClassField(
                        fieldName + Constants.TIMESTR, type))
                        && (field.getType().isAssignableFrom(Date.class))) {
                    if (entry.getValue() instanceof Date)
                        predicates.add(cb.equal(root.get(field.getName()),
                                (Date) entry.getValue()));
                }
            }
        }

        return predicates;
    }

    /**
     * returns page size string,if no page size is provided in query string than
     * it returns default page size string
     *
     * @param params
     * @return
     */
    @Transactional
    protected int getPageSize(Map<String, Object> params) {

        int pageSize = Integer.parseInt(play.i18n.Messages
                .get(Constants.PAGE_SIZE));
        // Fetch pagination info
        // check for page size
        // if page size is not numeric then default page size will be used
        if (params.containsKey(Constants.PAGE_SIZE)) {
            String pageSizeStr = (String) params.get(Constants.PAGE_SIZE);
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                if ((pageSize > Constants.PAGE_SIZE_LIMIT) || (pageSize < 0)) {
                    pageSize = Integer.parseInt(play.i18n.Messages
                            .get(Constants.PAGE_SIZE));
                }
            } catch (NumberFormatException nfe) {
                Logger.error("Invalid Page size " + nfe.getMessage());
            }
        }
        return pageSize;
    }

    /**
     * apply model specific predicates
     *
     * @param cb
     * @param root
     * @param params
     * @param type
     * @return
     */
    /*
	 * @Transactional protected List<Predicate>
	 * getModelSpecificPredicates(CriteriaBuilder cb, Root root, Map<String,
	 * Object> params, Class type) {
	 * 
	 * List<Predicate> predicates = new ArrayList<Predicate>(); Field field =
	 * null; for (Entry<String, Object> entry : params.entrySet()) { try { if
	 * ((field=type.getDeclaredField(entry.getKey())) != null &&
	 * root.get(entry.getKey()) != null) {
	 * predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
	 * params.remove(entry.getKey()); } } catch (NoSuchFieldException |
	 * SecurityException e) { Logger.warn("field name:" + entry.getKey() +
	 * "not in model "+e.getLocalizedMessage()); } }
	 * 
	 * return predicates; }
	 */
    @Transactional
    protected List<Predicate> getModelSpecificPredicates(CriteriaBuilder cb,
                                                         Root root, Map<String, Object> params, Class type) {

        List<Predicate> predicates = new ArrayList<Predicate>();
        Field field = null;

        Iterator<Map.Entry<String, Object>> iterator = params.entrySet()
                .iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, Object> entry = iterator.next();

            if ((field = UtilityMethods.getClassField(entry.getKey(), type)) != null && root.get(entry.getKey()) != null) {
                predicates.add(cb.equal(root.get(entry.getKey()),
                        entry.getValue()));
                iterator.remove();
            }
        }


        return predicates;
    }

    @Transactional
    public T find(Class type, Object id) {
        return (T) em.find(type, id);
    }

    @Override
    public int getCountWithParams(Map<String, Object> params) {

        return 0;
    }

    @Override
    public List<T> updateMultiple(List<String> guidList, Map<String, Object> valuesToUpdate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String guid) {
        // TODO Auto-generated method stub

    }
}
