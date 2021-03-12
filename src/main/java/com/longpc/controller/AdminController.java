package com.longpc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.longpc.dto.MessageDTO;
import com.longpc.dto.QuestionDTO;
import com.longpc.dto.QuizDTO;
import com.longpc.dto.SearchQuestionDTO;
import com.longpc.dto.SubjectDTO;
import com.longpc.service.IQuestionService;
import com.longpc.service.IQuizService;
import com.longpc.service.ISubjectService;
import com.longpc.util.FieldConstant;
import com.longpc.util.PagingUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private ISubjectService subjectService;
	@Autowired
	private IQuizService quizService;
	private PagingUtil pagingUtil = new PagingUtil();

	@GetMapping("")
	public String index() {
		return "admin";
	}

	@GetMapping("/create_question")
	public String indexCreateQuestion(Model model, HttpSession session) {
		String url = "error";
		try {
			QuestionDTO questionDTO = new QuestionDTO();
			List<SubjectDTO> listSubjects = subjectService.findAll();

			url = "create_question";
			HashMap<String, String> comboxBoxSubject = null;
			if (listSubjects != null) {
				comboxBoxSubject = new HashMap<String, String>();
				for (SubjectDTO subjectDTO : listSubjects) {
					comboxBoxSubject.put(subjectDTO.getId(), subjectDTO.getName());
				}
			} else {
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setStatus(false);
				messageDTO.setContent("Không có môn học");
				session.setAttribute(FieldConstant.MESSAGE, messageDTO);
			}
			model.addAttribute("question", questionDTO);
			model.addAttribute("listSubject", comboxBoxSubject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	@GetMapping("/create_quiz")
	public String indexCreateQuiz(Model model) {
		String url = "error";
		try {
			QuizDTO quizDTO = new QuizDTO();
			List<SubjectDTO> listSubjects = subjectService.findAll();
			url = "create_quiz";
			HashMap<String, String> comboxBoxSubject = new HashMap<String, String>();
			for (SubjectDTO subjectDTO : listSubjects) {
				comboxBoxSubject.put(subjectDTO.getId(), subjectDTO.getName());
			}
			model.addAttribute("quiz", quizDTO);
			model.addAttribute("listSubject", comboxBoxSubject);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return url;
	}

	@PostMapping("/create_quiz")
	public String createQuiz(@ModelAttribute("quiz") QuizDTO quiz,
			@RequestParam("cbxNumQuestion") String cbxNumQuestion, HttpSession session) {
		String url = "error";
		MessageDTO messageDTO = new MessageDTO();
		try {
			quiz.setNumQuestion(Integer.parseInt(cbxNumQuestion));
			quiz.setTimeTake(quiz.getTimeTake()*60);
			quizService.createQuiz(quiz);
			messageDTO.setContent("Tạo quiz thành công");
			messageDTO.setStatus(true);
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:create_quiz";
	}

	@PostMapping("/create_question")
	public String createQuestion(@ModelAttribute("question") QuestionDTO questionDTO, HttpSession session) {
		MessageDTO messageDTO = new MessageDTO();
		try {
			questionService.createQuestion(questionDTO);
			messageDTO.setContent("Tạo câu hỏi thành công");
			messageDTO.setStatus(true);
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		} catch (Exception e) {
			e.printStackTrace();
			messageDTO.setContent("Tạo câu hỏi thất bại");
			messageDTO.setStatus(false);
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		}
		return "redirect:create_question";
	}

	@GetMapping("/view_question")
	public String viewQuestion(@RequestParam(defaultValue = "1")Integer page,
								@ModelAttribute("searchQuestionDTO") SearchQuestionDTO searchQuestionDTO,
								HttpSession session, Model model) {
		try {
			if(searchQuestionDTO!=null) {
				model.addAttribute("searchQuestionDTO",searchQuestionDTO);
			}else {
				searchQuestionDTO= new SearchQuestionDTO();
				model.addAttribute("searchQuestionDTO",searchQuestionDTO);
			}
			int offset=0;
			if(page!=1) {
				offset=(page-1)*pagingUtil.SIZE;
			}
			List<QuestionDTO> listQuestion= questionService.search(searchQuestionDTO,offset);
			HashMap<String,String> comboxBoxSubject= null;
			List<SubjectDTO> listSubjects= subjectService.findAll();
			if(listSubjects!=null) {
				comboxBoxSubject=new HashMap<String, String>();
				for(SubjectDTO subjectDTO:listSubjects) {
					comboxBoxSubject.put(subjectDTO.getId(),subjectDTO.getName());
				}
				model.addAttribute("listSubject",comboxBoxSubject);
			}
			model.addAttribute("listQuestion",listQuestion);
			pagingUtil.setCurrentPage(page);
			Long count=questionService.countAll(searchQuestionDTO);
			pagingUtil.calculatorMinMax(count.intValue());
			session.setAttribute("minPage", pagingUtil.getMinPage());
			session.setAttribute("maxPage",pagingUtil.getMaxPage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "view_question";
	}
	
	@GetMapping("/create_subject")
	public String getViewCreateSubject(Model model) {
		model.addAttribute("subject",new SubjectDTO());
		return "create_subject";
	}
	@PostMapping("/create_subject")
	public String createSubject(@ModelAttribute("subject")SubjectDTO subjectDTO,HttpSession session) {
		MessageDTO messageDTO= new MessageDTO();
		try {
			subjectService.createSubject(subjectDTO);
			messageDTO.setStatus(true);
			messageDTO.setContent("Tạo môn học thành công");
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		}catch(Exception e) {
			e.printStackTrace();
			messageDTO.setStatus(false);
			messageDTO.setContent("Tạo môn học thất bại");
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		}
		return "redirect:create_subject";
	}
}
