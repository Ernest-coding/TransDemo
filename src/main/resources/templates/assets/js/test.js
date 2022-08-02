$(function () {
    setImg();
})

/**
 * /Path/ 映射到实际的路径就是 files/ ，所以直接在 /Path/ 后面接上 files 的子目录就行
 */
function setImg() {
    $("#logo-4").attr("src", "/Path/image/logo/logo-4.png");
    $("#logo-5").attr("src", "/Path/image/logo/logo-5.png");
}