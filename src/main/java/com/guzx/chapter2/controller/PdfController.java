package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.PdfExportService;
import com.guzx.chapter2.service.impl.PdfView;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private MyBatisUserService userService;

    @RequestMapping("/export")
    public ModelAndView exportPdf(String userName, String note) {
        List<User_MyBatis> user_myBatisList = userService.getUsers(userName, note);
        View view = new PdfView(exportService());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(view);
        modelAndView.addObject(user_myBatisList);
        return modelAndView;
    }

    public PdfExportService exportService() {
        return (model, document, writer, request, response) -> {
            try {
                document.setPageSize(PageSize.A4);
                document.addTitle("用户信息");
                document.add(new Chunk("\n"));
                PdfPTable table = new PdfPTable(3);
                PdfPCell cell = null;
                Font f8 = new Font();
                f8.setColor(Color.red);
                f8.setStyle(Font.BOLD);
                cell = new PdfPCell(new Paragraph("id", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
//                Object o = model.get("user_MyBatisList");
                List<User_MyBatis> list = (List<User_MyBatis>) model.get("user_MyBatisList");
                for (User_MyBatis userMyBatis : list) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(userMyBatis.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(userMyBatis.getUserName() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(userMyBatis.getNote() + ""));
                    table.addCell(cell);
                }
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        };
    }

}
