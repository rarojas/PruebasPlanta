package com.selmec.rest;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 * @author ricardo
 */
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * {@inheritDoc}
     * @param request
     * @param response
     * @param authentication
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//            HttpServletResponse response, Authentication authentication)
//            throws ServletException, IOException {
//        SavedRequest savedRequest;                
//        savedRequest = requestCache.getRequest(request, response);
//
//        if (savedRequest == null || isAlwaysUseDefaultTargetUrl()) {
//            super.onAuthenticationSuccess(request, response, authentication);
//            return;
//        }
//
//        clearAuthenticationAttributes(request);
//
//        // Use the DefaultSavedRequest URL
//        String targetUrl = savedRequest.getRedirectUrl();
//
//        String gwtParameters = request.getParameter(getTargetUrlParameter());
//        if (StringUtils.hasText(gwtParameters)) {
//            targetUrl = targetUrl + "#" + gwtParameters;
//        }
//
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }
}
