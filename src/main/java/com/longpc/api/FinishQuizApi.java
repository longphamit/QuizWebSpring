package com.longpc.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longpc.dto.QuestionDTO;
import com.longpc.dto.QuizDoSessionDTO;
import com.longpc.dto.ResultQuizDTO;
import com.longpc.entity.UserEntity;
import com.longpc.service.IQuizDoService;
import com.longpc.util.FieldConstant;

@RestController
@RequestMapping("/api")
public class FinishQuizApi {
	@Autowired
	IQuizDoService quizDoService;
	@PostMapping("/finish_quiz")
	public String finishQuiz(HttpSession session) {
		try {
			int point = 0;
			QuizDoSessionDTO quizDoSessionDTO = (QuizDoSessionDTO) session.getAttribute(FieldConstant.QUIZ_DO);
			HashMap<String, QuestionDTO> hashQuestion = quizDoSessionDTO.getHashQuestion();
			for (Map.Entry<String, QuestionDTO> entry : hashQuestion.entrySet()) {
				if (entry.getValue().getChooseAnswer() != null) {
					if (entry.getValue().getChooseAnswer().equals(entry.getValue().getRightAnswer())) {
						point += 1;
					}
				}
			}
			UserEntity userEntity = (UserEntity) session.getAttribute(FieldConstant.USER);
			quizDoSessionDTO.setScore((point * 10) / quizDoSessionDTO.getNumQuestion());
			quizDoSessionDTO.setDoBy(userEntity.getEmail());
			session.removeAttribute(FieldConstant.QUIZ_DO);
			quizDoService.saveQuizDo(quizDoSessionDTO);
			ResultQuizDTO resultQuizDTO= new ResultQuizDTO();
			resultQuizDTO.setTotal(quizDoSessionDTO.getNumQuestion());
			resultQuizDTO.setNumRight(point);
			resultQuizDTO.setScore(quizDoSessionDTO.getScore());
			session.setAttribute("resultQuiz", resultQuizDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
