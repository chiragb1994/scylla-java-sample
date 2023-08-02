package com.chirag.scylla.models;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.SchemaHint;
import com.datastax.oss.driver.api.mapper.annotations.SchemaHint.TargetElement;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SchemaHint(targetElement = TargetElement.TABLE)
public class UserData {

  @PartitionKey(1)
  private String userId;

  private Map<String, String> data;
}
