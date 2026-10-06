function changeTextButton() {
var changeBtn = 
document.getElementById("changeButton");
if(changeBtn.value == "Click me!") {
changeBtn.value = "You click me";
changeBtn.classList.add("green");
changeBtn.classList.remove("red");
} else {
changeBtn.value = "Click me!";
changeBtn.classList.add("red");
changeBtn.classList.remove("green");
}
}