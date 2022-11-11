
package com.example.mongodb.Repository;

import com.example.mongodb.Doucument.PollResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongodbRepository extends MongoRepository<PollResult, String> {

    public List<PollResult> findById(Long id);
    public PollResult findByUtilDate(Long UtilDate);


}

/*

 */