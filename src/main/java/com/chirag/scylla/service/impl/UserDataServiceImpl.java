package com.chirag.scylla.service.impl;

import com.chirag.scylla.dao.UserDataDao;
import com.chirag.scylla.mapper.UserDataMapper;
import com.chirag.scylla.mapper.UserDataMapperBuilder;
import com.chirag.scylla.models.UserData;
import com.chirag.scylla.service.UserDataService;
import com.datastax.oss.driver.api.core.CqlSession;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDataServiceImpl implements UserDataService {

  private final UserDataDao userDataDao;

  public UserDataServiceImpl(CqlSession cqlSession) {
    UserDataMapper mapper = new UserDataMapperBuilder(cqlSession).build();
    this.userDataDao = mapper.userDataDao();
  }

  @Override
  public Optional<UserData> findByUserId(String userId) {
    log.info("Get data for user id {}", userId);
    return userDataDao.findByUserId(userId);
  }

  @Override
  public Optional<UserData> addData(String userId, Map<String, String> data) {
    log.info("Add data for user id {}: {}", userId, data);
    UserData userData = new UserData(userId, data);
    userDataDao.save(userData);
    return findByUserId(userId);
  }
}
