package com.dellemc.devops.meta;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataRepository extends MongoRepository<MetaData, String> {

}
