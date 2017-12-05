package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerFormApp extends HttpServlet{

	private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();

    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();
        String configFilePath = getServletContext().getRealPath(configFile);
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
        CommandHandler handler = commandHandlerMap.get(command);
        if (handler == null) {
            handler = new NullHandler();
        }
        String result = null;
        try {
            result = handler.process(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        if (result != null) {
        	response.setCharacterEncoding("utf-8");
        	response.setContentType("text/json");
        	response.setHeader("Cache-control","no-cache, no-store" );
        	response.setHeader("Pragma","no-cache" );
        	response.setHeader("Expries","-1" );
        	response.setHeader("Access-Control-Allow-Origin","*" );
        	response.setHeader("Access-Control-Allow-Methods","GET,POST" );
        	response.setHeader("Access-Control-Allow-Headers","Content-Type" );
        	response.setHeader("Access-Control-Max-Age", "86400");
        	PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
        }
    }
}
