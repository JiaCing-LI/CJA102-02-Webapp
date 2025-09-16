package fftest.memprocpn.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fftest.memprocpn.model.MemProCpnService;
import fftest.memprocpn.model.MemProCpnVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemProCpnServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("=== 進入 MemProCpnServlet.doPost ===");
			System.out.println("action = " + action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("cpnHolderDetailId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入折價卷編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/memprocpn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer cpnHolderDetailId = null;
			try {
				cpnHolderDetailId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("折價卷編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/memprocpn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			MemProCpnService memProCpnSvc = new MemProCpnService();
			MemProCpnVO memProCpnVO = memProCpnSvc.getOneMemProCpn(cpnHolderDetailId);
			if (memProCpnVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/memprocpn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memProCpnVO", memProCpnVO); // 資料庫取出的empVO物件,存入req
			String url = "/backend/memprocpn/listOneMemProCpn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		// 修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllMemProCpn.jsp的請求
			System.out.println("=== 進入 MemProCpnServlet.doPost ===");
			System.out.println("action = " + action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer cpnHolderDetailId = Integer.valueOf(req.getParameter("cpnHolderDetailId"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemProCpnService MemProCpnSvc = new MemProCpnService();
			MemProCpnVO memProCpnVO = MemProCpnSvc.getOneMemProCpn(cpnHolderDetailId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memProCpnVO", memProCpnVO); // 資料庫取出的memProCpnVO物件,存入req
			String url = "/backend/memprocpn/update_memprocpn_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		// 修改狀態
		if ("updateStatus".equals(action)) { // 來自update_MemProCpn_input.jsp的請求
			System.out.println("=== 進入 MemProCpnServlet.doPost ===");
			System.out.println("action = " + action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 1. 接收請求參數
			MemProCpnVO memProCpnVO = new MemProCpnVO();
			Integer cpnHolderDetailId = Integer.valueOf(req.getParameter("cpnHolderDetailId"));
			Byte cpnUseStatus = (req.getParameter("cpnUseStatus") != null) ? (byte) 1 : (byte) 0;
			memProCpnVO.setCpnHolderDetailId(cpnHolderDetailId);

			memProCpnVO.setCpnUseStatus(cpnUseStatus);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memProCpnVO", memProCpnVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/memprocpn/update_memprocpn_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemProCpnService memProCpnSvc = new MemProCpnService();

			memProCpnVO = memProCpnSvc.updateStatusMemProCpn(cpnHolderDetailId, cpnUseStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memProCpnVO", memProCpnVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/backend/memprocpn/listOneMemProCpn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		// 新增
		if ("insert".equals(action)) { // 來自 addMemProCpn.jsp 的請求
			System.out.println("=== 進入 MemProCpnServlet.doPost ===");
			System.out.println("action = " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			MemProCpnVO memProCpnVO = new MemProCpnVO();
			Integer memId = null;
			Integer proCpnId = null;
			Integer validDays = null;

			try {
				// 取參數
				String memIdStr = req.getParameter("memId");
				String proCpnIdStr = req.getParameter("proCpnId");
				String validDaysStr = req.getParameter("validDays");

				if (memIdStr == null || memIdStr.isBlank()) {
					errorMsgs.add("會員ID: 請勿空白");
				}
				if (proCpnIdStr == null || proCpnIdStr.isBlank()) {
					errorMsgs.add("折價券ID: 請勿空白");
				}
				if (validDaysStr == null || validDaysStr.isBlank()) {
					errorMsgs.add("有效天數: 請勿空白");
				}

				// 如果有錯誤，帶著 VO 回到表單
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memProCpnVO", memProCpnVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memprocpn/addMemProCpn.jsp");
					failureView.forward(req, res);
					return;
				}

				// 驗證成功 → 轉型
				memId = Integer.valueOf(memIdStr.trim());
				proCpnId = Integer.valueOf(proCpnIdStr.trim());
				validDays = Integer.valueOf(validDaysStr.trim());

			} catch (Exception e) {
				errorMsgs.add("參數格式錯誤: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/memprocpn/addMemProCpn.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MemProCpnService memProCpnSvc = new MemProCpnService();
			memProCpnVO = memProCpnSvc.addMemProCpn(memId, proCpnId, validDays);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/memprocpn/listAllMemProCpn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer cpnHolderDetailId = Integer.valueOf(req.getParameter("cpnHolderDetailId"));

			/*************************** 2.開始刪除資料 ***************************************/
			MemProCpnService memProCpnSvc = new MemProCpnService();

			memProCpnSvc.deleteMemProCpn(cpnHolderDetailId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/memprocpn/listAllMemProCpn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}