var localurl="D:\\Learning_File\\Code_Program\\java\\TransDemo\\files\\";
// var localurl="files\\";
var serverurl="/usr/local/duan/files/";
var winPt = "\\";
var linuxPt = "/";
$(function (){
    setImg();
})

function setImg(){
    $("#logo-4").attr("src", localurl + "image" + winPt + "logo" + winPt + "logo-4.png");
    $("#logo-5").attr("src", localurl + "image" + winPt + "logo" + winPt + "logo-5.png");
}