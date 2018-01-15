package com.dev.dao;

import org.springframework.stereotype.Repository;

import com.dev.domain.Tuser;

@Repository
public interface TuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tuser record);

    Tuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Tuser record);
}