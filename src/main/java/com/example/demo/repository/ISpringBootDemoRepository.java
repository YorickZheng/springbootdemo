package com.example.demo.repository;

import com.example.demo.entity.SpringBootEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhengyilong on 2017/11/30.
 */
public interface ISpringBootDemoRepository extends CrudRepository<SpringBootEntity,Long> {

}
