$(function () {

});

function subSetCoyName(op, id, name) {
    $.ajax({
        url: "/coy/setInfo?op=" + op + "&id=" + id + "&info=" + name,
        type: "GET",
        data: {},
        dataType: "json",
        success: function (data) {
            // window.location.href = "http://" + window.location.host + "/user/allMan";
        }
    });
}

function subSetLicense(op, id, license) {
    $.ajax({
        url: "/coy/setInfo?op=" + op + "&id=" + id + "&info=" + license,
        type: "GET",
        data: {},
        dataType: "json",
        success: function (data) {
            // window.location.href = "http://" + window.location.host + "/user/allMan";
        }
    });
}

function subSetType(op, id, type) {
    $.ajax({
        url: "/coy/setInfo?op=" + op + "&id=" + id + "&info=" + type,
        type: "GET",
        data: {},
        dataType: "json",
        success: function (data) {
            // window.location.href = "http://" + window.location.host + "/user/allMan";
        }
    });
}