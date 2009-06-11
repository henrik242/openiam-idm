package org.openiam.webadmin.busdel.base;

import diamelle.catalog.product.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;



public class ShowImage extends HttpServlet {

    private String docHome = ".";


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      HttpSession session = request.getSession(true);
      ServletConfig config = getServletConfig();
      ServletContext application = config.getServletContext();

      String type = getImage(request,response);
      response.setContentType("image/"+type);


    }

    protected String getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String type = "";
       int imgCount = 0;

       try {
          ImageAccess imageAccess = new ImageAccess();
          String imageId = request.getParameter("imageId");

          if (imageId != null) {
               ImageValue iv = (ImageValue) imageAccess.getImage(imageId);
               System.out.println("image is "+imageId);
               System.out.println("iv is "+iv);
               System.out.println("bytes"+iv.getImageBytes());
               byte [] img =  iv.getImageBytes();
               type = iv.getMimeType();

               ServletOutputStream servletoutputstream = response.getOutputStream();
               InputStream in = new ByteArrayInputStream(img);

                while ((imgCount = in.read()) != -1)
                    servletoutputstream.write(imgCount);

                servletoutputstream.flush();
           }

       } catch (Exception e) {
           request.setAttribute("errors","No such image . Choose another image");
           e.printStackTrace();
       }
       return type;
    }


}
