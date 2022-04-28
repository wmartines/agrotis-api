package com.agrotis.api.agrotisteste.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agrotis.api.agrotisteste.document.UserDocument;
import com.agrotis.api.agrotisteste.param.UserFilterParam;

public interface UserRepository extends  MongoRepository<UserDocument, String> {
	
	public static final String QUERY_FIND_ALL_PRODUCT =  
			"{$and :["
					// filter by initial start date
					+ "?#{#param.initialStartDate == null ? {'dtStart' : {$exists: true}} : {'dtStart' : {$gte : #param.initialStartDate}}},"
					// filter by final start date
					+ "?#{#param.finalStartDate == null ? {'dtStart' : {$exists: true}} : {'dtStart' : {$lte : #param.finalStartDate}}},"
					// filter by product name
					+ "?#{#param.name == null ? {'name' : {$exists: true}} : {'name' : {$regex : #param.name}}},"
					// filter by document id
					+ "?#{#param.documentId == null ? {'documentId' : {$exists: true}} : {'documentId' : #param.documentId}},"
			 + "]}";
	
	@Query(UserRepository.QUERY_FIND_ALL_PRODUCT)
	Page<UserDocument> findAllUsers (@Param("param") UserFilterParam param, Pageable pageable);

}
