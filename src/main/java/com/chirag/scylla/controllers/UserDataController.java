package com.chirag.scylla.controllers;

import com.chirag.scylla.models.UserData;
import com.chirag.scylla.service.UserDataService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-data")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataController {

  private final UserDataService userDataService;

  @GetMapping("/{userId}")
  public UserData getUserData(@PathVariable String userId) {
    return userDataService.findByUserId(userId).orElse(null);
  }

  @PostMapping("/{userId}")
  public UserData getUserData(@PathVariable String userId, @RequestBody Map<String, String> data) {
    return userDataService.addData(userId, data).orElse(null);
  }
}
