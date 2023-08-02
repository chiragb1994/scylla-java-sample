package com.chirag.scylla.mapper;

import com.chirag.scylla.dao.UserDataDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface UserDataMapper {

  @DaoFactory
  UserDataDao userDataDao();
}
