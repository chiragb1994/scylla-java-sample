package com.chirag.scylla.health;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.Metadata;
import com.datastax.oss.driver.api.core.metadata.Node;
import com.datastax.oss.driver.api.core.metadata.NodeState;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScyllaHealthIndicator implements HealthIndicator {

  private final CqlSession cqlSession;

  @Override
  public Health health() {
    Builder health;
    Map<String, Object> healthDetails = new HashMap<>(4);
    healthDetails.put("name", cqlSession.getName());
    cqlSession
        .getKeyspace()
        .ifPresent(keySpace -> healthDetails.put("keySpace", keySpace.asCql(true)));
    Metadata metadata = cqlSession.getMetadata();
    Map<String, Object> metadataMap = new HashMap<>(3);
    metadataMap.put("clusterName", metadata.getClusterName().orElse("NA"));
    metadataMap.put(
        "keySpaces",
        metadata.getKeyspaces().keySet().stream()
            .map(ci -> ci.asCql(true))
            .collect(Collectors.toList()));
    metadataMap.put(
        "nodes",
        metadata.getNodes().entrySet().stream()
            .map(entry -> new SimpleEntry<>(entry.getKey().toString(), nodeToMap(entry.getValue())))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
    healthDetails.put("metadata", metadataMap);
    healthDetails.put("metrics", cqlSession.getMetrics());
    health =
        metadata.getNodes().values().stream().allMatch(n -> n.getState().equals(NodeState.UP))
            ? Health.up()
            : Health.down();
    return health.withDetails(healthDetails).build();
  }

  private Map<String, Object> nodeToMap(Node node) {
    Map<String, Object> map = new HashMap<>();
    map.put("endpoint", node.getEndPoint().resolve());
    map.put("dc", node.getDatacenter());
    map.put("rack", node.getRack());
    map.put("version", node.getCassandraVersion());
    map.put("extras", node.getExtras());
    map.put("state", node.getState());
    map.put("upSinceMillis", node.getUpSinceMillis());
    map.put("openConnections", node.getOpenConnections());
    map.put("isReconnecting", node.isReconnecting());
    map.put("distance", node.getDistance());
    map.put("hostId", node.getHostId());
    return map;
  }
}
