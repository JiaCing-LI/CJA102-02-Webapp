package fftest.procpn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import fftest.procpn.model.ProCpnVO;
import fftest.procpn.service.ProCpnServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProCpnServlet extends HttpServlet {
	private ProCpnServiceImp svc = new ProCpnServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		// ⭐ CORS Header（前端 VS Code Live Server 必要）
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");

		res.setContentType("application/json; charset=UTF-8");

		String action = req.getParameter("action");
		if (action == null || !(action.equals("listAll") || action.equals("getOne"))) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "非法 action");
			return;
		}

		Gson gson = new Gson();
		PrintWriter out = res.getWriter();

		if ("getOne".equals(action)) {
			Integer id;
			try {
				id = Integer.valueOf(req.getParameter("proCpnId"));
			} catch (NumberFormatException e) {
				res.sendError(HttpServletResponse.SC_BAD_REQUEST, "proCpnId 格式錯誤");
				return;
			}

			ProCpnVO vo = svc.getOneProCpn(id);
			if (vo == null) {
				res.sendError(HttpServletResponse.SC_NOT_FOUND, "查無資料");
				return;
			}
			out.write(gson.toJson(vo));
		} else { // 預設回傳全部
			List<ProCpnVO> list = svc.getAll();
			out.write(gson.toJson(list));
		}
		out.flush();
		out.close();
	}
//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("proCpnId");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請折價卷XXXX");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/procpn/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer empno = null;
//			try {
//				empno = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("員工編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/procpn/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			ProCpnService procpnSvc = new ProCpnService();
//			ProCpnVO procpnVO = procpnSvc.getOneProCpn(empno);
//			if (procpnVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/procpn/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("procpnVO", procpnVO); // 資料庫取出的empVO物件,存入req
//			String url = "/procpn/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
}
