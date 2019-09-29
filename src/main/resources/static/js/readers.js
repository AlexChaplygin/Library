$(document).ready(function () {
    load();
});

function edit(Element) {
    window.location = 'reader.html?id=' + Element.id;
}

function load() {
    $.ajax({
        url: '/readers',
        type: 'get',
        success: function (data) {
            var table_body = '<table border="1">';
            table_body += '<thead>';
            table_body += '<tr>';
            table_body += '<td>';
            table_body += 'Name';
            table_body += '</td>';
            table_body += '<td>';
            table_body += '';
            table_body += '</td>';
            table_body += '<td>';
            table_body += '';
            table_body += '</td>';
            table_body += '</tr>';
            table_body += '</thead>';

            for (var i = 0; i < data.length; i++) {
                table_body += '<tr>';
                table_body += '<td>';
                table_body += data[i].name;
                table_body += '</td>';
                table_body += '<td>';
                table_body += '<a href="#" id=' + data[i].id + ' onclick="return edit(this);">edit</a>';
                table_body += '</td>';
                table_body += '<td>';
                table_body += '<a href="#" id=' + data[i].id + ' onclick="return deleteReader(this);">delete</a>';
                table_body += '</td>';
                table_body += '</tr>';
            }

            table_body += '</table>';
            $('#tableDiv').html(table_body);
        },
        error: function (xhr, str) {
            alert('Возникла ошибка: ' + xhr.responseCode);
        }
    });
}

function add() {
    window.location = 'reader.html';
}

function deleteReader(Element) {
    if (confirm('Are you sure ?')) {
        $.ajax({
            url: '/reader/delete/' + Element.id,
            type: 'DELETE',
            success: function (data) {
                load();
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });
    }
}