package com.concretepage.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;


@Repository
public class USSStatisticsRepository {

	 @Resource(name="redisTemplate")
	 private HashOperations<String, String, String> hashOps;	 	 
	 
	 private static final String KEY = "USSStatistics";
	 
	 public void addItem(String key,String obj) {
		 hashOps.put(KEY,key, obj);
	 }
	 public long getNumberOfItems(String key) {
		 return hashOps.size(key);
	 }
	 public Map<String,String> findAll(String key) {
		 return hashOps.entries(KEY);
	 }
	 public String findUSSStatisticsByKey(String key) {
		 return hashOps.get(KEY,key);
	 }
	 public List<String> findUSSStatisticsByKeys(String key,Collection<String> hashKeys) {
		return hashOps.multiGet(key, hashKeys);
	 }
	 public Set<String> findUSSStatisticsKeys(String key) {
			return hashOps.keys(key);
		 }
}
