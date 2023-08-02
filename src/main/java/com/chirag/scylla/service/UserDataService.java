package com.chirag.scylla.service;

import com.chirag.scylla.models.UserData;
import java.util.Map;
import java.util.Optional;

public interface UserDataService {

  Optional<UserData> findByUserId(String userId);

  Optional<UserData> addData(String userId, Map<String, String> data);
}
