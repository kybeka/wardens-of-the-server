async function refresh_high_scores() {
    const scores = await api.getTopScores();
    document.querySelector("#scoreboard").innerHTML = ejs.views_includes_scoreboard({ scores });
    // document.querySelector("#launcher").innerHTML = ejs.views_includes_launcher();
    initLinks();
}

function initLinks() {
    document.querySelectorAll("a").forEach(link => {
        link.addEventListener("click", async e => {
            e.preventDefault();
            let url = new URL(e.currentTarget.href);

            //history.pushState(history.state, "", url.pathname + url.search);
            switch (url.pathname) {
                case "/":
                    document.querySelector("main").innerHTML = ejs.views_homepage();
                    refresh_high_scores();
                    initLinks();
                    return;
                case "/customize":
                    document.querySelector("main").innerHTML = ejs.views_customize();
                    refresh_high_scores();
                    initLinks();
                    return;
            }
        });
    });

    document.querySelectorAll("form").forEach(form => {
        form.addEventListener("submit", async e => {
            e.preventDefault();

            const url = form.getAttribute("action");
            const method = form.getAttribute("method");
            // const body = new FormData(form);
            let player = document.querySelector("#player").value;
            let map = document.querySelector("#map").value;
            let level = document.querySelector("#level").value;
            let body = JSON.stringify({ player, map, level });


            const res = await fetch(url, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method,
                body
            });

            setTimeout(() => {
                document.querySelector("main").innerHTML += `<iframe id="myiframe" src="http://localhost:3000" height="700" width="500"> </iframe>`;
            }, 4000);

            if (res.status >= 400) throw new Error(res.status);

            // switch (url) {
            //     case "/play":
            //         // TODO: play
            //         break;
            // }




        });
    });
}

function init() {
    initLinks();
    refresh_high_scores();
    document.querySelector("main").innerHTML = ejs.views_homepage();
    initLinks();
}
