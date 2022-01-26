package teamB.market.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession(false);
		
		// 세션이 로그인 된 계정이 없으면 로그인 화면으로 리다이렉트
		// 로그인 후에 원래 접속하려고 했던 곳으로 redirect
		if(session == null || session.getAttribute("loginSession")==null) {
			response.sendRedirect("/login?redirectURL="+requestURI);
			return false;
		}
		return true;
	}
}
