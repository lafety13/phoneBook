
$(document).ready(function () {
    var table = $('#contacttable').DataTable({
        'select': 'single',
        'info': false,
        'columnDefs': [{
            "targets": [2],
            "visible": false,
            "searchable": false
        }],
        "createdRow" : function( row, data, index ) {
            if (data.hasOwnProperty("id")) {
                row.id = "row_" + data.id;
            }
        }
    });

    //retriving contact from db and filling form
    $('#contacttable tbody').on('click', 'tr', function () {
        editContact(this);
    });

    var editContact = function (row) {
        table.$('tr.selected').removeClass('selected');
        $(row).addClass('selected');
        resetEditForm();
        $("#deletecontactbutton").css({"display":"block"});
        $('#formName').html('Update');
        var data = table.row(row).data();
        $.get("/contact/" + data[2], function (response) {
            $('#lastName').val(response.lastName);
            $('#firstName').val(response.firstName);
            $('#middleName').val(response.middleName);
            $('#mobilePhone').val(response.mobilePhone);
            $('#homePhone').val(response.homePhone);
            $('#address').val(response.address);
            $('#email').val(response.email);
            $('#id').val(response.id);
        });
    };

    //new contact form
    $(document).on('click', '#deletecontactbutton', function (event) {
        window.location = '/contact/delete/' + $('#id').val();
    });

    //new contact form
    $(document).on('click', '#newcontactbutton', function (event) {
        $('#lastName').val("");
        $('#firstName').val("");
        $('#middleName').val("");
        $('#mobilePhone').val("");
        $('#homePhone').val("");
        $('#address').val("");
        $('#email').val("");
        $('#id').val(0);
        $('#formName').html('Add');
        $('#deletecontactbutton').css({'display': 'none'});
    });

    var resetEditForm = function () {
        $('#success').hide();
        $(".remove").remove();
        $(".has-error").removeClass("has-error");
        $('#lastName').attr("placeholder", '');
        $('#firstName').attr("placeholder", '');
        $('#middleName').attr("placeholder", '');
        $('#mobilePhone').attr("placeholder", '');
        $('#homePhone').attr("placeholder", '');
        $('#address').attr("placeholder", '');
        $('#email').attr("placeholder", '');
    };


});