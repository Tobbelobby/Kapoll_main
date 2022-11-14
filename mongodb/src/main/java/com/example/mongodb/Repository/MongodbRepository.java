package com.example.mongodb.Repository;

import com.example.mongodb.Doucument.PollResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongodbRepository extends MongoRepository<PollResult, String> {

}