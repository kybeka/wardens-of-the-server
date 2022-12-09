async function refresh_high_scores() {
    const scores = await api.getTopScores();
    document.querySelector("#scoreboard").innerHTML = ejs.views_includes_scoreboard({ scores });
    document.querySelector("#launcher").innerHTML = ejs.views_includes_launcher();
}

function initLinks() {
    document.querySelectorAll("a").forEach(link => {
        link.addEventListener("click", async e => {
            e.preventDefault();

            let url = new URL(e.currentTarget.href);

            history.pushState(history.state, "", url.pathname + url.search);
            switch (url.pathname) {
                case "/":
                    document.querySelector("main").innerHTML = ejs.views_homepage();
                    return;
                case "/customize":
                    document.querySelector("main").innerHTML = ejs.views_customize();
                    return;
            }
        });
    });

    document.querySelectorAll("form").forEach(form => {
        form.addEventListener("submit", async e => {
            e.preventDefault();

            const url = form.getAttribute("action");
            const method = form.getAttribute("method");
            const body = new FormData(form);

            const res = await fetch(url, { method, body });
            if (res.status >= 400) throw new Error(res.status);

            switch (url) {
                case "/play":
                    // TODO: play
                    break;
            }
        });
    });
}

function init() {
    refresh_high_scores();
    document.querySelector("main").innerHTML = ejs.views_homepage();
    initLinks();
}