package it.begear.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import it.begear.database.NotificaDAO;
import it.begear.models.Dipendente;
import it.begear.models.Notifica;

@Controller
public class NotificaController {
@Autowired
private NotificaDAO daoNot;
private Logger log = Logger.getLogger(NotificaController.class);

@RequestMapping(value="admin/inserisciNot", method=RequestMethod.POST)
public String inserisciNotifica(Notifica n) {
	daoNot.inserisciNot(n);
	return "redirect:admin/loadNot";
}

@RequestMapping(value="/admin/loadNot", method=RequestMethod.GET)
public ModelAndView load(ModelMap model) {
	model.addAttribute("listaNot", daoNot.listaNot());
	return new ModelAndView("gestioneNot", "formNot", new Notifica());
}

@RequestMapping(value = "/user/downloadNot/{id}", method = RequestMethod.GET)
public void downloadNot(@PathVariable int id, HttpServletRequest req, HttpServletResponse res) {
	Notifica n = new Notifica();
	n.setId(id);
	Notifica not = daoNot.cercaNot(n);

	String mimeType = req.getServletContext().getMimeType(not.getNome());
	if (mimeType == null) {
		mimeType = "application/octet-stream";
	}

	res.setContentType(mimeType);
	try {
		res.setContentLength((int) not.getFile().length());
	} catch (SQLException e) {
		log.error(e.getStackTrace());
	}

	// set headers for the response
	String headerKey = "Content-Disposition";
	String headerValue = String.format("attachment; filename=\"%s\"", not.getNome());
	res.setHeader(headerKey, headerValue);
	InputStream is = null;
	OutputStream os = null;
	try {
		is = (ByteArrayInputStream) not.getFile().getBinaryStream();

		os = res.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
	} catch (SQLException | IOException e) {
		log.error("Download failed", e);
	} finally {
		if (is != null)
			try {
				is.close();
			} catch (IOException e) {
				log.error(e.getStackTrace());
			}

		if (os != null)
			try {
				os.close();
			} catch (IOException e) {
				log.error(e.getStackTrace());
			}
	}
}
//@RequestMapping(value = "/admin/inserisciFileNot", method = RequestMethod.POST)
//public String inserisciFileNot(@RequestParam("file") MultipartFile file, Notifica not, HttpSession session) {
//	if (!file.isEmpty()) {
//		File myFile = new File(file.getOriginalFilename());
//		not.setNome(myFile.getName());
//		String cf = (String) session.getAttribute("cf_user");
//		Dipendente dip = new Dipendente();
//		dip.setCf(cf);
//		not.setDip(dip);
//		try {
//			Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
//			not.setFile(blob);
//		} catch (SQLException | IOException e) {
//			log.error(e.getStackTrace());
//		}
//
//		java.util.Date date = new java.util.Date();
//		Date sqlDate = new Date(date.getTime());
//		doc.setData(sqlDate);
//		dao.inserisciDoc(doc);
//	}
//
//	return "redirect:/user/loadDoc";
//}
}

