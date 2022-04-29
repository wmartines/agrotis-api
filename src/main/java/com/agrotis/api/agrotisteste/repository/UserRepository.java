package com.agrotis.api.agrotisteste.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agrotis.api.agrotisteste.document.UserDocument;
import com.agrotis.api.agrotisteste.param.UserFilterParam;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends  MongoRepository<UserDocument, String> {
	
	/** The Constant QUERY_FIND_ALL_USER. */
	public static final String QUERY_FIND_ALL_USER =  
			"{$and :["
					// filter by initial start date
					+ "?#{#param.initialStartDate == null ? {'dtStart' : {$exists: true}} : {'dtStart' : {$gte : #param.initialStartDate}}},"
					// filter by final start date
					+ "?#{#param.finalStartDate == null ? {'dtStart' : {$exists: true}} : {'dtStart' : {$lte : #param.finalStartDate}}},"
					// filter by user name
					+ "?#{#param.name == null ? {'name' : {$exists: true}} : {'name' : {$regex : #param.name}}},"
					// filter by document id
					+ "?#{#param.documentId == null ? {'documentId' : {$exists: true}} : {'documentId' : #param.documentId}},"
			 + "]}";
	
	/**
	 * Find all users.
	 *
	 * @param UserFilterParam the param
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query(UserRepository.QUERY_FIND_ALL_USER)
	Page<UserDocument> findAllUsers (@Param("param") UserFilterParam param, Pageable pageable);

}
