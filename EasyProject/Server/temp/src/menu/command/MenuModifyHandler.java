package menu.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Menu;
import menu.service.MenuData;
import menu.service.MenuModifyService;

import mvc.command.CommandHandler;

public class MenuModifyHandler implements CommandHandler{
	private MenuModifyService modifyService = new MenuModifyService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String menu_name = request.getParameter("menu_name");

		MenuData menuData = modifyService.getMenu(menu_name);
		request.setAttribute("menuData", menuData);

		return "/view/menu/menuModifyForm.jsp";
		
		}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");//한글 인코딩
		
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/upload"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		int maxSize = 5*1024*1024;  //최대 업로될 파일크기 1Mb
		
		

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);  
       
		 MultipartRequest imageUp = null;
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  
			 //파일 정보가 있다면
		     while(files.hasMoreElements()){
		       //input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
		       String name = (String)files.nextElement();
		   
		       //서버에 저장된 파일 이름
		       filename = imageUp.getFilesystemName(name);
		     }
		  }catch(Exception e){
		     e.printStackTrace();
		  }
		
		
		Menu menu = new Menu();
		String menu_name = imageUp.getParameter("menu_name");
		int HSprice = Integer.parseInt(imageUp.getParameter("HSprice"));
		int HLprice = Integer.parseInt(imageUp.getParameter("HLprice"));
		int ISprice = Integer.parseInt(imageUp.getParameter("ISprice"));
		int ILprice = Integer.parseInt(imageUp.getParameter("ILprice"));
		String menu_info = imageUp.getParameter("menu_info");
		String menu_category = imageUp.getParameter("menu_category");

		menu.setMenu_name(menu_name);
		menu.setPrice_HS(HSprice);
		menu.setPrice_HL(HLprice);
		menu.setPrice_IS(ISprice);
		menu.setPrice_IL(ILprice);
		menu.setMenu_info(menu_info);
		menu.setMenu_image(filename);
		menu.setMenu_category(menu_category);
		
		
	
		request.setAttribute("menu_name", menu_name);
		modifyService.updateMenu(menu, menu_name);
		return "/view/menu/modifySuccess.jsp";
		
		
	}
}


