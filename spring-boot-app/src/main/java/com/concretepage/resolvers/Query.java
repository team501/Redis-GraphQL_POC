package com.concretepage.resolvers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concretepage.dao.USSStatisticsRepository;
import com.concretepage.model.InductForLastHourOutput;
import com.concretepage.model.USSStatistics;
import com.concretepage.util.JSONUtil;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

	@Autowired
    private USSStatisticsRepository ussStatisticsRepository;

	private DateTimeFormatter format =  
            new DateTimeFormatterBuilder().appendPattern("MM-dd-yyyy[ [HH][:mm]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter(); 
	
	public List<USSStatistics> ussStatistics(String key) {
		
//		setStaticData(60);		
         Map<String, String> mapObj = ussStatisticsRepository.findAll(key);
         List<USSStatistics> list = mapObj.values().stream().map(entry -> JSONUtil.getStaticsObject(entry)).collect(Collectors.toList());
         return list;
    }
    
    public USSStatistics findUSSStatisticsByKey(String key) {
    	//setStaticData(60);
    	String ussStatistics= ussStatisticsRepository.findUSSStatisticsByKey(key);
    	return JSONUtil.getStaticsObject(ussStatistics);
    }
    
    public InductForLastHourOutput getInductsForLastHour(String key,String startTime) {
    	setStaticData(60);
    	Set<String> set = new TreeSet<>(ussStatisticsRepository.findUSSStatisticsKeys(key));
    	
    	InductForLastHourOutput inductForLastHourOutput = new InductForLastHourOutput();
    	List<USSStatistics> lstUssStatistics=new ArrayList<>();
    	List<String> hashKeys = getLastXMinsKeys(set,60,startTime);
    	List<String> listOP = ussStatisticsRepository.findUSSStatisticsByKeys(key, hashKeys);
    	listOP.removeAll(Collections.singleton(null));
    	if(!listOP.isEmpty()) {
    		lstUssStatistics = listOP.stream().map(entry -> (entry!=null?JSONUtil.getStaticsObject(entry):null)).collect(Collectors.toList());
    	}
    	
    	inductForLastHourOutput.setDuration(60);
    	inductForLastHourOutput.setTotalInducts(lstUssStatistics.size());
    	inductForLastHourOutput.setCondition("true");
    	inductForLastHourOutput.setMeasure(0);
    	inductForLastHourOutput.setLstUssStatistics(lstUssStatistics);
    	return inductForLastHourOutput;
    }

	private List<String> getLastXMinsKeys(Set<String> set, Integer limit, String startTime) {
		List<String> datesListstr = new ArrayList<>();
		LocalDateTime localStartDate = LocalDateTime.parse(startTime,format);
		LocalDateTime localEndDate = localStartDate.minusMinutes(limit);
		for(String val:set) {
			LocalDateTime dt = LocalDateTime.parse(val,format);
			if(dt.isAfter(localEndDate) && dt.isBefore(localStartDate)) {
				datesListstr.add(val);
			}
		}
		return datesListstr;
	}
		
	public void setStaticData(Integer limit) {
		LocalDateTime now = LocalDateTime.now();
		for(int i=0; i < limit; i++ ) {
			ussStatisticsRepository.addItem(now.plusMinutes(i).format(format),JSONUtil.getStaticsJSON(new USSStatistics(103666230, 103666230, 103666230, 2002066, 764, 647,
					"hjfvfdk", "sdhgfc", 707351, 167653, "Chute1", "", "", null, null, null, 
					null, null, null, "04-DEC-18 01.04.42.277000000 PM", "04-DEC-18 01.04.42.277000000 PM", 
					null, null)));
		}
	}
    
}