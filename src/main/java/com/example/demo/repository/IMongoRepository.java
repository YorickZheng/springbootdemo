package com.example.demo.repository;

import com.example.demo.entity.SpringBootEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhengyilong on 2017/12/1.
 */
public interface IMongoRepository extends MongoRepository<SpringBootEntity,Long> {
}
