-- Удаляем таблицы, если они существуют
DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

-- Создаем таблицу urls
CREATE TABLE urls (
                      id SERIAL PRIMARY KEY, -- Используем SERIAL вместо AUTO_INCREMENT
                      name VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Значение по умолчанию для created_at
);

-- Создаем таблицу url_checks
CREATE TABLE url_checks (
                            id SERIAL PRIMARY KEY, -- Используем SERIAL вместо AUTO_INCREMENT
                            url_id INT NOT NULL, -- Ссылка на таблицу urls
                            status_code INT,
                            h1 VARCHAR(100),
                            title VARCHAR(100),
                            description VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Значение по умолчанию для created_at
                            FOREIGN KEY (url_id) REFERENCES urls(id) ON DELETE CASCADE -- Внешний ключ с каскадным удалением
);