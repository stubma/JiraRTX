package luma.jira.plugin.rtxnotifier;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.google.common.collect.Maps;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AdminServlet.class);
	private static final String PLUGIN_STORAGE_KEY_BASE = "luma.jira.plugin.rtxnotifier";
	static final String PLUGIN_STORAGE_KEY_AGENT_ADDRESS = PLUGIN_STORAGE_KEY_BASE + ".agent.address";
	static final String PLUGIN_STORAGE_KEY_AGENT_PORT = PLUGIN_STORAGE_KEY_BASE + ".agent.port";
	
	private UserManager m_userManager;
	private LoginUriProvider m_loginUriProvider;
	private TemplateRenderer m_renderer;
	private PluginSettingsFactory m_pluginSettingsFactory;
	
	// flag indicate value is saved
	private boolean m_saved = false;
	
	public AdminServlet(UserManager userManager, LoginUriProvider loginUriProvider, TemplateRenderer renderer, PluginSettingsFactory pluginSettingsFactory) {
		m_userManager = userManager;
		m_loginUriProvider = loginUriProvider;
		m_renderer = renderer;
		m_pluginSettingsFactory = pluginSettingsFactory;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserProfile userProfile = m_userManager.getRemoteUser();
		if(userProfile == null || !m_userManager.isSystemAdmin(userProfile.getUserKey())) {
			log.info("not logged in, rediector to login");
			redirectToLogin(request, response);
		} else {
			// create context
			Map<String, Object> context = Maps.newHashMap();
			PluginSettings pluginSettings = m_pluginSettingsFactory.createGlobalSettings();
			String addr = (String)pluginSettings.get(PLUGIN_STORAGE_KEY_AGENT_ADDRESS);
			if(addr == null)
				addr = "";
			String port = (String)pluginSettings.get(PLUGIN_STORAGE_KEY_AGENT_PORT);
			if(port == null)
				port = "";
			context.put("addr", addr);
			context.put("port", port);
			context.put("saved", new Boolean(m_saved));
			
			// render page
			response.setContentType("text/html;charset=utf-8");
			m_renderer.render("admin.vm", context, response.getWriter());
			
			// reset 
			m_saved = false;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PluginSettings pluginSettings = m_pluginSettingsFactory.createGlobalSettings();
		pluginSettings.put(PLUGIN_STORAGE_KEY_AGENT_ADDRESS, req.getParameter("addr"));
		pluginSettings.put(PLUGIN_STORAGE_KEY_AGENT_PORT, req.getParameter("port"));
		m_saved = true;
		resp.sendRedirect("admin");
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