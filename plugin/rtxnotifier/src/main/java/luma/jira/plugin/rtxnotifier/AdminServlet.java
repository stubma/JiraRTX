package luma.jira.plugin.rtxnotifier;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.atlassian.templaterenderer.TemplateRenderer;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AdminServlet.class);

	private UserManager m_userManager;
	private LoginUriProvider m_loginUriProvider;
	private TemplateRenderer m_renderer;

	public AdminServlet(UserManager userManager, LoginUriProvider loginUriProvider, TemplateRenderer renderer) {
		m_userManager = userManager;
		m_loginUriProvider = loginUriProvider;
		m_renderer = renderer;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserProfile userProfile = m_userManager.getRemoteUser();
		if(userProfile == null || !m_userManager.isSystemAdmin(userProfile.getUserKey())) {
			log.info("not logged in, rediector to login");
			redirectToLogin(request, response);
		} else {
			response.setContentType("text/html;charset=utf-8");
			m_renderer.render("admin.vm", response.getWriter());
		}
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(m_loginUriProvider.getLoginUri(getUri(request)).toASCIIString());
	}

	private URI getUri(HttpServletRequest request) {
		StringBuffer builder = request.getRequestURL();
		if (request.getQueryString() != null) {
			builder.append("?");
			builder.append(request.getQueryString());
		}
		return URI.create(builder.toString());
	}
}