
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

//j - buffereader, i - printwriter

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        BufferedReader j = new BufferedReader(new FileReader(new File(path)));
        
        Note note = new Note();
        String title = j.readLine();
        String contents = j.readLine();
        String allLine ;
         
        while((allLine = j.readLine()) != null){
            contents = contents + "<br>" + allLine;
        }

        String edit = request.getParameter("edit");
        System.out.println(title);
        System.out.println(contents);
        
        if(edit == null){
            
            note.setTitle(title);
            note.setContents(contents);
            request.setAttribute("note", note);
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            j.close();
            return;
            
        }else if(edit.isEmpty()){
            
            note.setTitle(title);
            
            if(contents != null && !contents.isEmpty()){
                note.setContents(contents.replace("<br>", "\r\n"));
            }else{
                note.setContents(contents);
            }
            
            request.setAttribute("note", note);
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            j.close();
            return;
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter i = new PrintWriter(new BufferedWriter(new 
        FileWriter(path, false)));
        
        String actions = request.getParameter("action");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        
        if(actions.equals("Save")){
            i.println(title);
            i.println(contents);
        }else if(actions.equals("Delete")){
            i.println("");
            i.println("");
        }
       
        i.close();
        doGet(request, response);
        
    }
}
