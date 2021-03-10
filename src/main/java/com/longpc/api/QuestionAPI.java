package com.longpc.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longpc.dto.QuizDoSessionDTO;
import com.longpc.dto.SearchQuestionDTO;
import com.longpc.service.IQuestionService;
import com.longpc.util.FieldConstant;

@RestController
@RequestMapping("/api")
public class QuestionAPI {
	@Autowired
	IQuestionService questionService;
	@GetMapping("/choose_answer")
	public String chooseAnswer(@RequestParam("answer")String answer,
								@RequestParam("questionId")String questionId, HttpSession session) {
		QuizDoSessionDTO quizDoSessionDTO=(QuizDoSessionDTO) session.getAttribute(FieldConstant.QUIZ_DO);
		quizDoSessionDTO.getHashQuestion().get(questionId).setChooseAnswer(answer);
		return "ok";
	}
	
	@GetMapping("/num_question")
	public String getNumQuestion(@RequestParam("subjectId")String subjectId) {
		try {
			SearchQuestionDTO searchQuestionDTO= new SearchQuestionDTO();
			searchQuestionDTO.setSubjectId(subjectId);
			searchQuestionDTO.setStatus("ACTIVE");
			long count= questionService.countAll(searchQuestionDTO);
			return ""+count;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0+"";
	}
	@PostMapping("/update_status")
	public String updateStatus(
			@RequestParam("questionId")String questionId,
			@RequestParam("status")String status) {
		try {
			questionService.setStatus(status, questionId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
