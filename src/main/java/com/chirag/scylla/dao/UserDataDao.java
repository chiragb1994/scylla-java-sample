package com.chirag.scylla.dao;

import com.chirag.scylla.models.UserData;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import java.util.Optional;

@Dao
public interface UserDataDao {

  @Select
  Optional<UserData> findByUserId(String userId);

  @Insert
  void save(UserData userData);
}
