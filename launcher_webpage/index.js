var customize = (`<form>
<div class="form-item">
    <label for="terrain">Choose your terrain:</label>
    <select name="terrain" id="terrain">
        <option value="choose">Choose</option>
        <option value="new_york">New York</option>
        <option value="santorini">Santorini</option>
        <option value="tokyo">Tokyo</option>
        <option value="neom">Neom</option>
    </select>
</div>
<div class="form-item">
    <label for="level">Choose the difficulty:</label>
    <select name="level" id="level">
        <option value="choose">Choose</option>
        <option value="easy">Easy</option>
        <option value="medium">Medium</option>
        <option value="hard">Hard</option>
    </select>
</div>
</form>`);

var home = (`
<article>
    <h2>Welcome to Build & Cross!</h2>
    Lorem ipsum dolor sit amet,
    consectetur adipiscing elit.
    Maecenas ultrices justo id nisi bibendum,
    vitae mattis nunc laoreet.
    Ut eleifend metus arcu, eget vehicula turpis accumsan in.
    Etiam ut posuere elit. Morbi mollis sed nulla pulvinar consequat.
    Aliquam consectetur aliquam odio eget malesuada.
    Pellentesque ex sem, sodales quis fringilla eu, blandit et dui.
    Donec eleifend risus euismod, mollis justo sollicitudin, consequat urna.
    Phasellus egestas auctor nunc, a maximus mi lacinia nec.
    In eget dapibus velit, eu interdum ipsum. Duis maximus erat sed tortor iaculis semper.
    Nunc vitae nibh hendrerit, sollicitudin libero sed, porta orci.
    Mauris placerat vulputate mi. Donec vitae.
</article>`);

var submitButton = (`<br><br>
<input id="start-button" type="submit" value="Launch Minecraft!">`);

function setMain(page) {
    let newMain;
    if (page == 'customize') {
        document.getElementById("home").className = "";
        document.querySelector("#customize").className = "current-page";
        newMain = customize;
    } else {
        newMain = home;
        document.getElementById("home").className = "current-page";
        document.getElementById("customize").className = "";
    }
    newMain += submitButton;
    document.querySelector("main").innerHTML = newMain;
}