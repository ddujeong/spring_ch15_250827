package com.ddu.validate;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	@RequestMapping(value = "/joinOk") // 유저가 입력한 값이 유효한 값인지 체크 -> validation(유효성 체크)
	public String joinOk(StudentDto studentDto , Model model, BindingResult result) {
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(studentDto,  result ); // result가 발생한 error를 받은 결과
		
		if( result.hasErrors()) { // result 내에 error가 1개라도 있우묜 true
			System.out.println(result.getFieldErrorCount()); 
			// 에러 발생 갯수
			//FieldError fieldError = result.getFieldError("id");
			// 해당(id) 필드의 에러 내용을 가져오기
			//System.out.println(fieldError.getCode());
			// 해당 필드의 에러 코드를 가져오기
			
			List<FieldError> fieldErrors = result.getFieldErrors();
			String errorMsg = null;
			// 모든 에러를 list로 반환
			for (FieldError fieldError2 : fieldErrors) {
				System.out.println("에러가 발생한 항목 : " + fieldError2.getField());
				System.out.println("에러가 발생한 코드명 : " + fieldError2.getCode());
				
				errorMsg=fieldError2.getCode();
			}
			model.addAttribute("error", errorMsg);
		return "joinOk" ; // 가입 성공 -> 회원거입 패아지로 이동
		}
		
		model.addAttribute("sDto",studentDto);
		return "joinOk";
	}
	@RequestMapping(value = "/join2")
	public String join2() {
		return "join2";
	}
	@RequestMapping(value = "/joinOk2")
	public String joinOk2(@Valid StudentDto studentDto,BindingResult result, Model model) {
		
		if( result.hasErrors()) { // result 내에 error가 1개라도 있우묜 true
			System.out.println(result.getFieldErrorCount()); 
			// 에러 발생 갯수
			//FieldError fieldError = result.getFieldError("id");
			// 해당(id) 필드의 에러 내용을 가져오기
			//System.out.println(fieldError.getCode());
			// 해당 필드의 에러 코드를 가져오기
			
			List<FieldError> fieldErrors = result.getFieldErrors();
			String errorMsg = null;
			// 모든 에러를 list로 반환
			for (FieldError fieldError2 : fieldErrors) {
				System.out.println("에러가 발생한 항목 : " + fieldError2.getField());
				System.out.println("에러가 발생한 코드명 : " + fieldError2.getCode());
				
				errorMsg=fieldError2.getCode();
			}
			model.addAttribute("error", errorMsg);
		return "joinOk" ; // 가입 성공 -> 회원거입 패아지로 이동
		}
		model.addAttribute("sDto",studentDto);
		return "joinOk";
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
}
