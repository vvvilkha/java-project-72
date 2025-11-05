package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,21,21,22,22,24,24,24,25,25,25,25,25,25,25,27,27,28,28,28,29,29,32,32,33,33,33,34,34,37,37,38,38,45,45,45,45,45,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <main class=\"flex-grow-1\">\r\n        <section>\r\n            <div class=\"container-lg mt-5\">\r\n                <h1>Сайты</h1>\r\n                <table class=\"table table-bordered table-hover mt-3\">\r\n                    <thead>\r\n                    <tr>\r\n                        <th class=\"col-1\">ID</th>\r\n                        <th>Имя</th>\r\n                        <th class=\"col-2\">Последняя проверка</th>\r\n                        <th class=\"col-1\">Код ответа</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ");
				if (!page.getUrls().isEmpty()) {
					jteOutput.writeContent("\r\n                        ");
					for (var url : page.getUrls()) {
						jteOutput.writeContent("\r\n                            <tr>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("</td>\r\n                                <td><a href=\"/urls/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(url.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(url.getName());
						jteOutput.writeContent("</a></td>\r\n                                <td>\r\n                                    ");
						if (page.getLatestAllChecks().containsKey(url.getId())) {
							jteOutput.writeContent("\r\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(page.getLatestAllChecks().get(url.getId()).getCreatedAt().format(page.getFormatter()));
							jteOutput.writeContent("\r\n                                    ");
						}
						jteOutput.writeContent("\r\n                                </td>\r\n                                <td>\r\n                                    ");
						if (page.getLatestAllChecks().containsKey(url.getId())) {
							jteOutput.writeContent("\r\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(page.getLatestAllChecks().get(url.getId()).getStatusCode());
							jteOutput.writeContent("\r\n                                    ");
						}
						jteOutput.writeContent("\r\n                                </td>\r\n                            </tr>\r\n                        ");
					}
					jteOutput.writeContent("\r\n                    ");
				}
				jteOutput.writeContent("\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </section>\r\n    </main>\r\n\r\n    ");
			}
		}, null, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
