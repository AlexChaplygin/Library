$(document).ready(function () {
    load();
});

function edit(Element) {
    window.location = 'book.html?id=' + Element.id;
}

function load() {
    $.ajax({
        url: '/books',
        type: 'get',
        success: function (data) {
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
            table_body += 'Reader';
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
                table_body += data[i].title;
                table_body += '</td>';
                table_body += '<td>';
                table_body += data[i].author;
                table_body += '</td>';
                table_body += '<td>';
                table_body += data[i].releaseDate;
                table_body += '</td>';
                table_body += '<td>';
                if(data[i].reader != null) {
                    table_body += data[i].reader.name;
                } else {
                    table_body += "-";
                }
                table_body += '</td>';
                table_body += '<td>';
                table_body += '<a href="#" id=' + data[i].id + ' onclick="return edit(this);">edit</a>';
                table_body += '</td>';
                table_body += '<td>';
                table_body += '<a href="#" id=' + data[i].id + ' onclick="return deleteBook(this);">delete</a>';
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
    window.location = 'book.html';
}

function deleteBook(Element) {
    if (confirm('Are you sure ?')) {
        $.ajax({
            url: '/book/delete/' + Element.id,
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

function relocate_home()
{
    location.href = "index.html";
}