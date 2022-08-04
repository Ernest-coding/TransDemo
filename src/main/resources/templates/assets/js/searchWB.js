$(function () {
    setURL();
    setLoginURL();
});

function setURL() {

    var url = "http://" + window.location.host + "/oth/searchWaybill";

    $("#searchForm").attr("action", url);
}

function setLoginURL() {
    var url = "http://" + window.location.host + "/manage";

    $("#loginBtn").attr("href", url);
}
