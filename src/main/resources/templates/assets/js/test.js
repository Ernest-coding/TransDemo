$(function () {
    setImg();
})

/**
 * /Path/ 映射到实际的路径就是 files/ ，所以直接在 /Path/ 后面接上 files 的子目录就行
 */
function setImg() {
    $("#about4").attr("src", "/Path/image/about/about4.jpg");
    $("#about5").attr("src", "/Path/image/about/about5.jpg");
    $("#about-01").attr("src", "/Path/image/about/about-01.jpg");
    $("#about-02").attr("src", "/Path/image/about/about-02.jpg");
    $("#about-03").attr("src", "/Path/image/about/about-03.jpg");
    $("#about-img-1").attr("src", "/Path/image/about/about-img-1.jpg");
    $("#office-img1").attr("src", "/Path/image/about/office-img1.jpg");
    $("#office-img2").attr("src", "/Path/image/about/office-img2.jpg");




    $("#logo").attr("src", "/Path/image/logo/logo.png");
    $("#logo-4").attr("src", "/Path/image/logo/logo-4.png");
    $("#logo-5").attr("src", "/Path/image/logo/logo-5.png");

}