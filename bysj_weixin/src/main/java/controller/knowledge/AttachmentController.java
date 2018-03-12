package controller.knowledge;

import model.knowledge.ContentAttachment;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import service.IContentAttachmentService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liqiao on 2018/3/4.
 * 附件的controller
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController implements ServletConfigAware,ServletContextAware {
    private ServletContext servletContext;
    private ServletConfig servletConfig;
    @Resource
    private IContentAttachmentService contentAttachmentService;
    /**
     * 查看附件
     * @param id 附件id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/preview")
    public void preview(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取文件下载路径
        ContentAttachment attachment = contentAttachmentService.queryById(Integer.parseInt(id));
        String path = attachment.getLocal_path();
        String filename = attachment.getFilename();
        File file = new File(path);
        if(file.exists()){
            String fileType = filename.split("\\.")[1];

            if ("txt".equals(fileType)) {
                response.setContentType("text/plain");
            } else if ("pdf".equals(fileType)) {
                response.setContentType("application/pdf");
            } else {
                ResponseUtils.renderText(response, "文件类型错误，下载失败");
                return;
            }


            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream ouputStream = response.getOutputStream();
            byte b[] = new byte[1024];
            int n ;
            while((n = inputStream.read(b)) != -1){
                ouputStream.write(b,0,n);
            }
            //关闭流、释放资源
            ouputStream.close();
            inputStream.close();


        }else{

            ResponseUtils.renderText(response, "文件类型错误，下载失败");
            return;
        }
    }

    /**
     * 下载附件
     * @param id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SmartUpload smartUpload = new SmartUpload();
//        response.setContentType("application/pdf");
//        smartUpload.initialize(servletConfig, request, response);
////        smartUpload.setContentDisposition(null);
//        try {
//            smartUpload.downloadFile("/home/ubuntu/tomcatHttps/webapps/bysj_weixin/login.jsp");
//        } catch (SmartUploadException e) {
//            e.printStackTrace();
//        }
        //使用流下载文件，因为传统的会被屏蔽
        //获取文件下载路径
        String path = "/home/ubuntu/tomcatHttps/webapps/a.txt";
        String fileName = request.getParameter("fileName");
        String fileRealName = request.getParameter("fileRealName");
//        if(fileName==null || fileName=="" ||fileRealName==null ||fileRealName==""){
//            return null;
//        }
        fileRealName+="a.txt";
        HttpHeaders headers = new HttpHeaders();
//        String realPath = request.getSession().getServletContext().getRealPath("/");
        String realPath = path;
//        File file= new File(realPath+File.separator+"upload"+File.separator+"pdf"+File.separator+fileName);
        File file = new File(path);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(fileRealName.getBytes("utf-8"), "ISO8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file,  HttpServletRequest request, HttpServletResponse response)  {
        //项目部署的路径
        String filePath = request.getSession().getServletContext().getRealPath("/");
        filePath += "WEB-INF/fileTmp";
        File filepath = new File(filePath);
        if(!filepath.exists()){
            filepath.mkdir();
        }
        JSONObject jsonObject = new JSONObject();

        if(!file.isEmpty()) {
            //上传文件名
            String filename = file.getOriginalFilename();

            //将上传文件保存到一个目标文件当中
            try {
                file.transferTo(new File(filePath + File.separator + filename));
                jsonObject.put("result", "success");
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put("result", "error");
            }

        } else {
            jsonObject.put("result", "error");
        }
        ResponseUtils.renderJson(response, jsonObject.toString());
    }




    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
