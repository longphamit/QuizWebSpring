package com.longpc.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longpc.dto.QuizDoSessionDTO;
import com.longpc.util.FieldConstant;

@RestController
@RequestMapping("/api")
public class ChooseAnswerApi {
	@GetMapping("/choose_answer")
	public String chooseAnswer(@RequestParam("answer")String answer,
								@RequestParam("questionId")String questionId, HttpSession session) {
		QuizDoSessionDTO quizDoSessionDTO=(QuizDoSessionDTO) session.getAttribute(FieldConstant.QUIZ_DO);
		quizDoSessionDTO.getHashQuestion().get(questionId).setChooseAnswer(answer);
		return "ok";
	}
}
