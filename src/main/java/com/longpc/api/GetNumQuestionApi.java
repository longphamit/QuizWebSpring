package com.longpc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longpc.dto.QuestionDTO;
import com.longpc.dto.SearchQuestionDTO;
import com.longpc.service.IQuestionService;

@RestController
@RequestMapping("/api")
public class GetNumQuestionApi {
	@Autowired
	IQuestionService questionService;
	
	@GetMapping("/num_question")
	public String getNumQuestion(@RequestParam("subjectId")String subjectId) {
		try {
			SearchQuestionDTO searchQuestionDTO= new SearchQuestionDTO();
			searchQuestionDTO.setSubjectId(subjectId);
			long count= questionService.countAll(searchQuestionDTO);
			return ""+count;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0+"";
	}
}
