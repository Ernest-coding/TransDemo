$(function () {

});

function subSetName(type, id, name) {
    $.ajax({
        url: "/user/setInfo?type=" + type + "&op=2&id=" + id + "&info=" + name,
        type: "GET",
        data: {},
        dataType: "json",
        success: function (data) {
            // window.location.href = "http://" + window.location.host + "/user/allMan";
        }
    });
}

function subSetPhone(type, id, phone) {
    $.ajax({
        url: "/user/setInfo?type=" + type + "&op=1&id=" + id + "&info=" + phone,
        type: "GET",
        data: {},
        dataType: "json",
        success: function (data) {
            // window.location.href = "http://" + window.location.host + "/user/allMan";
        }
    });
}