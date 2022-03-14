package project.apicapstone.zuul.filter;

//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;

//@Component
public class PreFilter{//} extends ZuulFilter {
//    private static Logger log = LoggerFactory.getLogger(PreFilter.class);
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletRequest requestWrapper = new PostHttpServletRequest(request);
//        ctx.setRequest(requestWrapper);
//        log.info("PreFilter: " + String.format("%s request to %s", requestWrapper.getMethod(), requestWrapper.getRequestURL().toString()));
//        return null;
//    }
}
