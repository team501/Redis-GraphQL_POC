package com.concretepage.util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.concretepage.model.USSStatistics;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	public static String getStaticsJSON(USSStatistics statics) {
		try {
			return mapper.writeValueAsString(statics);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static USSStatistics getStaticsObject(String json) {
		try {
			return mapper.readValue(json, USSStatistics.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getStaticsJSONList(List<USSStatistics> statics) {
		return statics.parallelStream().map(stats -> getStaticsJSON(stats)).collect(Collectors.toList());		
	}
	
	public List<USSStatistics> getStaticsObjectList(List<String> json) {
		return json.parallelStream().map(stats -> getStaticsObject(stats)).collect(Collectors.toList());
	}
	
	public List<USSStatistics> getStaticData() {
		
		
		/*values (103666230,12041301320302002066,null,null,null,null,null,null,707351,167653,null,null,'302103',null
				,null,null,107763996,20181204130441034,to_timestamp('04-DEC-18 01.04.41.096000000 PM','DD-Mon-YY SS:MS')
				,to_timestamp('04-DEC-18 01.04.41.103077000 PM','DD-Mon-YY SS:MS'),
				to_timestamp('04-DEC-18 01.04.41.104732000 PM','DD-Mon-YY SS:MS'),'302501',0)*/
		return null;
	}
}
