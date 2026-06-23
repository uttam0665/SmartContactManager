console.log("script loaded");

// change page theme

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", function () {
  // Your function call or code here
  changeTheme(currentTheme);
});

// TODO
function changeTheme() {
  // set to web page
  console.log(currentTheme);

  changePageTheme(currentTheme, currentTheme);

  // set the listener to change the theme
  const changeThemeButton = document.querySelector("#theme_change_button");
  changeThemeButton.addEventListener("click", () => {
    const oldTheme = currentTheme;
    if (currentTheme === "light") {
      currentTheme = "dark";
    } else {
      currentTheme = "light";
    }
    changePageTheme(oldTheme, currentTheme);
  });
}

// sent theme to localstorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

//get the value from local storage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

function changePageTheme(oldTheme, currentTheme) {
  setTheme(currentTheme);
  document.querySelector("html").classList.remove(oldTheme);
  document.querySelector("html").classList.add(currentTheme);
  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent =
    currentTheme == "light" ? "dark" : "light";
}

// end page theme
