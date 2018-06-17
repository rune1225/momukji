package com.momukji.mfront.controller.customer;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.momukji.common.util.ComUtil;
import com.momukji.mfront.domain.CustomerVO;
import com.momukji.mfront.service.customer.CustomerService;

@RequestMapping(value="/customer")
@Controller
public class CustomerController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="customerService")
    private CustomerService customerService;
	
	/**
	 * 회원 가입 절차
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/join/{step}")
	public ModelAndView join(HttpServletRequest request, HttpSession session,
			@PathVariable(value="step") int step ) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String joinUrl = "/customer/join/";
		
		switch (step) {
		// 약관페이지
		case 1:
			// 본인인증, 아이핀 인증을 위한 인증 데이터 생성
			
			session.setAttribute("joinTicket1", "1");			
			mv.addObject("joinUrl", joinUrl);
			mv.setViewName("/customer/joinagree");
			
			break;
		
		// 회원가입
		case 2:
			
			if (session.getAttribute("joinTicket1") == null) {
				joinUrl = "/customer/join/1";
				session.setAttribute("joinTicket1", "1");			
				mv.addObject("joinUrl", joinUrl);
				mv.setViewName("/customer/joinagree");
				break;
			}
			
			session.removeAttribute("joinTicket1");
			session.setAttribute("joinTicket2", "1");
			mv.setViewName("/customer/join");
			
		default:
			//인증 session이 존재 하지 않는 상태로 접속했을 경우의 오류
			
			break;
		}
		
		return mv;
	}
	
	/**
	 * EMAIL 중복 체크
	 * @param request
	 * @param response
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/duplicationEmailCheck")
	@ResponseBody
	public HashMap<String, Object> duplicationEmailCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mc_email", required=false, defaultValue="")String mc_email) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		try {
			
			//아이디(이메일) 조회
			int result = customerService.duplicationEmailCheck(mc_email.trim());
			
			System.out.println("중복결과:"+result);
			
			//로그인 결과값 0 : 중복아이디없음 1 : 중복아이디있음
			responseJSON.put("result", result);
			
			//세션저장
			if(result > 0)
				session.setAttribute("duplicationEmailCheck", result);
			
		} catch (Exception e) {
			log.error("MomukjiCustomerController duplicationEmailCheck() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
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
		System.out.println(hashMap.get("email_send_agree"));
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
	 * 로그인화면호출
	 * @param request
	 * @param response
	 * @return json
	 * @throws Exception
	 */
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,  HttpServletResponse response, HttpSession session,
			@RequestParam(value="join_yn", required=false, defaultValue="")String join_yn) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String joinUrl = "/customer/login";
		
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
	
	@RequestMapping(value="/logout")
	@ResponseBody
	public HashMap<String, Object> logout(HttpServletRequest request,  HttpServletResponse response, HttpSession session) throws Exception {
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		session.removeAttribute("customerVO");
		
		session.removeAttribute("viewcountList");
		
		response.setContentType("application/json;charset=UTF-8");
		
		//추후 : 로그아웃 로그 업데이트
		
		responseJSON.put("result", "success");
		
		return responseJSON;
	}
	
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
	
	/**
	 * 회원 리스트
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
}
