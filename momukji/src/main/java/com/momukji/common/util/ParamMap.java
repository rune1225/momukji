package com.momukji.common.util;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.momukji.common.util.CamelUtil;

@SuppressWarnings("unchecked")
public class ParamMap {

	private Map<String,Object> map;

	public ParamMap() {
		this.map = new HashMap<String,Object>();
	}

	public Map<String,Object> get() {
		return this.map;
	}

	public void setParamMap(HashMap<String,Object> hm){
		this.map = hm;
	}

	/**
	 * @param key
	 * @return object
	 */
	public Object get(String key) {
		return map.get(key);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, int value) {
		map.put(key, new Integer(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, float value) {
		map.put(key, new Float(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, long value) {
		map.put(key, new Long(value));
	}

	/**
	 * @param key
	 * @param d
	 */
	public void put(String key, double value) {
		put(key, new Double(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
	    if(value == null) value = "";
		map.put(key, new String(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		map.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(objToStr(map.get(key),"").equals("")) return 0;
		return Integer.parseInt(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(objToStr(map.get(key),"").equals("")) return 0;
		return Float.parseFloat(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(objToStr(map.get(key),"").equals("")) return 0;
		return (long)Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(objToStr(map.get(key),"").equals("")) return 0;
		return Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public String getString(String key) {
        if(map.containsKey(key) == false) return "";
		return objToStr(map.get(key),"");
	}

	/**
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
	    if(map.containsKey(key) == false) return null;
		return map.get(key);
	}

	/**
	 *
	 */
	public String toString() {
		return map.toString();
	}

	/**
	 * 대소문자로 key 변경
	 * @param mode
	 * @return
	 */
	public Map replaceCase(String mode){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        HashMap<String,Object> rtnMap = new HashMap<String,Object>();
        String exclusionKeyword="cwareAction,";
		if(mode.equals("lower")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
	            	rtnMap.put(entry.getKey().toString(), entry.getValue());
	            }else{
	            	rtnMap.put(entry.getKey().toString().toLowerCase(), entry.getValue());
	            }
	        }
		}else if(mode.equals("upper")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
	            	rtnMap.put(entry.getKey().toString(), entry.getValue());
	            }else{
	            	rtnMap.put(entry.getKey().toString().toUpperCase(), entry.getValue());
	            }
	        }
		}
		setParamMap(rtnMap);
		return this.map;
	}

	/**
	 * Camel 표기법으로 변경
	 * @return
	 */
	public Map replaceCamel(){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        HashMap<String,Object> rtnMap = new HashMap<String,Object>();
        String exclusionKeyword="cwareAction,";
        while(iterator.hasNext()){
            entry = (Entry)iterator.next();
            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
            	rtnMap.put(entry.getKey().toString(), entry.getValue());
            }else{
            	rtnMap.put(CamelUtil.convert2CamelCase(entry.getKey().toString().toUpperCase()), entry.getValue());
            }
        }
		setParamMap(rtnMap);
		return this.map;
	}

	/**
	 * 대소문자로 key 변경 신규 생성 후 return
	 * @param mode
	 * @return
	 */
	public Map replaceCaseCreate(String mode){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        String exclusionKeyword="cwareAction,";
		if(mode.equals("lower")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
	            	rtnMap.put(entry.getKey().toString(), entry.getValue());
	            }else{
	            	rtnMap.put(entry.getKey().toString().toLowerCase(), entry.getValue());
	            }
	        }
		}else if(mode.equals("upper")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
	            	rtnMap.put(entry.getKey().toString(), entry.getValue());
	            }else{
	            	rtnMap.put(entry.getKey().toString().toUpperCase(), entry.getValue());
	            }
	        }
		}
		return rtnMap;
	}

	/**
	 * Camel 표기법으로 변경 신규 생성 후 return
	 * @return
	 */
	public Map replaceCamelCreate(){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        String exclusionKeyword="cwareAction,";
        while(iterator.hasNext()){
            entry = (Entry)iterator.next();
            if(exclusionKeyword.indexOf(entry.getKey().toString()) >= 0){
            	rtnMap.put(entry.getKey().toString(), entry.getValue());
            }else{
            	rtnMap.put(CamelUtil.convert2CamelCase(entry.getKey().toString().toUpperCase()), entry.getValue());
            }
        }
		return rtnMap;
	}

	/**
	 *
	 * @param objVal
	 * @param emptyValue
	 * @return
	 */
    private String objToStr(Object objVal, String emptyValue){
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = objVal.toString();
        return emptyValue;
    }

	/**
	 * @param key
	 * @return
	 */
	public Timestamp getTime(String key) throws Exception {
	    if(map.containsKey(key) == false) return null;
	    if (map.get(key) == null || "".equals(map.get(key)))
			return null;
	    try {
	    	return new Timestamp((new SimpleDateFormat("yyyyMMdd")).parse(getString(key).replaceAll("\\D", "")).getTime());
	    } catch (ParseException p) {
	    	throw new Exception("format is not exactly!!");
	    }
	}
	
	public Timestamp getTimestamp(String key) throws Exception {
	    if(map.containsKey(key) == false) return null;
	    if (map.get(key) == null || "".equals(map.get(key)))
			return null;
	    try {
	    	return new Timestamp((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse(getString(key)).getTime());
	    } catch (ParseException p) {
	    	throw new Exception("format is not exactly!!");
	    }
	}

	public String forwardToString() {
		Object obj = null;
		Object[] objArray = null;
		StringBuffer sb = new StringBuffer();
		String rtnValue="";
		String[] keys = (String[]) map.keySet().toArray(new String[0]);
		for (int i = 0; i < keys.length; i++) {
			obj = map.get(keys[i]);
			if (obj.getClass().isArray()) {
				objArray = (Object[]) obj;
				for (int j = 0; j < objArray.length; j++) {
					sb.append(keys[i]+"="+objArray[j]+"&");
				}
			} else {
				sb.append(keys[i]+"="+obj+"&");
			}
		}

		try{
			rtnValue = URLEncoder.encode(sb.toString(), "UTF-8");
		}catch(Exception e){}

		return rtnValue;
	}

}