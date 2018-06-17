package com.momukji.mfront.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.momukji.common.util.ComUtil;
import com.momukji.mfront.domain.CustomerVO;
import com.momukji.mfront.domain.MenuVO;
import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.service.admin.AdminService;
import com.momukji.mfront.service.customer.CustomerService;
import com.momukji.mfront.service.menu.MenuService;
import com.momukji.mfront.service.restaurant.RestaurantService;

@RequestMapping(value="/admin")
@Controller
public class AdminController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="adminService")
    private AdminService adminService;
	
	@Resource(name="customerService")
    private CustomerService customerService;
	
	@Resource(name="restaurantService")
    private RestaurantService restaurantService;
	
	@Resource(name="menuService")
    private MenuService menuService;
	
	/**
	 * 어드민로그인화면호출
	 * @param request
	 * @param response
	 * @return json
	 * @throws Exception
	 */
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,  HttpServletResponse response, HttpSession session,
			@RequestParam(value="join_yn", required=false, defaultValue="")String join_yn) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String joinUrl = "/admin/login";
		
		if(join_yn.equals(""))
			join_yn="0";
		
		switch (Integer.parseInt(join_yn)) {
		// 회원가입후 로그인
		case 1:
			// 본인인증, 아이핀 인증을 위한 인증 데이터 생성
			
			session.setAttribute("join_yn", join_yn);			
			mv.addObject("join_yn", join_yn);
			
			break;
		
		default://일반로그인
			
			break;
		}
		
		mv.setViewName(joinUrl);
		
		return mv;
	}
	
	/**
	 * 회원가입 - 저장
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/joinsave")
	public ModelAndView joinsave(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mv = new ModelAndView("redirect:/customer/login?join_yn=1");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap = ComUtil.requestToHashMap(request);
		
		CustomerVO 	customer 	= new CustomerVO();
		/* 이메일 중복 체크 후 주석 제거
		if (session.getAttribute("joinTicket2") == null || session.getAttribute("duplicationEmailCheck") == null) {
			//delAllCertSession(session);세션삭제
			mv.setViewName("/customer/join/1");
			return mv;
		}
		*/
		//System.out.println("이메일:"+hashMap.get("email_send_agree").toString());
		
		customer.setMc_Email(hashMap.get("mc_email").toString().trim());
		customer.setMc_Password(hashMap.get("mc_password").toString().trim());
		customer.setMc_Name(hashMap.get("mc_name").toString().trim());
		customer.setMc_Phone(hashMap.get("mc_phone").toString().trim());
		customer.setMc_Birth(hashMap.get("mc_birth").toString().trim());
		//customer.setMc_Sex(hashMap.get("mc_sex").toString().trim().charAt(0));
		if(hashMap.get("email_send_agree")!=null)
			customer.setMc_MailingYn('1');
		else
			customer.setMc_MailingYn('0');
		
		customer.setMc_Status('0');
		customer.setMc_LoginAPIType('0');
		//customer.setMc_LoginAPIType(has.hMap.get("mc_loginapitype").toString().trim().charAt(0));
		
		if (log.isDebugEnabled()) {
			log.debug("=================== CERT INFO START (joinSaveController) ===================");
			log.debug("Email	 	: " + customer.getMc_Email());			
			log.debug("Name	 		: " + customer.getMc_Name());
			log.debug("LoginAPIType	: " + customer.getMc_LoginAPIType());
			log.debug("Status    	: " + customer.getMc_Status());			
			log.debug("=================== CERT INFO E N D (joinSaveController) ===================");
		}
		
		int custSaveResult = customerService.joinSave(customer);
		
		if (custSaveResult != 1) { mv.setViewName("/customer/join"); return mv; }
		
		session.removeAttribute("joinSaveStart");
		
		try {
			log.debug("=================== 가입 완료 ===================");
			
			log.debug("=================== 강제 로그아웃 ===================");
			
			log.debug("=================== 세션 설정 ===================");
			
			session.removeAttribute("joinTicket2");
			session.removeAttribute("duplicationMemberCheck");
			
		} catch (Exception e) {
			log.error("MomukjiCustomerController joinsave() : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * 로그인처리기
	 * @param request
	 * @param response
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/logincheck")
	@ResponseBody
	public HashMap<String, Object> logincheck(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="prev_url", required=false, defaultValue="")String prev_url) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		//로그인 결과값 0 : 실패(아이디없음) 1 : 실패(비밀번호틀림) 2: 성공
		responseJSON.put("result", 0);
		
		request.setCharacterEncoding("utf-8");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap = ComUtil.requestToHashMap(request);
		
		try {
			
			//아이디(이메일) 조회
			List<CustomerVO> cus = customerService.selectCustomer(hashMap.get("mc_email").toString().trim());
			
			
			if(cus.size()==0){
				System.out.println("로그인정보 없음");
				return responseJSON;
			}
			
			System.out.println(cus.size());

			if(!cus.get(0).getMc_Password().equals(hashMap.get("mc_password").toString().trim())){
				System.out.println("비밀번호 불일치");
				responseJSON.put("result", 1);
				return responseJSON;
			}
			
			//세션저장
			session.setAttribute("customerVO", cus.get(0));
			//session.setAttribute("prev_url", prev_url);
			
			responseJSON.put("result", 2);
			responseJSON.put("prev_url", prev_url);
			
		} catch (Exception e) {
			log.error("MomukjiCustomerController logincheck() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
	}
	
	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 식당리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param searchoption
	 * @param searchword
	 * @param page
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/restaurantlist")
	public ModelAndView restaurantList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="searchoption", required=false, defaultValue="")String searchoption,
			@RequestParam(value="searchword", required=false, defaultValue="")String searchword,
			@RequestParam(value="page", required=false, defaultValue="")String page) throws Exception{
		
		ModelAndView mv = new ModelAndView("/admin/restaurantlist");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
	    if(page==""||page==null)
	    	page = "0";
	    
	    int limit = 10;
	    
		try {
			
			if(searchword.trim()==""){
				return mv;
			}
			
			//검색 키워드
			hashmap.put(searchoption, searchword);
			
			//페이징 처리
		    hashmap.put("limit", limit);
			hashmap.put("page", limit * Integer.parseInt(page));
			
			//모먹지나우 식당 조회
			List<RestaurantVO> restaurantList = restaurantService.selectRestaurantList(hashmap);
			
			mv.addObject("restaurantList", restaurantList);
			mv.addObject("totalCount", restaurantList.get(0).getRowNum());
			mv.addObject("searchOption", searchoption);
			mv.addObject("searchWord", searchword);
			
			//System.out.println(restaurantList.get(0).getRowNum()+"리스트사이즈:"+restaurantList.size()+"/searchword:"+searchword);
			
		} catch (Exception e) {
			log.error("AdminController restaurantList() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 식당 정보 입력 양식
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping(value="/restaurantform")
	public ModelAndView restaurantform(HttpServletRequest request,  HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String url = "/admin/restaurantform";
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 식당 정보
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping(value="/viewrestaurant")
	public ModelAndView viewRestaurant(HttpServletRequest request,  HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_no", required=false, defaultValue="")String mr_no) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String url = "/admin/viewrestaurant";
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		hashmap.put("mr_no", mr_no);
		
		//식당 조회
		List<RestaurantVO> res = restaurantService.selectRestaurant(hashmap);
		
		if(res.size()>0){
		
			url = "/admin/viewrestaurant";
			
			mv.addObject("res", res.get(0));
			
		}
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 식당 저장
	 * @param request
	 * @param response
	 * @param session
	 * @param formdata
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/insertrestaurant")
	public ModelAndView insertRestaurant(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		
		ModelAndView mv = new ModelAndView();
		
		String url = "viewrestaurant?mr_no=";
		
		//파일이 저장될 path 설정
		String root_path = "/usr/local/server/apache-tomcat-7.0.70/webapps/";
		String path = "/upload/images";
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			HashMap<String, Object> formdata = ComUtil.requestToHashMap(request);
			
			RestaurantVO res = new RestaurantVO();
			
			res.setMr_Id(formdata.get("mr_Id").toString().trim());
			res.setMr_Name(formdata.get("mr_Name").toString());
			res.setMr_Position_Lat(formdata.get("mr_Position_Lat").toString().trim());
			res.setMr_Position_Lng(formdata.get("mr_Position_Lng").toString().trim());
			res.setMr_Address(formdata.get("mr_Address").toString().trim());
			res.setMr_Phone(formdata.get("mr_Phone").toString().trim());
			res.setMr_PartnerYn(formdata.get("mr_PartnerYn").toString().trim());
			res.setMr_TvYn(formdata.get("mr_TvYn").toString().trim());
			res.setMr_DeliveryYn(formdata.get("mr_DeliveryYn").toString().trim());
			res.setMr_DeliveryArea(formdata.get("mr_DeliveryArea").toString().trim());
			res.setMr_ParkingYn(formdata.get("mr_ParkingYn").toString().trim());
			res.setMr_Seat(formdata.get("mr_Seat").toString().trim());
			res.setMr_OpenTime(formdata.get("mr_OpenTime").toString().trim());
			res.setMr_ClosingDay(formdata.get("mr_ClosingDay").toString().trim());
			res.setMr_Introduce(formdata.get("mr_Introduce").toString().trim());
			res.setMfc_No(Integer.parseInt(formdata.get("mfc_No").toString()));
			res.setMlc1_No(Integer.parseInt(formdata.get("mlc1_No").toString()));
			res.setMlc2_No(Integer.parseInt(formdata.get("mlc2_No").toString()));
			
			if(0 < restaurantService.insertRestaurant(res)){
				
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				
				url += res.getMr_No();
				
				//이미지 파일 업로드 처리
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			    
				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			    
				MultipartFile mFile = null;
				
				hashmap.put("mr_No", res.getMr_No());
				hashmap.put("mlc2_No", res.getMlc2_No());
				
				//지역카테고리 카운팅
				if(0<restaurantService.updateLocalCategoryCount(hashmap)){
					
					log.debug("------------- MLC2 COUNTUP SUCCESS -------------");
		            
				}
				
				//업장 뷰 카운팅 로우 생성
				if(0<restaurantService.insertResVisitCount(hashmap)){
					
					log.debug("---------- MRVC COUNTER MAKING SUCCESS ----------");
					
				}
				
			    
				while(iterator.hasNext()){
					
					mFile = multipartHttpServletRequest.getFile(iterator.next());
			        
					String origName = new String(mFile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지
					
			        if(mFile.isEmpty() == false){
			        	
			            log.debug("------------- file start -------------");
			            log.debug("name : "+mFile.getName());
			            log.debug("filename : "+mFile.getOriginalFilename());
			            log.debug("size : "+mFile.getSize());
			            log.debug("-------------- file end --------------\n");
			            
						path = path+"/restaurant/"+res.getMr_No();
						
						// 디레토리가 없다면 생성
						File dir = new File(root_path + path);
						if (!dir.isDirectory()) {
							System.out.println("isDirectory:"+dir.isDirectory());
							System.out.println("mkdirs:"+dir.mkdirs());
						}
						
						// 파일 명 변경
						String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
						
						String saveFileName = res.getMr_No()+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ext;
						
						// 설정한 path에 파일저장
						File serverFile = new File(root_path + path + File.separator + saveFileName);
						mFile.transferTo(serverFile);
					
						hashmap.put("mri_Img_Url", path+File.separator);
						hashmap.put("mri_Img_Name", saveFileName);
						hashmap.put("mri_Img_OrgName", origName);
						
						// 이미지 경로 DB 저장
						restaurantService.insertResImg(hashmap);
			            
			        }
			    }
				
				return new ModelAndView("redirect:/admin/menulist?searchoption=mr_no&searchword="+res.getMr_No());
				
			}
			
		} catch (Exception e) {
			log.error("AdminController insertRestaurant() : " + e.getMessage());
		}
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 식당 수정
	 * @param request
	 * @param response
	 * @param session
	 * @param formdata
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/updaterestaurant")
	public ModelAndView updateRestaurant(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		
		String url = "viewrestaurant?mr_no=";
		
		ModelAndView mv = new ModelAndView();
		
		//파일이 저장될 path 설정
		String root_path = "/usr/local/server/apache-tomcat-7.0.70/webapps";
		String path = "/upload/images";
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			HashMap<String, Object> formdata = ComUtil.requestToHashMap(request);
			
			int mr_no = Integer.parseInt(formdata.get("mr_No").toString());
			
			if(0 < restaurantService.updateRestaurant(formdata)){
				
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				
				//이미지 파일 업로드 처리
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			    
				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			    
				MultipartFile mFile = null;
			    
				while(iterator.hasNext()){
					
					mFile = multipartHttpServletRequest.getFile(iterator.next());
			        
					String origName = new String(mFile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지
					
			        if(mFile.isEmpty() == false){
			        	
			            log.debug("------------- file start -------------");
			            log.debug("name : "+mFile.getName());
			            log.debug("filename : "+mFile.getOriginalFilename());
			            log.debug("size : "+mFile.getSize());
			            log.debug("-------------- file end --------------\n");
			            
						path = path+"/restaurant/"+mr_no;
						
						// 디레토리가 없다면 생성
						File dir = new File(root_path + path);
						if (!dir.isDirectory()) {
							dir.mkdirs();
						}
						
						// 파일 명 변경
						String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
						
						String saveFileName = mr_no+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ext;
						
						// 설정한 path에 파일저장
						File serverFile = new File(root_path + path + File.separator + saveFileName);
						mFile.transferTo(serverFile);
					
						hashmap.put("mr_No", mr_no);
						hashmap.put("mri_Img_Url", path+File.separator);
						hashmap.put("mri_Img_Name", saveFileName);
						hashmap.put("mri_Img_OrgName", origName);
						
						// 이미지 경로 DB 저장
						if(0==restaurantService.updateResImg(hashmap)){
							if(0<restaurantService.insertResImg(hashmap)){
								url += mr_no;
								mv.setViewName(url);
							}
						}
			            
			        }
			    }
				
				return new ModelAndView("redirect:/admin/viewrestaurant?mr_no="+mr_no);
				
			}
			
		} catch (Exception e) {
			log.error("AdminController insertRestaurant() : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * 메뉴리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param searchoption
	 * @param searchword
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/menulist")
	public ModelAndView menuList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="searchoption", required=false, defaultValue="")String searchoption,
			@RequestParam(value="searchword", required=false, defaultValue="")String searchword) throws Exception{
		
		ModelAndView mv = new ModelAndView("/admin/menulist");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
		try {
			
			if(searchword.trim()==""){
				return mv;
			}
			
			//검색 키워드
			hashmap.put(searchoption, searchword);
			
			//모먹지나우 식당 조회
			List<MenuVO> menuList = menuService.selectMomukjiEntireMenu(hashmap);
			
			mv.addObject("menuList", menuList);
			mv.addObject("totalCount", menuList.size());
			mv.addObject("searchOption", searchoption);
			mv.addObject("searchWord", searchword);
			
			//System.out.println("리스트사이즈:"+menuList.size()+"/searchword:"+searchword);
			
		} catch (Exception e) {
			log.error("AdminController menuList() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 식당 정보 양식
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping(value="/menuform")
	public ModelAndView menuform(HttpServletRequest request,  HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_no", required=false, defaultValue="")String mr_no) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String url = "/admin/menuform";
		
		mv.addObject("mr_no", mr_no);
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 메뉴 저장
	 * @param request
	 * @param response
	 * @param session
	 * @param formdata
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/insertmenu")
	@ResponseBody
	public ModelAndView insertMenu(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		
		ModelAndView mv = new ModelAndView();
		
		String url = "/admin/menulist?searchoption=mr_no&searchword=";
		
		//파일이 저장될 path 설정
		String root_path = "/usr/local/server/apache-tomcat-7.0.70/webapps";
		String path = "/upload/images";
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			HashMap<String, Object> formdata = ComUtil.requestToHashMap(request);
			
			url += formdata.get("mr_No").toString().trim();
			
			MenuVO menu = new MenuVO();
			
			menu.setMm_Name(formdata.get("mm_Name").toString().trim());
			menu.setMm_Price(Integer.parseInt(formdata.get("mm_Price").toString()));
			menu.setMm_Type(formdata.get("mm_Type").toString().trim());
			menu.setMm_Contents(formdata.get("mm_Contents").toString());
			menu.setMmc_No(Integer.parseInt(formdata.get("mmc_No").toString()));
			menu.setMr_No(Integer.parseInt(formdata.get("mr_No").toString().trim()));
			
			if(0 < menuService.insertMenu(menu)){
				
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				
				//이미지 파일 업로드 처리
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			    
				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			    
				MultipartFile mFile = null;
			    
				while(iterator.hasNext()){
					
					mFile = multipartHttpServletRequest.getFile(iterator.next());
			        
					String origName = new String(mFile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지
					
			        if(mFile.isEmpty() == false){
			        	
			            log.debug("------------- file start -------------");
			            log.debug("name : "+mFile.getName());
			            log.debug("filename : "+mFile.getOriginalFilename());
			            log.debug("size : "+mFile.getSize());
			            log.debug("-------------- file end --------------\n");
			            
						path = path+"/menu/"+menu.getMm_No();
						
						// 디레토리가 없다면 생성
						File dir = new File(root_path + path);
						if (!dir.isDirectory()) {
							System.out.println("mkdirs : "+dir.mkdirs()+"["+root_path + path+"]");
						}
						
						// 파일 명 변경
						String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
						
						String saveFileName = menu.getMm_No()+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ext;
						
						// 설정한 path에 파일저장
						File serverFile = new File(root_path + path + File.separator + saveFileName);
						mFile.transferTo(serverFile);
					
						hashmap.put("mm_No", menu.getMm_No());
						hashmap.put("mmi_Img_Url", path+File.separator);
						hashmap.put("mmi_MenuImg_Name", saveFileName);
						hashmap.put("mmi_MenuImg_OrgName", origName);
						
						// 이미지 경로 DB 저장
						if(1>menuService.updateMenuImg(hashmap)){
							if(0<menuService.insertMenuImg(hashmap)){
								mv.addObject("mm_no", menu.getMm_No());
							}
						}
			        }
			    }
				
				return new ModelAndView("redirect:"+url);
				
			}
			
		} catch (Exception e) {
			log.error("AdminController insertMenu() : " + e.getMessage());
		}
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 메뉴 정보 양식
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping(value="/viewmenu")
	public ModelAndView viewMenu(HttpServletRequest request,  HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_no", required=false, defaultValue="")String mr_no,
			@RequestParam(value="mm_no", required=false, defaultValue="")String mm_no) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String url = "/admin/viewmenu";
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		hashmap.put("mm_No", mm_no);
		
		//식당 조회
		MenuVO menu = menuService.selectMenu(hashmap);
		
		if(menu != null){
		
			url = "/admin/viewmenu";
			
			mv.addObject("menu", menu);
			
		}
		
		mv.setViewName(url);
		
		return mv;
	}
	
	/**
	 * 메뉴 수정
	 * @param request
	 * @param response
	 * @param session
	 * @param formdata
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/updatemenu")
	public ModelAndView updateMenu(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		
		String url = "/admin/viewmenu?mm_no=";
		ModelAndView mv = new ModelAndView();
		
		//파일이 저장될 path 설정
		String root_path = "/usr/local/server/apache-tomcat-7.0.70/webapps";
		String path = "/upload/images";
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			HashMap<String, Object> formdata = ComUtil.requestToHashMap(request);
			
			int mm_no = Integer.parseInt(formdata.get("mm_No").toString());
			
			if(0 < menuService.updateMenu(formdata)){
				
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				
				//이미지 파일 업로드 처리
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			    
				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			    
				MultipartFile mFile = null;
			    
				while(iterator.hasNext()){
					
					mFile = multipartHttpServletRequest.getFile(iterator.next());
			        
					String origName = new String(mFile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지
					
			        if(mFile.isEmpty() == false){
			        	
			            log.debug("------------- file start -------------");
			            log.debug("name : "+mFile.getName());
			            log.debug("filename : "+mFile.getOriginalFilename());
			            log.debug("size : "+mFile.getSize());
			            log.debug("-------------- file end --------------\n");
			            
						path = path+"/menu/"+mm_no;
						
						// 디레토리가 없다면 생성
						File dir = new File(root_path + path);
						if (!dir.isDirectory()) {
							dir.mkdirs();
						}
						
						// 파일 명 변경
						String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
						
						String saveFileName = mm_no+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ext;
						
						// 설정한 path에 파일저장
						File serverFile = new File(root_path + path + File.separator + saveFileName);
						mFile.transferTo(serverFile);
					
						hashmap.put("mm_No", mm_no);
						hashmap.put("mmi_Img_Url", path+File.separator);
						hashmap.put("mmi_MenuImg_Name", saveFileName);
						hashmap.put("mmi_MenuImg_OrgName", origName);
						
						// 이미지 경로 DB 저장
						if(1>menuService.updateMenuImg(hashmap)){
							if(0<menuService.insertMenuImg(hashmap)){
								
							}
						}
			            
			        }
			        
					url += mm_no;
					url += "&mr_no=" + formdata.get("mr_No").toString();
			        
			    }
				
				return new ModelAndView("redirect:"+url);
				
			}
			
		} catch (Exception e) {
			log.error("AdminController updateMenu() : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * 회원리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param searchoption
	 * @param searchword
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 회원 정보 수정
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 회원 탈퇴 처리
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
}
