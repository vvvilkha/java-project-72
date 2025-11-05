@import gg.jte.Content
@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BasePage
@param Content content
@param Content footer = null
@param BasePage page = null
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Анализатор страниц</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body class="d-flex flex-column min-vh-100">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${NamedRoutes.mainPage()}">Анализатор страниц</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <div class="navbar-nav">
                <a class="nav-link" href="${NamedRoutes.mainPage()}">Главная</a>
                <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
            </div>
        </div>
    </div>
</nav>


@if(page != null && page.getFlash() != null && page.getFlashType() != null)
    @if(page.getFlashType().equals("info"))
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-info" role="alert">
            <p class="m-0">${page.getFlash()}</p>
        </div>
    @elseif(page.getFlashType().equals("danger"))
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-danger" role="alert">
            <p class="m-0">${page.getFlash()}</p>
        </div>
    @elseif(page.getFlashType().equals("success"))
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
            <p class="m-0">${page.getFlash()}</p>
        </div>
    @endif
@endif

<main class="flex-grow-1">
    <section>
        ${content}
    </section>
</main>


@if (footer != null)
    <footer class="footer border-top py-3 mt-5 bg-light">
        <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
            ${footer}
        </div>
    </footer>
@endif
</body>
</html>