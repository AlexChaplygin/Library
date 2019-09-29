var qsParm = new Array();
var dataGlobal;

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
            url: '/reader/' + qsParm.id,
            type: 'get',
            success: function (data) {
                dataGlobal = data;
                $("#name").val(data.name);
                load();
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });
    }
});

function load() {

    var table_body = '<table border="1">';
    table_body += '<thead>';
    table_body += '<tr>';
    table_body += '<td>';
    table_body += 'Title';
    table_body += '</td>';
    table_body += '<td>';
    table_body += 'Author';
    table_body += '</td>';
    table_body += '<td>';
    table_body += 'Date';
    table_body += '</td>';
    table_body += '<td>';
    table_body += '';
    table_body += '</td>';
    table_body += '</tr>';
    table_body += '</thead>';

    for (var i = 0; i < dataGlobal.books.length; i++) {
        table_body += '<tr>';
        table_body += '<td>';
        table_body += dataGlobal.books[i].title;
        table_body += '</td>';
        table_body += '<td>';
        table_body += dataGlobal.books[i].author;
        table_body += '</td>';
        table_body += '<td>';
        table_body += dataGlobal.books[i].releaseDate;
        table_body += '</td>';
        table_body += '<td>';
        table_body += '<a href="#" id=' + dataGlobal.books[i].id + ' onclick="return openBook(this);">Open book</a>';
        table_body += '</td>';
        table_body += '</tr>';
    }

    table_body += '</table>';
    $('#tableDiv').html(table_body);

}

function openBook(Element) {
    window.location = 'book.html?id=' + Element.id;
}

function myFunction() {
    if ($('#name').val() !== "") {

        var formData = {
            id: qsParm.id,
            title: $("#name").val(),
        };

        $.ajax({
            url: '/reader/save',
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