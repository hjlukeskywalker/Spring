package com.kh.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView board(
			@RequestParam(value="cPage", defaultValue="1")int cPage, 
			@RequestParam(value="numPerpage", defaultValue="5")int numPerpage, ModelAndView mv) {
		mv.addObject("list", service.selectBoardList(cPage,numPerpage));
		int totalData=service.selectBoardCount();
		mv.addObject("pagebar",PageFactory.getPageBar(totalData,cPage,numPerpage,"boardList.do"));
		mv.setViewName("board/boardList");
		return mv;
		
	}
	@RequestMapping("/board/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	@RequestMapping("/board/insertBoard.do")
	public ModelAndView insertBoard(Board board, ModelAndView mv,
			@RequestParam(value="upFile", required=false) MultipartFile[] upFile,
			HttpServletRequest req) {
		log.info("파일명 : "+upFile[0].getOriginalFilename());
		log.info("파일크기{} : ",upFile[0].getSize());
		log.info("파일명 : "+upFile[1].getOriginalFilename());
		log.info("파일크기{} : ",upFile[1].getSize());		
		
		//저장경로설정하기 -> 실제경로를 가져와야함
		String path=req.getServletContext().getRealPath("/resources/upload/board/");
		File dir=new File(path);
		
		//폴더가 없다면 생성
		if(!dir.exists()) dir.mkdirs();
		
		//업로드처리하기 다중
		for(MultipartFile f:upFile) {
			if(!f.isEmpty()) {
				String originalFilename=f.getOriginalFilename();
				String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
				
				//.jpg .pdf
				
				//리네임 규칙설정
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rndNum=(int)(Math.random()*10000);
				String reName=sdf.format(System.currentTimeMillis())+"_"+rndNum+ext;
				
				//리네임으로 파일 업로드하기
				try {
					f.transferTo(new File(path+reName));
					board.getAttachments().add(Attachment.builder().originalFilename(originalFilename).renamedFilename(reName).build());
				}catch(IOException e) {
					e.printStackTrace();
				}
			
			}
		}//for문끝
		
		log.info("{}",board);
		
		String msg="성공";
		try {
		service.insertBoard(board);
		}catch(RuntimeException e) {
			msg="등록실패";
		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc","/board/boardList.do");
		
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/board/boardView.do")
		public ModelAndView boardView(int no, ModelAndView mv) { 
		
		mv.addObject("board",service.selectBoardView(no));
		mv.setViewName("board/boardView");
		log.info("여기야?");
		return mv;
	}
	@RequestMapping("/board/fileDownload.do")
		public void fileDownload(@RequestParam(value="oriname") String oriname,
				@RequestParam(value="rename") String rename,
				HttpServletResponse resp,
				HttpServletRequest req,
				@RequestHeader(value="user-agent")String header) {
		String path=req.getServletContext().getRealPath("/resources/upload/board/");
		File saveFile= new File(path+rename);
		
		BufferedInputStream bis=null;
		ServletOutputStream sos=null;
		try {
			bis=new BufferedInputStream(new FileInputStream (saveFile));
			
			sos=resp.getOutputStream();
			
			
			boolean isMS=header.indexOf("Trident")!=-1||header.indexOf("MSIE")!=-1;
			String encodeStr="";
			
			
			if(isMS) {
				encodeStr=URLEncoder.encode(oriname,("UTF-8"));
				encodeStr=encodeStr.replaceAll("\\", "%20");
			}else {
				encodeStr=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			resp.setContentType("application/octet-stream;charset=utf-8");
			resp.setHeader("Content-Disposition", "attachment;filename=\""+encodeStr+"\"");
			
			
			int read=-1;
			while((read=bis.read())!=-1){
				sos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
				sos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}