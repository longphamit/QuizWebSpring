package com.longpc.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.longpc.dto.MessageDTO;
import com.longpc.dto.QuestionDTO;
import com.longpc.dto.QuizDTO;
import com.longpc.dto.QuizDoSessionDTO;
import com.longpc.dto.SubjectDTO;
import com.longpc.entity.UserEntity;
import com.longpc.service.IQuestionService;
import com.longpc.service.IQuizService;
import com.longpc.service.ISubjectService;
import com.longpc.util.FieldConstant;

@Controller
public class HomeController {
	@Autowired
	ISubjectService subjectService;
	@Autowired
	IQuizService quizService;
	@Autowired
	IQuestionService questionService;
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String getHomeIndex(Model model,HttpSession session) {
		MessageDTO messageDTO= new MessageDTO();
		try {
			List<SubjectDTO>subjects= subjectService.findAll();
			model.addAttribute("listSubjects", subjects);
			UserEntity userEntity=(UserEntity) session.getAttribute(FieldConstant.USER);
			messageDTO.setContent("Welcome "+userEntity.getEmail());
			messageDTO.setStatus(true);
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	@RequestMapping(value = "/view_subject",method = RequestMethod.GET)
	public String getViewSubjectIndex(@RequestParam("id")String id ,Model model,HttpSession session) {
		MessageDTO messageDTO= new MessageDTO();
		try {
			List<QuizDTO> listQuizDTOs= quizService.getQuizOfSubject(id);
			model.addAttribute("listQuiz", listQuizDTOs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "view_quiz";
	}
	@RequestMapping(value = "/do_quiz",method = RequestMethod.GET)
	public String getViewDoQuizIndex(@RequestParam("id")String id,@RequestParam(required = false)String questionId ,Model model,HttpSession session) {
		MessageDTO messageDTO= new MessageDTO();
		try {
			
			if(session.getAttribute(FieldConstant.QUIZ_DO)==null) {
				QuizDoSessionDTO quizDoSessionDTO= new QuizDoSessionDTO();
				QuizDTO quizDTO=quizService.findById(id);
				session.setAttribute("quiz", quizDTO);
				List<QuestionDTO> listQuestion= questionService.search(id);
				HashMap<String,QuestionDTO> questions=new HashMap<String, QuestionDTO>();
				for(QuestionDTO questionDTO:listQuestion) {
					questions.put(questionDTO.getId(), questionDTO);
				}
				quizDoSessionDTO.setNumQuestion(listQuestion.size());
				quizDoSessionDTO.setListQuestion(listQuestion);
				quizDoSessionDTO.setHashQuestion(questions);
				quizDoSessionDTO.setQuizDTO(quizDTO);
				session.setAttribute(FieldConstant.QUIZ_DO, quizDoSessionDTO);
				QuestionDTO questionDTO= quizDoSessionDTO.getListQuestion().get(0);
				model.addAttribute("questionDo",questionDTO);
			}else {
				QuizDoSessionDTO quizDoSessionDTO= (QuizDoSessionDTO) session.getAttribute(FieldConstant.QUIZ_DO);
				QuestionDTO questionDTO= quizDoSessionDTO.getListQuestion().get(0);
				model.addAttribute("questionDo",questionDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "do_quiz";
	}
	@RequestMapping(value = "/do_quiz",method = RequestMethod.POST)
	public String doQuiz(@RequestParam("id")String id,
						@RequestParam("questionId")String questionId,
						@RequestParam("inputTimer")String inputTimer ,
						Model model,HttpSession session) {
		MessageDTO messageDTO= new MessageDTO();
		try {
			QuizDoSessionDTO quizDoSessionDTO= (QuizDoSessionDTO) session.getAttribute(FieldConstant.QUIZ_DO);
			quizDoSessionDTO.setCurrentTimeDo(inputTimer);
			if(questionId!=null) {
				QuestionDTO questionDTO= quizDoSessionDTO.getHashQuestion().get(questionId);
				model.addAttribute("questionDo",questionDTO);
			}else {
				QuestionDTO questionDTO= quizDoSessionDTO.getListQuestion().get(0);
				model.addAttribute("questionDo",questionDTO);
			}
			session.setAttribute(FieldConstant.QUIZ_DO, quizDoSessionDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "do_quiz";
	}
}
