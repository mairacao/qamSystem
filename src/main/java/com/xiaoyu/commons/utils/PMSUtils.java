package com.xiaoyu.commons.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SuppressWarnings("rawtypes")
public class PMSUtils {
	
	
	/**
	 * 返回空字符串
	 * 
	 * @param str
	 * @return
	 * 
	 */
	public static String isNull(Object str) {
		if (isEmpty(String.valueOf(str))) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return 空 true 非空 false
	 */
	public static boolean isEmpty(Object obj) {
		if(null == obj){
			return true;
		}
		
		if(obj instanceof String){
			if (StringUtils.isNotBlank((CharSequence) obj) && !"null".equals(obj) && !"undefined".equals(obj)) {
				return false;
			}
		}else if(obj instanceof Map){
			if(((Map) obj).size() > 0){
				return false;
			}
		}else if(obj instanceof ArrayList){
			if(((ArrayList) obj).size() > 0){
				return false;
			}
		}else if(obj instanceof Long){
			return false;
		}else if(obj instanceof Integer){
			return false;
		}else if(obj instanceof Date){
			return false;
		}
		return true;
	}
	
	/**
	 * 【正则】判定字符串是否是浮点类型字符串
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {  
	    if (null == str || "".equals(str)) {  
	        return false;  
	    }  
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");  
	    return pattern.matcher(str).matches();  
	}  
	
	/**
	 * 将字符串 转换成double 为空 转换成 0.0
	 * @param obj
	 * @return
	 */
	public static Double parseDouble(Object obj){
		if (isEmpty(String.valueOf(obj))) {
			return 0.0;
		} else {
			return Double.parseDouble(String.valueOf(obj));
		}
	}
	
	/**
	 * 获得一个UUID【生成20字节的UUID字符串,不含'-'字符. TODO 未追加jvmId,ip和mac信息,需要完善】
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
		+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
	
	/**
	 * 生成随机字符串(包括 字母、数字)
	 * @param length 字符串长度
	 * @return
	 */
	public static String getRandomStr(int length) {
		String str = "";
		String source = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String[] s = source.split(",");
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * 35);
			str += s[rand];
		}
		return str;
	}
	
	/**
	 * 生成随机字符串(纯数字) 
	 * @param length	字符串长度
	 * @return
	 */
	public static String getRandomCode(int length) {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}

	/**
	 * 去除字符串开头和结尾的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str.charAt(0) == ' ') {
			str = str.substring(1, str.length());
		}
		if (str.charAt(str.length() - 1) == ' ') {
			str = str.substring(0, str.length() - 1);
		}
		if (str.charAt(0) == ' ' || str.charAt(str.length() - 1) == ' ') {
			str = PMSUtils.trim(str);
		}

		return str;
	}

	

	/**
	 * 数字型字符串 流水号 自增
	 * @param liuShuiHao  例如 传入 '0001' 输出: '0002'
	 * @return
	 */
	public static String getSequenceNum(String liuShuiHao){
	    Integer intHao = Integer.parseInt(liuShuiHao);
	    intHao++;
	    String strHao = intHao.toString();
	    while (strHao.length() < liuShuiHao.length()){
	    	strHao = "0" + strHao;
	    }
	    return strHao;
	}
	
	/**
	 * 得到当前session（适合web项目）
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		HttpSession session = getCurRequest().getSession();
		return session;
	}
	
	/**
	 * (获得当前的request) （适合web项目）
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getCurRequest(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if(requestAttributes != null && requestAttributes instanceof ServletRequestAttributes){
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
			return servletRequestAttributes.getRequest();
		}
		return null;
	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request HttpServletRequest
	 * @return
	 */
	public static Map<String, String> getRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	/**
	 * 正则匹配字符串内容  以 'prefix'为开始， 'suffix'为结尾 的内容
	 	<li> (?=exp) 	匹配exp前面的位置 
		<li> (?<=exp) 	匹配exp后面的位置 
		<li> (?!exp) 	匹配后面跟的不是exp的位置 
		<li> (?< !exp) 	匹配前面不是exp的位置
		<li> 例如： '你好${userName},你的工号是${userNo}！！' --> [userName, userNo]
	 * @param prefix	要截取的字符串前缀
	 * @param suffix	截取字符串的后缀
	 * @param str		待截取字符串
	 * @return
	 * 
	 */
	public static List<String> getSubstringByRegular(String prefix, String suffix, String str){
		char[] pre = prefix.toCharArray();
		char[] suff = suffix.toCharArray();
		String ppre = null;
		for (char c : pre) {
			if(ppre == null){
				ppre = "";
			}
			ppre += "\\"+c;
		}
		String ssuff = null;
		for (char c : suff) {
			if(ssuff == null){
				ssuff = "";
			}
			ssuff += "\\"+c;
		}
		
        List<String> ls=new ArrayList<String>();
        Pattern pattern = Pattern.compile("(?<="+ppre+")(.+?)(?="+ssuff+")");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
            ls.add(matcher.group());
        return ls;
    }
	
	/**
	 * 分页截取数据对象
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static List subListPage(List list, int pageNum, int pageSize){
		int fromIndex = (pageNum - 1) * pageSize;
        if (fromIndex >= list.size()) {
            return Collections.emptyList();
        }
 
        int toIndex = pageNum * pageSize;
        if (toIndex >= list.size()) {
            toIndex = list.size();
        }
        return list.subList(fromIndex, toIndex);
	}

	
	
	private static Pattern linePattern = Pattern.compile("_(\\w)");  
    /**下划线转驼峰*/  
    public static String lineToHump(String str){  
        str = str.toLowerCase();  
        Matcher matcher = linePattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }  
    private static Pattern humpPattern = Pattern.compile("[A-Z]");  
    /**驼峰转下划线,效率比上面高*/  
    public static String humpToLine(String str){  
        Matcher matcher = humpPattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }  
	
	public static void main(String[] args) {
		System.out.println( getSubstringByRegular("${", "}","你好${userName},你的工号是${userNo}"));
		System.out.println( getSubstringByRegular("#{", "}","你好#{userName},你的工号是#{userNo}"));
		
		System.out.println(humpToLine("typeId"));
		/*Map<String, Long> m = new TreeMap<String, Long>();
		for (long i = 10000L; i < 99999L; i++) {
			String code = String.valueOf(i);
			String key = String.valueOf(code.hashCode() % 20);
			if (key.length() >= 2)
				key = key.substring(0, 2);
			else {
				while (key.length() < 2) {
					key = new StringBuffer("0").append(key).toString();
				}
			}
			if (m.containsKey(key)) {
				long value = m.get(key);
				m.put(key, value + 1);
			} else {
				m.put(key, 1L);
			}
			System.out.println(key);
		}
		System.out.println(m.toString());*/
	}
}

