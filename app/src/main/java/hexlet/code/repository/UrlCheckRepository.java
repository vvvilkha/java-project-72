package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        if (urlCheck == null) {
            throw new IllegalArgumentException("UrlCheck cannot be null");
        }

        var sql = "INSERT INTO url_checks(url_id, status_code, h1, title, description, created_at)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, urlCheck.getUrlId());
            stmt.setInt(2, urlCheck.getStatusCode());
            stmt.setString(3, urlCheck.getH1() == null ? "" : urlCheck.getH1());
            stmt.setString(4, urlCheck.getTitle() == null ? "" : urlCheck.getTitle());
            stmt.setString(5, urlCheck.getDescription() == null ? "" : urlCheck.getDescription());
            var createdAt = LocalDateTime.now();
            stmt.setTimestamp(6, Timestamp.valueOf(createdAt));
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> getUrlChecks(Long urlId) {
        if (urlId == null) {
            throw new IllegalArgumentException("URL ID cannot be null");
        }

        List<UrlCheck> result = new ArrayList<>();
        var sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY id DESC";
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, urlId); // Подставляем значение url_id
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var code = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var urlCheck = new UrlCheck(code, title, h1, description, urlId, createdAt);
                urlCheck.setId(id);
                result.add(urlCheck);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching URL checks for URL ID: " + urlId, e);
        }

        return result;
    }

    public static Optional<UrlCheck> getLastUrlCheck(Long urlId) {
        UrlCheck urlCheck = new UrlCheck();
        String sql = "SELECT id, url_id, status_code, h1, title, description, created_at\n"
                + "FROM url_checks\n"
                + "WHERE url_id = ?\n"
                + "ORDER BY created_at DESC\n"
                + "LIMIT 1";
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var code = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                return Optional.of(new UrlCheck(code, title, h1, description, urlId, createdAt));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error sql exception", e);
        }
        return Optional.empty();
    }

    public static Map<Long, UrlCheck> getAllUrlCheck() {
        Map<Long, UrlCheck> result = new HashMap<>();

        String sql = "SELECT DISTINCT ON (url_id) * FROM url_checks ORDER BY url_id DESC, id DESC";

        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var urlId = resultSet.getLong("url_id");
                var code = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var urlCheck = new UrlCheck(code, title, h1, description, urlId, createdAt);
                result.put(urlId, urlCheck);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all URL checks", e);
        }

        return result;
    }
}
