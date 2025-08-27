package com.ddu.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) { // 검증할 객체(StudentDto)의 클래스 타입 정보
		// TODO Auto-generated method stub
		return StudentDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		StudentDto studentDto  = (StudentDto)target;
		
		String id = studentDto.getId();
		String pw = studentDto.getPw();
		int age = studentDto.getAge();
		if (id.strip().isEmpty() || id == null) { // id가 null값이거나 비어있을때
			errors.rejectValue("id", "아이디를 입력해주세요"); // (에러가 발생한 필드 이름, 에러 메세지)
			System.out.println("에러가 발생된 아이디 : " + id);
		}
		
		if(pw.strip().isEmpty() || pw == null) { // pw가 null값이거나 비어있을때
			errors.rejectValue("pw", "비밀번호를 입력해주세요"); // (에러가 발생한 필드 이름, 에러 메세지)
			System.out.println("에러가 발생된 비밀번호 : " + pw);
		}
		
		if (age >= 20 || age <= 0) { // 나이가 20살 이상일 경우, 나이가 0살 이하(음수)일 경우
			errors.rejectValue("age", "20세 미만만 가입 가능합니다"); // (에러가 발생한 필드 이름, 에러 메세지)
			System.out.println("에러가 발생된 나이 : " + age);
		}
	}

}
