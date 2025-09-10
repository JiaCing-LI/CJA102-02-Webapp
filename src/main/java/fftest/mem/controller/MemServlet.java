package fftest.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import fftest.mem.model.MemJDBCDAO;
import fftest.mem.model.MemVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/uploadServlet.do")
@MultipartConfig
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
	// 將由底下的第26~30行用 java.io.File 於 ContextPath 之下, 自動建立目地目錄
	private MemJDBCDAO dao = new MemJDBCDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		String memName = req.getParameter("memName");

		// 抓第一個上傳檔案
		Part part = req.getPart("memPic");
		InputStream in = part.getInputStream();
		byte[] picBytes = in.readAllBytes();
		in.close();

		// 封裝成 VO
		MemVO mem = new MemVO();
		mem.setMemName(memName);
		mem.setMemPic(picBytes);

		// 存進 DB
		dao.insert(mem);
		String url = "/frontend/Upload.html";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);
	}
}
