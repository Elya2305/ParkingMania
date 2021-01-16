package com.outofmemory.config.firebase;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.excetion.auth.FirebaseTokenInvalidException;
import com.outofmemory.service.FirebaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class FirebaseFilter extends OncePerRequestFilter {

	private final static String HEADER_NAME = "X-Authorization-Firebase";

	private final FirebaseService firebaseService;

	public FirebaseFilter(FirebaseService firebaseService) {
		this.firebaseService = firebaseService;
	}

	// todo fix recursion
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Start firebase filtering");
		String xAuth = request.getHeader(HEADER_NAME);
		if (StringUtils.isBlank(xAuth)) {
			filterChain.doFilter(request, response);
		} else {
			try {
				FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);

				String userName = holder.getUid();

				Authentication auth = new FirebaseAuthenticationToken(userName, holder);
				SecurityContextHolder.getContext().setAuthentication(auth);

				filterChain.doFilter(request, response);
			} catch (FirebaseTokenInvalidException e) {
				throw new SecurityException(e);
			}
		}
		log.info("End firebase filtering");
	}
}
