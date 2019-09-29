var qsParm = new Array();
var readerGlobalId;
var readersGlobal;

$(document).ready(function () {
    var query = window.location.search.substring(1);
    if (query.length != 0) {
        var parms = query.split('&');
        for (var i = 0; i < parms.length; i++) {
            var pos = parms[i].indexOf('=');
            if (pos > 0) {
                var key = parms[i].substring(0, pos);
                var val = parms[i].substring(pos + 1);
                qsParm[key] = val;
            }
        }

        $.ajax({
            url: '/book/' + qsParm.id,
            type: 'get',
            success: function (data) {
                $("#title").val(data.title);
                $("#author").val(data.author);
                $("#date").text(data.releaseDate);
                if (data.reader != null) {
                    $("#reader").text(data.reader.name);
                    readerGlobalId = data.reader.id;
                } else
                    $("#reader").text("-");
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });

        $.ajax({
            url: '/readers',
            type: 'get',
            success: function (data) {
                readersGlobal = data;
                var select = '<select id="readersSelect" size=1>';
                select += '<option value="0"> </option>';

                for (var i = 0; i < data.length; i++) {

                    if (data[i].id === readerGlobalId) {
                        select += '<option selected value="' + data[i].id + '">' + data[i].name + '</option>';
                    } else {
                        select += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }

                select += '</select>';
                $('#selectDiv').html(select);
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });
    }
});

function myFunction() {
    if ($('#title').val() !== "" && $('#author').val() !== "") {

        var selectedReaderId = $("#readersSelect").val();
        var selectedReader = null;

        if (readersGlobal != null)
            for (var i = 0; i < readersGlobal.length; i++) {
                if (readersGlobal[i].id == selectedReaderId) {
                    selectedReader = readersGlobal[i];
                    break;
                }
            }

        var formData = {
            id: qsParm.id,
            title: $("#title").val(),
            author: $("#author").val(),
            date: $("#date").val(),
            reader: selectedReader
        };

        $.ajax({
            url: '/book/save',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (data) {
                window.location = 'index.html';
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });
    }
}