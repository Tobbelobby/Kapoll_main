
package com.example.mongodb.Repository;

import com.example.mongodb.Doucument.PollResult;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongodbRepository extends MongoRepository<PollResult, String> {

    public PollResult findBypoll(JSONObject poll);


}

/*

 */