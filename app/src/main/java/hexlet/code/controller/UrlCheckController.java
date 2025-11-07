package hexlet.code.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;


@Slf4j
public class UrlCheckController {
    public static void create(Context ctx) throws SQLException {
        var idStr = ctx.pathParam("id");
        try {
            var id = Long.parseLong(idStr);

            Url url = UrlRepository.find(id).orElseThrow(() -> new NotFoundResponse("Страница не найдена"));
            log.info("Получен ID: {}", id);

            if (!isValidUrl(url.getName())) {
                ctx.sessionAttribute("flash", "Некорректный URL");
                ctx.sessionAttribute("flash-type", "danger");
                ctx.redirect(NamedRoutes.urlPath(id));
                return;
            }

            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            log.info("ok");
            Document document = Jsoup.parse(response.getBody());

            var code = response.getStatus();

            Element titleTag = document.selectFirst("title");
            String title = (titleTag != null && !titleTag.text().isBlank())
                    ? titleTag.text()
                    : document.title();

            Element h1Tag = document.selectFirst("h1");
            String h1 = (h1Tag != null && !h1Tag.text().isBlank())
                    ? h1Tag.text()
                    : title;

            String description;
            Element metaDescription = document.selectFirst("meta[name=description]");
            if (metaDescription != null && !metaDescription.attr("content").isBlank()) {
                description = metaDescription.attr("content");
            } else {
                Element p = document.selectFirst("p");
                description = (p != null && !p.text().isBlank()) ? p.text() : title;
            }

            var urlCheck = new UrlCheck(code, title, h1, description, id);
            UrlCheckRepository.save(urlCheck);
            log.info("check saved");

            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlPath(url.getId()));


        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect(NamedRoutes.urlPath(Long.parseLong(idStr)));
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Произошла ошибка при проверке страницы: " + e.getMessage());
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect(NamedRoutes.urlPath(Long.parseLong(idStr)));
        }
    }

    public static boolean isValidUrl(String urlString) {
        try {
            new java.net.URL(urlString).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
