package com.sc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.bean.Users;
import com.sc.service.UserService;

@Controller
@RequestMapping("/userctlr")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/showUsers.do")
	public ModelAndView showUsers(ModelAndView mv){
		List<Users> usersList = userService.selectUsers();
		mv.addObject("usersList", usersList);
		mv.setViewName("user/showUsers");
		return mv;
	}
	
	@RequestMapping("/showUsersJson.do")
	@ResponseBody
	public List<Users> showUsersJson(){
		return userService.selectUsers();
	}
	
	@RequestMapping("/showUsersPage.do")
	public ModelAndView showUsersPage(ModelAndView mv,
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="5")Integer pageSize){
		mv.addObject("pi", userService.selectUsersPage(pageNum, pageSize, null));
		mv.setViewName("user/showUsersPage");
		return mv;
	}
	
	@RequestMapping("/goadd.do")
	public ModelAndView goadd(ModelAndView mv){
		mv.setViewName("user/addUser");
		return mv;
	}
	
	@RequestMapping("/addUser.do")
	public ModelAndView addUser(ModelAndView mv,MultipartFile files,Users u,HttpSession hs) throws IllegalStateException, IOException{
		if(files!=null&&!StringUtils.isEmpty(files.getOriginalFilename())){
			String path= hs.getServletContext().getRealPath("upload");
			String oldname=files.getOriginalFilename();//ԭ�ļ�����
			String extend=oldname.substring(oldname.lastIndexOf("."));//��չ��
			String newname=System.currentTimeMillis()+extend;//���ļ�����
			File f=new File(path+"/"+newname);//����Ŀ�ĵ��ļ�����
			files.transferTo(f);//�����ļ�
			u.setPic(newname);
		}
		userService.addUsers(u);
		mv.setViewName("redirect:./showUsers.do");
		return mv;
	}
	
	@RequestMapping("/goupdate.do")
	public ModelAndView goupdate(ModelAndView mv,Integer uid){
		Users userById = userService.getUserById(uid);
		System.out.println(userById);
		mv.addObject("user", userById);
		mv.setViewName("user/updateUser");
		return mv;
	}
	
	@RequestMapping("/updateUser.do")
	public ModelAndView updateUser(ModelAndView mv,MultipartFile files,Users u,HttpSession hs) throws IllegalStateException, IOException{
		System.out.println("**************83***************");
		if(files!=null&&!StringUtils.isEmpty(files.getOriginalFilename())){
			String path= hs.getServletContext().getRealPath("upload");
			String oldname=files.getOriginalFilename();//ԭ�ļ�����
			String extend=oldname.substring(oldname.lastIndexOf("."));//��չ��
			String newname=System.currentTimeMillis()+extend;//���ļ�����
			File f=new File(path+"/"+newname);//����Ŀ�ĵ��ļ�����
			files.transferTo(f);//�����ļ�
			u.setPic(newname);
		}
		System.out.println("*************93****************");
		userService.updateUsers(u);
		mv.setViewName("redirect:./showUsers.do");
		return mv;
	}
	
	@RequestMapping("/deleUser.do")
	public ModelAndView deleUser(ModelAndView mv,Users u){
		userService.delUsers(u);
		mv.setViewName("redirect:./showUsers.do");
		return mv;
	}
	
	
	@RequestMapping("download.do")
	public ResponseEntity<byte[]> download(String name,HttpServletRequest req) throws IOException{
		HttpHeaders headers = new HttpHeaders();
		String path = req.getSession().getServletContext().getRealPath("upload");
		File fileSource = new File(path +"/"+ name);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", name );
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(fileSource),headers,HttpStatus.OK);
	}
}
