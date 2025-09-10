package fftest.mem.controller;

import java.io.IOException;
import java.io.InputStream;

import fftest.mem.model.MemJDBCDAO;
import fftest.mem.model.MemVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getPicture")
public class GetPictureServlet extends HttpServlet {
	private MemJDBCDAO dao = new MemJDBCDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/png"); // 預設是 JPG，若你存的是 PNG 可以改 "image/png"
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			MemVO mem = dao.findByPrimaryKey(memId);

			byte[] pic = mem.getMemPic();
			if (pic != null && pic.length > 0) {
				out.write(pic);
			} else {
				// 如果沒圖，送一張預設圖片
				InputStream in = getServletContext().getResourceAsStream("/images/noImage.png");
				byte[] buf = in.readAllBytes();
				out.write(buf);
				in.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}