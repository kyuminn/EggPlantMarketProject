package teamB.market.domain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import teamB.market.web.interceptor.LoginCheckInterceptor;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
	// controller를 거치지 않고 바로 뷰 페이지를 보여 줄 때 사용
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		// 최상위 경로로 요청했을때 main.jsp 응답
//		registry.addViewController("/").setViewName("main");
//	}
	
	// interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/","/item/detail/*","/myapp/**", "/member/add","/member/socialAdd","/member/emailAuthCallBack",
						"/login/**", "/logout", "/css/**", "/images/**", "/icons/**",
						"/javascript/**", "/error/**","/item/search/*");
	}
}
