/**
 *
 */

$(document).ready(function () {

    $('#myTab a').on('click', function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        $('.myForm #editModal').modal();
        var href = $(this).attr('href');
        var text = $(this).text();
        var e = document.getElementById('id-field'); //скрывать поле id
        if (text == 'Edit') {
            $.get(href, function (user) {
                e.style.display = 'block';
                $('.myForm #id').val(user.id);
                $('.myForm #name').val(user.name);
                $('.myForm #surname').val(user.surname);
                $('.myForm #email').val(user.email);
                $('.myForm #password').val(user.password);

                if (user.roles.length > 1) {
                    for (let i = 0; i < user.roles.length; i++) {
                        listOfRoles.options[i].selected = true;
                    }
                }else{
                    listOfRoles.value = user.roles[0].role;
                }

            });
            $('.myForm #exampleModal').modal();
        } else {

            e.style.display = 'none';
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #surname').val('');
            $('.myForm #email').val('')
            $('.myForm #password').val('');
            $('.myForm #exampleModal').modal();
        }

    });
});