package project.apicapstone.zuul.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PostHttpServletRequest extends HttpServletRequestWrapper {

    public PostHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
    @Override
    public String getMethod() {
        return "POST";
    }
}
