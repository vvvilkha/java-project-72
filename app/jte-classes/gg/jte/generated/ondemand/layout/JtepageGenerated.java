package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,21,21,21,21,21,21,21,21,21,21,28,28,28,28,28,28,28,28,28,29,29,29,29,29,29,29,29,29,36,36,37,37,39,39,39,41,41,43,43,43,45,45,47,47,47,49,49,50,50,54,54,54,59,59,62,62,62,65,65,67,67,67,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content footer, BasePage page) {
		jteOutput.writeContent("<!doctype html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"utf-8\" />\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n    <title>Анализатор страниц</title>\r\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\r\n          rel=\"stylesheet\"\r\n          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\r\n          crossorigin=\"anonymous\">\r\n</head>\r\n<body class=\"d-flex flex-column min-vh-100\">\r\n\r\n<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n    <div class=\"container-fluid\">\r\n        <a class=\"navbar-brand\"");
		var __jte_html_attribute_0 = NamedRoutes.mainPage();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Анализатор страниц</a>\r\n        <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\r\n                aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n            <span class=\"navbar-toggler-icon\"></span>\r\n        </button>\r\n        <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n            <div class=\"navbar-nav\">\r\n                <a class=\"nav-link\"");
		var __jte_html_attribute_1 = NamedRoutes.mainPage();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Главная</a>\r\n                <a class=\"nav-link\"");
		var __jte_html_attribute_2 = NamedRoutes.urlsPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Сайты</a>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</nav>\r\n\r\n\r\n");
		if (page != null && page.getFlash() != null && page.getFlashType() != null) {
			jteOutput.writeContent("\r\n    ");
			if (page.getFlashType().equals("info")) {
				jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-info\" role=\"alert\">\r\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlash());
				jteOutput.writeContent("</p>\r\n        </div>\r\n    ");
			} else if (page.getFlashType().equals("danger")) {
				jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-danger\" role=\"alert\">\r\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlash());
				jteOutput.writeContent("</p>\r\n        </div>\r\n    ");
			} else if (page.getFlashType().equals("success")) {
				jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-success\" role=\"alert\">\r\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlash());
				jteOutput.writeContent("</p>\r\n        </div>\r\n    ");
			}
			jteOutput.writeContent("\r\n");
		}
		jteOutput.writeContent("\r\n\r\n<main class=\"flex-grow-1\">\r\n    <section>\r\n        ");
		jteOutput.setContext("section", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n    </section>\r\n</main>\r\n\r\n\r\n");
		if (footer != null) {
			jteOutput.writeContent("\r\n    <footer class=\"footer border-top py-3 mt-5 bg-light\">\r\n        <div class=\"text-center p-4\" style=\"background-color: rgba(0, 0, 0, 0.05);\">\r\n            ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\r\n        </div>\r\n    </footer>\r\n");
		}
		jteOutput.writeContent("\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		Content footer = (Content)params.getOrDefault("footer", null);
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, footer, page);
	}
}
