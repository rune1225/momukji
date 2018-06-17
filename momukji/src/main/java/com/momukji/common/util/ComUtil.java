package com.momukji.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * Common method
 *
 * @version 1.0, 2016/08/10
 * @author  Yoon Han <rune1225@gmail.com>
 */
public class ComUtil implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

  /**
   * <PRE>
   * Desc     : Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
   * </PRE>
   * @param   str       : 원본
   * @param   separator : 구분자
   * @return  String[]
   */
    public static String[] split(String str, String separator){
        return StringUtils.split(str, separator);
    }

  /**
   * <PRE>
   * Desc     : Replaces multiple characters in a String in one go.
   * </PRE>
   * @param   str     : 바꾸려는 문자열을 가진 원본
   * @param   pattern : 찾을 문자열
   * @param   replace : 바꿔줄 문자열
   * @return  String
   */
  public static String replaceChars(String text, String repl, String with)
  {
    return StringUtils.replace(text, repl, with);
  }

    // CheckBox Value Mapping
    // i)   0  >> 1
    // ii) any >> 0
    public static String checkBoxValueSet(String inputValue){
        String rtnValue = "0";
        if( inputValue == null ) return rtnValue;
        if( inputValue.equals("") ) return rtnValue;
        if( !inputValue.equals("0") ) rtnValue = "1";
        return rtnValue;
    }

    // Object - > String
    public static String objToStr(Object objVal){
        return objToStr(objVal, "");
    }
    // Object - > String
    public static String objToStr(Object objVal, String emptyValue){
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = objVal.toString();
        return emptyValue;
    }

    // Object - > int
    public static int objToInt(Object objVal){
        return objToInt(objVal, 0);
    }
    // Object - > int
    public static int objToInt(Object objVal, int emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = Integer.parseInt(objVal.toString());
        return emptyValue;
    }

    // Object - > long
    public static long objToLong(Object objVal){
        return objToLong(objVal, 0);
    }
    // Object - > long
    public static long objToLong(Object objVal, long emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = (long)Double.parseDouble(objVal.toString());
        return emptyValue;
    }

    // Object - > long
    public static long objToDoubleToLong(Object objVal){
    	long emptyValue = 0;
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = (long)objToDouble(objVal);
        return emptyValue;
    }

    // Object - > double
    public static double objToDouble(Object objVal){
        return objToDouble(objVal, 0);
    }
    // Object - > double
    public static double objToDouble(Object objVal, double emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = Double.parseDouble(objVal.toString());
        return emptyValue;
    }
    // Double - > String
    public static String objToDoubleToStr(Object objVal){
    	String emptyValue = "";
        if(objVal == null)		return emptyValue;
        emptyValue = objToStr(objToDoubleToLong(objVal));//objVal.toString();
        return emptyValue;
    }

    // Object - > boolean
    public static boolean objToBoolean(Object objVal){
        return objToBoolean(objVal, false);
    }
    // Object - > boolean
    public static boolean objToBoolean(Object objVal, boolean emptyValue){
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = Boolean.valueOf(objVal.toString());
        return emptyValue;
    }

    /** String 을 구분자에 따라 잘라서 배열에 저장 **/
  public static String[] getStringToArray(String strVale, String CheckValue, int ArrayCount){
    String [] tempArray = new String[ArrayCount];
    if(strVale == null)    return tempArray;
    if(strVale.equals("")) return tempArray;

    int s = 0;
    int e = 0;
    int i = 0;

    while ((e = strVale.indexOf(CheckValue, s)) >= 0)
    {
      tempArray[i] = strVale.substring(s, e);
      s = e+CheckValue.length();
      i++;
    }
    return tempArray;
  }



  /**
   * <PRE>
   * Desc     : 123,456 -> int type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  int
   */
  public static int setCurrencyToInt(String paramMoney){
    int money = 0;
    StringBuffer strMoney = new StringBuffer(paramMoney);

    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',')
      {
        strMoney.deleteCharAt(i);
      }
    }

    try
    {
      money = Integer.parseInt(strMoney.toString());
    } catch (NumberFormatException nfe) {
      money = -1; // error -1 return
    }
    return money;
  }

  /**
   * <PRE>
   * Desc     : 123,456 -> double type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  double
   */
  public static double setCurrencyToDouble(String paramMoney){
    double money = 0;
    StringBuffer strMoney = new StringBuffer(paramMoney);
    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',')
      {
          strMoney.deleteCharAt(i);
      }
    }

    try
    {
      money = Double.parseDouble(strMoney.toString());
    } catch (NumberFormatException nfe) {
      money = -1; // error -1 return
    }
    return money;
  }

  /**
   * <PRE>
   * Desc     : 123,456 -> String type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  String
   */
  public static String setCurrencyToStr(String paramMoney)
  {
    if (paramMoney == null)
    {
      paramMoney = "";
    }

    StringBuffer strMoney = new StringBuffer(paramMoney);
    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',' || strMoney.charAt(i) == '-' || strMoney.charAt(i) == '/')
      {
        strMoney.deleteCharAt(i);
      }
    }

    if (isNumber(strMoney.toString()))
    {
      return strMoney.toString();
    } else {
      return null;
    }
  }

  /**
   * <PRE>
   * Desc     : int -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : int
   * @return  String
   */
  public static String setIntToCurrency(int paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : double -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setIntToCurrency(double paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : long -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setIntToCurrency(long paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : long -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setLongToCurrency(long paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : String -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : String
   * @return  String
   */
  public static String setStrToCurrency(String paramMoney)
  {
    if (paramMoney.equals(""))
      return "0";
    if (!isNumber(paramMoney))
      return null;

    Long paramLong = new Long(paramMoney);
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramLong.longValue());
  }

  /**
   * <PRE>
   * Desc     : number check
   * </PRE>
   * @param   strNumber : String
   * @return  boolean
   */
  public static boolean isNumber(String strNumber)
  {
    boolean isSuccess = true;

    try
    {
      new Long(strNumber);
    } catch (NumberFormatException nfe) {
      isSuccess = false;
    }
    return isSuccess;
  }

  /**
   * <PRE>
   * Desc     : 숫자에 대해(금액) TRUNC
   * </PRE>
   * @param   param : 숫자
   * @param   param : 숫자
   * @return  float
   */
  public static float Truncate(float param, float tunc)
  {
    param = param / tunc;
    Float floatTrunc = new Float(param);
    Integer IntTrunc = new Integer(floatTrunc.intValue());
    param = IntTrunc.floatValue();
    param = param  * tunc;
    return param;
  }

  /**
   * <PRE>
   * Desc     : 정수를 받아서 반올림(5이하 버림, 5이상 올림)
   * </PRE>
   * @param   number : 정수
   * @return  long
   */
  public static long Round(int number)
  {
    Integer i = new Integer(number);
    double d_number = i.doubleValue();
    long round_number = Math.round(d_number/10);
    long exchange_number = round_number * 10;
    return exchange_number;
  }

  /**
   * <PRE>
   * Desc     : double를 받아서 반올림(5이하 버림, 5이상 올림)
   * </PRE>
   * @param   number : double
   * @return  long
   */
  public static long Round(double number)
  {
    long round_number = Math.round(number/10);
    long exchange_number = round_number * 10;
    return exchange_number;
  }

  /**
   * <PRE>
   * Desc     : String 형식을 받아서 Html 형식으로 변환
   * </PRE>
   * @param   comment : String
   * @return  String
   */
  public static String convertHtmlBr(String comment)
  {
    int length = comment.length();
    StringBuffer buffer = new StringBuffer();

    if (comment.equals(null))
    {
      buffer.append("");
      return buffer.toString();
    }

    for (int i = 0; i < length; ++i)
    {
      String comp = comment.substring(i, i+1);
      if ("\r".compareTo(comp) == 0)
      {
        comp = comment.substring(++i, i+1);
        if ("\n".compareTo(comp) == 0)
          buffer.append("<BR>\r");
        else
          buffer.append("\r");
      }
      buffer.append(comp);
    }
    return buffer.toString();
  }

	/**
     * db에서 사용할수 없는 값들을 변환(&,',^)   web --> db
     * @param dbstring 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String script2web(String dbstring){
        int index=0;
        String temp = dbstring;

        while((index=temp.indexOf("~&"))>=0) {
            temp = temp.substring(0,index)+"{"+temp.substring(index+2);
        }
        while((index=temp.indexOf("~`"))>=0) {
            temp = temp.substring(0,index)+"\\"+temp.substring(index+2);
        }
        while((index=temp.indexOf("~^"))>=0) {
            //temp = temp.substring(0,index)+"\r\n"+temp.substring(index+2);
			temp = temp.substring(0,index)+"<br>"+temp.substring(index+2);
        }
        return temp;
    }

	/**
     * db에서 사용할수 없는 값들을 변환()   text --> db
     * @param dbstring 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String text2db(String desc){
        if(desc==null || desc.length()==0 ) return "";
        if(desc.length() == 1){
            if(desc.equals("\\")){
                return " ";
            }else if(desc.equals("\"")){
                return "'";
            }else{
                return desc;
            }
        }

        String temp = desc;
        int index=0;

        while((index=temp.indexOf('"'))>=0) {
            temp = temp.substring(0,index)+"'"+temp.substring(index+1);
        }

        String lastStr1 = temp.substring(temp.length()-1, temp.length());

        if (lastStr1.equals("\\") ){
		    return temp.substring(0, temp.length()-1) + " ";
        }

        return temp;

    }

	/**
     * web에서 사용할수 없는 값들을 변환   db --> web
     * @param desc 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String db2script(String desc){
        if(desc==null || desc.length()==0 )
			return "";

        String temp = desc;
        int index=-2;

/*        while((index=temp.indexOf('\'',index+2))>=0) {
            temp = temp.substring(0,index)+"\'\'"+temp.substring(index+1);
        }
*/
        while((index=temp.indexOf('{'))>=0) {
            temp = temp.substring(0,index)+"~&"+temp.substring(index+1);
        }
        while((index=temp.indexOf('\\'))>=0) {
            temp = temp.substring(0,index)+"~`"+temp.substring(index+1);
        }
        while((index=temp.indexOf("\r\n"))>=0) {
            temp = temp.substring(0,index)+"~^"+temp.substring(index+2);
        }
        while((index=temp.indexOf('\n'))>=0) {
            temp = temp.substring(0,index)+"~^"+temp.substring(index+1);
        }
        while((index=temp.indexOf('"'))>=0) {
            temp = temp.substring(0,index)+"`"+temp.substring(index+1);
        }
/*
        while((index=temp.indexOf("'",index+2))>=0) {
            temp = temp.substring(0,index)+"`"+temp.substring(index+2);
        }
*/
        return temp;
    }



  /**
   * <PRE>
   * Desc     : 변수가 한글이 포함되어 있는지 Check
   * </PRE>
   * @param   argStr : 문자열
   * @return  boolean
   */
  public static boolean isString(String argStr)
  {
    // 문자열의 길이와 문자열의 바이트배열의 길이를 비교해서 체크
    if (argStr.length() == argStr.getBytes().length)
      return false;
    else
      return true;
  }

  /**
   * <PRE>
   * Desc     : 특정문자 변환 Check
   * </PRE>
   * @param   str     : 바꾸려는 문자열을 가진 원본
   * @param   pattern : 찾을 문자열
   * @param   replace : 바꿔줄 문자열
   * @return  String
   */
  public static String replace(String str, String pattern, String replace)
  {
    int s = 0;
    int e = 0;
    StringBuffer result = new StringBuffer();

    while ((e = str.indexOf(pattern, s)) >= 0)
    {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e+pattern.length();
    }

    result.append(str.substring(s));
    return result.toString();
  }

  /**
   * <PRE>
   * Desc     : 특수 char(& , " ) 를 ( , , ' ) 로 변환
   * </PRE>
   * @param   str : 특수 char(& , " )
   * @return  String
   */
  public static String charReplace(String str)
  {
    str = str.replace('&',',');
    str = str.replace('"', ' ');
    return str;
  }

  /**
   * Desc     : 좌측버튼 URL
   */
  public static final String RIGHT_BTN_URL = "/common/images/button/btn_list_forward.gif";
  /**
   * Desc     : 우측버튼 URL
   */
  public static final String LEFT_BTN_URL  = "/common/images/button/btn_list_previous.gif";

  /**
   * <PRE>
   * Desc     : GET 방식의 게시판의 counter
   * </PRE>
   * @param   int    : current_page
   * @param   int    : total_page
   * @param   Stirng : callee_url
   * @return  myIndexList(true, 10, current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, callee_url, null) call
   */
  public static String myIndexList(int current_page, int total_page, String callee_url)
  {
    return myIndexList(true, 10, current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, callee_url, null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(1)
   * </PRE>
   * @param   int : current_page
   * @param   int : total_page
   * @return  myIndexList(false, 10,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null) call
   */
  public static String myIndexList(int current_page, int total_page)
  {
    return myIndexList(false, 10,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(2)
   * </PRE>
   * @param   int : list_limit
   * @param   int : current_page
   * @param   int : total_page
   * @return  myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null) call
   */
  public static String myIndexList(int list_limit, int current_page, int total_page)
  {
    return myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(3)
   * </PRE>
   * @param   int    : list_limit
   * @param   int    : current_page
   * @param   int    : total_page
   * @param   String : font_color
   * @return  myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", font_color) call
   */
  public static String myIndexList(int list_limit, int current_page, int total_page, String font_color)
  {
    return myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", font_color);
  }

  /**
   * <PRE>
   * Desc     : Navigation Index List
   * </PRE>
   * @param   method_type T/F : get/post
   * @param   list_limit      : setting number (displayed number)
   * @param   current_page
   * @param   total_page
   * @param   left_image_url
   * @param   right_image_url
   * @param   callee_url
   * @return  html로 변환
   */
  public static String myIndexList(boolean method_type, int list_limit, int current_page, int total_page,
                                   String left_image_url, String right_image_url, String callee_url, String font_color)
  {
    int startpage;
    int endpage;
    int curpage;
    StringBuffer returnList = new StringBuffer();

    if (list_limit    < 0)       list_limit      = 0;
    if (current_page  < 0)       current_page    = 0;
    if (total_page    < 0)       total_page      = 0;
    if (left_image_url  == null) left_image_url  = "";
    if (right_image_url == null) right_image_url = "";
    if (callee_url      == null) callee_url      = "";
    if (font_color      == null) font_color      = "";


    startpage = ((current_page - 1) / list_limit) * list_limit + 1;

    endpage = (((startpage - 1) +  list_limit) / list_limit) * list_limit;

    if (total_page <= endpage)
    {
      endpage = total_page;
    }

    /**
    if (current_page > list_limit){
        curpage = startpage - 1;
        if (method_type)
            returnList.append("<a href='"+callee_url+"?page="+curpage+"'>");
        else
            returnList.append("<a href='javascript:"+callee_url+"("+curpage+");'>");

        returnList.append("<img src='"+left_image_url+"' border='0' align='absmiddle'></a>");
        returnList.append("... ");
        returnList.append("\n");
    }
    **/

    curpage = startpage;
    while (curpage <= endpage)
    {
      if (curpage == current_page)
      {
        returnList.append("<u>"+current_page+"</u>&nbsp;\n");
      }
      else
      {
        if (method_type)
          returnList.append("<u><a href='"+callee_url+"?page="+curpage+"'>"+curpage+"</a></u>&nbsp;\n");
        else
          returnList.append("<u><a href='javascript:"+callee_url+"("+curpage+");'>"+curpage+"</a></u>&nbsp;\n");
      }
      curpage++;
    }

    /**
    if (total_page > endpage){
        returnList.append(" ...");
        if (method_type)
            returnList.append("<a href='"+callee_url+"?page="+curpage+"'>");
        else
            returnList.append("<a href='javascript:"+callee_url+"("+curpage+");'>");

        returnList.append("<img src='"+right_image_url+"' border='0' align='absmiddle'></a>");
        returnList.append("\n");
    }
    **/

    return returnList.toString();
  }

  /**
   * <PRE>
   * Desc     : String[] => Integer[]으로 변환
   * </PRE>
   * @param   strs : String[]
   * @return  Integer[]
   */
  public static Integer[] setStrToInteger(String[] strs)
  {
    if ( strs == null ) return null;
    Integer[] ints = new Integer[strs.length];
    for (int i=0 ; i< ints.length ; i++)
    {
      try
      {
        ints[i] = new Integer(strs[i]);
      } catch (NumberFormatException e) {
        ints[i] = new Integer(0);
      }
    }
    return ints;
  }

  /**
   * <PRE>
   * Desc     : 원하는 값의 존재 유무 (해당번째)
   * </PRE>
   * @param   strs : 해당문자열
   * @param   comp : 비교값
   * @return  int
   */
  public static int getArrayCompare(String strs, String comp)
  {
    return strs.indexOf(comp, 0);
  }

  /**
   * <PRE>
   * Desc     : 원하는 값의 존재 유무
   * </PRE>
   * @param   strs[] : 해당문자열
   * @param   comp   : 비교값
   * @return  boolean
   */
  public static boolean getArrayCompare(String[] strs, String comp)
  {
    if ( strs == null ) return false;

    for (int i=0 ; i< strs.length ; i++)
    {
      if (strs[i].equals(comp)) return true;
    }
    return false;
  }

  /**
   * <PRE>
   * Desc     : 원하는 위치값의 존재 유무
   * </PRE>
   * @param   strs[]   : 해당문자열
   * @param   position : 위치값
   * @param   comp     : 비교값
   * @return  boolean
   */
  public static boolean getArrayCompare(String[] strs, int position, String comp)
  {
    if ( strs == null ) return false;
    if ( strs.length < position ) return false;
    if ( strs[position].equals(comp) ) return true;

    return false;
  }

  /**
   * <PRE>
   * Desc     : 유효한 값의 갯수
   * </PRE>
   * @param   strs[] : 문자열
   * @return  int
   */
  public static int getArrayCount(String[] strs)
  {
    int count = 0;
    try
    {
      if ( strs == null ) return count;
      for (int i=0 ; i< strs.length ; i++)
      {
        if (!strs[i].equals("") && strs[i] != null)
          count++;
      }
    } catch (Exception e) {
    }

    return count;
  }

  /**
   * <PRE>
   * Desc     : String[] => Double[]으로 변환
   * </PRE>
   * @param   strs[]
   * @return  Double[]
   */
  public static Double[] setStrToDouble(String[] strs)
  {
    if ( strs == null ) return null;
    Double[] doubles = new Double[strs.length];

    for (int i=0 ; i< doubles.length ; i++)
    {
      try
      {
        doubles[i] = new Double(strs[i]);
      } catch (NumberFormatException e) {
        doubles[i] = new Double(0);
      }
    }
    return doubles;
  }

  /**
   * <PRE>
   * Desc     : String 으로 받은 인수를 size 1 씩 짤라서 지정된 size 의 배열에 저장
   * </PRE>
   * @param   Amt   : 문자열
   * @param   Count : size
   * @return  String[] : 문자열을 배열에 setting
   */
  public static String[] arrayAmtSetting(String Amt, int Count)
  {
    String [] AmtArray = new String[Count];
    int AmtLength = Amt.length();
    int compLength = Count - AmtLength;

    for (int i = 0 ; i < Count ; i++)
    {
      if (compLength > i)
      {
        AmtArray[i] = "";
      } else {
        AmtArray[i] = Amt.substring(i-compLength,i-compLength+1);
      }
    }
    return AmtArray;
  }


  /**
   * <PRE>
   * Desc     : KOREA 로 변경
   * </PRE>
   * @param   en 문자열
   * @return  String
   */
  public static String enToKo(String en)
  {
    String korean=null;
    try
    {
      korean = new String(en.getBytes("8859_1"),"KSC5601");
    } catch(Exception e) {
      //e.printStackTrace();
      return korean;
    }
    return korean;
  }

  /**
   * <PRE>
   * Desc     : DB에 Data를 저장할때
   * </PRE>
   * @param   ko : korea 문자열
   * @return  String
   */
  public static String koToEn(String ko)
  {
    String english=null;
    try
    {
      english = new String(ko.getBytes("KSC5601"),"8859_1");
    } catch(Exception e) {
      e.printStackTrace();
      //return english;
    }
    return english;
  }

    // get norest allot months
  	/*
    public static Vector getAllotMonthCal(String norest_allot_months){
        String convertStr = StringUtils.leftPad(norest_allot_months,36,"0");
        Vector vtRtn = new Vector();

        for(int i=0 ; i < 36 ; i++){
            if( convertStr.substring(i,i+1).equals("1") ){
                vtRtn.add(new Integer(i+1));
            }
        }
        return vtRtn;

        //String arrayVal[] = new String[36];
        //int cnt = 0;
        //for ( int i = 0 ; i < 36 ; i++ ){
        //    if(convertStr.substring(i,i+1).equals("1")){;
        //        arrayVal[cnt] = Integer.toString(i+1);
        //        cnt++;
        //    }
        //}
        //
        //String rtnArray[] = new String[cnt];
        //for( int j = 0 ; j < cnt; j++){
        //    rtnArray[j] = arrayVal[j];
        //}
        //return rtnArray;
    }
    */

    /**
     * 무이자 할부 개월 수 구하기 (숫자로 변환)
     */
    public static long getNorestAllotMonthsNumber(String norest_allot_months){
        long NorestAllotMonthsNumber = 0;

    	if(norest_allot_months != null && norest_allot_months.length() == 36){
        	for(int i=0; i<36; i++){
        		if( "1".equals(norest_allot_months.substring(i, i+1)) ){
        			NorestAllotMonthsNumber ++;
        		} else {
        			break;
        		}
        	}
        }
    	return NorestAllotMonthsNumber;
    }

    /**
	 *  주어진 스트링의 쿠키 값 리턴
	 */
    public static String getCookie(HttpServletRequest request, String name)
			throws ServletException, IOException
	{

		String value = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++)
			{
				if ( cookies[i].getName().equals(name) )
				{
					value = cookies[i].getValue();
					return value;
				}
			}
		}

		return null;
	}
    
    /**
	 *  주어진 스트링의 쿠키 값 리턴
	 */
    public static String getCookieNonValue(HttpServletRequest request, String name)
			throws ServletException, IOException
	{

		String value = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++)
			{
				if ( cookies[i].getName().equals(name) && !"".equals(cookies[i].getValue()) && cookies[i].getValue() != null )
				{					
					value = cookies[i].getValue();
					return value;
				}
			}
		}

		return null;
	}

	/**
	 * <PRE>
	 * Desc     : Null String을 "" String으로 바꿔준다.
	 * </PRE>
	 * @param   String  문자열
	 * @return  String  문자열 or ""
	 */
	public static String NVL(String str){
		if(str == null || str.length() <= 0)
			return "";
		else
			return str;
	}


	/**
	 * <PRE>
	 * Desc     : Null String을 replace String으로 바꿔준다.
	 * </PRE>
	 * @param   String  검사 문자열
	 * @param   String  바뀔 문자열
	 * @return  String  문자열
	 */
	public static String NVL(String str, String replace){
		if(str == null || str.length() <= 0)
			return replace;
		else
			return str;
	}

	/**
	 * <PRE>
	 * Desc     : Null String을 replace String으로 바꿔준다.
	 *            Null 인 경우만 check 해서 replace 해주도록 변경
	 * </PRE>
	 * @param   String  검사 문자열
	 * @param   String  바뀔 문자열
	 * @return  String  문자열
	 */
	public static String isNull(String str, String replace){
		if(str == null)
			return replace;
		else
			return str;
	}



   	public static String replaceOne(String str) {

		if ( str == null ) return null;

		StringBuffer buff = new StringBuffer();
		char charArray[] = str.toCharArray();
		for (int i = 0; i<charArray.length; i++) {
			buff.append(charArray[i]);
			if (charArray[i]==39) {
				buff.append("\'");
			}
		}
		str = new String(buff);
		return str;
	}

   	public static String replaceQue(String str) {

		if ( str == null ) return null;

		StringBuffer buff = new StringBuffer();
		char charArray[] = str.toCharArray();
		for (int i = 0; i<charArray.length; i++) {
			if (charArray[i] != 39 && charArray[i] != 34) {
				buff.append(charArray[i]);
			}
		}
		str = new String(buff);
		return str;
	}

     /**
     * 문자 하나가 알파벳인지 검사
     *
     * @param   str 검사 하고자 하는 문자
     * @return  알파벳인지의 여부에 따라 'true' or 'false'
     */
    public static boolean isAlpha(char c) {
        if ((c < 'a' || c > 'z') &&
            (c < 'A' || c > 'Z') &&
            c != '_' && c != ' ')
            return false;
        return true;
    }


    /**
     * 문자열이 알파벳인지 검사
     *
     * @param   str 검사 하고자 하는 문자열
     * @return  알파벳인지의 여부에 따라 'true' or 'false'
     */
    public static boolean isAlpha(String str) {
        if (str == null) return false;

        str = str.trim();
        int len = str.length();
        if (len == 0)
            return false;

        for (int i = 0; i < len; i++) {
            if (!isAlpha(str.charAt(i)))
                return false;
        }
        return true;
    }


    /**
     * 상품 코드를 Barcode로 변환
     *
     * @param   str1 상품코드
     * @param   str2 단품코드
     * @return  Barcode
     */
    public static String getBarcode(String str1, String str2) {
        //= CODE 39
        String[] code = {   "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
    						"B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L",
    						"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
    						"X", "Y", "Z", "-", ",", " ", "$", "/", "+", "%"};
        String barcode = (str1 + str2).toUpperCase();
        int lencode = barcode.length();
        int sumcode = 0;
        int k = 0;
        String cutcode = "";

        for(int i=0; i<lencode; i++){
        	cutcode = barcode.substring(i, i+1);
        	k=0;
        	for(int j=0; j<43; j++){
        		//= CODE39에 대응되는 값의 위치를 찾는다.
        		k=j;
        		if( cutcode.equals(code[j]) ) break;
        	}
        	//= 대응되는 위치값을 더한다.
        	sumcode = sumcode + k;
        }

        //= BarCode를 생성한다.
        barcode = barcode + code[(sumcode % 43)];

        return barcode;
    }


    /**
     * ArrayList를 Message 배열로 변환
     *
     * @param   ArrayList
     * @return  Message 배열
     */
    /*
    public static ParamMap[] getHashMapArr(ArrayList arrList){
    	if(arrList == null || arrList.size() == 0) return null;
    	Collection collection = new ArrayList();
    	Iterator i=arrList.iterator();
    	ParamMap msg = null;
        while(i.hasNext()){
        	msg = new ParamMap();
        	msg.setParamMap((HashMap)i.next());
        	collection.add( msg );
        }
        return (ParamMap[])collection.toArray(new ParamMap[0]);
    }
    */

	public static String toXml(List<?> list){
		StringBuffer xml = new StringBuffer();
		for(int i=0;i<list.size();i++){
			xml.append(((String)list.get(i)).replaceAll("\\<(\\?)[xml](\\w+)(.*)(\\?)\\>", ""));
			// 특수문자는 앞에 \\
			// [xml] xml 세글자
			// [a-z] a부터 z까지 한개의 문자
			// [a-z]+ 한개 이상의 문자
			// [a-z]* 0개 이상의 문자
			// \\w 공백 문자 한개 이상
			// .* 모든 문자 0개 이상
			// () 그룹
		}
		return xml.toString();
	}

	/**
	 * form에서 전송된 Request Parameter를 HashMap으로 변환
	 *
	 * @param HttpServletRequest
	 * @return HashMap<String, Object>
	 */
	public static HashMap<String, Object> requestToHashMap(HttpServletRequest request) {
		HashMap<String, Object> commandMap = new HashMap<String, Object>();
		Enumeration<?> enumeration = request.getParameterNames();

		while(enumeration.hasMoreElements()){
			String key = (String) enumeration.nextElement();
			String[] values = request.getParameterValues(key);
			
			if(values!=null){
				commandMap.put(key, (values.length > 1) ? values:values[0] );
			}
		}

		return commandMap;
	}
	
	/**
	 * Map에 있는 key값을 String[]로 변환
	 *
	 * @param Map map
	 * @param String key
	 * @return String[]
	 */
	public static String[] mapToParamArray(Map<?,?> map, String key) {
		if(map == null) return null;
		
		String[] arr = null;
		if (map.containsKey(key)) {
			if (map.get(key) instanceof String) {
				arr = new String[1];
				arr[0] = (String) map.get(key);
			} else if (map.get(key) instanceof String[]) {
				arr = (String[]) map.get(key);
			} else {
				arr = new String[0];
			}
		} else {
			arr = new String[0];
		}
		return arr;
	}

	/**
	 * 접속서버와 컨테이너 정보 출력
	 *
	 * @return String
	 */
	public static String getMachineName() {
		return System.getProperty("hnsp.machine.name");
	}
	
	/**
	 * String 을 byte 길이 만큼 자르기
	 * @param str
	 * @param byteLength
	 * @return
	 */
	public static String subStringBytes(String str, int byteLength) {
    	int length = str.length();
    	int retLength = 0;
    	int tempSize = 0;
    	int asc;
    	for (int i = 1; i <= length; i++) {
    		asc = (int) str.charAt(i - 1);
    		if (asc > 127) {
    			if (byteLength >= tempSize + 2) {
    				tempSize += 2;
    				retLength++;
    			} else {
    				return str.substring(0, retLength);
    			}
    		} else {
    			if (byteLength > tempSize) {
    				tempSize++;
    				retLength++;
    				}
    			}
    		}

    	return str.substring(0, retLength);
    }

    /**
     * Deflater byte[] 압축
     * @param bytesToCompress
     * @return
     * @throws IOException
     */
    public static byte[] compress(byte[] bytesToCompress) throws IOException {
        // Compressor with highest level of compression.
        Deflater compressor = new Deflater(Deflater.BEST_COMPRESSION);
        compressor.setInput(bytesToCompress); // Give the compressor the data to compress.
        compressor.finish();

        // Create an expandable byte array to hold the compressed data.
        // It is not necessary that the compressed data will be smaller than
        // the uncompressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(bytesToCompress.length);

        // Compress the data
        byte[] buf = new byte[bytesToCompress.length + 100];
        while (!compressor.finished()){
            bos.write(buf, 0, compressor.deflate(buf));
        }

        bos.close();

        // Get the compressed data
        return bos.toByteArray();
    }

    /**
     * Deflater byte[] 압축  풀기
     * @param compressedBytes
     * @return
     * @throws IOException
     * @throws DataFormatException
     */
    public static byte[] decompress(byte[] compressedBytes) throws IOException, DataFormatException {
        // Initialize decompressor.
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedBytes);  // Give the decompressor the data to decompress.
        decompressor.finished();

        // Create an expandable byte array to hold the decompressed data.
        // It is not necessary that the decompressed data will be larger than
        // the compressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedBytes.length);

        // Decompress the data
        byte[] buf = new byte[compressedBytes.length + 100];
        while (!decompressor.finished()){
            bos.write(buf, 0, decompressor.inflate(buf));
        }

        bos.close();

        // Get the decompressed data.
        return bos.toByteArray();
    }   
    
	/**
	 * WAS SERVER HOST NAME 가져오기
	 * 
	 * @return
	 */
	public static String getServerHostName(){
		String hostname = "";
		try {
			hostname = java.net.InetAddress.getLocalHost().getHostName();
			return hostname;
		} catch (UnknownHostException e) {
			return "";
		}
	}
	
	/**
	 * 최근본상품 map list 로 만들기
	 * 
	 * @param mGoodsCookie
	 * @return List
	 */
	public static List<Map<String, Object>> getRecentlyGoodsList(HttpServletRequest request){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			String mGoodsCookie = "";
			if(ComUtil.getCookie(request,"mGoodsCookie")!= null){
				mGoodsCookie = URLDecoder.decode(ComUtil.getCookie(request,"mGoodsCookie"), "UTF-8");
			}
			
			if(mGoodsCookie == null || mGoodsCookie.length() == 0){
				return list;
			}
			
			String[] str_array = split(mGoodsCookie, "^");
			
			for(int i = 0; i < str_array.length; i++){
				String[] goods_array = split(str_array[i], "$$");
				Map<String, Object> map = new HashMap<String, Object>();
				
				if(goods_array.length > 4){
					map.put("goodsCode", goods_array[0]);
					map.put("goodsName", goods_array[1]);
					map.put("salePrice", goods_array[2]);
					map.put("imageUrl", goods_array[3]);
					map.put("imageS", goods_array[4]);
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	/**
	 * <PRE>
	 * Desc : Flex TextArea 에서 저장한 String 을 Html 형식으로 변환 (\r -> <br>)
	 * </PRE>
	 * @param comment : String
	 * @return String
	 */
	public static String flexConvertHtmlBr(String comment)
	{
		int length = comment.length();
		StringBuffer buffer = new StringBuffer();
		
		if (comment.equals(null))
		{
			buffer.append("");
			return buffer.toString();
		}
		for (int i = 0; i < length; ++i)
		{
			String comp = comment.substring(i, i+1);
			if ("\r".compareTo(comp) == 0)
			{
				buffer.append("<BR>\r");
			}
			buffer.append(comp);
		}
		return buffer.toString();
	}
	
	public static String getBannerLinkUrl(String url, String context_path){
		if(url == null || url.length() == 0) return "";
		if(url.indexOf("http://") > -1){
			return url;
		}else{
			return context_path+url;
		}
	}
	
	public static String getUrlParam(String url, String param){
		
		try {
			if (url == null || url.trim().length() == 0 || url.indexOf("?") == -1) { return ""; }
			String query = url.substring(url.indexOf("?")+1, url.length());
			String[] q = query.split("&");
			
			for(int i=0 ; i<q.length; i++){
				String key = q[i].substring(0, q[i].indexOf("="));
				String value = q[i].substring(q[i].indexOf("=")+1, q[i].length());
				value = URLDecoder.decode(value, "UTF-8");
				if(param.equals(key)){
					return value;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return "";
	}
	
	/**
	 * 리스트 형식 만들어 주기
	 * @param list
	 * @param obj
	 * @return
	 */
	public static List<Object> addOptionList(List<Object> list, Object obj){
		try {
			if(list == null ||list.size() == 0){
				list = new ArrayList<Object>();
			}
			list.add(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	/**
	 * get 웹,앱 접속 FLAG
	 * @param request
	 * @return Integer (-1:분석불가, -2:알수없음, 10:iOS웹, 11:iOS앱, 20:안드로이드웹, 21:안드로이드앱)
	 */
	public static int getUserAgentFlag(HttpServletRequest request) {
		int resultCode = -1;
		String agent = request.getHeader("User-Agent");
		
		if (agent != null) {
			agent = agent.toLowerCase();
			
			if (agent.indexOf("iphone") > -1 || agent.indexOf("ipod") > -1 || agent.indexOf("ipad") > -1) {
				if (agent.indexOf("hnsapp") > -1) {
					resultCode = 11;
				}
				else {
					resultCode = 10;
				}
			}
			else if (agent.indexOf("android") > -1) {
				if (agent.indexOf("hnsapp") > -1) {
					resultCode = 21;
				}
				else {
					resultCode = 20;
				}
			}
			else {
				resultCode = -2;
			}
		}
		
		return resultCode;
	}
	
	/**
	 * 문자열 내 문자열 삽입
	 * @param orgString (대상 문자열)
	 * @param insertString (삽입 문자열)
	 * @param gap (반복적으로 삽입 문자열 넣을 문자열 길이)
	 * @param insertCount (삽입 문자열 넣는 수)
	 * @param startLine (삽입 문자열 넣는 방향. left, right)
	 * @return String
	 * 
	 * ex.
	 * addStringByPattern("123123123123", "-", 3, 1, "left") -> "123-123123123"
	 * addStringByPattern("123123123123", "-", 3, 2, "right") -> "123123-123-123"
	 */
	public static String addStringByPattern(String orgString, String insertString, int gap, int insertCount, String startLine) {
		StringBuffer sb = new StringBuffer();
		
		int strLength = orgString.length();
		int i = 0;
		
		if (startLine == null || startLine.toLowerCase().equals("left")) {
			for (i=0; i <= strLength;) {
				if (insertCount == 0 || (i+gap >= strLength)) { break; }
				sb.append(orgString.substring(i, (i+gap)));
				sb.append(insertString);
				insertCount --;
				i += gap;
			}
			
			if (i < strLength) { sb.append(orgString.substring(i, orgString.length())); }
		}
		else {
			for (i=strLength; i > 0;) {
				if (insertCount == 0 || (i-gap <= 0)) { break; }
				sb.insert(0, orgString.substring((i-gap), i));
				sb.insert(0, insertString);
				insertCount --;
				i -= gap;
			}
			
			if (i > 0) { sb.insert(0, orgString.substring(0, i)); }
		}
		
		return sb.toString();
	}
	
	/**
	* 숫자형태의 가격을 format 형식의 문자열로 리턴
	* <br><br>priceNumberToString(12345, "{만}만 {천}천 {백}백 {십}십 {원}원") -> "1만 2천 3백 4십 5원"
	* <br>priceNumberToString(36000, "{만}만 {천}천 {백}백 {십}십 {원}원") -> "3만 6천 0백 0십 0원"
	* <br>priceNumberToString(12345, null) -> "1만 2천 3백 4십 5원"
	* <br>priceNumberToString(36000, null) -> "3만 6천원"
	* <br>priceNumberToString(36000, "") -> "3만 6천원"
	* @param price
	* @param format ({만}, {천}, {백}, {십}, {원})
	* @return String
	*/
	public static String priceNumberToString(double price, String format) {
		StringBuffer returnString = new StringBuffer();
		int tenThousand = 0, thousand = 0, hundred = 0, ten = 0, won = 0;
	
		tenThousand = ((Double) (price / 10000)).intValue();
		thousand = ((Double)((price % 10000) / 1000)).intValue();
		hundred = ((Double)((price % 1000) / 100)).intValue();
		ten = ((Double)((price % 100) / 10)).intValue();
		won = ((Double)(price % 10)).intValue();
	
		if (format != null && !"".equals(format.trim())) {
			returnString.append(format.replace("{만}", String.valueOf(tenThousand))
			.replace("{천}", String.valueOf(thousand))
			.replace("{백}", String.valueOf(hundred))
			.replace("{십}", String.valueOf(ten))
			.replace("{원}", String.valueOf(won)));
		}
		else {
			if (tenThousand != 0) { returnString.append(tenThousand + "만"); }
			if (thousand != 0) { returnString.append(" " + thousand + "천"); }
			if (hundred != 0) { returnString.append(" " + hundred + "백"); }
			if (ten != 0) { returnString.append(" " + ten + "십"); }
			if (won != 0) { returnString.append(" " + won + "원"); }
			else { returnString.append("원"); }
		}
	
		return returnString.toString().trim();
	}
}